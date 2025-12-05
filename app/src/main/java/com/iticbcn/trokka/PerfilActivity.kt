package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iticbcn.trokka.Loby_Activity.Companion.PERFIL
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class PerfilActivity : AppCompatActivity() {
    private lateinit var tvNombreUsuario: TextView
    private lateinit var imgFlechita: ImageView
    private lateinit var btn_mapa: ImageView
    private lateinit var btn_lupa: ImageView
    private lateinit var btn_profile: ImageView
    private lateinit var btn_preferencias: ImageView

    private lateinit var cvEditarObjetos: CardView //Hecho
    private lateinit var cvSubirObjetos: CardView //Hecho
    private lateinit var cvFavs: CardView // Hecho
    private lateinit var cvCanviarCompta: CardView // Hehco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val perfil: String =intent.extras?.getString(PERFIL)?: ""
        initComponents()
        initListeners()
        initUi(perfil)
    }

    private fun setNombreUsuario(perfil: String){
        tvNombreUsuario.text = perfil
    }
    private fun initUi(perfil: String){
        setNombreUsuario(perfil)
    }
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

    private fun navigateToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPreferencies() {
        val intent = Intent(this, PreferenciasAcrivity::class.java)
        startActivity(intent)
    }

    private fun navigateToUploadObject() {
        val intent = Intent(this, UploadObjectActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFavs() {
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMyObjects() {
        val intent = Intent(this, MyObjectsActivity::class.java)
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
        btn_preferencias.setOnClickListener {
            navigateToPreferencies()
        }
        cvCanviarCompta.setOnClickListener {
            navigateToMain()
        }
        cvSubirObjetos.setOnClickListener {
            navigateToUploadObject()
        }
        cvFavs.setOnClickListener {
            navigateToFavs()
        }
        cvEditarObjetos.setOnClickListener {
            navigateToMyObjects()
        }

    }

    private fun initComponents(){
        imgFlechita = findViewById(R.id.imgFlechita)
        btn_mapa = findViewById(R.id.btn_mapa)
        btn_lupa = findViewById(R.id.btn_lupa)
        btn_profile = findViewById(R.id.btn_profile)
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario)
        btn_preferencias = findViewById(R.id.btn_preferencias)

        cvEditarObjetos = findViewById(R.id.cvEditarObjetos)
        cvSubirObjetos = findViewById(R.id.cvSubirObjetos)
        cvFavs = findViewById(R.id.cvFavs)
        cvCanviarCompta = findViewById(R.id.cvCanviarCompta)
    }
}