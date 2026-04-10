package com.iticbcn.trokka

import android.app.Application
import android.content.Intent
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.util.UUID

class MyApp: Application() {

    private lateinit var inicio: Timestamp
    private lateinit var final: Timestamp
    private val deviceId = "marc"

    override fun onCreate() {
        super.onCreate()
        VoiceChat.initObjects(this)

        VoiceChat.initiListeners{command ->
            handleVoiceCommand(command)
        }

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

    private fun handleVoiceCommand(command: String?) {
        when {
            command?.contains("abrir mapa") == true -> {
                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)
            }
            command?.contains("abrir inicio") == true -> {
                val intent = Intent(this, Loby_Activity::class.java)
                startActivity(intent)
            }
            command?.contains("abrir perfil") == true -> {
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
            }
            command?.contains("cambiar cuenta") == true -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}