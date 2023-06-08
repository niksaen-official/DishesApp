package com.niksaen.test.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import androidx.appcompat.app.AlertDialog
import com.niksaen.test.R
import com.niksaen.test.databinding.DialogDishesBinding
import com.niksaen.test.remote.dishes.DishesItem
import com.squareup.picasso.Picasso

class DishesDialog(context: Context, dishesItem: DishesItem) {
    private var dialog: AlertDialog? = null
    private val dialogUi:DialogDishesBinding
    init {
        dialogUi = DialogDishesBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        dialogUi.dishesNameView.text = dishesItem.name
        Picasso.get().load(dishesItem.image_url).into(dialogUi.dishesImageView)
        dialogUi.priceView.text =
            dialogUi.priceView.text.toString().replace("price", dishesItem.price.toString())
        dialogUi.weightView.text =
            dialogUi.weightView.text.toString().replace("weight", dishesItem.weight.toString())
        dialogUi.descriptionView.text = dishesItem.description
        dialogUi.closeBtn.setOnClickListener {
            dialog!!.dismiss()
        }
        builder.setCancelable(false)
        builder.setView(dialogUi.root)
        dialog = builder.create()
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.corner_bg_radius_15dp)
    }

    fun setAddToCartButtonListener(onClickListener: OnClickListener){
        dialogUi.addToCartBtn.setOnClickListener(onClickListener)
    }

    fun close(){
        dialog?.dismiss()
    }
    fun show() {
        dialog?.show()
    }
}