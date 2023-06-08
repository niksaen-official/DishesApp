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
import com.niksaen.test.remote.categories.CategoriesItem
import com.squareup.picasso.Picasso

class CategoriesAdapter(val context: Context,val list: ArrayList<CategoriesItem>): RecyclerView.Adapter<CategoriesVH>() {
    var onItemClickListener: AdapterView.OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH =
        CategoriesVH(LayoutInflater.from(context).inflate(R.layout.item_categories,null))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        Picasso.get()
            .load(list[position].image_url)
            .into(holder.image)
        holder.text.text = list[position].name
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(null,holder.itemView,position,1)
        }
    }
}
class CategoriesVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val image: ImageView =itemView.findViewById(R.id.imageBg)
    val text: TextView =itemView.findViewById(R.id.text)
}