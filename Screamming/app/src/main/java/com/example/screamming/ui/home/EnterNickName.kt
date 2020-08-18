package com.example.screamming.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.screamming.Data
import com.example.screamming.R
import com.example.screamming.ui.home.tempData.EnterNickNameFlag
import kotlinx.android.synthetic.main.activity_enter_nick_name.*
import java.io.*
import java.net.Socket


class EnterNickName : AppCompatActivity() {

    var os: OutputStream? = null
    var oos: ObjectOutputStream? = null

    var socket: Socket? = null
    var networkReader: BufferedReader? = null
    var networkWriter: BufferedWriter? = null

    private val ip = "27.115.252.90:100" // IP

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
            if(socket!!.isConnected)
            socket!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    open fun setSocket(ip: String?, port: Int) {
        try {
            socket = Socket(ip, port)
        } catch (e: IOException) {
            System.out.println(e)
            e.printStackTrace()
        }
    }
    fun SendByte() {
        setSocket(ip, port)
        if (socket != null && socket!!.isConnected) {
//            os = socket?.getOutputStream()  //등록한 OutputStream을 ObjectOutputStream 방식으로 사용합니다.
//            oos = ObjectOutputStream(os) //byte[] 파일을 object 방식으로 통째로 전송합니다.
            var socket_out : BufferedWriter? = null
            try {
                socket_out = BufferedWriter(OutputStreamWriter(socket!!.getOutputStream(), "euc-kr"))
            } catch (e: IOException) {
                Toast.makeText(this,"서버 연결 오류", Toast.LENGTH_SHORT)
                e.printStackTrace()
            }
            var TestTemp : String = sample_EditText.toString()
            var TempByteArray = TestTemp.toByteArray()

            if (socket_out != null) {
                socket_out.write(TestTemp)
                socket_out.flush()
            }
            socket_out?.close()
        }
    }
}