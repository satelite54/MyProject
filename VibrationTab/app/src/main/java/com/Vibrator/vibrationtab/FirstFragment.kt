package com.Vibrator.vibrationtab

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.Vibrator.vibrationtab.FirstFragment.VibrationState.VibrationFlag
import com.Vibrator.vibrationtab.FirstFragment.VibrationState.vib
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    object VibrationState {
        var VibrationFlag : Boolean = false
        lateinit var vib: Vibrator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroy() { // 뒤로가기 누를 시 호출
        super.onDestroy()
        if(VibrationFlag)
            vib.cancel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_Apply.setOnClickListener {
            //method
//            val timings1 = longArrayOf(100, 100, 0, 400, 0, 200, 0, 400)
//            val amplitudes1 = intArrayOf(0, 50, 0, 100, 0, 50, 0, 150) 되는 패턴
            // intArrayOf(0, 253, 0, 254, 0, 253, 0, 254)
            val timings1 = longArrayOf(100, 10000, 0, 10000) //계속 진동
            val amplitudes1 = intArrayOf(0, 255, 0, 255)
            val timings2 = longArrayOf(100, 1000, 0, 250) // 1초 진동 후 0.25초 대기
            val amplitudes2 = intArrayOf(0, 255, 0, 0)
            val timings3 = longArrayOf(100, 500, 0, 125) // 0.5초 진동 후 0.125초 대기
            val amplitudes3 = intArrayOf(0, 255, 0, 0)
            val timings4 = longArrayOf(100, 600, 0, 300, 0, 150, 0, 300) // 0.6초 진동 후 0.3초 대기 후 0.15초 진동 0.3초 대기
            val amplitudes4 = intArrayOf(0, 255, 0, 0, 0, 255, 0 , 0)
            val timings5 = longArrayOf(100, 200, 0, 200)  // 0.2초 진동 후 0.2초 대기
            val amplitudes5 = intArrayOf(0, 255, 0, 0)

            vib = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            when (radioGroup1.checkedRadioButtonId) {
                radioButton1.id -> {
                    vib.vibrate(VibrationEffect.createWaveform(timings1, amplitudes1, 1))
                    VibrationFlag = true
                }
                radioButton2.id -> {
                    vib.vibrate(VibrationEffect.createWaveform(timings2, amplitudes2, 1))
                    VibrationFlag = true
                }
                radioButton3.id -> {
                    vib.vibrate(VibrationEffect.createWaveform(timings3, amplitudes3, 1))
                    VibrationFlag = true
                }
                radioButton4.id -> {
                    vib.vibrate(VibrationEffect.createWaveform(timings4, amplitudes4, 1))
                    VibrationFlag = true
                }
                radioButton5.id -> {
                    vib.vibrate(VibrationEffect.createWaveform(timings5, amplitudes5, 1))
                    VibrationFlag = true
                }
            }
        }
        btn_Stop.setOnClickListener {
            if(VibrationFlag) {
                vib.cancel()
                VibrationFlag = false
            }
        }
    }
}