package com.iticbcn.trokka

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class LobyActivity : BaseLoggedActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapterLoby
    private lateinit var svSerch: SearchView
    private lateinit var btnSpeak: ConstraintLayout

    private lateinit var viewModel: LobyViewModel

    companion object {
        const val PERFIL = "perfil"
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            iniciarEscuchaVoz()
        } else {
            Toast.makeText(this, "Funcions de veu desactivades", Toast.LENGTH_SHORT).show()
        }
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
        comprobarPermisoMicro()
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

        btnSpeak.setOnClickListener {
            iniciarEscuchaVoz()
        }
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
        btnSpeak = findViewById(R.id.btn_speak)
    }

    private fun initRecycler() {

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapterLoby(
            viewModel.getItems(),
            onItemClick = { item ->
                viewModel.toggleFav(item)

                val mensaje = if (item.fav) {
                    FirebaseConnector.addFavoritos()
                    "Has afegit a preferits: ${item.titulo}"
                } else {
                    FirebaseConnector.looseFavoritos()
                    "Has eliminat de preferits: ${item.titulo}"
                }

                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.adapter = adapter
    }

    private fun comprobarPermisoMicro() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // Si NO lo tiene, se lo pedimos
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
        // Nota: Si YA lo tiene, no hacemos nada aquí, porque onResume() de la clase Base
        // se ejecutará justo después de onCreate() y lo encenderá automáticamente.
    }
}
