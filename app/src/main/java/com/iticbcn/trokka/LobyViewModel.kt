package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LobyViewModel : ViewModel() {

    private val _items = MutableLiveData<MutableList<Producte>>()
    val items: LiveData<MutableList<Producte>> = _items

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadProductes()
    }

    // Llamada a API usando coroutine
    fun loadProductes() {
        viewModelScope.launch {

            try {
                val response = withContext(Dispatchers.IO) {
                    ProducteAPI.API().getAllObjecte()
                }

                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()
                    _items.value = lista.toMutableList()
                    _error.value = null
                } else {
                    _error.value = "Error ${response.code()}: ${response.message()}"
                    Log.e("LobyViewModel", "Error ${response.code()}: ${response.message()}")
                }

            } catch (e: IOException) {
                _error.value = "Error de red: ${e.localizedMessage}"
                Log.e("LobyViewModel", "Error de red: ${e.localizedMessage}", e)
            } catch (e: HttpException) {
                _error.value = "Error HTTP: ${e.localizedMessage}"
                Log.e("LobyViewModel", "Error HTTP: ${e.localizedMessage}", e)
            } catch (e: Exception) {
                _error.value = "Error desconocido: ${e.localizedMessage}"
                Log.e("LobyViewModel", "Error desconocido: ${e.localizedMessage}", e)
            }
        }
    }

    fun toggleFav(producte: Producte) {
        val list = _items.value ?: return
        val index = list.indexOf(producte)
        if (index != -1) {
            list[index].fav = !list[index].fav
            _items.value = list
        }
    }

    fun getItems(): MutableList<Producte> {
        return _items.value ?: mutableListOf()
    }
}
