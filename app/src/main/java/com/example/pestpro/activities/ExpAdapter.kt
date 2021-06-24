package com.example.pestpro.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pestpro.R


class ExpAdapter: RecyclerView.Adapter<ExpAdapter.ViewHolder>(){

    private val itemImages = intArrayOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4
        )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagenew1: ImageView = itemView.findViewById(R.id.imagenew1)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.exp_img, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imagenew1.setImageResource(itemImages[position])

        holder.itemView.setOnClickListener{
                v:View -> Toast.makeText(v.context,"Clicked on the item",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemImages.size
    }


}
