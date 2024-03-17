package com.example.group6_projectpart1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class JobAdapter(private val context: Context, public var list: List<JobData>):RecyclerView.Adapter<JobAdapter.JobHolder>(){
    class JobHolder(inflater: LayoutInflater,parent: ViewGroup)
        :RecyclerView.ViewHolder(inflater.inflate(R.layout.card,parent,false)){

        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):JobHolder {
        val inflater = LayoutInflater.from(parent.context)
        return JobHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: JobAdapter.JobHolder, position: Int) {
        val job: JobData = list[position]

        holder.title.text = job.Title
        holder.description.text = job.Description

        val storageRef: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(job.Image)
        Glide.with(context)
            .load(storageRef)
            .into(holder.imageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(context,JobDetails::class.java)
            intent.putExtra("title", job.Title)
            intent.putExtra("desc", job.Description)
            intent.putExtra("fulldesc", job.FullDesc)
            intent.putExtra("image", job.Image)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int  = list.size
}
