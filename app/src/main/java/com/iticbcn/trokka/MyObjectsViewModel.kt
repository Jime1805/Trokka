package com.iticbcn.trokka

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyObjectsViewModel : ViewModel() {

    private val _items = MutableLiveData<MutableList<Producte>>()
    val items: LiveData<MutableList<Producte>> = _items

    fun getUserItems(user: String) {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) {
                ClientAPI.ProducteAPI().getObjecteByUser(user)
            }

            if (items.isSuccessful) {
                val llista = items.body() ?: emptyList<Producte>()
                _items.value = llista.toMutableList()
            }
        }
    }

    fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            ClientAPI.ProducteAPI().deleteObjecteById(itemId)
        }
    }
}