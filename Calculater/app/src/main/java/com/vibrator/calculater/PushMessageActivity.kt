package com.vibrator.calculater

import android.app.Application
import android.app.NotificationManager
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.preference.PreferenceManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class MyAppsApplication : Application() {

    public override fun onCreate() {
        super.onCreate();

        val notificationBuilder: NotificationCompat.Builder

        // 안드로이드 8.0 이상일 경우 채널 생성
        //오레오 버전이상에서는 푸시 알림에 체널이 있어야함.. 그거에 대한 예외처리
        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
        var CHANNEL_ID = "channel"
        var notiId = 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        }
        else {
            notificationBuilder = NotificationCompat.Builder(this)
        }
        var TotalElectricCost: String = ""

        var TextMessage: String = ""
        TotalElectricCost = loadData(TextMessage) // 알림 내용 Load
        TextMessage = "이달의 예상 전기요금은 ${TotalElectricCost}원"

        var title = "이달의 전기요금"

        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(TextMessage)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setLargeIcon(bitmap)
        notificationBuilder.setShowWhen(true)
        notificationBuilder.color = ContextCompat.getColor(this, R.color.colorAccent)
        notificationBuilder.priority = NotificationCompat.PRIORITY_DEFAULT
        // 푸시 알람
        NotificationManagerCompat.from(this).notify(notiId, notificationBuilder.build())
    }
    fun loadData(content: String): String {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val kWhCost = pref.getInt("KEY_COST_ELECTRONIC", 0)
        var ElectricCost: String = content
        if(kWhCost != 0) {
            ElectricCost = kWhCost.toString()
        }
        return ElectricCost
    }
}
