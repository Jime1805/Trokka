package com.iticbcn.trokka

import android.R

data class Producte (
    val id: Int,
    val titulo: String,
    val image_path: String,
    val user: String,
    val description: String,
    val aCanvi: String,
    var fav: Boolean
)