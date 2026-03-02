package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PerfilViewModel: ViewModel() {
    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    private val _nombreUsuario = MutableLiveData<String>()
    val nombreUsuario: LiveData<String> = _nombreUsuario

    fun setNombreUsuario(nombre: String) {
        _nombreUsuario.value = nombre
    }

    fun deleteUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val usuario = UsuariRequest(username, email, password)
                val response = ClientAPI.UsuariAPI().deleteUserById(usuario)

                if (response.isSuccessful) {
                    _state.value = Pair(true, "Cuenta eliminada correctamente")
                } else {
                    _state.value = Pair(false, response.errorBody()?.string() ?: "No se pudo eliminar la cuenta")
                }
            } catch (e: Exception) {
                Log.e("Exceptions", "deleteUser", e)
                _state.value = Pair(false, "Error de conexión")
            }
        }
    }


}