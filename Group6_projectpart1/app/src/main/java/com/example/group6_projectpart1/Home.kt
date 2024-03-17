package com.example.group6_projectpart1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Home : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var jobAdapter: JobAdapter
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val logout: TextView = findViewById(R.id.logout)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        jobAdapter = JobAdapter(this,emptyList())
        recyclerView.adapter = jobAdapter

        databaseReference = FirebaseDatabase.getInstance().getReference("job")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jobList = mutableListOf<JobData>()
                for (dataSnapshot in snapshot.children) {
                    val job = dataSnapshot.getValue(JobData::class.java)
                    job?.let { jobList.add(it) }
                }
                jobAdapter.list = jobList
                jobAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        logout.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Ok") { dialog, which ->
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }

    }
}