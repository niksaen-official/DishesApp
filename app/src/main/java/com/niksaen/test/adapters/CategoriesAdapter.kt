package com.niksaen.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.test.R
import com.niksaen.test.databinding.ItemCategoriesBinding
import com.niksaen.test.remote.categories.CategoriesItem
import com.squareup.picasso.Picasso

class CategoriesAdapter(val context: Context,val list: ArrayList<CategoriesItem>): RecyclerView.Adapter<CategoriesVH>() {
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        val vh = CategoriesVH(ItemCategoriesBinding.inflate(LayoutInflater.from(context)))
        vh.onItemClickListener = onItemClickListener
        return vh
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.bind(list[position])
    }
}
class CategoriesVH(private val icb: ItemCategoriesBinding) : RecyclerView.ViewHolder(icb.root){
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    fun bind(categoriesItem: CategoriesItem){
        Picasso.get().load(categoriesItem.image_url).into(icb.imageBg)
        icb.text.text = categoriesItem.name
        icb.root.setOnClickListener {
            onItemClickListener?.onItemClick(null,icb.root,adapterPosition,1)
        }
    }
}