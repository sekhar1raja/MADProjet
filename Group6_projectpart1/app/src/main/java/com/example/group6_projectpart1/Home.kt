package com.example.group6_projectpart1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Job

class Home : AppCompatActivity() {
    private var adapter: MainAdapter? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)



        val query = FirebaseDatabase.getInstance().reference.child("jobs")
        val options = FirebaseRecyclerOptions.Builder<job>().setQuery(query,job::class.java).build()
        adapter = MainAdapter(options)
        val mainRecycler: RecyclerView = findViewById(R.id.mainRecycler)

        mainRecycler.layoutManager = LinearLayoutManager(this)
        mainRecycler.adapter = adapter;

    }
    override fun onDestroy() {
        super.onDestroy()
        adapter?.stopListening()
    }
}