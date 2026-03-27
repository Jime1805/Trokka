package com.iticbcn.trokka

import android.app.Application
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.util.UUID

class MyApp: Application() {

    private lateinit var inicio: Timestamp
    private lateinit var final: Timestamp
    private val deviceId = "device1"

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        inicio = Timestamp(System.currentTimeMillis())

        CoroutineScope(Dispatchers.IO).launch {
            FirebaseConnector.cargarInfo(deviceId)
            FirebaseConnector.addEntradasApp()
            FirebaseConnector.guardarInfo(deviceId, FirebaseConnector.dataInfo)
        }
    }

    fun calcularTempsFinal(): Long {
        final = Timestamp(System.currentTimeMillis())
        val tiempoTotal = final.time - inicio.time

        inicio = final

        return tiempoTotal
    }
}