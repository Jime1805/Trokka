package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.iticbcn.trokka.Loby_Activity.Companion.PERFIL

class PerfilActivity : AppCompatActivity() {

    private lateinit var tvNombreUsuario: TextView
    private lateinit var bottomNav: BottomNavigationView

    private lateinit var cvEditarObjetos: CardView
    private lateinit var cvSubirObjetos: CardView
    private lateinit var cvFavs: CardView
    private lateinit var cvCanviarCompta: CardView

    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val perfil: String = intent.extras?.getString(PERFIL) ?: ""

        initComponents()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_flechita)

        setupNavView()
        initListeners()
        initUi(perfil)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_superior, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.iConfiguracio -> {
                drawerLayout.openDrawer(GravityCompat.END)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupNavView() {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.iIdioma, R.id.iTema, R.id.iPrivacidad, R.id.iAmigos -> navigateToPreferencies()
                R.id.iInicio -> navigateToLoby()
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }

    private fun setNombreUsuario(perfil: String) {
        tvNombreUsuario.text = perfil
    }

    private fun initUi(perfil: String) {
        setNombreUsuario(perfil)
    }

    private fun navigateToLoby() {
        startActivity(Intent(this, Loby_Activity::class.java))
    }

    private fun navigateToMapa() {
        startActivity(Intent(this, MapActivity::class.java))
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun navigateToPreferencies() {
        startActivity(Intent(this, PreferenciasAcrivity::class.java))
    }

    private fun navigateToUploadObject() {
        startActivity(Intent(this, UploadObjectActivity::class.java))
    }

    private fun navigateToFavs() {
        startActivity(Intent(this, FavoritosActivity::class.java))
    }

    private fun navigateToMyObjects() {
        startActivity(Intent(this, MyObjectsActivity::class.java))
    }

    private fun initListeners() {
        cvCanviarCompta.setOnClickListener { navigateToMain() }
        cvSubirObjetos.setOnClickListener { navigateToUploadObject() }
        cvFavs.setOnClickListener { navigateToFavs() }
        cvEditarObjetos.setOnClickListener { navigateToMyObjects() }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.loby_fragment -> navigateToLoby()
                R.id.mapa_fragment -> navigateToMapa()
            }
            true
        }
    }

    private fun initComponents() {
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario)

        cvEditarObjetos = findViewById(R.id.cvEditarObjetos)
        cvSubirObjetos = findViewById(R.id.cvSubirObjetos)
        cvFavs = findViewById(R.id.cvFavs)
        cvCanviarCompta = findViewById(R.id.cvCanviarCompta)

        toolbar = findViewById(R.id.cvHeader)
        navView = findViewById(R.id.navView)
        drawerLayout = findViewById(R.id.main)

        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
    }
}
