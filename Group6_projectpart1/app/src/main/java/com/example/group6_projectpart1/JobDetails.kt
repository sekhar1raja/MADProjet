package com.example.group6_projectpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class JobDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_job_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val title: TextView = findViewById(R.id.title)
        val image: ImageView = findViewById(R.id.imageView2)
        val full: TextView = findViewById(R.id.fulldesc)
        val desc: TextView = findViewById(R.id.desc)
        val submit: Button = findViewById(R.id.submit)

        val tit: String? = intent.getStringExtra("title")
        val des: String? = intent.getStringExtra("desc")
        val fulldes: String? = intent.getStringExtra("fulldesc")
        val imag: String? = intent.getStringExtra("image")
        val i: String = imag.toString()


        title.text = tit
        full.text = fulldes
        desc.text = des

        val storageRef: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(i)
        Glide.with(this)
            .load(storageRef)
            .into(image)

        submit.setOnClickListener {
            val intent = Intent(this,success::class.java)
            startActivity(intent)
            finish()
        }

    }
}