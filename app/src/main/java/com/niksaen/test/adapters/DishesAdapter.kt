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
import com.niksaen.test.remote.dishes.DishesItem
import com.squareup.picasso.Picasso

class DishesAdapter(val context: Context, val list: ArrayList<DishesItem>): RecyclerView.Adapter<DishesVH>() {
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesVH =
        DishesVH(LayoutInflater.from(context).inflate(R.layout.item_dishes,null))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DishesVH, position: Int) {
        holder.name.text=list[position].name
        Picasso.get().load(list[position].image_url).into(holder.image)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(null,holder.itemView,position,1)
        }
    }
}
class DishesVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val name=itemView.findViewById<TextView>(R.id.dishesName)
    val image=itemView.findViewById<ImageView>(R.id.dishesImage)
}