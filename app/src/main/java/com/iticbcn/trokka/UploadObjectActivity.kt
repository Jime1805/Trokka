package com.iticbcn.trokka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iticbcn.trokka.databinding.ActivityUploadObjectBinding

class UploadObjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadObjectBinding
    private val viewModel: UploadObjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadObjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.response.observe(this) { resposta ->
            Toast.makeText(
                this,
                resposta,
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun initListeners() {
        binding.imgFlechita.setOnClickListener {
            finish()
        }

        binding.btnSubirObjeto.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descpcio = binding.etDescripcion.text.toString()
            val acanvi = binding.etAcanvi.text.toString()

            val producteSend = ProducteSend(
                titulo = titulo,
                description = descpcio,
                user = User.username,
                isFav = false,
                acanvi = acanvi
            )

            viewModel.sendProducte(producteSend)
        }
    }
}