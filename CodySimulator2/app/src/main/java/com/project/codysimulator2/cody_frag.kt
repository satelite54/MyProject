package com.project.codysimulator2

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_cody_frag.*
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import java.io.*
import java.text.SimpleDateFormat


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

    private lateinit var ImgView: ImageView
    private val REQUEST_CODE = 0

    var ImgLoadFlag = false // 전체 이미지 로드

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

        // 맨 처음 디폴트 이미지 저장
        for(ImageCnt in 1..8) {
            var FileExistFlag: Boolean = false
            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_$ImageCnt")
            if(!f.exists()) { // 파일이 없으면
                imageView1.setImageResource(R.drawable.blue_cloth)
                imageView2.setImageResource(R.drawable.blue_cloth)
                imageView3.setImageResource(R.drawable.blue_cloth)
                imageView4.setImageResource(R.drawable.blue_cloth)
                imageView5.setImageResource(R.drawable.blue_cloth)
                imageView6.setImageResource(R.drawable.blue_cloth)
                imageView7.setImageResource(R.drawable.blue_cloth)
                imageView8.setImageResource(R.drawable.blue_cloth)
            }
        }

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

        button_Load.setOnClickListener {
            ImgLoadFlag = true
            onClick(button_Load)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    val `in`: InputStream? = data!!.data?.let {
                        context?.contentResolver?.openInputStream(
                            it
                        )
                    }
                    val img =
                        BitmapFactory.decodeStream(`in`)
                    if (`in` != null) {
                        `in`.close()
                    }
                    if(ImgLoadFlag == true) { // 뷰간 이미지 복사 // 이미지 로드시 밀어낸다.
                        var filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_1")
                        var fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_2")
                        var ImgName: String = "Cloth_1"

                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_7")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_8")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_8"
                        loadImageFromStorage(ImgName, 8)
                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_6")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_7")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_7"
                        loadImageFromStorage(ImgName, 7)
                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_5")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_6")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_6"
                        loadImageFromStorage(ImgName, 6)
                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_4")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_5")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_5"
                        loadImageFromStorage(ImgName, 5)
                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_3")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_4")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_4"
                        loadImageFromStorage(ImgName, 4)
                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_2")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_3")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_3"
                        loadImageFromStorage(ImgName, 3)
                        filePre = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_1")
                        fileNow = File("/data/data/com.project.codysimulator2/app_imageDir","Cloth_2")
                        filePre.renameTo(fileNow)
                        ImgName = "Cloth_2"
                        loadImageFromStorage(ImgName, 2)
                    }
                    var strImgName = "Cloth_1"
                    imageView1.setImageBitmap(img)
                    createDirectoryAndSaveFile(imageView1, strImgName)
                } catch (e: java.lang.Exception) {
                }
            } else if (resultCode == RESULT_CANCELED) {
                ImgLoadFlag = false
                Toast.makeText(activity!!.applicationContext, "사진 선택 취소", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun onClick(b: Button?) {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, REQUEST_CODE)
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
                        val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_1")
                        if(f.exists()) {
                            var b = BitmapFactory.decodeStream(FileInputStream(f))
                            b = removeBackground(b)
                            (v as ImageView).setImageBitmap(b)
                        }
                    } else if (dragData == "ImageView2") {
                        if (dragData == "ImageView2") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_2")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
                    }  else if (dragData == "ImageView3") {
                        if (dragData == "ImageView3") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_3")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
                    } else if (dragData == "ImageView4") {
                        if (dragData == "ImageView4") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_4")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
                    } else if (dragData == "ImageView5") {
                        if (dragData == "ImageView5") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_5")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
                    } else if (dragData == "ImageView6") {
                        if (dragData == "ImageView6") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_6")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
                    } else if (dragData == "ImageView7") {
                        if (dragData == "ImageView7") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_7")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
                    } else if (dragData == "ImageView8") {
                        if (dragData == "ImageView8") {
                            val f = File("/data/data/com.project.codysimulator2/app_imageDir", "Cloth_8")
                            if(f.exists()) {
                                var b = BitmapFactory.decodeStream(FileInputStream(f))
                                b = removeBackground(b)
                                (v as ImageView).setImageBitmap(b)
                            }
                        }
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

    fun LoadImageView() {// 맨 처음 로드
        for (ImgCnt in 1..8) { // 8번 반복
            var ImgName: String = "Cloth_$ImgCnt"
            loadImageFromStorage(ImgName, ImgCnt)
        }
    }

    fun loadImageFromStorage(ImgName: String, ImgCnt: Int) {
        try {
            val f = File("/data/data/com.project.codysimulator2/app_imageDir", ImgName)
            if(f.exists()) {
                val b = BitmapFactory.decodeStream(FileInputStream(f))
                var img = view?.findViewById(R.id.imageView1 + ImgCnt - 1) as ImageView
                img.setTag("ImageView$ImgCnt")
                img.setImageBitmap(b)
                if(ImgCnt == 8) {
                    img = view?.findViewById(R.id.imageView1 + ImgCnt) as ImageView
                    img.setTag("ImageView${ImgCnt + 1}")
                }
            }
            else {
                val b = BitmapFactory.decodeResource(resources, R.drawable.blue_cloth)
                // 저장해놓은 파일이 없을 시
                var img = view?.findViewById(R.id.imageView1 + ImgCnt - 1) as ImageView
                img.setTag("ImageView$ImgCnt")
                img.setImageBitmap(b)
                if(ImgCnt == 8) {
                    img = view?.findViewById(R.id.imageView1 + ImgCnt) as ImageView
                    img.setTag("ImageView${ImgCnt + 1}")
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun createDirectoryAndSaveFile(v: View, fileName: String) {
        val cw = ContextWrapper(requireActivity().applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val direct: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        if (!direct.exists()) {
            val wallpaperDirectory = File("/data/data/com.project.codysimulator2/app_imageDir")
            wallpaperDirectory.mkdirs()
        }
        val file = File(direct , fileName)
        if (file.exists()) {
            file.delete()
        }

        val bitmap: Bitmap = getBitmapFromView(v)

        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getBitmapFromView(v: View): Bitmap {

        val b = Bitmap.createBitmap(
            v.width, v.height,
            Bitmap.Config.ARGB_8888
        )
        val c = Canvas(b)
        v.draw(c)
        return b
    }

    fun removeBackground(bitmap: Bitmap): Bitmap { //GrabCut 부분
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
        val source = Mat(1, 1, CvType.CV_8U, Scalar.all(3.0)) //2 객체 제거 3 객체 인식
        Core.compare(mask, source , mask, Core.CMP_EQ)
        //이 부분이 중요함. 스칼라 사용 Scalar(255,255, 255,255), not Scalar(255,255,255)
        val foreground = Mat(
            img.size(), CvType.CV_8UC3, Scalar(
                255.0,
                255.0, 255.0, 255.0
            )
        )
        img.copyTo(foreground, mask)
        // 매트릭스를 비트맵으로 바꿔줌
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

//    fun settingPermission(){
//        var permis = object  : PermissionListener {
//            //            어떠한 형식을 상속받는 익명 클래스의 객체를 생성하기 위해 다음과 같이 작성
//            override fun onPermissionGranted() {
//                Toast.makeText(this@MainActivity, "권한 허가", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                Toast.makeText(this@MainActivity, "권한 거부", Toast.LENGTH_SHORT)
//                    .show()
//                ActivityCompat.finishAffinity(this@MainActivity) // 권한 거부시 앱 종료
//            }
//        }
//
//        TedPermission.with(this)
//            .setPermissionListener(permis)
//            .setRationaleMessage("카메라 사진 권한 필요")
//            .setDeniedMessage("카메라 권한 요청 거부")
//            .setPermissions(
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
////                android.Manifest.permission.READ_EXTERNAL_STORAGE,
//                android.Manifest.permission.CAMERA)
//            .check()
//    }
//
//    @Throws(IOException::class)
//    private fun createImageFile() : File {
//        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) // 파일 이름 설정
//        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//            "JPEG_${timeStamp}_",
//            ".jpg",
//            storageDir
//        ).apply{
//            currentPhotoPath = absolutePath
//        }
//    }
//
//    fun startCapture(){
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                val photoFile: File? = try{
//                    createImageFile()
//                }catch(ex:IOException){
//                    null
//                }
//                photoFile?.also{
//                    val photoURI : Uri = FileProvider.getUriForFile(
//                        this,
//                        "org.techtown.capturepicture.fileprovider",
//                        it
//                    )
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//                }
//            }
//        }
//    }

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