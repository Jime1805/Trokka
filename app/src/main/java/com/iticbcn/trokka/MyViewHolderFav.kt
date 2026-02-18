package com.iticbcn.trokka

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolderFav(
    itemView: View,
    private val onItemClick: (Producte) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvUsuari: TextView = itemView.findViewById(R.id.tvUsuari)
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitol)
    private val tvDescripcio: TextView = itemView.findViewById(R.id.tvDescripcio)
    private val img: ImageView = itemView.findViewById(R.id.img)
    private val imgCorazon: ImageView = itemView.findViewById(R.id.imgFav)

    fun bind(producte: Producte) {
        tvUsuari.text = producte.user
        tvTitle.text = producte.titulo
        tvDescripcio.text = producte.description
        img.contentDescription = producte.image_path
        if(producte.fav){
            imgCorazon.setImageResource(R.drawable.ic_favorito_lleno)
        }
        else{
            imgCorazon.setImageResource(R.drawable.ic_favorito_vacio)
        }

        imgCorazon.setOnClickListener {
            onItemClick(producte)
        }
    }
}