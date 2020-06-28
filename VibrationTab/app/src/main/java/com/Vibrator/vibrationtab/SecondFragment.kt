package com.Vibrator.vibrationtab//Made by 김태헌

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.Vibrator.vibrationtab.FirstFragment.VibrationState.VibrationFlag
import com.Vibrator.vibrationtab.FirstFragment.VibrationState.vib
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_Apply4.setOnClickListener {

            // 기존에 진동이 있다면 Stop
            if(VibrationFlag) {
                vib.cancel()
                VibrationFlag = false
            }
            val strintensity: String = editTextNumber.text.toString()
            val strtempintensity: String =strintensity.trim()
            var nIntensity: Int = Integer.parseInt(strtempintensity)

            vib = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if(nIntensity > 255) {
                var t1 = Toast.makeText(
                    activity!!.applicationContext,
                    "진동 강도의 범위는 0 ~ 255로 설정해 주세요.",
                    Toast.LENGTH_SHORT
                )
                t1.show()
            }
            else if(nIntensity < 0) {
                var t1 = Toast.makeText(
                    activity!!.applicationContext,
                    "진동 강도의 범위는 0 ~ 255로 설정해 주세요.",
                    Toast.LENGTH_SHORT
                )
                t1.show()
            }
            else {
                val strfrequency: String = editTextNumber3.text.toString()
                val strtempfrequency: String =strfrequency.trim()
                var nfrequency: Int = Integer.parseInt(strtempfrequency)
                var Lfrequency: Long = nfrequency.toLong()
                // 예외 처리

                val timings = longArrayOf(100, Lfrequency, 0, Lfrequency) // 1초 진동 후 0.25초 대기
                val amplitudes = intArrayOf(0, nIntensity, 0, 0)

                vib.vibrate(VibrationEffect.createWaveform(timings, amplitudes, 1))
                VibrationFlag = true
            }
            }

        btn_Stop4.setOnClickListener {
            if(VibrationFlag) {
                vib.cancel()
                VibrationFlag = false
            }
        }
    }
}
