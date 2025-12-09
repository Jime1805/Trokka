package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class UploadObjectActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView
    private lateinit var et_descripcion: EditText
    private lateinit var et_acanvi: EditText
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_object)

        initComponents()
        initListeners()
        /*initUi(perfil)*/
    }

    /*private fun initUi(perfil: String){
        setNombreUsuario(perfil)
    }*/

    private fun navigateToLoby(){
        val intent = Intent(this, Loby_Activity::class.java)
        startActivity(intent)
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
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMapa()
                R.id.loby_fragment -> navigateToLoby()
                R.id.perfil_fragment -> navigateToProfile()
            }
            true
        }
    }

    private fun initComponents(){
        imgFlechita = findViewById(R.id.imgFlechita)

        et_descripcion = findViewById<EditText>(R.id.et_descripcion)
        et_acanvi = findViewById<EditText>(R.id.et_acanvi)

        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
    }
}