package com.iticbcn.trokka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapterMYObjects(
    private var items: List<Producte>,
    private val edit: (Producte) -> Unit,
    private val delete: (Producte) -> Unit
) : RecyclerView.Adapter<MyViewHolderMyObjects>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolderMyObjects {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cv_my_objects, parent, false)
        return MyViewHolderMyObjects(view, edit, delete)
    }

    override fun onBindViewHolder(holder: MyViewHolderMyObjects, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Producte>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}