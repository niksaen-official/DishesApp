package com.niksaen.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.test.DishesApplication
import com.niksaen.test.R
import com.niksaen.test.modules.BagModule
import com.niksaen.test.remote.dishes.DishesItem
import com.squareup.picasso.Picasso

class BagAdapter(val context: Context, private val list: ArrayList<DishesItem>,val bagModule: BagModule): RecyclerView.Adapter<BagVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagVH =
        BagVH(LayoutInflater.from(context).inflate(R.layout.item_bag,null))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BagVH, position: Int) {
        Picasso.get().load(list[position].image_url).into(holder.image)
        holder.text.text = list[position].name
        holder.price.text = "${list[position].price} ₽"
        holder.weight.text = " · ${list[position].weight}г"
        holder.countView.text = bagModule.getDishesCount(list[position])
            .toString()
        holder.minusBtn.setOnClickListener{
            bagModule.remove(list[position])
            bagModule.getDishesCount(list[position]).toString()
        }
        holder.plusBtn.setOnClickListener {
            bagModule.add(list[position])
            bagModule.getDishesCount(list[position]).toString()
        }
    }
}
class BagVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val image: ImageView =itemView.findViewById(R.id.imageDishes)
    val text: TextView =itemView.findViewById(R.id.nameDishesView)
    val price: TextView =itemView.findViewById(R.id.priceView)
    val weight: TextView = itemView.findViewById(R.id.weightView)
    val plusBtn: ImageView = itemView.findViewById(R.id.plusBtn)
    val countView: TextView = itemView.findViewById(R.id.countView)
    val minusBtn: ImageView = itemView.findViewById(R.id.minusBtn)
}