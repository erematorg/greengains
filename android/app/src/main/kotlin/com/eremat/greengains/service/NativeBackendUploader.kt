package com.eremat.greengains.service

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Native Backend Uploader - Runs independently of Flutter
 *
 * This class handles all backend uploads in pure Kotlin, ensuring:
 * - Uploads work even when app is swiped away
 * - No dependency on Flutter/Dart lifecycle
 * - Proper error handling and retry logic
 * - Extensive logging for verification
 */
class NativeBackendUploader(
    private val context: Context,
    private val uploadIntervalMs: Long = 120_000L // 2 minutes default
) {
    private val gson = Gson()
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var uploadJob: Job? = null

    // Buffer for sensor readings (max 1000 entries ~ 10 minutes of data at 5s intervals)
    private val sensorBuffer = mutableListOf<SensorReading>()
    private val maxBufferSize = 1000

    // Backend configuration
    private val baseUrl = "https://greengains.onrender.com"
    private val apiKey = "Vb9kS3tP0xQ7fY2L"

    // HTTP client with reasonable timeouts
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    // Track upload statistics for verification
    private var totalUploadsAttempted = 0
    private var totalUploadsSucceeded = 0
    private var totalUploadsFailed = 0
    private var lastUploadTime: Long = 0

    /**
     * Starts the periodic upload timer
     * This will upload batched data every N minutes, independent of Flutter
     */
    fun start() {
        Log.i(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.i(TAG, "🚀 NATIVE BACKEND UPLOADER STARTING")
        Log.i(TAG, "   Upload Interval: ${uploadIntervalMs / 1000}s")
        Log.i(TAG, "   Backend URL: $baseUrl/upload")
        Log.i(TAG, "   Max Buffer Size: $maxBufferSize readings")
        Log.i(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")

        uploadJob = coroutineScope.launch {
            while (isActive) {
                try {
                    delay(uploadIntervalMs)
                    uploadBatch()
                } catch (e: Exception) {
                    Log.e(TAG, "⚠️  Upload timer error: ${e.message}", e)
                }
            }
        }
    }

    /**
     * Stops the uploader
     */
    fun stop() {
        Log.i(TAG, "🛑 NATIVE BACKEND UPLOADER STOPPING")
        Log.i(TAG, "   Stats - Attempted: $totalUploadsAttempted, Succeeded: $totalUploadsSucceeded, Failed: $totalUploadsFailed")

        uploadJob?.cancel()
        coroutineScope.coroutineContext.cancelChildren()
        httpClient.dispatcher.executorService.shutdown()
    }

    /**
     * Adds a sensor reading to the buffer
     * Called from ForegroundService whenever sensor data arrives
     */
    fun addReading(reading: SensorReading) {
        synchronized(sensorBuffer) {
            sensorBuffer.add(reading)

            // Implement circular buffer to prevent memory overflow
            while (sensorBuffer.size > maxBufferSize) {
                val removed = sensorBuffer.removeAt(0)
                Log.w(TAG, "⚠️  Buffer overflow! Removing oldest reading (ts=${removed.timestamp})")
            }

            // Log every 50 readings for monitoring
            if (sensorBuffer.size % 50 == 0) {
                Log.d(TAG, "📊 Buffer size: ${sensorBuffer.size} readings")
            }
        }
    }

    /**
     * Gets current buffer size (for monitoring)
     */
    fun getBufferSize(): Int = synchronized(sensorBuffer) { sensorBuffer.size }

    /**
     * Uploads batched sensor data to backend
     * This runs every N minutes automatically
     */
    private suspend fun uploadBatch() = withContext(Dispatchers.IO) {
        val readings: List<SensorReading>

        synchronized(sensorBuffer) {
            if (sensorBuffer.isEmpty()) {
                Log.d(TAG, "⏭️  No data to upload, skipping...")
                return@withContext
            }

            // Take snapshot of buffer and clear it
            readings = sensorBuffer.toList()
            sensorBuffer.clear()
        }

        Log.i(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.i(TAG, "📤 STARTING UPLOAD")
        Log.i(TAG, "   Readings to upload: ${readings.size}")
        Log.i(TAG, "   Time since last upload: ${(System.currentTimeMillis() - lastUploadTime) / 1000}s")

        try {
            // Get device ID from SharedPreferences
            val deviceId = getOrCreateDeviceId()

            // Check if user allows location sharing
            val shareLocation = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
                .getBoolean("flutter.share_location", false)

            // Build payload matching backend schema
            val payload = buildPayload(deviceId, readings, shareLocation)

            // Convert to JSON
            val jsonPayload = gson.toJson(payload)
            Log.d(TAG, "   Payload size: ${jsonPayload.length} characters")
            Log.d(TAG, "   Sample: ${jsonPayload.take(200)}...")

            // Create HTTP request
            val request = Request.Builder()
                .url("$baseUrl/upload")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-Key", apiKey)
                .post(jsonPayload.toRequestBody("application/json".toMediaType()))
                .build()

            totalUploadsAttempted++

            // Execute request
            val response = httpClient.newCall(request).execute()

            if (response.isSuccessful) {
                totalUploadsSucceeded++
                lastUploadTime = System.currentTimeMillis()

                Log.i(TAG, "✅ UPLOAD SUCCESSFUL!")
                Log.i(TAG, "   Status: ${response.code}")
                Log.i(TAG, "   Response: ${response.body?.string()}")
                Log.i(TAG, "   Total succeeded: $totalUploadsSucceeded")
            } else {
                totalUploadsFailed++

                Log.e(TAG, "❌ UPLOAD FAILED!")
                Log.e(TAG, "   Status: ${response.code}")
                Log.e(TAG, "   Response: ${response.body?.string()}")
                Log.e(TAG, "   Total failed: $totalUploadsFailed")

                // Re-add readings to buffer for retry
                synchronized(sensorBuffer) {
                    sensorBuffer.addAll(0, readings)
                    Log.i(TAG, "   📥 Re-added ${readings.size} readings to buffer for retry")
                }
            }

            response.close()

        } catch (e: IOException) {
            totalUploadsFailed++

            Log.e(TAG, "❌ NETWORK ERROR!", e)
            Log.e(TAG, "   Type: ${e.javaClass.simpleName}")
            Log.e(TAG, "   Message: ${e.message}")
            Log.e(TAG, "   Total failed: $totalUploadsFailed")

            // Re-add readings to buffer for retry
            synchronized(sensorBuffer) {
                sensorBuffer.addAll(0, readings)
                Log.i(TAG, "   📥 Re-added ${readings.size} readings to buffer for retry")
            }

        } catch (e: Exception) {
            totalUploadsFailed++

            Log.e(TAG, "❌ UNEXPECTED ERROR!", e)
            Log.e(TAG, "   Type: ${e.javaClass.simpleName}")
            Log.e(TAG, "   Message: ${e.message}")

        } finally {
            Log.i(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        }
    }

    /**
     * Builds the JSON payload matching backend schema
     */
    private fun buildPayload(
        deviceId: String,
        readings: List<SensorReading>,
        shareLocation: Boolean
    ): Map<String, Any?> {
        // Calculate aggregated location if available and user allows
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

        // Build batch array
        val batch = readings.map { reading ->
            mapOf(
                "t" to reading.timestamp,
                "light" to reading.light,
                "accel" to reading.accelerometer?.let { listOf(it.x, it.y, it.z) },
                "gyro" to reading.gyroscope?.let { listOf(it.x, it.y, it.z) }
            ).filterValues { it != null }
        }

        // Build final payload
        return mapOf(
            "device_id" to deviceId,
            "timestamp" to System.currentTimeMillis(),
            "batch" to batch,
            "location" to if (avgLat != null && avgLon != null) {
                mapOf(
                    "lat" to avgLat,
                    "lon" to avgLon,
                    "accuracy_m" to avgAccuracy
                )
            } else null
        ).filterValues { it != null }
    }

    /**
     * Gets or creates a unique device ID
     */
    private fun getOrCreateDeviceId(): String {
        val prefs = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
        var deviceId = prefs.getString("flutter.device_id", null)

        if (deviceId == null) {
            deviceId = java.util.UUID.randomUUID().toString()
            prefs.edit().putString("flutter.device_id", deviceId).apply()
            Log.i(TAG, "   Generated new device ID: $deviceId")
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
