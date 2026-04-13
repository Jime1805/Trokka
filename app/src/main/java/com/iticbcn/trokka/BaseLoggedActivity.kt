package com.iticbcn.trokka

import android.Manifest
import android.content.Intent
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

    open fun procesarComandoDeVoz(command: String?) {
        when {
            command?.contains("abrir mapa") == true -> {
                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)
            }
            command?.contains("abrir inicio") == true -> {
                val intent = Intent(this, LobyActivity::class.java)
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
            command?.contains("crear nueva cuenta") == true -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            command?.contains("siri dona 500 euros") == true -> {
                Toast.makeText(
                    this,
                    "Has donat 500 euros a esta app",
                    Toast.LENGTH_SHORT
                ).show()
            }
            command?.contains("alexa dona 500 euros") == true -> {
                Toast.makeText(
                    this,
                    "Has donat 500 euros a esta app",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}