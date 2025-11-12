package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var etUserReg: EditText
    lateinit var etCorreoReg: EditText
    lateinit var etPassReg: EditText
    lateinit var etRePassReg: EditText
    lateinit var btnRegistrarse: Button
    lateinit var tvIniciarSesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initComponent()
        initListeners()
    }

    private fun proofEmail(email: String): Boolean{
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS
        if (!emailPattern.matcher(email).matches()){
            etCorreoReg.error = "Correo electrónico no valido"
            return false
        }
        return true
    }

    private fun proofPassword(password: String, rePassword: String): Boolean{
        if (password.length < 6 || password.length > 16){
            etPassReg.error = "La contraseña debe tener entre 6 y 16 caracteres"
            return false
        }

        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        if(!password.matches(passwordPattern)){
            etPassReg.error = "La contraseña debe de tener al menos una mayúscula, una minúscula y un número"
            return false
        }

        if (password != rePassword){
            etRePassReg.error = "No coincide con la contraseña de arriba"
            return false
        }
        return true
    }

    private fun proofData(): Boolean {
        val username = etUserReg.text.toString().trim()
        val email = etCorreoReg.text.toString().trim()
        val password = etPassReg.text.toString()
        val rePassword = etRePassReg.text.toString()
        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            etUserReg.error = if (username.isEmpty()) "Campo obligatorio" else null
            etCorreoReg.error = if (email.isEmpty()) "Campo obligatorio" else null
            etPassReg.error = if (password.isEmpty()) "Campo obligatorio" else null
            etRePassReg.error = if (rePassword.isEmpty()) "Campo obligatorio" else null
            return false
        }

        if(!proofEmail(email) or !proofPassword(password, rePassword)) return false

        return true
    }

    private fun navigateToLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun initListeners(){
        tvIniciarSesion.setOnClickListener {
            navigateToLogin()
        }
        btnRegistrarse.setOnClickListener {
            if (proofData()){
                navigateToLogin()
            }
        }
    }

    private fun initComponent(){
        etUserReg = findViewById(R.id.etUserReg)
        etCorreoReg = findViewById(R.id.etCorreoReg)
        etPassReg = findViewById(R.id.etPassReg)
        etRePassReg = findViewById(R.id.etRePassReg)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        tvIniciarSesion = findViewById(R.id.tvIniciarSesion)
    }
}