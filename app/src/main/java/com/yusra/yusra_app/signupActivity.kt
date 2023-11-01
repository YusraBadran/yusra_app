package com.yusra.yusra_app

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class signupActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)


        val fname : EditText = findViewById(R.id.f_name)
        val lname : EditText = findViewById(R.id.l_name)
        val username : EditText = findViewById(R.id.user_name)
        val email : EditText = findViewById(R.id._email)
        val password : EditText = findViewById(R.id._password)
        val btnSignup :Button = findViewById(R.id.btnSignup)

        


        val adb = AppDB(applicationContext)
        adb.open()



        btnSignup.setOnClickListener{
//            val aldb = AlertDialog.Builder(applicationContext)
            val Fname = fname.text.toString()
            val Lname = lname.text.toString()
            val Username = username.text.toString()
            val Email = email.text.toString()
            val Password = password.text.toString()


            adb.insertUsers(Fname,Lname,Username,Email,Password)


        }

    }
}
