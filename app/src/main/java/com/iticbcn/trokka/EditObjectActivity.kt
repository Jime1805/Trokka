package com.iticbcn.trokka

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        initListeners()
    }

    private fun initListeners() {
        binding.imgFlechita.setOnClickListener {
            finish()
        }

        binding.btnSubirObjeto.setOnClickListener {
            val producte: Producte? = viewModel.item.value

            val producteSend: ProducteSend = ProducteSend(
                binding.etTitulo.text.toString(),
                producte!!.user,
                binding.etDescripcion.text.toString(),
                binding.etAcanvi.text.toString(),
                producte.fav
            )

            viewModel.saveItem(producte.id, producteSend)

        }
    }

    private fun initObservers() {
        viewModel.errorGet.observe(this) { error ->
            if (error != null) {
                Toast.makeText(
                    this,
                    error,
                    Toast.LENGTH_LONG
                ).show()

            } else {
                setData(viewModel.item.value ?: DataSource.producteVuit)
            }
        }

        viewModel.errorSave.observe(this) { error ->
            if (error != null) {
                Toast.makeText(
                    this,
                    error,
                    Toast.LENGTH_LONG
                ).show()

            } else {
                finish()
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
        binding.etAcanvi.setText(producte.acanvi)
    }
}