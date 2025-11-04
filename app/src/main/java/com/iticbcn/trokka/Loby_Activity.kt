package com.iticbcn.trokka

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class Loby_Activity : AppCompatActivity() {

    private lateinit var tvUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loby)
        val usuario: String =intent.extras?.getString(LOBY)?: ""
        initComponents()

        initUi(usuario)
    }

    private fun initUi(usuario: String) {
        setCorreo(usuario)
    }

    private fun setCorreo(usuario: String){
        tvUser.text = "Benvenido: $usuario"
    }

    private fun initComponents(){
        tvUser = findViewById(R.id.tvUser)
    }
}