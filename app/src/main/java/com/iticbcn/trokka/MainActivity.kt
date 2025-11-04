package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etNombreUsuario: EditText
    private lateinit var etCorreoInit: EditText
    private lateinit var etPassInit: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var tvRegistrarse: TextView

    companion object{
        const val LOBY = "init_session"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
    }
    private fun navigateToRegister(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLoby(){
        val intent = Intent(this, Loby_Activity::class.java)
        intent.putExtra(LOBY, etNombreUsuario.text.toString())
        startActivity(intent)
    }

    private fun initListeners(){
        tvRegistrarse.setOnClickListener {
            navigateToRegister()
        }
        btnIniciarSesion.setOnClickListener {
            navigateToLoby()
        }
    }
    private fun initComponents(){
        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etCorreoInit = findViewById(R.id.etCorreoInit)
        etPassInit = findViewById(R.id.etPassInit)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        tvRegistrarse = findViewById(R.id.tvRegistrarse)
    }
}