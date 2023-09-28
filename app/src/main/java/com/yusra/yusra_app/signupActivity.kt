package com.yusra.yusra_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class signupActivity : AppCompatActivity() {
    val tvback: TextView = findViewById(R.id.tvback)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


            tvback.setOnClickListener{
            val intent = Intent(applicationContext,loginActivity::class.java)
            startActivity(intent)
        }
    }
}