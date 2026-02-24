package com.iticbcn.trokka

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.iticbcn.trokka.databinding.ActivityEditObjectBinding

class EditObjectActivity : AppCompatActivity() {

    private val viewModel: EditObjectViewModel by viewModels()
    private lateinit var binding: ActivityEditObjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditObjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initData()
    }

    private fun initObservers() {
        viewModel.error.observe(this) { error ->
            if (error != null) {
                Toast.makeText(
                    this,
                    error,
                    Toast.LENGTH_LONG
                )

            } else {
                setData(viewModel.item.value ?: DataSource.producteVuit)
            }
        }
    }

    private fun initData() {
        val producteId = intent.getIntExtra("productId", 0)

        viewModel.getItem(producteId)


    }

    private fun setData(producte: Producte) {
        binding.etTitulo.setText(producte.titulo)
        binding.etDescripcion.setText(producte.description)
        binding.etAcanvi.setText(producte.aCanvi)
    }
}