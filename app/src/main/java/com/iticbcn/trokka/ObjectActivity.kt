package com.iticbcn.trokka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iticbcn.trokka.Loby_Activity.Companion.PERFIL
import com.iticbcn.trokka.MainActivity.Companion.LOBY

class ObjectActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView
    private lateinit var btn_mapa: ImageView
    private lateinit var btn_lupa: ImageView
    private lateinit var btn_profile: ImageView
    private lateinit var btn_initChat: TextView

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
        btn_mapa.setOnClickListener {
            navigateToMaps()
        }
        btn_profile.setOnClickListener {
            navigateToProfile()
        }
        btn_lupa.setOnClickListener {
            navigateToLoby()
        }
        btn_initChat.setOnClickListener {
            navigateToChat()
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
        btn_lupa = findViewById(R.id.btn_lupa)
        btn_mapa = findViewById(R.id.btn_mapa)
        btn_profile = findViewById(R.id.btn_profile)
        btn_initChat = findViewById(R.id.btn_initChat)
    }
}