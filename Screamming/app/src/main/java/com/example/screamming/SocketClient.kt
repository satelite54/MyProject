package com.example.screamming

import android.util.Log
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.logging.Handler

internal class ClientThread : Thread() {
    override fun run() {
        val host = "localhost"
        val port = 5001

        var handler : Handler? = null

        try {
            val socket = Socket(host, port)
            val outputStream = ObjectOutputStream(socket.getOutputStream())
            outputStream.writeObject("안녕!")
            outputStream.flush()
            Log.d("ClientThread", "서버로 보냄.")
            val inputStream = ObjectInputStream(socket.getInputStream())
            val input = inputStream.readObject() as String // Object로 받아도 무방
            Log.d("ClientThread", "받은 데이터 : $input")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}