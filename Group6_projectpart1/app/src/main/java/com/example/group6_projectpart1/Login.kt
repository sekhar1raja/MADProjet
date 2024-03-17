package com.example.group6_projectpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signup: TextView = findViewById(R.id.signup)
        val login: Button = findViewById(R.id.buttonLogin)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)


        signup.setOnClickListener{
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        if(currentUser != null) {
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val userEmail: String = email.text.toString().trim()
            val userPassword:String = password.text.toString().trim()

            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "User doesn't Exist", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}