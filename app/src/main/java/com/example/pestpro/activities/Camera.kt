package com.example.pestpro.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pestpro.R
import com.example.pestpro.api.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.thankyou_popup.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class Camera : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        submit_btn.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.thankyou_popup, null)
            val builder = AlertDialog.Builder(this)
                    .setView(view)
            val popup = builder.show()

            view.go_back.setOnClickListener {
                val intent = Intent(this, MainActivity_App::class.java)
                startActivity(intent)
            }

            val desc1 = edit_txt_cam.text.toString().trim()
            val user1 = edit_txt_username.text.toString().trim()
            if (user1.isEmpty()) {
                editTextUserName1.error = "Username required"
                editTextUserName1.requestFocus()
                return@setOnClickListener
            }
//            suspend fun upload(inputStream:InputStream) {
//                val trans_media = MultipartBody.Part.createFormData("pic", RequestBody.create(MediaType.parse("image/*") inputStream . readBytes ()))
            val img1_path = "C:\\Users\\prati\\AndroidStudioProjects\\PestPro1\\app\\src\\main\\res\\drawable\\add_photo.png"
            val trans_from: RequestBody = RequestBody.create(MediaType.parse("text/plain"), user1)
            val trans_msg: RequestBody = RequestBody.create(MediaType.parse("text/plain"), desc1)
            val device_udid: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "b9fcb97c-a523-4ff5-9c4d-729bbb7fb630")
            val device_type: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "android")

            if (img1_path != null) {
                val trans_media = File(img1_path)
                val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), trans_media)
                MultipartBody.Part.createFormData("trans_media", trans_media.getName(), requestFile)


                RetrofitClient.instance.imagepost(trans_media, trans_from, trans_msg, device_udid, device_type).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(applicationContext, "Invalid User", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) = if (response.isSuccessful) {
                        var responseData: JSONObject = JSONObject(response.body().toString())

                        if (responseData.getInt("Statuscode") == 200) {
                            var data: JSONObject = (responseData.getJSONObject("data"))

                            var insect_data = data.getString("insect_data")
                            //var uuid = data.getString("uuid")
                            //Toast.makeText(applicationContext, uuid, Toast.LENGTH_LONG).show()
                            Toast.makeText(applicationContext, insect_data, Toast.LENGTH_LONG).show()
                        } else {

                            Toast.makeText(applicationContext, "Error While Submitting Data", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    }

                })

            }
        }
        capture_image.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)

            val captureImage: Button? = null
            if (captureImage != null) {
                captureImage.isEnabled = false
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
            } else
                if (captureImage != null) {
                    captureImage.isEnabled = true
                }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (camera1.drawable.constantState == ContextCompat.getDrawable(this, R.drawable.plus)?.constantState) {
            val pic1: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            camera1.setImageBitmap(pic1)
        } else if (camera2.drawable.constantState == ContextCompat.getDrawable(this, R.drawable.plus)?.constantState) {
            val pic2: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            camera2.setImageBitmap(pic2)
        } else if (camera3.drawable.constantState == ContextCompat.getDrawable(this, R.drawable.plus)?.constantState) {
            val pic3: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            camera3.setImageBitmap(pic3)
        } else if (camera4.drawable.constantState == ContextCompat.getDrawable(this, R.drawable.plus)?.constantState) {
            val pic4: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            camera4.setImageBitmap(pic4)
        } else {
            val pic5: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            camera5.setImageBitmap(pic5)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val captureImage: Button? = null
            if (captureImage != null) {
                captureImage.isEnabled = true
            }
        }
    }

}



