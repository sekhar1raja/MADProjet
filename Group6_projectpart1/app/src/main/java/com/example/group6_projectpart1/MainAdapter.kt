    package com.example.group6_projectpart1
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import android.widget.TextView
    import android.widget.ImageView
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import com.firebase.ui.database.FirebaseRecyclerAdapter
    import com.firebase.ui.database.FirebaseRecyclerOptions
    import com.google.firebase.storage.FirebaseStorage
    import com.google.firebase.storage.StorageReference

    class MainAdapter(options: FirebaseRecyclerOptions<job>)
        : FirebaseRecyclerAdapter<job, MainAdapter.MyViewHolder>(options) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return MyViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(
            holder: MyViewHolder,
            position: Int,
            model: job
        ) {
            val storageReference: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.PHOTO)
            Glide.with(holder.itemView.context)
                .load(storageReference)
                .into(holder.imageView)

            holder.title.text = model.TITLE
            holder.description.text = model.NAME

            // Debugging: Log data to check if it's being retrieved correctly
            Log.d("MainAdapter", "Title: ${model.TITLE}, Name: ${model.NAME}, Photo: ${model.PHOTO}")
        }

        // Inner ViewHolder class
        class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
            : RecyclerView.ViewHolder(inflater.inflate(R.layout.card, parent, false)) {

            val title: TextView = itemView.findViewById(R.id.title)
            val description: TextView = itemView.findViewById(R.id.description)
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
        }
    }
