package com.example.pestpro.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pestpro.R

class NewActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new2)

        val i = intent
        val a_Title= intent.getStringExtra("title1")
        val a_Label= intent.getStringExtra("label1")
        val aDate= intent.getStringExtra("date1")
        val aImage= intent.getStringExtra("image1")

    }
}