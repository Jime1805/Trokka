package com.iticbcn.trokka

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    fun register(username: String, email: String, password: String, rePassword: String){

        if (username.isBlank() || email.isBlank() || password.isBlank() || rePassword.isBlank()) {
            _state.value = Pair(false, "Todos los campos son obligatorios")
            return
        }

        if (!proofEmail(email)) {
            _state.value = Pair(false, "Correo electrónico no válido")
            return
        }

        val passwordResult = proofPassword(password, rePassword)
        if (!passwordResult.first) {
            _state.value = passwordResult
            return
        }

        createUser(username, email, password)
    }

    private fun createUser(username: String, email: String, password: String) {
        viewModelScope.launch {

            try {
                val response = ClientAPI.UsuariAPI().postUsers(
                    UsuariRequest(username, email, password)
                )

                if (response.isSuccessful) {
                    _state.value = Pair(true, response.body() ?: "Usuario creado con éxito")
                } else {
                    _state.value = Pair(false, response.errorBody()?.string() ?: "Error servidor")
                }

            } catch (e: Exception) {
                Log.e("Exceptions", "createUser", e)
                _state.value = Pair(false, "Error de conexión")
            }
        }
    }

    private fun proofEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun proofPassword(password: String, rePassword: String): Pair<Boolean, String> {

        if (password.length !in 6..16) {
            return Pair(false, "La contraseña debe tener entre 6 y 16 caracteres")
        }

        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        if (!password.matches(passwordPattern)) {
            return Pair(false, "Debe tener mayúscula, minúscula y número")
        }

        if (password != rePassword) {
            return Pair(false, "Las contraseñas no coinciden")
        }

        return Pair(true, "OK")
    }
}