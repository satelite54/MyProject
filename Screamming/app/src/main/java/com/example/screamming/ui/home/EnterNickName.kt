package com.example.screamming.ui.home

import android.R.id.edit
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.screamming.Data
import com.example.screamming.R
import com.example.screamming.ui.home.tempData.EnterNickNameFlag
import kotlinx.android.synthetic.main.activity_enter_nick_name.*
import java.io.*
import java.net.Socket


class EnterNickName : AppCompatActivity() {

    var socket: Socket? = null
    var networkReader: BufferedReader? = null
    var networkWriter: BufferedWriter? = null

    private val ip = "172.30.1.34" // IP

    private val port = 21000 // PORT번호

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_nick_name)
        EnterNickNameFlag = true
        Button3.setOnClickListener {
            var EnterNickName = sample_EditText.text.toString()
            var Data = Data
            Data.UserName = EnterNickName
            var SocketSendByte = TheardClass()
            if(SocketSendByte.state == Thread.State.NEW)
                SocketSendByte.start()
        }
    }
//    private val SocketSendByte: Thread = object : Thread() {
    inner class TheardClass:Thread() {
        override fun run() {
            SendByte()
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            socket!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    open fun setSocket(ip: String?, port: Int) {
        try {
            socket = Socket(ip, port)
            networkWriter = BufferedWriter(OutputStreamWriter(socket!!.getOutputStream()))
            networkReader = BufferedReader(InputStreamReader(socket!!.getInputStream()))
        } catch (e: IOException) {
            System.out.println(e)
            e.printStackTrace()
        }
    }
    fun SendByte() {
        setSocket(ip, port)
        if (socket != null && socket!!.isConnected) {
            val os: OutputStream? =
                socket?.getOutputStream()  //등록한 OutputStream을 ObjectOutputStream 방식으로 사용합니다.
            val oos = ObjectOutputStream(os) //byte[] 파일을 object 방식으로 통째로 전송합니다.
            var TestTemp : String = "      "
            var TempByteArray = TestTemp.toByteArray()
            oos.write(TempByteArray)

            oos.close()
            if (os != null) {
                os.close()
            }
        }
    }
}