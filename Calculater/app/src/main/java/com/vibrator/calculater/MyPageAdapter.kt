package com.vibrator.calculater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter


class MyPagerAdapter(fm : androidx.fragment.app.FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) { //switch()문과 동일하다.
            0 -> {FirstFragment()}
            else -> {SecondFragment()}
        }
    }

    override fun getCount(): Int {
        return 2 //2개니깐
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "전기료"
            else -> "수도료"
        }
    }
}