package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    fun login(nombre: String, pass: String) {

        if (nombre.isBlank() || pass.isBlank()) {
            _state.value = Pair(false, "Todos los campos son obligatorios")
            return
        }

        viewModelScope.launch {
            try {

                val response = ClientAPI.UsuariAPI().getUserByName(nombre)

                if (response.isSuccessful) {

                    val user = response.body()

                    if (user != null) {

                        if (user.contrasenya == pass) {
                            _state.value = Pair(true, "Inicio de sesión correcto")
                        } else {
                            _state.value = Pair(false, "Contraseña incorrecta")
                        }

                    } else {
                        _state.value = Pair(false, "Usuario no encontrado")
                    }

                } else {
                    _state.value = Pair(false, "Usuario no encontrado")
                }

            } catch (e: Exception) {
                Log.e("Exceptions", "login", e)
                _state.value = Pair(false, "Error de conexión")
            }
        }
    }
}