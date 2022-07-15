package com.example.githubapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.intellij.lang.annotations.Language
import org.w3c.dom.Text

  class RecyclerAdapter(val reposfapi: List<Repodetails>?): RecyclerView.Adapter<RecyclerAdapter.ViewHolder> (){
//    private var repos=arrayOf("1","2","3")
//    private var descr=arrayOf("A","B","C")
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {

        fun onItemClick(position: Int){

        }

    }

     fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val indrepo = reposfapi?.get(position)
        if (indrepo != null) {
            holder.reponame.text = indrepo.name
            holder.description.text = indrepo.description
            holder.forks.text=indrepo.forks_count.toString()
            holder.watchers.text=indrepo.watchers_count.toString()
            if(indrepo.language!=null)
            holder.language.text= indrepo.language
            else
            holder.language.text="Not Specified"
        }
    }

    override fun getItemCount(): Int {
        return reposfapi?.size!!
    }

    inner class ViewHolder(itemView: View, listener: RecyclerAdapter.onItemClickListener): RecyclerView.ViewHolder(itemView)
    {
        var reponame: TextView
        var description: TextView
        var forks: TextView
        var watchers: TextView
        var language: TextView

        init{
            reponame= itemView.findViewById(R.id.repo_name)
            description= itemView.findViewById(R.id.description)
            itemView.setOnClickListener { listener.onItemClick(bindingAdapterPosition)
            }
            forks=itemView.findViewById(R.id.forks)
            watchers=itemView.findViewById(R.id.watchers)
            language=itemView.findViewById(R.id.language)
        }
    }

}