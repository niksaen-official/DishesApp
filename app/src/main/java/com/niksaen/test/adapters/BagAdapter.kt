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
import com.niksaen.test.databinding.ItemBagBinding
import com.niksaen.test.modules.BagModule
import com.niksaen.test.remote.dishes.DishesItem
import com.squareup.picasso.Picasso
import org.koin.java.KoinJavaComponent.inject

class BagAdapter(val context: Context, private val list: ArrayList<DishesItem>): RecyclerView.Adapter<BagVH>() {
    private val bagModule: BagModule by inject(BagModule::class.java)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagVH {
        return BagVH(ItemBagBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BagVH, position: Int) {
        holder.bind(list[position],bagModule.getDishesCount(list[position]))
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
class BagVH(ibb:ItemBagBinding) : RecyclerView.ViewHolder(ibb.root){
    val image = ibb.imageDishes
    val text = ibb.nameDishesView
    val price = ibb.priceView
    val weight = ibb.weightView
    val plusBtn = ibb.plusBtn
    val countView = ibb.countView
    val minusBtn = ibb.minusBtn

    fun bind(dishesItem: DishesItem,count:Int){
        Picasso.get().load(dishesItem.image_url).into(image)
        text.text = dishesItem.name
        price.text = "${dishesItem.price} ₽"
        weight.text = " · ${dishesItem.weight}г"
        countView.text = count.toString()
    }
}