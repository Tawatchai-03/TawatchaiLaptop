package com.example.laptoppricethanachai

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etProcessorSpeed = findViewById<EditText>(R.id.et_processor_speed)
        val etRamSize = findViewById<EditText>(R.id.et_ram_size)
        val etStorageCapacity = findViewById<EditText>(R.id.et_storage_capacity)
        val etScreenSize = findViewById<EditText>(R.id.et_screen_size)
        val etWeight = findViewById<EditText>(R.id.et_weight)
        val tvPrice = findViewById<TextView>(R.id.tv_price)
        val btnPredict = findViewById<Button>(R.id.btn_predict)

        btnPredict.setOnClickListener {
            val processorSpeed = etProcessorSpeed.text.toString().toDouble()
            val ramSize = etRamSize.text.toString().toInt()
            val storageCapacity = etStorageCapacity.text.toString().toInt()
            val screenSize = etScreenSize.text.toString().toDouble()
            val weight = etWeight.text.toString().toDouble()

            val apiService = RetrofitClient.getClient().create(ApiService::class.java)
            val call = apiService.getLaptopPrice(processorSpeed, ramSize, storageCapacity, screenSize, weight)

            call.enqueue(object : Callback<PriceResponse> {
                override fun onResponse(call: Call<PriceResponse>, response: Response<PriceResponse>) {
                    if (response.isSuccessful) {
                        val price = response.body()?.price
                        tvPrice.text = "ราคาที่ทำนาย: $price บาท"
                    } else {
                        Toast.makeText(this@MainActivity, "เกิดข้อผิดพลาดในการรับข้อมูล", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PriceResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "ไม่สามารถเชื่อมต่อกับเซิร์ฟเวอร์", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
