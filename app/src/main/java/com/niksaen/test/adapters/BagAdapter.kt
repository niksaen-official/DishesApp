package com.niksaen.test.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.test.DishesApplication
import com.niksaen.test.R
import com.niksaen.test.remote.categories.CategoriesItem
import com.niksaen.test.remote.dishes.DishesItem
import com.squareup.picasso.Picasso

class BagAdapter(val context: Context, val list: ArrayList<DishesItem>): RecyclerView.Adapter<BagVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagVH =
        BagVH(LayoutInflater.from(context).inflate(R.layout.item_bag,null))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BagVH, position: Int) {
        Picasso.get().load(list[position].image_url).into(holder.image)
        holder.text.text = list[position].name
        holder.countView.text = (context.applicationContext as DishesApplication).bagModule?.getDishesCount(list[position]).toString()
        holder.minusBtn.setOnClickListener{
            (context.applicationContext as DishesApplication).bagModule?.remove(list[position])
            holder.countView.text = (context.applicationContext as DishesApplication).bagModule?.getDishesCount(list[position]).toString()
        }
        holder.plusBtn.setOnClickListener {
            (context.applicationContext as DishesApplication).bagModule?.add(list[position])
            holder.countView.text = (context.applicationContext as DishesApplication).bagModule?.getDishesCount(list[position]).toString()
        }
    }
}
class BagVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val image=itemView.findViewById<ImageView>(R.id.dishesImage)
    val text=itemView.findViewById<TextView>(R.id.dishesNameView)
    val price=itemView.findViewById<TextView>(R.id.priceView)
    val weight = itemView.findViewById<TextView>(R.id.weightView)
    val plusBtn = itemView.findViewById<ImageView>(R.id.plusBtn)
    val countView = itemView.findViewById<TextView>(R.id.countView)
    val minusBtn = itemView.findViewById<ImageView>(R.id.minusBtn)
}