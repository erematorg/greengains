package com.eremat.greengains.service.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.util.Log

class Barometer(sensorManager: SensorManager) :
    BaseSensor<Float>(sensorManager, Sensor.TYPE_PRESSURE, "GreenGainsBarometer") {

    override fun processEvent(event: SensorEvent) {
        val pressure = event.values[0]
        Log.d("GreenGainsBarometer", "Pressure reading: ${pressure} hPa")
        _dataFlow.value = pressure
    }
}
