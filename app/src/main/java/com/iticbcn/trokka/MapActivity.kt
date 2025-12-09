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

class MapActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView
    private lateinit var btn_puntTrobada: ImageView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initComponents()
        initListeners()
        /*initUi(perfil)*/
    }

    private fun navigateToPuntTrobada() {
        // TODO
    }

    private fun navigateToLoby() {
        val intent = Intent(this, Loby_Activity::class.java)
        startActivity(intent)
    }

    private fun navigateToMapa() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToProfile() {
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }

    private fun initListeners() {
        imgFlechita.setOnClickListener {
            finish()
        }
        btn_puntTrobada.setOnClickListener {
            navigateToPuntTrobada()
        }
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.loby_fragment -> navigateToLoby()
                R.id.perfil_fragment -> navigateToProfile()
            }
            true
        }
    }

    private fun initComponents() {
        imgFlechita = findViewById(R.id.imgFlechita)
        btn_puntTrobada = findViewById(R.id.btn_puntTrobada)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
    }
}