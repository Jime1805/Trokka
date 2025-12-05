package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MyObjectsActivity : AppCompatActivity() {

    lateinit var imgFlechita: ImageView
    lateinit var btn_mapa: ImageView
    lateinit var btn_lupa: ImageView
    lateinit var btn_profile: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_objects)
        initComponents()
        initListeners()
    }

    private fun navigateToMapa(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPerfil(){
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }

    private fun initListeners() {
        imgFlechita.setOnClickListener {
            navigateToPerfil()
        }
        btn_mapa.setOnClickListener {
            navigateToMapa()
        }
        btn_profile.setOnClickListener {
            navigateToPerfil()
        }
    }

    private fun initComponents(){
        imgFlechita = findViewById(R.id.imgFlechita)
        btn_mapa = findViewById(R.id.btn_mapa)
        btn_lupa = findViewById(R.id.btn_lupa)
        btn_profile = findViewById(R.id.btn_profile)
    }
}