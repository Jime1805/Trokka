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

class RegisterActivity : AppCompatActivity() {

    lateinit var etUserReg: EditText
    lateinit var etCorreoReg: EditText
    lateinit var etPassReg: EditText
    lateinit var etRePassReg: EditText
    lateinit var btnEnviar: Button
    lateinit var tvIniciarSesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initComponent()
        initListeners()
    }

    private fun navigateToLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun initListeners(){
        tvIniciarSesion.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun initComponent(){
        etUserReg = findViewById(R.id.etUserReg)
        etCorreoReg = findViewById(R.id.etCorreoReg)
        etPassReg = findViewById(R.id.etPassReg)
        etRePassReg = findViewById(R.id.etRePassReg)
        btnEnviar = findViewById(R.id.btnEnviar)
        tvIniciarSesion = findViewById(R.id.tvIniciarSesion)
    }
}