package com.vibrator.calculater

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 세로모드로 고정 (가로 세로 전환 시 메인 엑티비티가 계속 생성되서..)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main) // tablayout 출력

        val tv1 = TextView(this)
        val tv2 = TextView(this)

        tv1.text = "전기료"
        tv1.gravity = Gravity.CENTER
        tv1.textSize = 30f
        tabs_main.getTabAt(0)?.customView = tv1

        tv2.text = "수도료"
        tv2.gravity = Gravity.CENTER
        tv2.textSize = 30f
        tabs_main.getTabAt(1)?.customView = tv2
    }
}