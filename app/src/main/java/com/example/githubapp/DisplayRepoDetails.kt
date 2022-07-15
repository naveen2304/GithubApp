package com.example.githubapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson

class DisplayRepoDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_repo_details)
        val gson = Gson()
        val message=gson.fromJson(intent.getStringExtra(EXTRA_MESSAGE),Repodetails::class.java)
        Log.d("CLick",message.name)
        val Name: TextView=findViewById(R.id.Name)
        val Description: TextView=findViewById(R.id.Description)
        Name.text=message.name
        Description.text="Description: "+message.description
        val urlbutton=findViewById<Button>(R.id.url)
        val size: TextView=findViewById(R.id.size)
        val openissues: TextView=findViewById(R.id.open_issues)
        val default_branch: TextView= findViewById(R.id.default_branch)
        openissues.text="Open Issues: "+message.open_issues.toString()
        default_branch.text="Default Branch: "+message.default_branch
        size.text="Size: "+message.size.toString()
        urlbutton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(message.html_url)
            })
        }
    }
}