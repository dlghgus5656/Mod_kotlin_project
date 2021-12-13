package com.example.mod_kotlin_project

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class DIDpage : AppCompatActivity() {

    private lateinit var QRcode: ImageView
    private lateinit var QRdata: EditText
    private lateinit var GenerateQRcode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_didpage)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)

        ab.setDisplayHomeAsUpEnabled(true)

        QRcode = findViewById(R.id.QRcode)
        QRdata = findViewById(R.id.QRdata)
        GenerateQRcode = findViewById(R.id.GenerateQRcode)

        GenerateQRcode.setOnClickListener {
            val data = QRdata.text.toString().trim()

            if (data.isEmpty()) {
                Toast.makeText(this, "정보를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show()
            } else {
                val writer = QRCodeWriter()
                try {
                    var bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for (x in 0 until width) {
                        for (y in 0 until height) {
                            bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    QRcode.setImageBitmap(bmp)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }
}