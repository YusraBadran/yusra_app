package com.yusra.yusra_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

class ChatActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        val etQuestion = findViewById<EditText>(R.id.etQuestion)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val txtResponse = findViewById<TextView>(R.id.txtResponse)


        btnSubmit.setOnClickListener{
            val question = etQuestion.text.toString()
            Toast.makeText(this,question,Toast.LENGTH_SHORT).show()
            getResponse(question){response ->
                runOnUiThread{
                    txtResponse.text=response
                }

            }
        }
          }

//        etQuestion.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEND) {
//
//                // setting response tv on below line.
//                txtResponse.text = "Please wait.."
//
//                // validating text
//                val question = etQuestion.text.toString().trim()
//                Toast.makeText(this, question, Toast.LENGTH_SHORT).show()
//                if (question.isNotEmpty()) {
//                    getResponse(question) { response ->
//                        runOnUiThread {
//                            txtResponse.text = response
//                        }
//                    }
//                }
//                return@OnEditorActionListener true
//            }
//            false
//        })
//    }
    fun getResponse(question: String, callback: (String) -> Unit){
        val apiKey = "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"
        val url = "https://api.openai.com/v1/completions"


        val requestBody ="""
        {

            "model": "text-advinci-003",
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0 
        }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization"," Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "=============================API Failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    Log.v("data","=================================="+body)
                }
                else{
                    Log.v("data","====================================empty")
                }

                val jsonObject=JSONObject(body)
                val jsonArray:JSONArray=jsonObject.getJSONArray("choices")
                val textResult=jsonArray.getJSONObject(0).getString("text")
                callback(textResult)
            }
        })
    }
}
