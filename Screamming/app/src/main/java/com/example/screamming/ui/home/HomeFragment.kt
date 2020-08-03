package com.example.screamming.ui.home

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.example.screamming.Data
import com.example.screamming.R
import com.example.screamming.ui.home.tempData.EnterNickNameFlag
import com.example.screamming.ui.home.tempData.FragmentChangeFlag
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

object tempData {
    var FragmentChangeFlag = false
    var EnterNickNameFlag = false
}

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var MaxAmplitude : Double = 0.0
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    var t = Timer()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var LoadMaxAmplitude = loadData()
        Data.UserName = ""
        Data.MaxAmplitude = LoadMaxAmplitude
        var Screamming : SoundMeter = SoundMeter(activity!!.applicationContext)
        var ScreammingAdd : SoundMeter = SoundMeter(activity!!.applicationContext)
        var ScreammingAdd2 : SoundMeter = SoundMeter(activity!!.applicationContext)
        var ScreammingAdd3 : SoundMeter = SoundMeter(activity!!.applicationContext)
        var Amplitude : Double = 0.0
        val time: Long = 10000

        button.setOnClickListener {
            if(EnterNickNameFlag == true) { // 타이머 재생성
                t = Timer()
                EnterNickNameFlag = false
            }
            val lastTime = System.currentTimeMillis()
            t.scheduleAtFixedRate(
                object : TimerTask() {
                    override fun run() {
                        var currentTime = System.currentTimeMillis()

                        if((currentTime-lastTime) > time) {
                            Screamming.stop()
                            if(FragmentChangeFlag != true)
                                text_home.text = "0"
                            t.cancel()
                        }
                        Screamming.start()
                        Amplitude = Screamming.amplitude
                        if(Amplitude > 32000) {
                            ScreammingAdd.start()
                            Amplitude += ScreammingAdd.amplitude
                            if(Amplitude > 64000) {
                                ScreammingAdd2.start()
                                Amplitude += ScreammingAdd2.amplitude
                                if(Amplitude > 96000) {
                                    ScreammingAdd3.start()
                                    Amplitude += ScreammingAdd3.amplitude
                                }
                            }
                        }
                        if(Amplitude == null)
                            Amplitude = 0.0
                        when(AmplitudeToDb(Amplitude)) {
                            in 40.0..60.0 -> {
                                if(FragmentChangeFlag != true) {
                                    var Thread = ThreadClass()
                                    Thread.run()
                                }
                            }
                            in 60.1..80.0 -> {
                                if(FragmentChangeFlag != true) {
                                    var Thread = ThreadClass()
                                    Thread.run()
                                }
                            }
                            in 80.1..100.0 -> {
                                if(FragmentChangeFlag != true) {
                                    var Thread = ThreadClass()
                                    Thread.run()
                                }
                            }
                        }
                        MaxAmplitude(AmplitudeToDb(Amplitude))
                        saveData(AmplitudeToDb(Amplitude))
                        var Data = Data
                        Data.MaxAmplitude = AmplitudeToDb(Amplitude)
                        if(FragmentChangeFlag != true)
                            text_home.text = AmplitudeToDb(Amplitude).toString()
                    }
                },
                0,
                500
            )
        }

        button2.setOnClickListener {
            if(MaxAmplitude > 0) {
                val intent = Intent(activity!!.applicationContext, EnterNickName::class.java)
                t.cancel();
                startActivity(intent)
            }
            else {
                var t1 = Toast.makeText(activity!!.applicationContext, "데시벨 측정을 먼저 해주세요!", Toast.LENGTH_SHORT)
                t1.show()
            }
        }

        button3.setOnClickListener {
            saveData(0.0)
            Data.UserName = ""
            Data.MaxAmplitude = 0.0
            text_home2.text = "0.0"
            MaxAmplitude = 0.0
        }
    }

    override fun onStop(){ // Fragment 전환이 되었을 때 Theard가 UI를 건들이면 죽어서 추가
        super.onStop();
        FragmentChangeFlag = true
        t.cancel();
    }

    override fun onResume() {
        super.onResume()
        t = Timer()
        FragmentChangeFlag = false
    }

    fun MaxAmplitude(Amplitude : Double) : Double {
        if(MaxAmplitude < Amplitude) {
            MaxAmplitude = Amplitude
            if(MaxAmplitude != null) {
                text_home2.text = MaxAmplitude.toString()
            }
            else {
                text_home2.text = "0"
            }
        }
        return MaxAmplitude
    }

    fun AmplitudeToDb(Amplitude: Double) : Double { // Amplitude 데시벨로 변환
        var db = 20 * kotlin.math.log10(Amplitude)
        return  db
    }

    inner class ThreadClass:Thread(){
        override fun run(){ // UI 스레드 생성 Glide 사용 시 메인 스레드에서만 UI 변경해야해서
            activity!!.runOnUiThread {
                val rabbit = view?.findViewById(R.id.imageView) as ImageView
                val gifImage = GlideDrawableImageViewTarget(rabbit)
                Glide.with(activity!!.applicationContext).load(R.drawable.screamming).into(gifImage)
            }
        }
    }
    
    fun saveData(MaxDB: Double) // Max 데시벨을 저장하기 위한 용도
    {
        var ConvertDB  = MaxDB.toFloat()
        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!.applicationContext)
        val editor = pref.edit()

        editor.putFloat("KEY_MAXDB", ConvertDB)
            .apply()
    }
    fun loadData(): Double {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!.applicationContext)

        val editText1 = pref.getFloat("KEY_MAXDB", 0f)
        if(editText1 >= 0) {
            text_home2.setText(editText1.toString())
            MaxAmplitude = editText1.toDouble()
        }
        return MaxAmplitude
    }

}