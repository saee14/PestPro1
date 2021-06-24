package com.example.pestpro.activities


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pestpro.R
import kotlinx.android.synthetic.main.previous_results.view.*

class MyAdapter(val arrayList: MutableList<Model>, val context: Context):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(model: Model){
            itemView.pest_title.text = model.title
            itemView.pest_label.text = model.label
            itemView.pest_date.text = model.date
            itemView.image1.setImageResource(model.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.previous_results, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener{
            val model = arrayList[position]
            val gTitle : String = model.title
            val gLabel : String = model.label
            val gDate : String = model.date
            val gImag : Int = model.img

            val intent = Intent(context, NewActivity::class.java)
            intent.putExtra("title", gTitle)
            intent.putExtra("label", gLabel)
            intent.putExtra("date", gDate)
            intent.putExtra("image", gImag)

            context.startActivity(intent)


        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}