package com.iticbcn.trokka

data class Producte(
    val id: Int,
    val titulo: String,
    val image_path: String,
    val user: String,
    val description: String,
    val aCanvi: String,
    var fav: Boolean
)

data class ProducteSend(
    var titulo: String,
    var user: String,
    var description: String,
    var aCanvi: String,
    var isFav: Boolean
)