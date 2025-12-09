package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyObjectsActivity : AppCompatActivity() {

    lateinit var imgFlechita: ImageView
    private lateinit var bottomNav: BottomNavigationView


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

    private fun navigateToLoby(){
        val intent = Intent(this, Loby_Activity::class.java)
        startActivity(intent)
    }

    private fun initListeners() {
        imgFlechita.setOnClickListener {
            navigateToPerfil()
        }
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMapa()
                R.id.loby_fragment -> navigateToLoby()
                R.id.perfil_fragment -> navigateToPerfil()
            }
            true
        }
    }

    private fun initComponents(){
        imgFlechita = findViewById(R.id.imgFlechita)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
    }
}