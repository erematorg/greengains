package com.eremat.greengains.service.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Manages both Accelerometer and Gyroscope since they are often used together.
 * Does not inherit from BaseSensor because it manages two sensors.
 */
class MotionSensors(private val sensorManager: SensorManager) : SensorEventListener {
    private val tag = "GreenGainsMotion"

    private val _accelerometerFlow = MutableStateFlow<FloatArray?>(null)
    val accelerometerFlow: StateFlow<FloatArray?> = _accelerometerFlow

    private val _gyroscopeFlow = MutableStateFlow<FloatArray?>(null)
    val gyroscopeFlow: StateFlow<FloatArray?> = _gyroscopeFlow

    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null

    init {
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        
        if (accelerometer == null) Log.w(tag, "Accelerometer not available")
        if (gyroscope == null) Log.w(tag, "Gyroscope not available")
    }

    fun start() {
        accelerometer?.let {
            // SENSOR_DELAY_NORMAL (~200ms) instead of UI (~67ms) - saves battery with zero data loss
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        Log.d(tag, "Motion listeners registered")
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        Log.d(tag, "Motion listeners unregistered")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                _accelerometerFlow.value = event.values.clone()
            }
            Sensor.TYPE_GYROSCOPE -> {
                _gyroscopeFlow.value = event.values.clone()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No-op
    }
}
