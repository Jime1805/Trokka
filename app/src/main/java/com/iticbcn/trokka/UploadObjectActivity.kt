package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UploadObjectActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView
    private lateinit var btn_mapa: ImageView
    private lateinit var btn_lupa: ImageView
    private lateinit var btn_profile: ImageView
    private lateinit var et_descripcion: EditText
    private lateinit var et_acanvi: EditText

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
            navigateToLoby()
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

        et_descripcion = findViewById<EditText>(R.id.et_descripcion)
        et_acanvi = findViewById<EditText>(R.id.et_acanvi)
    }
}