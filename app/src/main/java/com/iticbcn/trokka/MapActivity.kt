package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MapActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView
    private lateinit var btn_mapa: ImageView
    private lateinit var btn_lupa: ImageView
    private lateinit var btn_profile: ImageView
    private lateinit var btn_puntTrobada: ImageView

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
        btn_mapa.setOnClickListener {
            navigateToMapa()
        }
        btn_profile.setOnClickListener {
            navigateToProfile()
        }
        btn_lupa.setOnClickListener {
            navigateToLoby()
        }
        btn_puntTrobada.setOnClickListener {
            navigateToPuntTrobada()
        }
    }

    private fun initComponents() {
        imgFlechita = findViewById(R.id.imgFlechita)
        btn_mapa = findViewById(R.id.btn_mapa)
        btn_lupa = findViewById(R.id.btn_lupa)
        btn_profile = findViewById(R.id.btn_profile)
        btn_puntTrobada = findViewById(R.id.btn_puntTrobada)
    }
}