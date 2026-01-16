package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class Loby_Activity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapterLoby
    private lateinit var svSerch: SearchView

    companion object {
        const val PERFIL = "perfil"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loby)
        val usuario: String = intent.extras?.getString(LOBY) ?: ""
        initComponents()
        initListeners(usuario)
    }

    private fun initListeners(usuario: String) {
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMaps()
                R.id.perfil_fragment -> navigateToProfile(usuario)
            }
            true
        }
        itemSearchView()
    }

    private fun itemSearchView() {
        svSerch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filtrar(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filtrar(newText ?: "")
                return true
            }
        })
    }

    /*private fun navigateToObject() {
        val intent = Intent(this, ObjectActivity::class.java)
        startActivity(intent)
    }*/

    private fun navigateToProfile(usuario: String) {
        val intent = Intent(this, PerfilActivity::class.java)
        intent.putExtra(PERFIL, usuario)
        startActivity(intent)
    }

    private fun navigateToMaps() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun initComponents() {
        initRecycler()
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
        svSerch = findViewById(R.id.sv_serch)
    }

    private fun initRecycler() {
        // 1. Obtenir referència al RecyclerView del layout
        recyclerView = findViewById(R.id.rv_loby)

        // 2. Configurar LayoutManager (com es col·loquen les files)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. Crear llista de dades (des de DataSource o directament)
        val items = DataSource.items

        // 4. Crear l'Adapter passant les dades + funció de callback per clics
        adapter = MyAdapterLoby(
            items = items,
            onItemClick = { item ->
                // AQUÍ gestionem el clic: mostrem un Toast amb el títol

                if (item.isFav) {
                    item.isFav = false
                    Toast.makeText(
                        this,
                        "Has eliminat de preferits: " + item.titol,
                        Toast.LENGTH_SHORT
                    ).show()
                    initRecycler()
                } else {
                    item.isFav = true
                    Toast.makeText(
                        this,
                        "Has afegit a preferits: " + item.titol,
                        Toast.LENGTH_SHORT
                    ).show()
                    initRecycler()
                }

            }
        )

        // 5. Assignar l'Adapter al RecyclerView
        recyclerView.adapter = adapter
    }
}