package com.eremat.greengains.service.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Abstract base class for managing Android sensors.
 * Handles listener registration/unregistration and flow updates.
 */
abstract class BaseSensor<T>(
    private val sensorManager: SensorManager,
    private val sensorType: Int,
    private val tag: String
) : SensorEventListener {

    protected val _dataFlow = MutableStateFlow<T?>(null)
    val dataFlow: StateFlow<T?> = _dataFlow

    private var sensor: Sensor? = null

    init {
        sensor = sensorManager.getDefaultSensor(sensorType)
        if (sensor == null) {
            Log.w(tag, "Sensor not available on this device")
        } else {
            Log.d(tag, "Sensor available: ${sensor?.name}")
        }
    }

    fun start() {
        sensor?.let {
            // SENSOR_DELAY_NORMAL (~200ms) instead of UI (~67ms) - saves battery with zero data loss
            val success = sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(tag, "Listener registered: success=$success, sensor=${it.name}, type=${it.type}")
        } ?: Log.w(tag, "Cannot start: sensor is null")
    }

    fun stop() {
        if (sensor != null) {
            sensorManager.unregisterListener(this)
            Log.d(tag, "Listener unregistered")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No-op by default
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == sensorType) {
            if (tag == "GreenGainsBarometer") {
                Log.d(tag, "onSensorChanged called, values: ${event.values.joinToString()}")
            }
            processEvent(event)
        }
    }

    protected abstract fun processEvent(event: SensorEvent)
}
