package com.example.pestpro.activities


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.pestpro.R


class AwaitingResults : RecyclerView.Adapter<AwaitingResults.ViewHolder>(){

    private val itemTitles = arrayOf("What is this that i found yesterday? ")
    private val itemDetails1 = arrayOf("Delivered On")
    private val itemDetails2 = arrayOf("14/11/2020")
    private val itemImages1 = intArrayOf(
        R.drawable.image_1
    )



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image1: ImageView
        var textTitle: TextView
        var textDes1: TextView
        var textDes2: TextView

        init {
            image1 = itemView.findViewById<ImageView>(R.id.image1)
            textTitle = itemView.findViewById(R.id.pest_title)
            textDes1 = itemView.findViewById(R.id.pest_label)
            textDes2 = itemView.findViewById(R.id.pest_date)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_awaiting_results, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text= itemTitles[position]
        holder.textDes1.text= itemDetails1[position]
        holder.textDes2.text= itemDetails2[position]
        holder.image1.setImageResource(itemImages1[position])


    }

    override fun getItemCount(): Int {
        return itemTitles.size
    }


}
