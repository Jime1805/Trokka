package com.iticbcn.trokka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapterFav(
    private var items: List<Producte>,
    private val onItemClick: (Producte) -> Unit
) : RecyclerView.Adapter<MyViewHolderFav>() {

    private val itemsFiltrat = mutableListOf<Producte>()

    init{
        for (item in items){
            if(item.fav){
                itemsFiltrat.add(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolderFav {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cv_fav_obj, parent, false)
        return MyViewHolderFav(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun filtrar(text: String) {
        itemsFiltrat.clear()
        if (text.isEmpty()) {
            for (item in items){
                if(item.fav){
                    itemsFiltrat.add(item)
                }
            }
        } else {
            val textoLower = text.lowercase()
            items.forEach { producte ->
                if((
                    producte.titulo.lowercase().contains(textoLower) ||
                    producte.user.lowercase().contains(textoLower) ||
                    producte.description.lowercase().contains(textoLower) ||
                    producte.aCanvi.lowercase().contains(textoLower) ) &&
                    producte.fav
                ) {
                    itemsFiltrat.add(producte)
                }
            }
        }
        notifyDataSetChanged()
    }
}