package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class Loby_Activity : AppCompatActivity() {

    private lateinit var tvUser: TextView
    private lateinit var btn_mapa: ImageView
    private lateinit var btn_lupa: ImageView
    private lateinit var btn_profile: ImageView

    companion object{
        const val PERFIL = "perfil"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loby)
        val usuario: String =intent.extras?.getString(LOBY)?: ""
        initComponents()
        initListeners(usuario)
        initUi(usuario)
    }

    private fun initListeners(usuario: String){
        btn_mapa.setOnClickListener {
            navigateToMaps()
        }
        btn_profile.setOnClickListener {
            navigateToProfile(usuario)
        }
    }

    private fun navigateToProfile(usuario: String){
        val intent = Intent(this, PerfilActivity::class.java)
        intent.putExtra(PERFIL, usuario)
        startActivity(intent)
    }
    private fun navigateToMaps(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun initUi(usuario: String) {
        setCorreo(usuario)
    }

    private fun setCorreo(usuario: String){
        tvUser.text = "Benvenido: $usuario"
    }

    private fun initComponents(){
        tvUser = findViewById(R.id.tvUser)
        btn_mapa = findViewById(R.id.btn_mapa)
        btn_lupa = findViewById(R.id.btn_lupa)
        btn_profile = findViewById(R.id.btn_profile)
    }
}