package com.example.screamming

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.*
import java.net.Socket

open class MainActivity : AppCompatActivity() {

//    private var html = ""
//    private var mHandler: Handler? = null
//
//    var socket: Socket? = null
//    var networkReader: BufferedReader? = null
//    var networkWriter: BufferedWriter? = null
//
//    private val ip = "172.30.1.34" // IP
//
//    private val port = 21000 // PORT번호


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        checkRecordPermission()

//        mHandler = Handler();

//        checkUpdate.start()
    }
    override fun onStop() {
        super.onStop()
        try {
//            socket!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun checkRecordPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.RECORD_AUDIO),
                123
            )
        }
    }


//    private val checkUpdate: Thread = object : Thread() {
//        override fun run() {
//            try {
//                try {
//                    setSocket(ip, port);
//                } catch (e1: IOException) {
//                    e1.printStackTrace();
//                }
//                var line: String
//                Log.w("ChattingStart", "Start Thread")
//                while (true) {
//                    Log.w("Chatting is running", "chatting is running")
//                    line = networkReader!!.readLine()
//                    html = line
//                    mHandler!!.post(showUpdate)
//                }
//            } catch (e: Exception) {
//            }
//        }
//    }

//    private val showUpdate =
//        Runnable { Toast.makeText(this@MainActivity, "Coming word: $html", Toast.LENGTH_SHORT).show() }
//
//    @Throws(IOException::class)
//    open fun setSocket(ip: String?, port: Int) {
//        try {
//            socket = Socket(ip, port)
//            networkWriter = BufferedWriter(OutputStreamWriter(socket!!.getOutputStream()))
//            networkReader = BufferedReader(InputStreamReader(socket!!.getInputStream()))
//        } catch (e: IOException) {
//            System.out.println(e)
//            e.printStackTrace()
//        }
//    }
}