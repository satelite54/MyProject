package com.project.codysimulator2 // Made by 김태헌

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var bottomNavigationView // 바텀 네비게이션 뷰
            : BottomNavigationView? = null
    private var fm: FragmentManager? = null
    private var ft: FragmentTransaction? = null
    private var frag1: cody_frag? = null
    private var frag2: Server_frag? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavi)
        bottomNavigationView?.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_today -> setFrag(0)
                R.id.action_add -> setFrag(1)
            }
            true
        }
        frag1 = cody_frag()
        frag2 = Server_frag()
        setFrag(0) // 첫 프래그먼트 화면 지정
        // Example of a call to a native method
    }

            // 프레그먼트 교체
    private fun setFrag(n: Int) {
        fm = supportFragmentManager
        ft = fm!!.beginTransaction()
        when (n) {
            0 -> {
                ft!!.replace(R.id.Main_Frame, cody_frag())
                ft!!.commit()
            }
            1 -> {
                ft!!.replace(R.id.Main_Frame, Server_frag())
                ft!!.commit()
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}


//package com.project.codysimulator2
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.fragment.app.FragmentManager
//import kotlinx.android.synthetic.main.activity_main.*
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Example of a call to a native method
//
//    }
//
//    /**
//     * A native method that is implemented by the 'native-lib' native library,
//     * which is packaged with this application.
//     */
//    external fun stringFromJNI(): String
//
//    companion object {
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
//}