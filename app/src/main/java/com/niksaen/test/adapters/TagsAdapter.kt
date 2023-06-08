package com.niksaen.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.test.R

class TagsAdapter(val context: Context, val list: Array<String>): RecyclerView.Adapter<TagsVH>() {
    var onItemClickListener: OnItemClickListener? = null
    var buffView:TextView? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsVH = TagsVH(LayoutInflater.from(context).inflate(R.layout.item_tag,null))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TagsVH, position: Int) {
        holder.text.text=list[position]
        if(position == 0 && buffView == null){
            holder.text.setTextColor(context.getColor(R.color.white))
            holder.text.backgroundTintList = context.getColorStateList(R.color.navbar_active)
            buffView = holder.text
        }
        holder.itemView.setOnClickListener {
            holder.text.setTextColor(context.getColor(R.color.white))
            holder.text.backgroundTintList = context.getColorStateList(R.color.navbar_active)
            if(buffView!=null) {
                buffView!!.setTextColor(context.getColor(R.color.black))
                buffView!!.backgroundTintList = context.getColorStateList(R.color.tag_notchanged)
            }
            buffView = holder.text
            onItemClickListener?.onItemClick(null,holder.itemView,position,1)
        }
    }
}
class TagsVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val text=itemView.findViewById<TextView>(R.id.text)
}