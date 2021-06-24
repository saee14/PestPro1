package com.example.pestpro.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pestpro.R
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val intent = intent
        val aTitle= intent.getStringExtra("title")
        val aLabel= intent.getStringExtra("label")
        val aDate= intent.getStringExtra("date")
        val aImage= intent.getStringExtra("image")

        val new_arrayList = mutableListOf<Model_newActivity>()

        new_arrayList.add(
            Model_newActivity("Severity", "Moderately Dangerous, may kill the plant if not treated.",
            "Solution by \nDr. Amit Shukla", R.drawable.image_4,
            "A Brief",
            "Papaya mealybug became a pest when it invaded the Caribbean region. Since 1994 it has been recorded in 14 Caribbean countries. The pest has now spread to Pune area of Maharashtra also and is likely to be reported from other parts of the country as well.",
            "I found these in my farm today? I am not familiar with these pests. How dangerous are they? They seem to be feeding on the papaya leaves. I want to get rid of these and I would love your assistance in this matter.",
            "What do these pests do?",
            "Hi there, A mealybug that causes severe infestations of papaya and other hosts by feeding on fruits and stems, resulting in yellowing, stunting, deformed leaves, and fruit drop. Fruit becomes covered in crusts of mealybugs and waxy secretions, and the latter are common on leaves and stems, too. As the mealybug feeds on sap it expels liquid which falls onto leaves and attracts growth of dark fungi which prevent the leaves functioning normally.",
            "Generally, this is not a method for the control of this pest. However, practices, such as removal of host weeds and, importantly, control of ants associated with infestations, pruning low branches - to prevent foraging by ants - and those heavily infested, may help manage Papaya mealybug once established.",
            "Chemical Control",
            "The release of paraboloids has proved very successful wherever tried against this mealybug and, once released, insecticides use should be avoided.",
            "Was this review helpful? Rate us and leave a comment for us to improve our communication with you! Thank you!")
        )

        val myAdapter = MyAdapter_NewActivity(new_arrayList, this)

        RecyclerViewNewActivity.layoutManager = LinearLayoutManager(this)
        RecyclerViewNewActivity.adapter = myAdapter

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val apkpath = "Application link"
            intent.putExtra(Intent.EXTRA_TEXT, apkpath)
            startActivity(Intent.createChooser(intent, "ShareVia"))
        }
        return super.onOptionsItemSelected(item)
    }

}
