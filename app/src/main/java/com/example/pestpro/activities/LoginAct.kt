package com.example.pestpro.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pestpro.R
import com.example.pestpro.api.RetrofitClient
//import com.example.signup1.models.Response2
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextUserName1
import kotlinx.android.synthetic.main.activity_login.editTextPassword3
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        val FirstTime2 = preferences.getString("FirstTimeInstall", "")

        if (FirstTime2 == "Yes") {
            val intent = Intent(this@LoginAct, MainActivity_App::class.java)
            startActivity(intent)
        } else {
            val editor = preferences.edit()
            editor.putString("FirstTimeInstall", "Yes")
            editor.apply()
        }

        textViewRegister.setOnClickListener {
            startActivity(Intent(this@LoginAct, MainActivity::class.java))
        }

        buttonLogin.setOnClickListener {

            val username1 = editTextUserName1.text.toString().trim()
            val password1 = editTextPassword3.text.toString().trim()

            if (username1.isEmpty()) {
                editTextUserName1.error = "Username required"
                editTextUserName1.requestFocus()
                return@setOnClickListener
            }


            if (password1.isEmpty()) {
                editTextPassword3.error = "Password required"
                editTextPassword3.requestFocus()
                return@setOnClickListener
            }

            val username: RequestBody = RequestBody.create(MediaType.parse("text/plain"), username1)
            val password: RequestBody = RequestBody.create(MediaType.parse("text/plain"), password1)
            RetrofitClient.instance.userLogin(password, username)
                .enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(applicationContext, "Invalid User", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful) {

                            var responseData: JSONObject = JSONObject(response.body().toString())

                            if (responseData.getInt("Statuscode") == 200)
                            {
                                var data: JSONObject = (responseData.getJSONObject("data"))
                                var token = data.getString("token")
                                Toast.makeText(applicationContext,"Login Successful", Toast.LENGTH_LONG).show()
                                val intent=Intent(applicationContext,MainActivity_App::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)


                            }
                            else
                            {
                                Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_LONG).show()
                            }

                        } else {
                            Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_LONG).show()
                        }
                    }


                })

        }
    }
}



