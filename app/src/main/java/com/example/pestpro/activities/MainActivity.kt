package com.example.pestpro.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.pestpro.R
import com.example.pestpro.api.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.editTextEmail
import kotlinx.android.synthetic.main.activity_main.editTextPassword1
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginAct::class.java
            ))
        }

        buttonSignUp.setOnClickListener {

            val usernameText = editTextUserName.text.toString().trim()
            val password1Text = editTextPassword1.text.toString().trim()
            val password2Text = editTextPassword2.text.toString().trim()
            val emailText = editTextEmail.text.toString().trim()


            if (usernameText.isEmpty()) {
                editTextUserName.error = "Username required"
                editTextUserName.requestFocus()
                return@setOnClickListener
            }


            if (password1Text.isEmpty()) {
                editTextPassword1.error = "Password required"
                editTextPassword1.requestFocus()
                return@setOnClickListener
            }

            if (password2Text.isEmpty()) {
                editTextPassword2.error = "Password required"
                editTextPassword2.requestFocus()
                return@setOnClickListener
            }

            if (emailText.isEmpty()) {
                editTextEmail.error = "Email Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }

            val email:RequestBody = RequestBody.create(MediaType.parse("text/plain"),emailText)
            val password1:RequestBody = RequestBody.create(MediaType.parse("text/plain"),password1Text)
            val password2:RequestBody = RequestBody.create(MediaType.parse("text/plain"), password2Text)
            val username:RequestBody = RequestBody.create(MediaType.parse("text/plain"),usernameText)

            RetrofitClient.instance.createUser(email,password1,password2,username)
                .enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(applicationContext, "Invalid User", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful) {
                            var responseData:JSONObject = JSONObject(response.body().toString())

                            if (responseData.getInt("Statuscode")==200) {
                                var data:JSONObject = (responseData.getJSONObject("data"))
                                var token=data.getString("token")
                                Toast.makeText(applicationContext, "Success!", Toast.LENGTH_LONG).show()
                            } else {

                                Toast.makeText(applicationContext, "Registration Fail, Please Try again.", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(applicationContext, "We are facing some problem in your registration, please try again later.", Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }
    }


}





