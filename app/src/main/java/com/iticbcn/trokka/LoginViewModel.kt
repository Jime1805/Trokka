package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData<Triple<Boolean, String, UsuariResponse?>>() // para poder guardar el usuari y hacer delete en otra activity
    val state: LiveData<Triple<Boolean, String, UsuariResponse?>> = _state

    fun login(nombre: String, pass: String) {

        if (nombre.isBlank() || pass.isBlank()) {
            _state.value = Triple(false, "Todos los campos son obligatorios", null)
            return
        }

        viewModelScope.launch {
            try {
                val response = ClientAPI.UsuariAPI().getUserByName(nombre)

                if (response.isSuccessful) {

                    val user = response.body()

                    if (user != null) {

                        if (user.contrasenya == pass) {
                            _state.value = Triple(true, "Inicio de sesión correcto", user)
                        } else {
                            _state.value = Triple(false, "Contraseña incorrecta", null)
                        }

                    } else {
                        _state.value = Triple(false, "Usuario no encontrado", null)
                    }

                } else {
                    _state.value = Triple(false, "Usuario no encontrado", null)
                }

            } catch (e: Exception) {
                Log.e("Exceptions", "login", e)
                _state.value = Triple(false, "Error de conexión", null)
            }
        }
    }
}