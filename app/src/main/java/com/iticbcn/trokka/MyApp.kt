package com.iticbcn.trokka

import android.app.Application
import com.google.firebase.FirebaseApp
import java.sql.Timestamp

class MyApp: Application() {

    private lateinit var inicio: Timestamp
    private lateinit var final: Timestamp

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseConnector.addEntradasApp()
        inicio = Timestamp(System.currentTimeMillis())
    }

    override fun onTerminate() {
        super.onTerminate()
        final = Timestamp(System.currentTimeMillis())
        val diff = final.time - inicio.time
        FirebaseConnector.addHores(diff)
    }
}