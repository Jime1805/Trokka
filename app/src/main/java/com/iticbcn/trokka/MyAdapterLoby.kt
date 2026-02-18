package com.iticbcn.trokka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapterLoby(
    private var items: MutableList<Producte>,
    private val onItemClick: (Producte) -> Unit
) : RecyclerView.Adapter<MyViewHolderFav>() {

    private val itemsFiltrat = mutableListOf<Producte>()

    init {
        itemsFiltrat.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderFav {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cv_fav_obj, parent, false)
        return MyViewHolderFav(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        val item = itemsFiltrat[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemsFiltrat.size

    fun updateItems(newItems: MutableList<Producte>) {
        items = newItems
        itemsFiltrat.clear()
        itemsFiltrat.addAll(newItems)
        notifyDataSetChanged()
    }

    fun filtrarNoFavs(text: String) {
        itemsFiltrat.clear()
        if (text.isEmpty()) {
            itemsFiltrat.addAll(items)
        } else {
            val textoLower = text.lowercase()
            items.forEach { producte ->
                if (
                    producte.titulo.lowercase().contains(textoLower) ||
                    producte.user.lowercase().contains(textoLower) ||
                    producte.description.lowercase().contains(textoLower) ||
                    producte.aCanvi.lowercase().contains(textoLower)
                ) {
                    itemsFiltrat.add(producte)
                }
            }
        }
        notifyDataSetChanged()
    }
}
