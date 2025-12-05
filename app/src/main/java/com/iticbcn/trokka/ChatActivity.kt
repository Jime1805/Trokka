package com.iticbcn.trokka

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChatActivity : AppCompatActivity() {

    private lateinit var imgFlechita: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initComponents()
        initListeners()
    }

    private fun initListeners() {
        imgFlechita.setOnClickListener {
            finish()
        }
    }

    private fun initComponents() {
        imgFlechita = findViewById(R.id.imgFlechita)
    }
}