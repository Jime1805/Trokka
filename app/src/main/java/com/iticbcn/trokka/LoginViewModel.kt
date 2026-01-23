package com.iticbcn.trokka

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.io.path.Path

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    fun login(username: String, pass: String) {
        if (username == UserLogin.username || username == UserLogin.email) {
            _state.value = proofPassword(pass)
        } else {
            _state.value = Pair(false, "Nom d'usuari o email incorrectes.")
        }
    }

    private fun proofPassword(password: String): Pair<Boolean, String> {
        if (password.length !in 6..16) {
            return Pair(false, "La contraseña debe tener entre 6 y 16 caracteres")
        }

        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        if (!password.matches(passwordPattern)) {
            return Pair(
                false,
                "La contraseña debe de tener al menos una mayúscula, una minúscula y un número"
            )
        }

        return Pair(true, "Inici de sessió correcte.")
    }
}