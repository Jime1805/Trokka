package com.iticbcn.trokka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapterFav(
    private val items: List<Producte>,
    private val onItemClick: (Producte) -> Unit
) : RecyclerView.Adapter<MyViewHolderFav>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolderFav {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cv_fav_obj, parent, false)
        return MyViewHolderFav(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size


}