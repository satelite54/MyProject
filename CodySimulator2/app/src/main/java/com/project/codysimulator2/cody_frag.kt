package com.project.codysimulator2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cody_frag.*
import java.io.FileNotFoundException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cody_frag.newInstance] factory method to
 * create an instance of this fragment.
 */
class cody_frag : Fragment(), View.OnTouchListener, View.OnDragListener {
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

        // Sets the drag event listener for the View

        LoadImageView()

        imageView1.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView1.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView2.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView2.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView3.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView3.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView4.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView4.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView5.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView5.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView6.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView6.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView7.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
        imageView7.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
        imageView8.setOnTouchListener { v, event:MotionEvent ->
            onTouch(v, event)
        }
//        imageView8.setOnDragListener { v, event:DragEvent ->
//            onDrag(v, event)
//        }
        imageView9.setOnDragListener { v, event:DragEvent ->
            onDrag(v, event)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?) : Boolean{

        return if (event?.action == MotionEvent.ACTION_DOWN) {
            val dragShadowBuilder = View.DragShadowBuilder(v)
            view?.startDrag(null, dragShadowBuilder, v, 0)
            true
        } else {
            false
        }
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when(event?.action) {
            DragEvent.ACTION_DROP -> {

                val item = event.clipData.getItemAt(0)
                val dragData = item.text

                if (dragData == "ImageTag1") { // this gets jpg image from "drawable" folder,
//      set ImageView appropriately for your usage
                    (v as ImageView).setImageResource(android.R.drawable.image1)
                } else if (dragData == "ImageTag2") {
                    (v as ImageView).setImageResource(android.R.drawable.image2)
                }
                break

                val tvState = event.localState as View
                val tvParent= tvState.parent as ViewGroup
                tvParent.removeView(tvState)
                val container = v as ConstraintLayout
                container.addView(tvState)
                tvParent.removeView(tvState)
                tvState.x = event.x
                tvState.y = event.y
                v.addView(tvState)
                v.setVisibility(View.VISIBLE)
                return true
            }
            else -> return true // false를 리턴하게 되면 Drop 이벤트 수신못함.. ACTION_DRAG_STRATED 만 수신한다.
        }
    }

    fun LoadImageView() {
        for(ImgCnt in 1..8) { // 8번 반복
            var path : String = ""
            loadImageFromStorage(path, ImgCnt)
        }
    }

    fun loadImageFromStorage(path: String, ImgCnt: Int) {
        try {
            val b: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.blue_cloth)
            val img = view?.findViewById(R.id.imageView1 + ImgCnt) as ImageView
            img.setTag("ImageTag$ImgCnt")
            img.setImageBitmap(b)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
//
//    fun DragAndMove (v: View, event: MotionEvent, imageView: ImageView) : Boolean {
//        val pWidth = (v.parent as ViewGroup).width
//        val pHeight = (v.parent as ViewGroup).height
//
//        if (event.action == MotionEvent.ACTION_MOVE) {
//            v.x = v.x + event.x - v.width / 2
//            v.y = v.y + event.y - v.height / 2
//        } else if (event.action == MotionEvent.ACTION_UP) {
//            Log.d("bsjbsj", "detached...")
//            Log.d("bsjbsj", "v.x : ${v.x} + v.y : ${v.y} , v.x + v.width : ${v.x + v.width}, v.y + y.width : ${v.y + v.width}")
//            if (v.x < 0) {
//                v.x = 0F
//            } else if (v.x + v.width > pWidth) {
//                v.x = (pWidth - v.width).toFloat()
//            }
//            if (v.y < 0) {
//                v.y = 0F
//            } else if (v.y + v.height > pHeight) {
//                v.y = (pHeight - v.height).toFloat()
//            }
//
//        }
//        return true
//    }

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
