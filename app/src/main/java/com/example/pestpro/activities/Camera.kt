package com.example.pestpro.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pestpro.R
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.thankyou_popup.view.*

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
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val captureImage: Button? = null
            if (captureImage != null) {
                captureImage.isEnabled = true
            }
        }
    }
}
