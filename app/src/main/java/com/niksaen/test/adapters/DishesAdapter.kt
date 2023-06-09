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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesVH =
        DishesVH(ItemDishesBinding.inflate(LayoutInflater.from(context)))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DishesVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(null,holder.itemView,position,1) }
    }
}
class DishesVH(idb: ItemDishesBinding) : RecyclerView.ViewHolder(idb.root){
    val name = idb.dishesName
    val image = idb.dishesImage
    fun bind(dishesItem: DishesItem){
        name.text=dishesItem.name
        Picasso.get().load(dishesItem.image_url).into(image)
    }
}