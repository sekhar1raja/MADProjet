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

class Register : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mAuth = FirebaseAuth.getInstance()
        val login: TextView = findViewById(R.id.login)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val button: Button = findViewById(R.id.buttonLogin)

        button.setOnClickListener {
            val userEmail: String = email.text.toString().trim()
            val userPassword: String = password.text.toString().trim()

            val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")

            if(!emailRegex.matches(userEmail)){
                Toast.makeText(this,"Invalid Email", Toast.LENGTH_SHORT).show()
            }else if(userPassword.length < 7 ){
                Toast.makeText(this,"Invalid password", Toast.LENGTH_SHORT).show()
            }else{

                mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Success! Account is created.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, Login::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        login.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}