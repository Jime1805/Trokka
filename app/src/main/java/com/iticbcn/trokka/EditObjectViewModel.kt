package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditObjectViewModel : ViewModel() {
    private val _item = MutableLiveData<Producte>()
    val item: LiveData<Producte> = _item
    private val _errorGet = MutableLiveData<String?>()
    val errorGet: LiveData<String?> = _errorGet

    private val _errorSave = MutableLiveData<String?>()
    val errorSave: LiveData<String?> = _errorSave

    fun getItem(itemId: Int?) {
        viewModelScope.launch {
            if (itemId == null) {
                _errorGet.value = "Error, El id del producte es null."
            } else {
                val response = ClientAPI.ProducteAPI().getObjecteById(itemId)

                if (response.isSuccessful) {
                    val producte = response.body() ?: DataSource.producteVuit
                    _item.value = producte
                    _errorGet.value = null
                } else {
                    _errorGet.value = "Error ${response.code()}: ${response.message()}"
                    Log.e("EditObjectViewModel", "Error ${response.code()}: ${response.message()}")
                }
            }
        }
    }

    fun saveItem(id: Int, producteSend: ProducteSend) {
        viewModelScope.launch {
            try {
                val response = ClientAPI.ProducteAPI().putObjecte(id, producteSend)

                if (response.isSuccessful) {
                    _errorSave.value = null
                } else {
                    _errorSave.value = "Error ${response.code()}: ${response.message()}"
                    Log.e("EditObjectViewModel", "Error ${response.code()}: ${response.message()}")
                }
            } catch (exception: Exception) {
                   Log.e("CATCH", exception.toString())
            }
        }
    }
}