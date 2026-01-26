package com.iticbcn.trokka

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {
    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    fun register(
        username: String,
        email: String,
        password: String,
        rePassword: String
    ) {
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

        _state.value = Pair(true, "Registro correcto")
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
            return Pair(
                false,
                "La contraseña debe tener al menos una mayúscula, una minúscula y un número"
            )
        }

        if (password != rePassword) {
            return Pair(false, "Las contraseñas no coinciden")
        }

        return Pair(true, "Contraseña válida")
    }
}