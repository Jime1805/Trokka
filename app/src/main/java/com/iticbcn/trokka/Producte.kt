package com.iticbcn.trokka

data class Producte(
    val id: Int,
    val titulo: String,
    val image_path: String,
    val user: String,
    val description: String,
    val acanvi: String,
    var fav: Boolean
)

data class ProducteSend(
    var titulo: String,
    var user: String,
    var description: String,
    var acanvi: String,
    var isFav: Boolean
)