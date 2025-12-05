package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class FavoritosActivity : AppCompatActivity() {
    lateinit var imgFlechita: ImageView // Hecho
    lateinit var btn_mapa: ImageView // Hecho
    lateinit var btn_lupa: ImageView
    lateinit var btn_profile: ImageView //Hecho
    lateinit var rFavObj: RecyclerView // Mirar como hacer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        initComponents()
        initListeners()
    }

    private fun navigateToMapa(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToProfile(){
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
    }
    private fun initComponents(){
        imgFlechita = findViewById(R.id.imgFlechita)
        btn_mapa = findViewById(R.id.btn_mapa)
        btn_lupa = findViewById(R.id.btn_lupa)
        btn_profile = findViewById(R.id.btn_profile)
        rFavObj = findViewById(R.id.rFavObj)
    }
}