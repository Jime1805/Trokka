package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritosActivity : AppCompatActivity() {
    lateinit var imgFlechita: ImageView // Hecho
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapterFav
    private lateinit var sFavs: SearchView
    private val viewModel: FavoritosViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        initComponents()
        initRecycler()
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.favoritos.observe(this) { lista ->
            adapter.actualizarLista(lista)
        }
    }

    private fun navigateToMapa(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToProfile(){
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLoby(){
        val intent = Intent(this, Loby_Activity::class.java)
        startActivity(intent)
    }

    private fun initListeners() {
        imgFlechita.setOnClickListener {
            finish()
        }
        itemSearchView()
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMapa()
                R.id.loby_fragment -> navigateToLoby()
                R.id.perfil_fragment -> navigateToProfile()
            }
            true
        }
    }

    private fun itemSearchView() {
        sFavs.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filtrar(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filtrar(newText ?: "")
                return true
            }
        })
    }

    private fun initComponents(){
        imgFlechita = findViewById(R.id.imgFlechita)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
        sFavs = findViewById(R.id.sFavs)
        recyclerView = findViewById(R.id.rFavObj)
    }

    private fun initRecycler() {
        // 1. Configurar LayoutManager (com es col·loquen les files)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Crear llista de dades (des de DataSource o directament)
        val items = DataSource.items

        // 3. Crear l'Adapter passant les dades + funció de callback per clics
        adapter = MyAdapterFav(emptyList()){ item ->
            viewModel.toggleFavorito(item)

                if (item.fav){
                    item.fav = false
                    Toast.makeText(
                        this,
                        "Has eliminat de preferits: " + item.titulo,
                        Toast.LENGTH_SHORT
                    ).show()
                    initRecycler()
                }
                else{
                    item.fav = true
                    Toast.makeText(
                        this,
                        "Has afegit a preferits: " + item.titulo,
                        Toast.LENGTH_SHORT
                    ).show()
                    initRecycler()
                }

            }


        // 4. Assignar l'Adapter al RecyclerView
        recyclerView.adapter = adapter
    }
}