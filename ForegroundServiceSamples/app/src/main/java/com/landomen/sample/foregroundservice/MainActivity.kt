package com.landomen.sample.foregroundservice

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.landomen.sample.foregroundservice.service.ExampleForegroundService
import com.landomen.sample.foregroundservice.ui.ForegroundServiceScreen
import com.landomen.sample.foregroundservice.ui.HomeScreen
import com.landomen.sample.foregroundservice.ui.WorkManagerScreen
import com.landomen.sample.foregroundservice.workmanager.ExampleForegroundWorker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class MainActivity : ComponentActivity() {
    private var exampleService: ExampleForegroundService? = null

    private var serviceBoundState by mutableStateOf(false)
    private var displayableLocation by mutableStateOf<String?>(null)
    private var workerRunning by mutableStateOf(false)
    private var currentWorkerId: UUID? = null

    // needed to communicate with the service.
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // we've bound to ExampleLocationForegroundService, cast the IBinder and get ExampleLocationForegroundService instance.
            Log.d(TAG, "onServiceConnected")

            val binder = service as ExampleForegroundService.LocalBinder
            exampleService = binder.getService()
            serviceBoundState = true

            onServiceConnected()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            // This is called when the connection with the service has been disconnected. Clean up.
            Log.d(TAG, "onServiceDisconnected")

            serviceBoundState = false
            exampleService = null
        }
    }

    // we need notification permission to be able to display a notification for the foreground service
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            // if permission was denied, the service can still run only the notification won't be visible
        }

    private val locationPermissionRequest2 = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            // Precise location access granted, worker can run
            startWorkManagerForegroundWorker()
        } else {
            Toast.makeText(this, "Location permission is required!", Toast.LENGTH_SHORT).show()
        }
    }

    // we need location permission to be able to start the service
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted, service can run
                launchPendingServiceType()
            }

            permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted, service can still run.
                launchPendingServiceType()
            }

            else -> {
                // No location access granted, service can't be started as it will crash
                Toast.makeText(this, "Location permission is required!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        onForegroundServiceClick = {
                            navController.navigate("foreground_service")
                        },
                        onWorkManagerClick = {
                            navController.navigate("work_manager")
                        }
                    )
                }

                composable("foreground_service") {
                    ForegroundServiceScreen(
                        serviceRunning = serviceBoundState,
                        currentLocation = displayableLocation,
                        onStartForegroundServiceClick = ::onStartOrStopForegroundServiceClick
                    )
                }

                composable("work_manager") {
                    WorkManagerScreen(
                        workerRunning = workerRunning,
                        currentLocation = displayableLocation,
                        onStartWorkManagerClick = ::onStartWorkManagerWorkerClick,
                        onStopWorkManagerClick = ::onStopWorkManagerWorkerClick
                    )
                }
            }
        }

        checkAndRequestNotificationPermission()

        tryToBindToServiceIfRunning()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    /**
     * Check for notification permission before starting the service so that the notification is visible
     */
    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            )) {
                android.content.pm.PackageManager.PERMISSION_GRANTED -> {
                    // permission already granted
                }

                else -> {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    private fun onStartOrStopForegroundServiceClick() {
        if (exampleService == null) {
            // service is not yet running, start it after permission check
            locationPermissionRequest.launch(
                arrayOf(
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION
                )
            )
        } else {
            // service is already running, stop it
            exampleService?.stopForegroundService()
        }
    }

    private fun launchPendingServiceType() {
        startForegroundService()
    }

    private fun onStartWorkManagerWorkerClick() {
        locationPermissionRequest2.launch(ACCESS_FINE_LOCATION)
    }

    private fun onStopWorkManagerWorkerClick() {
        currentWorkerId?.let { id ->
            WorkManager.getInstance(this).cancelWorkById(id)
            workerRunning = false
            currentWorkerId = null
            displayableLocation = null
        }
    }

    private fun startWorkManagerForegroundWorker() {
        val id = UUID.randomUUID()
        currentWorkerId = id

        val workRequest = OneTimeWorkRequestBuilder<ExampleForegroundWorker>()
            .setExpedited(OutOfQuotaPolicy.DROP_WORK_REQUEST)
            .setId(id)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(this)
            .enqueue(workRequest)

        workerRunning = true

        lifecycleScope.launch {
            WorkManager.getInstance(applicationContext)
                // requestId is the WorkRequest id
                .getWorkInfoByIdLiveData(id)
                .asFlow()
                .collectLatest { workInfo ->
                    workInfo?.let { info ->
                        // Update the running state based on work state
                        if (info.state.isFinished) {
                            workerRunning = false
                            currentWorkerId = null
                        }

                        // Update location from progress data
                        info.progress.let { data ->
                            val lat = data.getDouble(
                                ExampleForegroundWorker.DATA_KEY_LOCATION_LAT,
                                Double.MAX_VALUE
                            )
                            val lng = data.getDouble(
                                ExampleForegroundWorker.DATA_KEY_LOCATION_LNG,
                                Double.MAX_VALUE
                            )
                            if (lat != Double.MAX_VALUE && lng != Double.MAX_VALUE) {
                                displayableLocation = "$lat, $lng"
                            }
                        }
                    }
                }
        }
    }

    /**
     * Creates and starts the ExampleLocationForegroundService as a foreground service.
     *
     * It also tries to bind to the service to update the UI with location updates.
     */
    private fun startForegroundService() {
        // start the service
        val serviceIntent = Intent(this, ExampleForegroundService::class.java)
        startForegroundService(serviceIntent)

        // bind to the service to update UI
        tryToBindToServiceIfRunning()
    }

    private fun tryToBindToServiceIfRunning() {
        Intent(this, ExampleForegroundService::class.java).also { intent ->
            bindService(intent, connection, 0)
        }
    }

    private fun onServiceConnected() {
        lifecycleScope.launch {
            // observe location updates from the service
            exampleService?.locationFlow?.map {
                it?.let { location ->
                    "${location.latitude}, ${location.longitude}"
                }
            }?.collectLatest {
                displayableLocation = it
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
