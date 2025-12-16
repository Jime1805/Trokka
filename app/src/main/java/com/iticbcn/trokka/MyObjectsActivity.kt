package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyObjectsActivity : AppCompatActivity() {

    lateinit var imgFlechita: ImageView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapterFav


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_objects)
        initComponents()
        initListeners()
    }

    private fun navigateToMapa() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPerfil() {
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLoby() {
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

    private fun initComponents() {
        initRecycler()
        imgFlechita = findViewById(R.id.imgFlechita)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun initRecycler() {
        // 1. Obtenir referència al RecyclerView del layout
        recyclerView = findViewById(R.id.rMyObj)

        // 2. Configurar LayoutManager (com es col·loquen les files)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. Crear llista de dades (des de DataSource o directament)
        val items = DataSource.items

        // 4. Crear l'Adapter passant les dades + funció de callback per clics
        adapter = MyAdapterFav(
            items = items,
            onItemClick = { item ->
                // AQUÍ gestionem el clic: mostrem un Toast amb el títol
                Toast.makeText(
                    this,
                    "Has fet clic a " + item.titol,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        // 5. Assignar l'Adapter al RecyclerView
        recyclerView.adapter = adapter
    }
}