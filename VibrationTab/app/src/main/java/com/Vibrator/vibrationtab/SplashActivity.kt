package com.Vibrator.vibrationtab//Made by 김태헌

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class SplashActivity : AppCompatActivity() {

    val SPLASH_VIEW_TIME: Long = 2000 //5초간 스플래시 화면을 보여줌 (ms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var mInterstitialAd: InterstitialAd
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.fullSize_ad_unit_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("asd", "The interstitial wasn't loaded yet.")
                }
            }
            override fun onAdFailedToLoad(i: Int) {
                super.onAdFailedToLoad(i)
                AdViewDelay(SPLASH_VIEW_TIME)
                Log.d("ad", "onAdFailedToLoad : $i")
            }

            override fun onAdClosed() {
                AdViewDelay(SPLASH_VIEW_TIME)
                // Code to be executed when the interstitial ad is closed.
            }
        }
    }

    fun AdViewDelay(value : Long) { // Intent 메소드가 onAdClosed에서 사용이 안되서 메소드를 새로 작성함
        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, value)
    }
}