package com.niksaen.test.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.test.R
import com.niksaen.test.databinding.ItemDishesBinding
import com.niksaen.test.remote.dishes.DishesItem
import com.squareup.picasso.Picasso

class DishesAdapter(val context: Context, val list: ArrayList<DishesItem>): RecyclerView.Adapter<DishesVH>() {
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesVH {
        val vh = DishesVH(ItemDishesBinding.inflate(LayoutInflater.from(context)))
        vh.onItemClickListener = onItemClickListener
        return vh
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DishesVH, position: Int) {
        holder.bind(list[position])
    }
}
class DishesVH(private val idb: ItemDishesBinding) : RecyclerView.ViewHolder(idb.root){
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    fun bind(dishesItem: DishesItem){
        idb.dishesName.text=dishesItem.name
        Picasso.get().load(dishesItem.image_url).into(idb.dishesImage)
        idb.root.setOnClickListener { onItemClickListener?.onItemClick(null,idb.root,adapterPosition,1) }
    }
}