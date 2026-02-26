package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoritosViewModel : ViewModel() {

    private val _favoritos = MutableLiveData<List<Producte>>()
    val favoritos: LiveData<List<Producte>> = _favoritos

    private var listaOriginal: List<Producte> = emptyList()

    init {
        cargarFavoritos()
    }

    private fun cargarFavoritos() {
        viewModelScope.launch {
            try {
                val response = ClientAPI.ProducteAPI().getObjecteFav()

                Log.d("FAVS_DEBUG", "Code = ${response.code()}")
                Log.d("FAVS_DEBUG", "Body = ${response.body()}")
                Log.e("FAVS_DEBUG", "ErrorBody = ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    listaOriginal = response.body() ?: emptyList()
                    _favoritos.value = listaOriginal
                } else {
                    _favoritos.value = emptyList()
                }

            } catch (e: Exception) {
                _favoritos.value = emptyList()
            }
        }
    }

    fun toggleFavorito(producte: Producte) {
        producte.fav = !producte.fav
        _favoritos.value = listaOriginal.filter { it.fav }
    }

    fun filtrar(text: String) {

        val listaFiltrada = if (text.isEmpty()) {
            listaOriginal.filter { it.fav }
        } else {
            val textoLower = text.lowercase()

            listaOriginal.filter {
                it.fav &&
                        (it.titulo.lowercase().contains(textoLower) ||
                            it.user.lowercase().contains(textoLower) ||
                            it.description.lowercase().contains(textoLower) ||
                            it.aCanvi.lowercase().contains(textoLower)
                        )
            }
        }

        _favoritos.value = listaFiltrada
    }
}
