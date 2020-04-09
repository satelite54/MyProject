package com.vibrator.calculater

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.*
import kotlin.math.floor
import kotlin.math.round


/**
 * A simple [Fragment] subclass.
 */

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) { // 여기가 실행코드
        super.onActivityCreated(savedInstanceState)

        loadData()

        val item = arrayOf("용도","대가족 생명유지 장치","복지할인","요금적용 기간")
        //ArrayAdapter 객체를 만들고 리스트뷰에 연결
        ElectroniclistView.adapter = ArrayAdapter(activity!!.applicationContext,android.R.layout.simple_list_item_1,item)

        //어댑터의 아이템은 안드로이드 스튜디오에서 제공해 주는 기본인
        //android.R.layout.simple_spinner_dropdown_item 을 사용했습니다.
        val items_purpose = resources.getStringArray(R.array.array_purpose)
        val myAdapter_purpose = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_dropdown_item, items_purpose) // Fragment에서 SpinnerAdapter 사용시 이렇게 //this 대신 activity!!.applicationContext

        val items_large_family_life_support_system = resources.getStringArray(R.array.array_large_family_life_support_system)
        val myAdapter_items_large_family_life_support_system = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_dropdown_item, items_large_family_life_support_system) // Fragment에서 SpinnerAdapter 사용시 이렇게 //this 대신 activity!!.applicationContext

        val items_array_welfare_discount = resources.getStringArray(R.array.array_welfare_discount)
        val myAdapter__welfare_discount = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_dropdown_item, items_array_welfare_discount) // Fragment에서 SpinnerAdapter 사용시 이렇게 //this 대신 activity!!.applicationContext

        val items_array_When_to_use = resources.getStringArray(R.array.arrayWhen_to_use)
        val myAdapter_items_array_When_to_use = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_dropdown_item, items_array_When_to_use) // Fragment에서 SpinnerAdapter 사용시 이렇게 //this 대신 activity!!.applicationContext

        Electronicspinner1.adapter = myAdapter_purpose
        Electronicspinner2.adapter = myAdapter_items_large_family_life_support_system
        Electronicspinner3.adapter = myAdapter__welfare_discount
        Electronicspinner4.adapter = myAdapter_items_array_When_to_use

        var tempSpinnerValue1: Int
        var tempSpinnerValue2: Int
        var tempSpinnerValue3: Int
        var tempSpinnerValue4: Int
        var tempEditText_Eletric: String

        btn_Eletronic.setOnClickListener() {
            tempSpinnerValue1 = Electronicspinner1.selectedItemPosition
            tempSpinnerValue2 = Electronicspinner2.selectedItemPosition
            tempSpinnerValue3 = Electronicspinner3.selectedItemPosition
            tempSpinnerValue4 = Electronicspinner4.selectedItemPosition
            tempEditText_Eletric = EditText_Electric.text.toString()

            var tempEditText_Eletric_translation: Int
            try {
                val tempstr: String = tempEditText_Eletric.trim();
                val TotalkWh: Int = Integer.parseInt(tempstr);
                tempEditText_Eletric_translation = tempEditText_Eletric.toInt()

            //아래 str에 숫자만 있을 경우는 NumberFormatException이
            //발생치 않으나 숫자 이외의 어떤 문자가 포함되면
            //NumberFormatException이 발생한다.
            //따라서 이 Exception이 발생하면 숫자 이외의 값이 입력되었다고
            //판단하고 재 입력 받도록 처리하면된다.
                var CalculateResult = CalculateEletronicCost(tempSpinnerValue1, tempSpinnerValue2, tempSpinnerValue3, tempSpinnerValue4, tempEditText_Eletric_translation)
                var SwapCostInt: Int
                CalculateResult /= 10
                CalculateResult = floor(CalculateResult) // kWh 정수 첫째자리 내림
                CalculateResult *= 10
                SwapCostInt = CalculateResult.toInt()
                EletronicCostResultView.text = "$SwapCostInt"
                val nextIntent = Intent(activity!!.applicationContext, Main2Activity::class.java)
                var CastkWh = SwapCostInt.toString()
                nextIntent.putExtra("kWhCost", CastkWh)
                saveData(SwapCostInt)
                startActivity(nextIntent)
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                var t1 = Toast.makeText(activity!!.applicationContext, "사용 전력량을 입력 안했거나 계산범위를 초과했습니다.", Toast.LENGTH_SHORT)
                t1.show()
            }
        }
    }

    private fun CalculateEletronicCost(Value1: Int, Value2: Int, Value3: Int, Value4: Int, TotalkWh: Int): Double
    {
        var DefaultCostElectronic: Int = 0 //기본요금
        var kWhCostTotal: Double = 0.0 // 전체 전기요금

        when(Value1) {
            0 -> { // 주택용(저압)
                val DefaultCost200kWh: Int = 910 // 기타계절 전기요금
                val DefaultCost201kwhto400: Int = 1600
                val DefaultCostUpto400: Int = 7300
                val kWhCost200kWh: Double = 93.3
                val kWhCost201kWhto400kWh: Double = 187.9
                val kWhCostUpto400kWh:Double = 280.6
                val kWhSuperUserCost1000kWh:Double = 709.5


                val DefaultCost300kWh: Int = 910 // 하계 전기요금
                val DefaultCost301kwhto450: Int = 1600
                val DefaultCostUpto450: Int = 7300
                val kWhCostSummer300kWh: Double = 93.3
                val kWhCostSummer301kWhto450kWh: Double = 187.9
                val kWhCostSummerUpto450kWh:Double = 280.6

                if(Value4 == 3) {// 하계시 요금계산
                    when (TotalkWh) {
                        in 1..300 -> {
                            DefaultCostElectronic = DefaultCost300kWh
                            kWhCostTotal = (TotalkWh * kWhCostSummer300kWh)
                        }
                        in 301..450 -> {
                            DefaultCostElectronic = DefaultCost301kwhto450
                            kWhCostTotal = (300 * kWhCostSummer300kWh)
                            kWhCostTotal += ((TotalkWh - 300) * kWhCostSummer301kWhto450kWh)
                        }
                        else -> {
                            DefaultCostElectronic = DefaultCostUpto450
                            kWhCostTotal = (300 * kWhCostSummer300kWh)
                            kWhCostTotal += (150 * kWhCostSummer301kWhto450kWh)
                            kWhCostTotal += ((TotalkWh - 450) * kWhCostSummerUpto450kWh)
                            if (TotalkWh > 1000) {
                                kWhCostTotal = (300 * kWhCostSummer300kWh)
                                kWhCostTotal += (150 * kWhCostSummer301kWhto450kWh)
                                kWhCostTotal += ((550) * kWhCostSummerUpto450kWh)
                                kWhCostTotal += ((TotalkWh - 1000) * kWhSuperUserCost1000kWh) // 슈퍼유저 요금계산
                            }
                        }
                    }
                }
                else {//기타 계절시 요금계산
                    when(TotalkWh) {
                        in 1 .. 200 -> {
                            DefaultCostElectronic = DefaultCost200kWh
                            kWhCostTotal = (TotalkWh * kWhCost200kWh)
                        }
                        in 201 .. 400 -> {
                            DefaultCostElectronic = DefaultCost201kwhto400
                            kWhCostTotal = (200 * kWhCost200kWh)
                            kWhCostTotal += ((TotalkWh - 200) * kWhCost201kWhto400kWh).toInt()
                        }
                        else -> {
                            DefaultCostElectronic = DefaultCostUpto400
                            kWhCostTotal = (200 * kWhCost200kWh)
                            kWhCostTotal += (200 * kWhCost201kWhto400kWh)
                            kWhCostTotal += ((TotalkWh - 400) * kWhCostUpto400kWh)
                            if(Value4 == 0) { // 동계 슈퍼요금 계산
                                if(TotalkWh > 1000) {
                                    kWhCostTotal = (200 * kWhCost200kWh)
                                    kWhCostTotal += (200 * kWhCost201kWhto400kWh)
                                    kWhCostTotal += ((600) * kWhCostUpto400kWh)
                                    kWhCostTotal += ((TotalkWh - 1000) * kWhSuperUserCost1000kWh) // 슈퍼유저 요금계산
                                }
                            }
                        }
                    }
                }
            }
            1 -> { // 주택용(고압)
                val DefaultCost200kWh: Int = 730 // 기타계절 전기요금
                val DefaultCost201kwhto400: Int = 1260
                val DefaultCostUpto400: Int = 6060
                val kWhCost200kWh: Double = 78.3
                val kWhCost201kWhto400kWh: Double = 147.3
                val kWhCostUpto400kWh:Double = 215.6
                val kWhSuperUserCost1000kWh:Double = 574.6

                val DefaultCost300kWh: Int = 730 // 하계 전기요금
                val DefaultCost301kwhto450: Int = 1260
                val DefaultCostUpto450: Int = 6060
                val kWhCostSummer300kWh: Double = 78.3
                val kWhCostSummer301kWhto450kWh: Double = 147.3
                val kWhCostSummerUpto450kWh:Double = 215.6

                if(Value4 == 3) {// 하계시 요금계산
                    when(TotalkWh) {
                        in 1 .. 300 -> {
                            DefaultCostElectronic = DefaultCost300kWh
                            kWhCostTotal = (TotalkWh * kWhCostSummer300kWh)
                        }
                        in 301 .. 450 -> {
                            DefaultCostElectronic = DefaultCost301kwhto450
                            kWhCostTotal = (300 * kWhCostSummer300kWh)
                            kWhCostTotal += ((TotalkWh - 300) * kWhCostSummer301kWhto450kWh)
                        }
                        else -> {
                            DefaultCostElectronic = DefaultCostUpto450
                            kWhCostTotal = (300 * kWhCostSummer300kWh)
                            kWhCostTotal += ((150) * kWhCostSummer301kWhto450kWh)
                            kWhCostTotal += ((TotalkWh - 450) * kWhCostSummerUpto450kWh)
                            if(TotalkWh > 1000) {
                                kWhCostTotal = (300 * kWhCostSummer300kWh)
                                kWhCostTotal += ((150) * kWhCostSummer301kWhto450kWh)
                                kWhCostTotal += ((550) * kWhCostSummerUpto450kWh)
                                kWhCostTotal += ((TotalkWh - 1000) * kWhSuperUserCost1000kWh) // 슈퍼유저 요금계산
                            }
                        }
                    }
                }
                else {//기타 계절시 요금계산
                    when(TotalkWh) {
                        in 1 .. 200 -> {
                            DefaultCostElectronic = DefaultCost200kWh
                            kWhCostTotal = (TotalkWh * kWhCost200kWh)
                        }
                        in 201 .. 400 -> {
                            DefaultCostElectronic = DefaultCost201kwhto400
                            kWhCostTotal = (200 * kWhCost200kWh)
                            kWhCostTotal += ((TotalkWh - 200) * kWhCost201kWhto400kWh)
                        }
                        else -> {
                            DefaultCostElectronic = DefaultCostUpto400
                            kWhCostTotal = (200 * kWhCost200kWh)
                            kWhCostTotal += (200 * kWhCost201kWhto400kWh)
                            kWhCostTotal += ((TotalkWh - 400) * kWhCostUpto400kWh)
                            if(Value4 == 0) { // 동계 슈퍼요금계산
                                if(TotalkWh > 1000) {
                                    kWhCostTotal = (200 * kWhCost200kWh)
                                    kWhCostTotal += (200 * kWhCost201kWhto400kWh)
                                    kWhCostTotal += ((600) * kWhCostUpto400kWh)
                                    kWhCostTotal += ((TotalkWh - 1000) * kWhSuperUserCost1000kWh) // 슈퍼유저 요금계산
                                }
                            }
                        }
                    }
                }
            }
        }

        var Required_usage_guaranteed_deduction: Int = 0
        if(TotalkWh in 0..200) {
            when (Value1) {
                0 -> Required_usage_guaranteed_deduction = 4000
                1 -> Required_usage_guaranteed_deduction = 2500
            }
        }

        kWhCostTotal = floor(kWhCostTotal)

        var TotalCost: Double = 0.0
        var DisCount: Double = 0.0
        when(Value1) { // 할인부분
            0 -> { // 주거용(저압)
                when(Value2) {
                    0 -> {  // 해당없음 대가족 생명유지장치
                        when(Value3) {
                            0 -> {  // 해당없음 복지할인
                                if((DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) < 1000)
                                    TotalCost = 1000.0
                                else {
                                    TotalCost = DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction
                                    TotalCost = floor(TotalCost)
                                }
                            }
                            1 -> { // 독립유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            2 -> { // 국가유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            3 -> { // 민주유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            4 -> { // 장애인(
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            5 -> { // 기초생활(생계, 의료)
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            6 -> { // 기초생활(주거, 교육)
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 10000
                                TotalCost = floor(TotalCost)
                            }
                            7 -> { // 차상위계층
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 8000
                                TotalCost = floor(TotalCost)
                            }
                            8 -> { // 사회복지시설
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                DisCount = TotalCost * 0.3
                            }
                        }
                    }
                    in 1..4 -> { // 기타할인
                        when(Value3) {
                            0 -> { // 해당없음
                                if((DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) < 1000)
                                    TotalCost = 1000.0
                                else {
                                    TotalCost = DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction
                                    TotalCost = floor(TotalCost)
                                }
                                if(Value2 == 4) {
                                    DisCount = (DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) * 0.3
                                    DisCount = floor(DisCount)
                                }
                                else {
                                    DisCount = (DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) * 0.3
                                    if(DisCount > 16000)
                                        DisCount = 16000.0
                                }
                            }
                            1 -> { // 독립유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)
                                if(DisCountB > 16000)
                                    DisCountB = 16000.0

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            2 -> { // 국가유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            3 -> { // 5.18민주 유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            4 -> { //장애인
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            5 -> { //기초생활수급(생계, 의료)
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (TotalCost - DisCountA) * 0.3
                                if(Value2 != 4) {
                                    if(DisCountB > 16000.0)
                                        DisCountB = 16000.0
                                }
                                DisCount = DisCountA + DisCountB
                            }
                            6 -> { // 기초생활수금(주거, 교육)
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 10000)
                                    DisCountA = 10000.0
                                DisCountB = (TotalCost - DisCountA) * 0.3
                                if(Value2 != 4) {
                                    if(DisCountB > 16000.0)
                                        DisCountB = 16000.0
                                }
                                DisCount = DisCountA + DisCountB
                            }
                            7 -> { // 차상위계층
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 8000)
                                    DisCountA = 8000.0
                                DisCountB = (TotalCost - DisCountA) * 0.3
                                if(Value2 != 4) {
                                    if(DisCountB > 16000.0)
                                        DisCountB = 16000.0
                                }
                                DisCount = DisCountA + DisCountB
                            }
                            8 -> { // 사회복지시설
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA = Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3
                                var DisCountB = DisCountA
                                if(DisCountB > 16000)
                                    DisCountB = 16000.0
                                if(DisCountA > DisCountB)
                                    DisCount = DisCountA
                                else
                                    DisCount = DisCountB
                            }
                        }
                    }
                }
            }
            1 -> { //주거용(고압)
                when(Value2) {
                    0 -> {  // 해당없음 대가족 생명유지장치
                        when(Value3) {
                            0 -> {  // 해당없음 복지할인
                                if((DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) < 1000)
                                    TotalCost = 1000.0
                                else {
                                    TotalCost = DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction
                                    TotalCost = floor(TotalCost)
                                }
                            }
                            1 -> { // 독립유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            2 -> { // 국가유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            3 -> { // 민주유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            4 -> { // 장애인
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            5 -> { // 기초생활(생계, 의료)
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 16000
                                TotalCost = floor(TotalCost)
                            }
                            6 -> { // 기초생활(주거, 교육)
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 10000
                                TotalCost = floor(TotalCost)
                            }
                            7 -> { // 차상위계층
                                TotalCost = DefaultCostElectronic + kWhCostTotal - 8000
                                TotalCost = floor(TotalCost)
                            }
                            8 -> { // 사회복지시설
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                DisCount = TotalCost * 0.3
                            }
                        }
                    }
                    in 1..4 -> { // 기타 할인
                        when(Value3) {
                            0 -> { // 해당없음
                                if((DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) < 1000)
                                    TotalCost = 1000.0
                                else {
                                    TotalCost = DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction
                                    TotalCost = floor(TotalCost)
                                }
                                if(Value2 == 4) {
                                    DisCount = (DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) * 0.3
                                    DisCount = floor(DisCount)
                                }
                                else {
                                    DisCount = (DefaultCostElectronic + kWhCostTotal - Required_usage_guaranteed_deduction) * 0.3
                                    if(DisCount > 16000)
                                        DisCount = 16000.0
                                }
                            }
                            1 -> { // 독립유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)
                                if(DisCountB > 16000)
                                    DisCountB = 16000.0

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            2 -> { // 국가유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            3 -> { // 5.18민주 유공자
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            4 -> { //장애인
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3)

                                if(DisCountA > DisCountB) {
                                    DisCount = DisCountA
                                }
                                else {
                                    DisCount = DisCountB
                                }
                            }
                            5 -> { //기초생활수급(생계, 의료)
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost

                                if(DisCountA > 16000)
                                    DisCountA = 16000.0
                                DisCountB = (TotalCost - DisCountA) * 0.3
                                if(Value2 != 4) {
                                    if(DisCountB > 16000.0)
                                        DisCountB = 16000.0
                                }

                                DisCount = DisCountA + DisCountB
                            }
                            6 -> { // 기초생활수금(주거, 교육)
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 10000)
                                    DisCountA = 10000.0
                                DisCountB = (TotalCost - DisCountA) * 0.3
                                if(Value2 != 4) {
                                    if(DisCountB > 16000.0)
                                        DisCountB = 16000.0
                                }
                                DisCount = DisCountA + DisCountB
                            }
                            7 -> { // 차상위계층
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA: Double = 0.0
                                var DisCountB: Double = 0.0

                                DisCountA = TotalCost
                                if(DisCountA > 8000)
                                    DisCountA = 8000.0
                                DisCountB = (TotalCost - DisCountA) * 0.3
                                if(Value2 != 4) {
                                    if(DisCountB > 16000.0)
                                        DisCountB = 16000.0
                                }
                                DisCount = DisCountA + DisCountB
                            }
                            8 -> { // 사회복지시설
                                TotalCost = DefaultCostElectronic + kWhCostTotal
                                TotalCost = floor(TotalCost)

                                var DisCountA = Required_usage_guaranteed_deduction + (TotalCost - Required_usage_guaranteed_deduction) * 0.3
                                var DisCountB = DisCountA
                                if(DisCountB > 16000)
                                    DisCountB = 16000.0
                                if(DisCountA > DisCountB)
                                    DisCount = DisCountA
                                else
                                    DisCount = DisCountB
                            }
                        }
                    }
                }
            }
        }

        TotalCost -= DisCount

        if(TotalCost < 0)
            TotalCost = 0.0

        var tax: Double = 0.0
        tax = TotalCost * 0.1
        tax = round(tax)

        var ElectronicDevelopMentCost: Double = 0.0
        ElectronicDevelopMentCost = (TotalCost * 0.037) / 10
        ElectronicDevelopMentCost = floor(ElectronicDevelopMentCost) * 10

        return TotalCost + tax + ElectronicDevelopMentCost
    }

    fun saveData(Cost: Int)
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!.applicationContext)
        val editor = pref.edit()

        editor.putInt("KEY_COST_ELECTRONIC", Cost)
            .apply()
    }
    fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!.applicationContext)
        val kWhCost = pref.getInt("KEY_COST_ELECTRONIC", 0)

        if(kWhCost != 0) {
            EletronicCostResultView.setText(kWhCost.toString())
        }
    }
}
