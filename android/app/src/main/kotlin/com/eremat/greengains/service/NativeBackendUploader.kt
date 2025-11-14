package com.eremat.greengains.service

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.UUID
import java.util.concurrent.TimeUnit

/**
 * Native Backend Uploader - Runs independently of Flutter.
 *
 * Responsibilities:
 * - Collect batched sensor samples
 * - Upload every couple of minutes via OkHttp
 * - Retry on failure while keeping the buffer bounded
 * - Report status events back to the service/Flutter
 */
class NativeBackendUploader(
    private val context: Context,
    private val uploadIntervalMs: Long = 120_000L,
    private val statusListener: NativeUploadStatusListener? = null
) {
    private val gson = Gson()
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var uploadJob: Job? = null

    // Buffer for sensor readings (max 1000 entries ~ 10 minutes of data at 600 ms sampling)
    private val sensorBuffer = mutableListOf<SensorReading>()
    private val maxBufferSize = 1000

    // Backend configuration
    private val baseUrl = "https://greengains.onrender.com"
    private val apiKey = "Vb9kS3tP0xQ7fY2L"

    // HTTP client with reasonable timeouts for shaky mobile networks
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    // Stats for diagnostics
    private var totalUploadsAttempted = 0
    private var totalUploadsSucceeded = 0
    private var totalUploadsFailed = 0
    private var lastUploadTime: Long = 0

    fun start() {
        if (uploadJob?.isActive == true) {
            Log.d(TAG, "Uploader already running, ignoring start()")
            return
        }

        Log.i(TAG, "********************************************")
        Log.i(TAG, "* Native backend uploader starting")
        Log.i(TAG, "* Interval: ${uploadIntervalMs / 1000}s")
        Log.i(TAG, "* Endpoint: $baseUrl/upload")
        Log.i(TAG, "* Buffer cap: $maxBufferSize readings")
        Log.i(TAG, "********************************************")

        uploadJob = coroutineScope.launch {
            while (isActive) {
                try {
                    kotlinx.coroutines.delay(uploadIntervalMs)
                    uploadBatch()
                } catch (t: Throwable) {
                    Log.e(TAG, "Upload timer loop crashed: ${t.message}", t)
                }
            }
        }
    }

    fun stop() {
        Log.i(TAG, "Native backend uploader stopping. success=$totalUploadsSucceeded failure=$totalUploadsFailed")
        uploadJob?.cancel()
        coroutineScope.coroutineContext.cancelChildren()
    }

    fun addReading(reading: SensorReading) {
        synchronized(sensorBuffer) {
            sensorBuffer.add(reading)

            // Circular buffer: drop oldest entries beyond maxBufferSize
            while (sensorBuffer.size > maxBufferSize) {
                val removed = sensorBuffer.removeAt(0)
                Log.w(TAG, "Buffer overflow. Dropping reading timestamp=${removed.timestamp}")
            }

            if (sensorBuffer.size % 50 == 0) {
                Log.d(TAG, "Buffered ${sensorBuffer.size} readings")
            }
        }
    }

    fun getBufferSize(): Int = synchronized(sensorBuffer) { sensorBuffer.size }

    private suspend fun uploadBatch() = withContext(Dispatchers.IO) {
        val readings: List<SensorReading>

        synchronized(sensorBuffer) {
            if (sensorBuffer.isEmpty()) {
                Log.d(TAG, "No sensor readings collected yet, skipping upload cycle")
                return@withContext
            }

            readings = sensorBuffer.toList()
            sensorBuffer.clear()
        }

        statusListener?.onStatus(
            NativeUploadStatusEvent(
                type = NativeUploadEventType.STARTED,
                batchSize = readings.size,
                bufferSize = getBufferSize()
            )
        )

        val now = System.currentTimeMillis()
        val timeSinceLast = if (lastUploadTime == 0L) {
            "n/a"
        } else {
            "${(now - lastUploadTime) / 1000}s"
        }

        Log.i(TAG, "--------------------------------------------")
        Log.i(TAG, "Uploading ${readings.size} readings to backend")
        Log.i(TAG, "Time since last upload: $timeSinceLast")

        try {
            val deviceId = getOrCreateDeviceId()
            val shareLocation = context
                .getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
                .getBoolean("flutter.share_location", false)

            val payload = buildPayload(deviceId, readings, shareLocation)
            val jsonPayload = gson.toJson(payload)

            val request = Request.Builder()
                .url("$baseUrl/upload")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-Key", apiKey)
                .post(jsonPayload.toRequestBody("application/json".toMediaType()))
                .build()

            totalUploadsAttempted++

            val response = httpClient.newCall(request).execute()
            response.use {
                if (it.isSuccessful) {
                    handleSuccess(readings.size, it.code)
                } else {
                    val errorBody = it.body?.string()
                    handleFailure(
                        readings,
                        "HTTP ${it.code}: ${errorBody ?: "empty body"}"
                    )
                }
            }
        } catch (ioe: IOException) {
            handleFailure(readings, "Network error: ${ioe.message}")
        } catch (t: Throwable) {
            handleFailure(readings, "Unexpected error: ${t.message}")
        }
    }

    private fun handleSuccess(batchSize: Int, statusCode: Int) {
        totalUploadsSucceeded++
        lastUploadTime = System.currentTimeMillis()
        Log.i(TAG, "Upload succeeded. batch=$batchSize status=$statusCode total=$totalUploadsSucceeded")

        statusListener?.onStatus(
            NativeUploadStatusEvent(
                type = NativeUploadEventType.SUCCESS,
                timestamp = lastUploadTime,
                batchSize = batchSize,
                bufferSize = getBufferSize()
            )
        )
    }

    private fun handleFailure(readings: List<SensorReading>, reason: String) {
        totalUploadsFailed++
        Log.e(TAG, "Upload failed (${readings.size} readings). $reason")
        requeueReadings(readings)

        statusListener?.onStatus(
            NativeUploadStatusEvent(
                type = NativeUploadEventType.FAILURE,
                batchSize = readings.size,
                bufferSize = getBufferSize(),
                errorMessage = reason
            )
        )
    }

    private fun requeueReadings(readings: List<SensorReading>) {
        if (readings.isEmpty()) return

        synchronized(sensorBuffer) {
            val toRestore = if (readings.size > maxBufferSize) {
                readings.takeLast(maxBufferSize)
            } else {
                readings
            }
            sensorBuffer.addAll(0, toRestore)

            while (sensorBuffer.size > maxBufferSize) {
                sensorBuffer.removeAt(0)
            }
        }
    }

    private fun buildPayload(
        deviceId: String,
        readings: List<SensorReading>,
        shareLocation: Boolean
    ): Map<String, Any?> {
        var avgLat: Double? = null
        var avgLon: Double? = null
        var avgAccuracy: Double? = null

        if (shareLocation) {
            val locations = readings.mapNotNull { it.location }
            if (locations.isNotEmpty()) {
                avgLat = locations.mapNotNull { it.latitude }.average()
                avgLon = locations.mapNotNull { it.longitude }.average()
                avgAccuracy = locations.mapNotNull { it.accuracy }.average()
            }
        }

        val batch = readings.map { reading ->
            mapOf(
                "t" to reading.timestamp,
                "light" to reading.light,
                "accel" to reading.accelerometer?.let { listOf(it.x, it.y, it.z) },
                "gyro" to reading.gyroscope?.let { listOf(it.x, it.y, it.z) }
            ).filterValues { it != null }
        }

        val locationMap = if (avgLat != null && avgLon != null) {
            mapOf(
                "lat" to avgLat,
                "lon" to avgLon,
                "accuracy_m" to avgAccuracy
            ).filterValues { it != null }
        } else {
            null
        }

        return mapOf(
            "device_id" to deviceId,
            "timestamp" to System.currentTimeMillis(),
            "batch" to batch,
            "location" to locationMap
        ).filterValues { it != null }
    }

    private fun getOrCreateDeviceId(): String {
        val prefs = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
        var deviceId = prefs.getString("flutter.device_id", null)

        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString()
            prefs.edit().putString("flutter.device_id", deviceId).apply()
            Log.i(TAG, "Generated new device ID: $deviceId")
        }

        return deviceId
    }

    companion object {
        private const val TAG = "NativeBackendUploader"
    }
}

/**
 * Data class for a single sensor reading
 */
data class SensorReading(
    val timestamp: Long,
    val light: Float?,
    val accelerometer: AccelData?,
    val gyroscope: GyroData?,
    val location: LocationData?
)

data class AccelData(val x: Float, val y: Float, val z: Float)
data class GyroData(val x: Float, val y: Float, val z: Float)
data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double?
)

enum class NativeUploadEventType {
    STARTED,
    SUCCESS,
    FAILURE
}

data class NativeUploadStatusEvent(
    val type: NativeUploadEventType,
    val timestamp: Long = System.currentTimeMillis(),
    val batchSize: Int = 0,
    val bufferSize: Int = 0,
    val errorMessage: String? = null
)

fun interface NativeUploadStatusListener {
    fun onStatus(event: NativeUploadStatusEvent)
}
