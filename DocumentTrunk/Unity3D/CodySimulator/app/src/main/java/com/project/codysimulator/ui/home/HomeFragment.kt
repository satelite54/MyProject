package com.project.codysimulator.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.codysimulator.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageView.setOnTouchListener{v: View, event: MotionEvent ->
            val pWidth = (v.parent as ViewGroup).width
            val pHeight = (v.parent as ViewGroup).height

            if (event.action == MotionEvent.ACTION_MOVE) {
                v.x = v.x + event.x - v.width / 2
                v.y = v.y + event.y - v.height / 2
            } else if (event.action == MotionEvent.ACTION_UP) {
                Log.d("bsjbsj", "detached...")
                Log.d("bsjbsj", "v.x : ${v.x} + v.y : ${v.y} , v.x + v.width : ${v.x + v.width}, v.y + y.width : ${v.y + v.width}")
                if (v.x < 0) {
                    v.x = 0F
                } else if (v.x + v.width > pWidth) {
                    v.x = (pWidth - v.width).toFloat()
                }
                if (v.y < 0) {
                    v.y = 0F
                } else if (v.y + v.height > pHeight) {
                    v.y = (pHeight - v.height).toFloat()
                }
            }
            true
        }
    }
}
