package com.iticbcn.trokka

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

abstract class BaseLoggedActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        // Si ya tenemos el permiso (porque el usuario dijo que sí en la pantalla principal), empezamos a escuchar
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            iniciarEscuchaVoz()
        }
    }

    protected fun iniciarEscuchaVoz() {
        VoiceChat.initiListeners { comando ->
            procesarComandoDeVoz(comando)
        }
    }

    open fun procesarComandoDeVoz(comando: String?) {
        Toast.makeText(this, "Has dicho: $comando", Toast.LENGTH_LONG).show()
        if (comando?.contains("hola") == true) {
            Toast.makeText(
                this,
                "Hola :D",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}