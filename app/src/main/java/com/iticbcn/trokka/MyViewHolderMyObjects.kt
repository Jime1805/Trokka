package com.iticbcn.trokka

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolderMyObjects(
    itemView: View,
    private val onItemClick: (Producte) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitol)
    private val tvDescripcio: TextView = itemView.findViewById(R.id.tvDescripcio)
    private val img: ImageView = itemView.findViewById(R.id.img)
    private val imgLlapis: ImageView = itemView.findViewById(R.id.imgLlapis)
    private val imgBin: ImageView = itemView.findViewById(R.id.imgBasura)

    fun bind(producte: Producte) {
        tvTitle.text = producte.titol
        tvDescripcio.text = producte.descripcion
        img.contentDescription = producte.img

        imgLlapis.setOnClickListener {
            onItemClick(producte)
        }

        imgBin.setOnClickListener {
            onItemClick(producte)
        }
    }
}