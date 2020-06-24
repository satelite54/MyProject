package com.project.codysimulator2

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cody_frag.*
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
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

    private lateinit var mContext: Context
    private lateinit var mActivity: Activity

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Sets the drag event listener for the View

        LoadImageView()

        imageView1.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView1.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView2.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView2.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView3.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView3.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView4.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView4.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView5.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView5.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView6.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView6.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView7.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
        imageView7.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
        imageView8.setOnTouchListener { v, event: MotionEvent ->
            onTouch(v, event)
        }
//        imageView8.setOnDragListener { v, event:DragEvent ->
//            onDrag(v, event)
//        }
        imageView9.setOnDragListener { v, event: DragEvent ->
            onDrag(v, event)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        return if (event?.action == MotionEvent.ACTION_DOWN) {
            val dragShadowBuilder = View.DragShadowBuilder(v)

            val item: ClipData.Item = ClipData.Item(v!!.tag as CharSequence)
            val mimeTypes =
                arrayOf<String>(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(tag, mimeTypes, item)

            view?.startDrag(data, dragShadowBuilder, v, 0)
            true
        } else {
            false
        }
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when (event?.action) {
            DragEvent.ACTION_DROP -> {

                val item = event.clipData.getItemAt(0)
                val dragData = item.text

                if (v!!.tag == "ImageView9") {
                    if (dragData == "ImageView1") {
                        var b: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.blue_cloth)
                        b = removeBackground(b)
                        (v as ImageView).setImageBitmap(b)
                    } else if (dragData == "ImageView2") {
                        (v as ImageView).setImageResource(R.drawable.blue_cloth)
                    }
                }
                return true
            }
            else -> return true // false를 리턴하게 되면 Drop 이벤트 수신못함.. ACTION_DRAG_STRATED 만 수신한다.
        }
    }

    override fun onResume() {
        super.onResume()

    }

    fun LoadImageView() {

        for (ImgCnt in 1..8) { // 8번 반복
            var path: String = ""
            loadImageFromStorage(path, ImgCnt)
        }
    }

    fun loadImageFromStorage(path: String, ImgCnt: Int) {
        try {
            var b: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.blue_cloth)
            var img = view?.findViewById(R.id.imageView1 + ImgCnt - 1) as ImageView
            img.setTag("ImageView$ImgCnt")
            img.setImageBitmap(b)
            if(ImgCnt == 8) {
                img = view?.findViewById(R.id.imageView1 + ImgCnt) as ImageView
                img.setTag("ImageView${ImgCnt + 1}")
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun removeBackground(bitmap: Bitmap): Bitmap { //GrabCut part
        var bitmap = bitmap
        val img = Mat()
        Utils.bitmapToMat(bitmap, img)
        val r = img.rows()
        val c = img.cols()
        val p1 = Point((c / 100).toDouble(), (r / 100).toDouble())
        val p2 = Point((c - c / 100).toDouble(), (r - r / 100).toDouble())
        val rect = Rect(p1, p2)

        val mask = Mat()
        val fgdModel = Mat()
        val bgdModel = Mat()
        val imgC3 = Mat()
        Imgproc.cvtColor(img, imgC3, Imgproc.COLOR_RGBA2RGB)
        Imgproc.grabCut(imgC3, mask, rect, bgdModel, fgdModel, 5, Imgproc.GC_INIT_WITH_RECT)
        val source = Mat(1, 1, CvType.CV_8U, Scalar(3.0))
        Core.compare(mask, source /* GC_PR_FGD */, mask, Core.CMP_EQ)
        //This is important. You must use Scalar(255,255, 255,255), not Scalar(255,255,255)
        val foreground = Mat(
            img.size(), CvType.CV_8UC3, Scalar(
                255.0,
                255.0, 255.0, 255.0
            )
        )
        img.copyTo(foreground, mask)
        // convert matrix to output bitmap
        bitmap = Bitmap.createBitmap(
            foreground.size().width.toInt(),
            foreground.size().height.toInt(),
            Bitmap.Config.ARGB_8888
        )
        Utils.matToBitmap(foreground, bitmap)
        return bitmap
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (mOpenCvCameraView != null) mOpenCvCameraView.disableView()
    }

    companion object {
        /**ㅉ
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