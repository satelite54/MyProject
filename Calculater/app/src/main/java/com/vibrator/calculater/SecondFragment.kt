package com.vibrator.calculater

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_second.*
import java.lang.NumberFormatException


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

    override fun onActivityCreated(savedInstanceState: Bundle?) { // 여기가 실행코드
        super.onActivityCreated(savedInstanceState)

        editText_one_one.isClickable = false
        editText_one_one.isFocusable = false

        loadData()
        button_water_cost.setOnClickListener() {
            var editText_radious_cost_temp: Int
            var editText_radious_temp: Int
            var editText_total_use_temp: Int
            var total_use: Int = 0
            var radious_cost: Int = 0
            var radious: Int = 0
            var CalculaterfristSeccess = false
            var CalculaterSecondSeccess = false
            var CalculaterThridSeccess = false
            var CalculaterfourSeccess = false
            var firstLineError = false
            var totalWaterCost = 0

            try {
                editText_radious_temp  = editText_radious.text.toString().toInt()
                radious = editText_radious_temp
            } catch (e: NumberFormatException) {
                firstLineError = true
                var t1 = Toast.makeText(activity!!.applicationContext, "구경을 입력하세요", Toast.LENGTH_SHORT)
                t1.show()
            }

            try {
                editText_radious_cost_temp  = editText_radious_cost.text.toString().toInt()
                radious_cost = editText_radious_cost_temp
            } catch (e: NumberFormatException) {
                firstLineError = true
                var t1 = Toast.makeText(activity!!.applicationContext, "구경 기본 요금을 입력하세요", Toast.LENGTH_SHORT)
                t1.show()
            }
            try {
                editText_total_use_temp  = editText_total_use.text.toString().toInt()
                total_use = editText_total_use_temp
            } catch (e: NumberFormatException) {
                firstLineError = true
                var t1 = Toast.makeText(activity!!.applicationContext, "총 사용량을 입력해 주세요", Toast.LENGTH_SHORT)
                t1.show()
            }

            if(editText_one_two.text.toString().toInt() == 0) {
                firstLineError = true
                var t1 = Toast.makeText(activity!!.applicationContext, "1단계의 사용범위는 반드시 입력해 주셔야 합니다", Toast.LENGTH_SHORT)
                t1.show()
            }
            else if(editText_up_one_one.text.toString().toInt() == 0) {
                firstLineError = true

                var t1 = Toast.makeText(activity!!.applicationContext, "1단계의 상수도 금액은 반드시 입력해 주셔야 합니다", Toast.LENGTH_SHORT)
                t1.show()
            }
            else if(editText_down_one_two.text.toString().toInt() == 0) {
                firstLineError = true

                var t1 = Toast.makeText(activity!!.applicationContext, "1단계의 하수도 금액은 반드시 입력해 주셔야 합니다", Toast.LENGTH_SHORT)
                t1.show()
            }
            else {
                if(total_use > 0) {
                    if(total_use <= editText_one_two.text.toString().toInt()) {
                        totalWaterCost = total_use * (editText_up_one_one.text.toString().toInt() + editText_down_one_two.text.toString().toInt()) + radious_cost
                        textView_total_cost.text = "$totalWaterCost"
                        CalculaterfristSeccess = true
                    }
                    else {
                        if(firstLineError)
                        else {
                            var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                            t1.show()
                        }
                    }
                }
            }
            if(firstLineError == false) {
                if(CalculaterfristSeccess == false) {
                    if(editText_two_one.text.toString().toInt() < editText_two_two.text.toString().toInt()){
                        if(total_use <= editText_two_two.text.toString().toInt()) {
                            if(total_use >= editText_two_one.text.toString().toInt()) {
                                totalWaterCost = total_use * (editText_up_two_one.text.toString().toInt() + editText_down_two_two.text.toString().toInt()) + radious_cost
                                textView_total_cost.text = "$totalWaterCost"
                                CalculaterSecondSeccess = true
                            }
                            else {
                                var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                                t1.show()
                            }
                        }
                        else {
                            var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                            t1.show()
                        }
                    }
                    else {
                        var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                        t1.show()
                    }
                    if(CalculaterSecondSeccess == false) {
                        if(editText_trd_one.text.toString().toInt() < editText_trd_two.text.toString().toInt()){
                            if(total_use <= editText_trd_two.text.toString().toInt()) {
                                if(total_use >= editText_trd_one.text.toString().toInt()) {
                                    totalWaterCost = total_use * (editText_up_trd_one.text.toString().toInt() + editText_down_trd_two.text.toString().toInt()) + radious_cost
                                    textView_total_cost.text = "$totalWaterCost"
                                    CalculaterThridSeccess = true
                                }
                                else {
                                    var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                                    t1.show()
                                }
                            }
                            else {
                                var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                                t1.show()
                            }
                        }
                        else {
                            var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                            t1.show()
                        }
                        if(CalculaterThridSeccess == false) {
                            if(editText_for_one.text.toString().toInt() < editText_for_two.text.toString().toInt()){
                                if(total_use <= editText_for_two.text.toString().toInt()) {
                                    if(total_use >= editText_for_one.text.toString().toInt()) {
                                        totalWaterCost = total_use * (editText_up_for_one.text.toString().toInt() + editText_down_for_two.text.toString().toInt()) + radious_cost
                                        textView_total_cost.text = "$totalWaterCost"
                                        CalculaterfourSeccess = true
                                    }
                                    else {
                                        var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                                        t1.show()
                                    }
                                }
                                else {
                                    var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                                    t1.show()
                                }
                            }
                            else {
                                var t1 = Toast.makeText(activity!!.applicationContext, "사용량 범위를 확인해 주세요.", Toast.LENGTH_SHORT)
                                t1.show()
                            }
                        }
                    }
                }
            }
            if(firstLineError == false) {
                if(CalculaterfristSeccess == true) {
                    var t1 = Toast.makeText(activity!!.applicationContext, "수도료 계산이 완료되었습니다. 계산된 비용은 실제 비용과 다를 수 있습니다.", Toast.LENGTH_SHORT)
                    t1.show()
                    val nextIntent = Intent(activity!!.applicationContext, Main2Activity::class.java)
                    var CastWater = totalWaterCost.toString()
                    nextIntent.putExtra("WaterCost", CastWater)
                    startActivity(nextIntent)
                }
                if(CalculaterSecondSeccess == true) {
                    var t1 = Toast.makeText(activity!!.applicationContext, "수도료 계산이 완료되었습니다. 계산된 비용은 실제 비용과 다를 수 있습니다.", Toast.LENGTH_SHORT)
                    t1.show()
                    val nextIntent = Intent(activity!!.applicationContext, Main2Activity::class.java)
                    var CastWater = totalWaterCost.toString()
                    nextIntent.putExtra("WaterCost", CastWater)
                    startActivity(nextIntent)
                }
                if(CalculaterThridSeccess == true) {
                    var t1 = Toast.makeText(activity!!.applicationContext, "수도료 계산이 완료되었습니다. 계산된 비용은 실제 비용과 다를 수 있습니다.", Toast.LENGTH_SHORT)
                    t1.show()
                    val nextIntent = Intent(activity!!.applicationContext, Main2Activity::class.java)
                    var CastWater = totalWaterCost.toString()
                    nextIntent.putExtra("WaterCost", CastWater)
                    startActivity(nextIntent)
                }
                if(CalculaterfourSeccess == true) {
                    var t1 = Toast.makeText(activity!!.applicationContext, "수도료 계산이 완료되었습니다. 계산된 비용은 실제 비용과 다를 수 있습니다.", Toast.LENGTH_SHORT)
                    t1.show()
                    val nextIntent = Intent(activity!!.applicationContext, Main2Activity::class.java)
                    var CastWater = totalWaterCost.toString()
                    nextIntent.putExtra("WaterCost", CastWater)
                    startActivity(nextIntent)
                }
            }

            saveData(radious, radious_cost, editText_one_one.text.toString().toInt(), editText_one_two.text.toString().toInt(),editText_up_one_one.text.toString().toInt(),editText_down_one_two.text.toString().toInt(),editText_two_one.text.toString().toInt(),editText_two_two.text.toString().toInt(),editText_up_two_one.text.toString().toInt(),editText_down_two_two.text.toString().toInt(),editText_trd_one.text.toString().toInt(),editText_trd_two.text.toString().toInt(),editText_up_trd_one.text.toString().toInt(),editText_down_trd_two.text.toString().toInt(),editText_for_one.text.toString().toInt(),editText_for_two.text.toString().toInt(),editText_up_for_one.text.toString().toInt(),editText_down_for_two.text.toString().toInt(), totalWaterCost)
        }
    }
    fun saveData(radiation: Int, radiation_cost: Int, One_One: Int, One_Two:Int, One_One_Up: Int, One_Two_down: Int, Two_One: Int, Two_Two:Int, Two_One_Up: Int, Two_Two_down: Int, Trd_One: Int, Trd_Two:Int, Trd_One_Up: Int, Trd_Two_down: Int, For_One: Int, For_Two:Int, For_One_Up: Int, For_Two_down: Int, FinalCost: Int)
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!.applicationContext)
        val editor = pref.edit()

        editor.putInt("KEY_RADIATION", radiation)
            .putInt("KEY_RADIATION111", radiation_cost)
            .putInt("KEY_RADIATION1", One_One)
            .putInt("KEY_RADIATION2", One_Two)
            .putInt("KEY_RADIATION3", One_One_Up)
            .putInt("KEY_RADIATION4", One_Two_down)
            .putInt("KEY_RADIATION5", Two_One)
            .putInt("KEY_RADIATION6", Two_Two)
            .putInt("KEY_RADIATION7", Two_One_Up)
            .putInt("KEY_RADIATION8", Two_Two_down)
            .putInt("KEY_RADIATION9", Trd_One)
            .putInt("KEY_RADIATION10", Trd_Two)
            .putInt("KEY_RADIATION11", Trd_One_Up)
            .putInt("KEY_RADIATION12", Trd_Two_down)
            .putInt("KEY_RADIATION13", For_One)
            .putInt("KEY_RADIATION14", For_Two)
            .putInt("KEY_RADIATION15", For_One_Up)
            .putInt("KEY_RADIATION16", For_Two_down)
            .putInt("KEY_RADIATION17", FinalCost)
            .apply()
    }
    fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!.applicationContext)
        val editText1 = pref.getInt("KEY_RADIATION", 0)
        val editText2 = pref.getInt("KEY_RADIATION111", 0)
        val editText3 = pref.getInt("KEY_RADIATION1", 0)
        val editText4 = pref.getInt("KEY_RADIATION2", 0)
        val editText5 = pref.getInt("KEY_RADIATION3", 0)
        val editText6 = pref.getInt("KEY_RADIATION4", 0)
        val editText7 = pref.getInt("KEY_RADIATION5", 0)
        val editText8 = pref.getInt("KEY_RADIATION6", 0)
        val editText9 = pref.getInt("KEY_RADIATION7", 0)
        val editText10 = pref.getInt("KEY_RADIATION8", 0)
        val editText11 = pref.getInt("KEY_RADIATION9", 0)
        val editText12 = pref.getInt("KEY_RADIATION10", 0)
        val editText13 = pref.getInt("KEY_RADIATION11", 0)
        val editText14 = pref.getInt("KEY_RADIATION12", 0)
        val editText15 = pref.getInt("KEY_RADIATION13", 0)
        val editText16 = pref.getInt("KEY_RADIATION14", 0)
        val editText17 = pref.getInt("KEY_RADIATION15", 0)
        val editText18 = pref.getInt("KEY_RADIATION16", 0)
        val editText19 = pref.getInt("KEY_RADIATION17", 0)

        if(editText1 != 0) {
            editText_radious.setText(editText1.toString())
        }
        if(editText2 != 0) {
            editText_radious_cost.setText(editText2.toString())
        }
        if(editText3 != 0) {
            editText_one_one.setText(editText3.toString())
        }
        if(editText4 != 0) {
            editText_one_two.setText(editText4.toString())
        }
        if(editText5 != 0) {
            editText_up_one_one.setText(editText5.toString())
        }
        if(editText6 != 0) {
            editText_down_one_two.setText(editText6.toString())
        }
        if(editText7 != 0) {
            editText_two_one.setText(editText7.toString())
        }
        if(editText8 != 0) {
            editText_two_two.setText(editText8.toString())
        }
        if(editText9 != 0) {
            editText_up_two_one.setText(editText9.toString())
        }
        if(editText10 != 0) {
            editText_down_two_two.setText(editText10.toString())
        }
        if(editText11 != 0) {
            editText_trd_one.setText(editText11.toString())
        }
        if(editText12 != 0) {
            editText_trd_two.setText(editText12.toString())
        }
        if(editText13 != 0) {
            editText_up_trd_one.setText(editText13.toString())
        }
        if(editText14 != 0) {
            editText_down_trd_two.setText(editText14.toString())
        }
        if(editText15 != 0) {
            editText_for_one.setText(editText15.toString())
        }
        if(editText16 != 0) {
            editText_for_two.setText(editText16.toString())
        }
        if(editText17 != 0) {
            editText_up_for_one.setText(editText17.toString())
        }
        if(editText18 != 0) {
            editText_down_for_two.setText(editText18.toString())
        }
        if(editText19 != 0) {
            textView_total_cost.text = editText19.toString()
        }
    }
}
