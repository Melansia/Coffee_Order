package com.example.coffeeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var tvOrderDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        tvOrderDetails = findViewById(R.id.tvOrderDetail)

        val intent = intent
        if (intent.hasExtra("order")) {
            val order = intent.getStringExtra("order")
            tvOrderDetails.text = order.toString()
        } else {
            val backToLogin = Intent(this, LoginActivity::class.java)
            startActivity(backToLogin)
        }
    }
}