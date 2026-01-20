package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var etNombreUsuario: EditText
    private lateinit var etCorreoInit: EditText
    private lateinit var etPassInit: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var tvRegistrarse: TextView

    companion object {
        const val LOBY = "init_session"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashScreen.setKeepOnScreenCondition {
            false
        }

        splashScreenConditions()

        initComponents()
        initListeners()
    }

    private fun splashScreenConditions() {
        splashScreen.setOnExitAnimationListener { splashView ->
            splashView.iconView?.animate()
                ?.scaleX(0.85f)
                ?.scaleY(0.85f)
                ?.alpha(0f)
                ?.setDuration(3000)
                ?.withEndAction { splashView.remove() }
                ?.start()
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLoby() {
        val intent = Intent(this, Loby_Activity::class.java)
        intent.putExtra(LOBY, etNombreUsuario.text.toString())
        startActivity(intent)
    }

    private fun initListeners() {
        tvRegistrarse.setOnClickListener {
            navigateToRegister()
        }
        btnIniciarSesion.setOnClickListener {
            viewModel.state.observe(this) { estado ->
                val (esCorrecte, msg) = estado

                // TODO: Hacer toast del mensage, si es true hace login y muestra un toast de correcto, si es false, muestra toas d'error
            }
        }
    }

    private fun initComponents() {
        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etCorreoInit = findViewById(R.id.etCorreoInit)
        etPassInit = findViewById(R.id.etPassInit)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        tvRegistrarse = findViewById(R.id.tvRegistrarse)
    }
}