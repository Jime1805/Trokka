package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class Loby_Activity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapterLoby
    private lateinit var svSerch: SearchView

    private lateinit var viewModel: LobyViewModel

    companion object {
        const val PERFIL = "perfil"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loby)

        val usuario: String = intent.extras?.getString(LOBY) ?: ""

        viewModel = ViewModelProvider(this)[LobyViewModel::class.java]

        initComponents()
        initRecycler()
        observeViewModel()
        initListeners(usuario)
    }

    private fun observeViewModel() {
        viewModel.items.observe(this) { items ->
            adapter.updateItems(items)
        }

        viewModel.error.observe(this) { mensaje ->
            mensaje?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initListeners(usuario: String) {

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMaps()
                R.id.perfil_fragment -> navigateToProfile(usuario)
            }
            true
        }

        svSerch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filtrarNoFavs(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filtrarNoFavs(newText ?: "")
                return true
            }
        })
    }

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
        recyclerView = findViewById(R.id.rv_loby)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
        svSerch = findViewById(R.id.sv_serch)
    }

    private fun initRecycler() {

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapterLoby(
            viewModel.getItems(),
            onItemClick = { item ->
                viewModel.toggleFav(item)

                val mensaje = if (item.isFav)
                    "Has afegit a preferits: ${item.titol}"
                else
                    "Has eliminat de preferits: ${item.titol}"

                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.adapter = adapter
    }
}
