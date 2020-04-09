package com.vibrator.calculater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if (intent.hasExtra("WaterCost")) {
            val Cost = intent.getStringExtra("WaterCost")
            textViewkWhCost.text = "$Cost"
            textView4.text = "수도"
            imageView.visibility = View.GONE
        }

        if (intent.hasExtra("kWhCost")) {
            val Cost = intent.getStringExtra("kWhCost")
            textViewkWhCost.text = "$Cost"
            textView4.text = "전기"
            imageView2.visibility = View.GONE
        }

        button123.setOnClickListener() {
            val nextIntent = Intent(this, Main3Activity::class.java)
            startActivity(nextIntent)
        }
    }
}
