package com.iticbcn.trokka

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.io.path.Path

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    fun login(username: String, pass: String) {
        if (username == UserLogin.username) {
            _state.value = proofPassword(pass)
        }
    }

    /*private fun proofEmail(email: String): Boolean {
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS
        if (!emailPattern.matcher(email).matches()) {
            etCorreoReg.error = "Correo electrónico no valido"
            return false
        }
        return true
    }*/

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