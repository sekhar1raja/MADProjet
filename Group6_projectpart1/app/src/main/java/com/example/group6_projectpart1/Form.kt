package com.example.group6_projectpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class Form : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        if(currentUser != null) {

            Handler().postDelayed({
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }else{
            Handler().postDelayed({
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}