package com.example.githubapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DisplayRepos : AppCompatActivity() {

//    private var layoutManager: RecyclerView.LayoutManager?=null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    private var resultapi: List<Repodetails>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_repos)

        val message= intent.getStringExtra(EXTRA_MESSAGE).toString()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
       // var adapter = RecyclerAdapter(mutableListOf())
       // recyclerView.adapter = adapter
        val repoApi = RetrofitHelper.getInstance().create(Gitservice::class.java)
        GlobalScope.launch {
            val result = repoApi.getrepos(organisation = message)
            resultapi=result.body()
            if (result != null) {
                runOnUiThread {
                    // Stuff that updates the UI
                    var adapter = RecyclerAdapter(resultapi)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@DisplayRepos)
                    adapter.setOnItemClickListener(object: RecyclerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val gson = Gson()
                            val message = resultapi?.get(position).toString()
                            val intent = Intent(this@DisplayRepos, DisplayRepoDetails::class.java).apply {
                                putExtra(EXTRA_MESSAGE,gson.toJson(resultapi?.get(position)))
                            }
                            startActivity(intent)
                        }
                    })
                }
            }
        }
    }
}