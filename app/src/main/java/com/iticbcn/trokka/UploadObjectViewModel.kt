package com.iticbcn.trokka

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UploadObjectViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    fun sendProducte(producteSend: ProducteSend) {
        viewModelScope.launch {
            val resposta = ClientAPI.ProducteAPI().createObject(producteSend)

            if (resposta.isSuccessful) {
                _response.value = resposta.body()

                FirebaseConnector.addCrearObjeto()
            } else {
                _response.value = "Error en les dades."
            }
        }
    }
}