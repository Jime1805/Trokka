package com.iticbcn.trokka

import android.os.Bundle
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
        setContentView(R.layout.activity_edit_object)
        binding = ActivityEditObjectBinding.inflate(layoutInflater)
        initData()
    }

    private fun initData() {
        val producteId = intent.getStringExtra("productId")?.toInt()

        viewModel.getItem(producteId)

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

    private fun setData(producte: Producte) {
        binding.etTitulo.setText(producte.titulo)
    }
}