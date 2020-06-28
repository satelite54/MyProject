package com.Vibrator.vibrationtab// Made by 김태헌

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var mAdView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 세로모드로 고정 (가로 세로 전환 시 메인 엑티비티가 계속 생성되서..)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main) // tablayout 출력

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest) // 애드몹 하단 배너 광고 출력
    }
}