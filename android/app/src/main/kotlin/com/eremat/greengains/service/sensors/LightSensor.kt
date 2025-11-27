package com.eremat.greengains.service.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager

class LightSensor(sensorManager: SensorManager) : 
    BaseSensor<Float>(sensorManager, Sensor.TYPE_LIGHT, "GreenGainsLight") {

    override fun processEvent(event: SensorEvent) {
        _dataFlow.value = event.values[0]
    }
}
