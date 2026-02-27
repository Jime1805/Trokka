package com.iticbcn.trokka

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val _state = MutableLiveData<Pair<Boolean, String>>()
    val state: LiveData<Pair<Boolean, String>> = _state

    fun register(username: String, email: String, password: String, rePassword: String):Boolean {

        if (username.isBlank() || email.isBlank() || password.isBlank() || rePassword.isBlank()) {
            _state.value = Pair(false, "Todos los campos son obligatorios")
            return false
        }

        if (!proofEmail(email)) {
            _state.value = Pair(false, "Correo electrónico no válido")
            return false
        }

        val passwordResult = proofPassword(password, rePassword)
        if (!passwordResult.first) {
            _state.value = passwordResult
            return false
        }
        val funciona = createUser(username, email, password)
        Log.d("REGISTER", "Entra despues de funciona?")
        if (!funciona){
            return false
        }
        Log.d("REGISTER", "Entra aqui register")
        return true
    }

    private fun createUser(username: String, email: String, password: String): Boolean {
        var funciona: Boolean = false

        viewModelScope.launch {

            try {
                Log.d("REGISTER", "Entra aqui")

                val response = ClientAPI.UsuariAPI().postUsers(
                    UsuariRequest(username, email, password)
                )

                Log.d("REGISTER", "Code = ${response.code()}")
                Log.d("REGISTER", "Body = ${response.body()}")
                Log.e("REGISTER", "ErrorBody = ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    Log.d("REGISTER", "Entra en succesfull")
                    funciona = true;
                    _state.value = Pair(true, response.body() ?: "Registro correcto")
                } else {
                    Log.d("REGISTER", "Entra en errorBody")
                    _state.value = Pair(false, response.errorBody()?.string() ?: "Error servidor")
                }

            } catch (e: Exception) {
                funciona = false
                _state.value = Pair(false, "Error de conexión")
            }
        }
        return funciona
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