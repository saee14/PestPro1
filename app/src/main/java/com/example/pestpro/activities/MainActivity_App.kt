package com.example.pestpro.activities


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pestpro.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.exp_img.*
import java.util.*

class MainActivity_App : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AwaitingResults.ViewHolder>? = null
    private var adapter2: RecyclerView.Adapter<ExpAdapter.ViewHolder>? = null
    private var imageV: ImageView? = null
    private var buttonimg1: Button? = null
    private var expandud: Button? = null
    private var button2: Button? = null
    private lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    val arrayList = mutableListOf<Model>()
    val displayList = mutableListOf<Model>()
    lateinit var myAdapter: MyAdapter
    lateinit var preferences: SharedPreferences

    var sortbtn: Button? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)

        sortbtn = findViewById(R.id.sort_btn)

        sort_btn.setOnClickListener {
            sortDialog()
        }

        arrayList.add(Model("Armyworm", "Delivered: 20/01/2021", "Result: 14/11/2000", R.drawable.image_1))
        arrayList.add(Model("Aphids", "Delivered: 20/01/2021", "Result: 14/11/2000", R.drawable.image_2))
        arrayList.add(Model("Cutworm", "Delivered: 20/01/2021", "Result: 14/11/2000", R.drawable.image_3))
        arrayList.add(Model("MeatBug", "Delivered: 20/01/2021", "Result: 14/11/2000", R.drawable.image_4))

        displayList.addAll(arrayList)

        myAdapter = MyAdapter(displayList, this)

        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.adapter = myAdapter

        preferences = getSharedPreferences("My_pref", Context.MODE_PRIVATE)
        val msortSetting = preferences.getString("Sort", "Ascending")

        if (msortSetting == "Ascending"){
            sortAscending(myAdapter)

        }
        else if(msortSetting == "Descending"){
            sortDescending(myAdapter)

        }

        setSupportActionBar(findViewById(R.id.toolbar1))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = AwaitingResults()
        recyclerView.adapter = adapter

        layoutManager = LinearLayoutManager(this)
        recyclerView2.layoutManager = layoutManager
        adapter2 = ExpAdapter()
        recyclerView2.adapter = adapter2
        (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        var expundud = findViewById<Button>(R.id.expandud)
        var button2 = findViewById<Button>(R.id.button2)

        expandud?.setOnClickListener {
            if (Card_View1.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(Card_View1, AutoTransition())
                Card_View1.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(Card_View1, AutoTransition())
                Card_View1.visibility = View.GONE

            }
        }

        button2?.setOnClickListener {
            if (recyclerView.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(recyclerView, AutoTransition())
                recyclerView.visibility = View.VISIBLE
                // recyclerView.text = "AWAITING RESULTS ^"

            } else {
                TransitionManager.beginDelayedTransition(recyclerView, AutoTransition())
                recyclerView.visibility = View.GONE
                // recyclerView.text = "AWAITING RESULTS"
            }
        }


        buttonimg1 = findViewById<Button>(R.id.buttonc1)
        buttonimg1?.setOnClickListener {
            imageV?.setImageResource(R.drawable.image_1)
        }

        toolbar = findViewById(R.id.toolbar1)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = androidx.appcompat.app.ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    private fun sortDescending(myAdapter: MyAdapter) {
        displayList.sortWith(compareBy { it.title })
        displayList.reverse()
        myAdapter.notifyDataSetChanged()
    }

    private fun sortAscending(myAdapter: MyAdapter) {
        displayList.sortWith(compareBy { it.title })
        myAdapter.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Home -> {
                val intent = Intent(this, MainActivity_App::class.java)
                startActivity(intent)
            }
            R.id.Profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.setting -> {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
            }
            R.id.About_Us -> {
                val intent = Intent(this, AboutUs::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                val intent = Intent(this, LoginAct::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Loged Out Successfully", Toast.LENGTH_LONG).show()
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar1, menu)
//        menuInflater.inflate(R.menu.share, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if (menuItem !=  null){
            val searchView = menuItem.actionView as SearchView


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        arrayList.forEach{
                            if (it.title.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        recyclerView1.adapter!!.notifyDataSetChanged()

                    }
                    else{
                        displayList.clear()
                        displayList.addAll(arrayList)
                        recyclerView1.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.camera) {
            val intent = Intent(this, Camera::class.java)
            startActivity(intent)
        }

//        if (item.itemId == R.id.sorting111){
//            sortDialog()
//        }

//        if (item.itemId == R.id.share) {
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.type = "text/plain"
//            val apkpath = "Application link"
//            intent.putExtra(Intent.EXTRA_TEXT, apkpath)
//            startActivity(Intent.createChooser(intent, "ShareVia"))
//        }
        return super.onOptionsItemSelected(item)


    }

    private fun sortDialog() {
        val options = arrayOf("Ascending", "Descending")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sort By")
        builder.setIcon(R.drawable.ic_sort)

        builder.setItems(options){dialog, which ->
            if (which == 0){
                val editor : SharedPreferences.Editor = preferences.edit()
                editor.putString("Sort", "Ascending")
                editor.apply()
                sortAscending(myAdapter)
                Toast.makeText(this@MainActivity_App, "Ascending Order", Toast.LENGTH_SHORT).show()
            }
            if (which == 1){
                val editor : SharedPreferences.Editor = preferences.edit()
                editor.putString("Sort", "Descending")
                editor.apply()
                sortDescending(myAdapter)
                Toast.makeText(this@MainActivity_App, "Descending Order", Toast.LENGTH_SHORT).show()

            }
        }

    }

}

