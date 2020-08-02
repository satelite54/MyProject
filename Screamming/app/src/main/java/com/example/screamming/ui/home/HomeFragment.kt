package com.example.screamming.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.example.screamming.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var Screamming : SoundMeter = SoundMeter(activity!!.applicationContext)
        var ScreammingAdd : SoundMeter = SoundMeter(activity!!.applicationContext)
        var ScreammingAdd2 : SoundMeter = SoundMeter(activity!!.applicationContext)
        var ScreammingAdd3 : SoundMeter = SoundMeter(activity!!.applicationContext)
        var Amplitude : Double = 0.0
        val time: Long = 10000

        button.setOnClickListener {
            val lastTime = System.currentTimeMillis()
            var t = Timer()
            t.scheduleAtFixedRate(
                object : TimerTask() {
                    override fun run() {
                        var currentTime = System.currentTimeMillis()
                        if((currentTime-lastTime) > time) {
                            Screamming.stop()
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
                                var Thread = ThreadClass()
                                Thread.run()
                            }
                            in 60.1..80.0 -> {
                                var Thread = ThreadClass()
                                Thread.run()
                            }
                            in 80.1..100.0 -> {
                                var Thread = ThreadClass()
                                Thread.run()
                            }
                        }
                        MaxAmplitude(AmplitudeToDb(Amplitude))
                        text_home.text = AmplitudeToDb(Amplitude).toString()
                    }
                },
                0,
                500
            )
        }
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
}