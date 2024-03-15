package com.example.group6_projectpart1

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class MainAdapter(options: FirebaseRecyclerOptions<job>)
    : FirebaseRecyclerAdapter<job, MainAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(
        holder: MainAdapter.MyViewHolder,
        position: Int,
        model: job
    ) {

        holder.title.text = model.Title
        holder.description.text = model.name


    }


    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.card,parent,false)){

        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)


    }

}