package com.yusra.yusra_app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val edusername: EditText = findViewById(R.id.username)
        val edpassword: EditText = findViewById(R.id.password)
        val btnLogin: Button = findViewById(R.id.btnSignup)
        val textwar: TextView = findViewById(R.id.textwar)
        val signUpText: TextView = findViewById(R.id.signUpText)


        val adb = AppDB(applicationContext)
        adb.open()


        signUpText.setOnClickListener {
            val intent = Intent(applicationContext,signupActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val pass = edpassword.text.toString()
            if (pass == "123"){
                val intent = Intent(applicationContext,ChatActivity::class.java)
                startActivity(intent)
                textwar.setText("")
            }else{
                textwar.text = "Wrong Password"
                textwar.setTextColor(Color.RED)
                edpassword.setText("")
                edpassword.requestFocus()
            }
        }
    }

    private fun LoginDB(username:String,password:String){

    }
}

