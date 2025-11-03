package com.iticbcn.trokka

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etNombreUsuario: EditText
    private lateinit var etCorreoInit: EditText
    private lateinit var etPassInit: EditText
    private lateinit var btnIniciarSesion: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUi()
    }

    private fun initComponents(){
        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etCorreoInit = findViewById(R.id.etCorreoInit)
        etPassInit = findViewById(R.id.etPassInit)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
    }
}