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

    fun deleteUserWithPassword(id: Long, password: String) {
        viewModelScope.launch {
            try {
                val responseUser = ClientAPI.UsuariAPI().getUserById(id)

                if (responseUser.isSuccessful){
                    val user = responseUser.body()

                    if (user != null && user.contrasenya == password) {
                        val deleteResponse = ClientAPI.UsuariAPI().deleteUserById(id)
                        if (deleteResponse.isSuccessful) {
                            _state.value = Pair(true, "Cuenta eliminada con éxito")
                        } else {
                            _state.value = Pair(false, "Error al eliminar la cuenta")
                        }
                    } else {
                        _state.value = Pair(false, "Contraseña incorrecta")
                    }
                }
            } catch (e: Exception) {
                Log.e("Exceptions", "deleteUser", e)
                _state.value = Pair(false, "Error de conexión")
            }
        }
    }

    fun changeUserName(oldName: String, newName: String) {

        viewModelScope.launch {
            try {

                val response = ClientAPI.UsuariAPI().putUserName(newName, oldName)

                if (response.isSuccessful) {
                    _state.value = Pair(true, "Nombre cambiado correctamente")
                } else {
                    _state.value = Pair(false, "Error al cambiar el nombre")
                }

            } catch (e: Exception) {
                Log.e("Exceptions", "changeUserName", e)
                _state.value = Pair(false, "Error de conexión")
            }
        }
    }

    fun changeUserPassword(nombre: String, password: String) {

        viewModelScope.launch {
            try {

                val response = ClientAPI.UsuariAPI().putUserPass(nombre, password)

                if (response.isSuccessful) {
                    _state.value = Pair(true, "Contraseña cambiada correctamente")
                } else {
                    _state.value = Pair(false, "Error al cambiar la contraseña")
                }

            } catch (e: Exception) {
                Log.e("Exceptions", "changeUserPassword", e)
                _state.value = Pair(false, "Error de conexión")
            }
        }
    }
}