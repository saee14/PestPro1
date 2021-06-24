package com.example.pestpro.activities


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pestpro.R
import kotlinx.android.synthetic.main.activity_new_1_1.view.*

class MyAdapter_NewActivity(private val new_arrayList:MutableList<Model_newActivity>, val context: Context):
    RecyclerView.Adapter<MyAdapter_NewActivity.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(model: Model_newActivity){
            itemView.pest_titlen.text = model.titlen
            itemView.pest_des.text = model.des
            itemView.pest_sol.text = model.sol
            itemView.image1n.setImageResource(model.imgn)
            itemView.textview4.text = model.brief
            itemView.textview5.text = model.briefinfo
            itemView.textview6.text = model.Ques
            itemView.textview7.text = model.Ques1
            itemView.textview8.text = model.Ans1
            itemView.textview9.text = model.Ans2
            itemView.textview10.text = model.CC
            itemView.textview11.text = model.CCAns
            itemView.textview12.text = model.review
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val u = LayoutInflater.from(parent.context).inflate(R.layout.activity_new_1_1, parent, false)
        return ViewHolder(u)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(new_arrayList[position])
    }

    override fun getItemCount(): Int {
        return new_arrayList.size
    }

}