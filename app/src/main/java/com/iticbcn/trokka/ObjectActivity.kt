package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iticbcn.trokka.Loby_Activity.Companion.PERFIL
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class ObjectActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView
    private lateinit var btn_initChat: TextView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        initComponents()
        initListeners()
    }

    private fun initListeners(){
        imgFlechita.setOnClickListener {
            finish()
        }
        btn_initChat.setOnClickListener {
            navigateToChat()
        }
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapa_fragment -> navigateToMaps()
                R.id.loby_fragment -> navigateToLoby()
                R.id.perfil_fragment -> navigateToProfile()
            }
            true
        }
    }

    private fun navigateToChat() {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToLoby(){
        val intent = Intent(this, Loby_Activity::class.java)
        /*intent.putExtra(PERFIL, usuario)*/
        startActivity(intent)
    }
    private fun navigateToProfile(){
        val intent = Intent(this, PerfilActivity::class.java)
        /*intent.putExtra(PERFIL, usuario)*/
        startActivity(intent)
    }
    private fun navigateToMaps(){
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun initComponents() {
        imgFlechita = findViewById(R.id.imgFlechita)
        btn_initChat = findViewById(R.id.btn_initChat)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setBackgroundColor(Color.TRANSPARENT)
    }
}