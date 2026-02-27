package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var etUserReg: EditText
    private lateinit var etCorreoReg: EditText
    private lateinit var etPassReg: EditText
    private lateinit var etRePassReg: EditText
    private lateinit var btnRegistrarse: Button
    private lateinit var tvIniciarSesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initComponents()
        initListeners()
        observeViewModel()
    }

    private fun initComponents() {
        etUserReg = findViewById(R.id.etUserReg)
        etCorreoReg = findViewById(R.id.etCorreoReg)
        etPassReg = findViewById(R.id.etPassReg)
        etRePassReg = findViewById(R.id.etRePassReg)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        tvIniciarSesion = findViewById(R.id.tvIniciarSesion)
    }

    private fun initListeners() {
        tvIniciarSesion.setOnClickListener {
            navigateToLogin()
        }

        btnRegistrarse.setOnClickListener {
            viewModel.register(
                etUserReg.text.toString(),
                etCorreoReg.text.toString(),
                etPassReg.text.toString(),
                etRePassReg.text.toString()
            )
            Toast.makeText(this, "El boton registrarse funciona", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { (ok, msg) ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            if (ok) {
                navigateToLogin()
                finish()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
