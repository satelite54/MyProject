package com.example.screamming.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.example.screamming.Data
import com.example.screamming.R
import com.example.screamming.ui.home.tempData.EnterNickNameFlag
import kotlinx.android.synthetic.main.activity_enter_nick_name.*
import kotlinx.android.synthetic.main.fragment_home.*

class EnterNickName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_nick_name)
        EnterNickNameFlag = true
        Button3.setOnClickListener {
            var EnterNickName = sample_EditText.text.toString()
            var Data = Data
            Data.UserName = EnterNickName
        }
    }
}