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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagVH =
        BagVH(ItemBagBinding.inflate(LayoutInflater.from(context)))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BagVH, position: Int) {
        holder.bind(list[position])
    }
}
class BagVH(private val ibb:ItemBagBinding) : RecyclerView.ViewHolder(ibb.root){
    private val bagModule: BagModule by inject(BagModule::class.java)

    fun bind(dishesItem: DishesItem){
        Picasso.get().load(dishesItem.image_url).into(ibb.imageDishes)
        ibb.nameDishesView.text = dishesItem.name
        ibb.priceView.text = "${dishesItem.price} ₽"
        ibb.weightView.text = " · ${dishesItem.weight}г"
        ibb.countView.text = bagModule.getDishesCount(dishesItem).toString()
        ibb.minusBtn.setOnClickListener{
            bagModule.remove(dishesItem)
            ibb.countView.text = bagModule.getDishesCount(dishesItem).toString()
        }
        ibb.plusBtn.setOnClickListener {
            bagModule.add(dishesItem)
            ibb.countView.text = bagModule.getDishesCount(dishesItem).toString()
        }
    }
}