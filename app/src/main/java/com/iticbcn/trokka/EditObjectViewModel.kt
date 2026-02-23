package com.iticbcn.trokka

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditObjectViewModel : ViewModel() {
    private val _item = MutableLiveData<Producte>()
    val item: LiveData<Producte> = _item
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getItem(itemId: Int?) {
        viewModelScope.launch {
            if (itemId == null) {
                _error.value = "Error, El id del producte es null."
            } else {
                val response = ProducteAPI.API().getObjecteById(itemId)

                if (response.isSuccessful) {
                    val producte = response.body() ?: DataSource.producteVuit
                    _item.value = producte
                    _error.value = null
                } else {
                    _error.value = "Error ${response.code()}: ${response.message()}"
                    Log.e("EditObjectViewModel", "Error ${response.code()}: ${response.message()}")
                }
            }
        }
    }
}