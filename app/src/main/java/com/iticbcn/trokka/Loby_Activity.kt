package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class Loby_Activity : AppCompatActivity() {

    private lateinit var tvUser: TextView
    private lateinit var iv_ImagenObjeto: ImageView
    private lateinit var bottomNav: BottomNavigationView

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
        iv_ImagenObjeto.setOnClickListener {
            navigateToObject()
        }
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMaps()
                R.id.perfil_fragment -> navigateToProfile(usuario)
            }
            true
        }
    }

    private fun  navigateToObject() {
        val intent = Intent(this, ObjectActivity::class.java)
        startActivity(intent)
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
        iv_ImagenObjeto = findViewById(R.id.ivImagenObjeto)
        bottomNav = findViewById(R.id.bottom_navigation)
    }
}