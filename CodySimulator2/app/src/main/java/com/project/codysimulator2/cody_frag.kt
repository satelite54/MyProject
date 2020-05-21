package com.project.codysimulator2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cody_frag.*
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cody_frag.newInstance] factory method to
 * create an instance of this fragment.
 */
class cody_frag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cody_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        imageView.setOnTouchListener { v: View, event: MotionEvent ->
            DragAndMove(v, event, imageView)
        }
    }

    fun DragAndMove (v: View, event: MotionEvent, imageView: ImageView) : Boolean {
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
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment cody_frag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            cody_frag().apply {
                arguments = Bundle().apply {

                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
