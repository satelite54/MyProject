package com.project.codysimulator2 // Made by 김태헌

//import com.project.codysimulator2.MainActivity.Singleton.mLoaderCallbackTemp
import android.Manifest.permission.CAMERA
import android.Manifest.permission_group.CAMERA
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Scalar
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {

    private var bottomNavigationView // 바텀 네비게이션 뷰
            : BottomNavigationView? = null
    private var fm: FragmentManager? = null
    private var ft: FragmentTransaction? = null
    private var frag1: cody_frag? = null
    private var frag2: Server_frag? = null

    val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            val TAG: String = "dsadsadsa"

            when (status) {
                LoaderCallbackInterface.SUCCESS -> {
                    Log.i(TAG, "OpenCV Manager Connected")
                    //from now onwards, you can use OpenCV API
                    val m = Mat(5, 10, CvType.CV_8UC1, Scalar(0.0))
                }
                LoaderCallbackInterface.INIT_FAILED -> Log.i(TAG, "Init Failed")
                LoaderCallbackInterface.INSTALL_CANCELED -> Log.i(
                    TAG,
                    "Install Cancelled"
                )
                LoaderCallbackInterface.INCOMPATIBLE_MANAGER_VERSION -> Log.i(
                    TAG,
                    "Incompatible Version"
                )
                LoaderCallbackInterface.MARKET_ERROR -> Log.i(
                    TAG,
                    "Market Error"
                )
                else -> {
                    Log.i(TAG, "OpenCV Manager Install")
                    super.onManagerConnected(status)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mLoaderCallbackTemp = mLoaderCallback

        bottomNavigationView = findViewById(R.id.bottomNavi)
        bottomNavigationView?.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_today -> setFrag(0)
                R.id.action_add -> setFrag(1)
            }
            true
        }
        frag1 = cody_frag()
        frag2 = Server_frag()
        setFrag(0) // 첫 프래그먼트 화면 지정
    }

    override fun onResume() {
        super.onResume()

        if (!OpenCVLoader.initDebug()) {
            Log.d(ContentValues.TAG, "onResume :: Internal OpenCV library not found.");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback);
        } else {
            Log.d(ContentValues.TAG, "onResum :: OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
            // 프레그먼트 교체
    private fun setFrag(n: Int) {
        fm = supportFragmentManager
        ft = fm!!.beginTransaction()
        when (n) {
            0 -> {
                ft!!.replace(R.id.Main_Frame, cody_frag())
                ft!!.commit()
            }
            1 -> {
                ft!!.replace(R.id.Main_Frame, Server_frag())
                ft!!.commit()
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}


//package com.project.codysimulator2
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.fragment.app.FragmentManager
//import kotlinx.android.synthetic.main.activity_main.*
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Example of a call to a native method
//
//    }
//
//    /**
//     * A native method that is implemented by the 'native-lib' native library,
//     * which is packaged with this application.
//     */
//    external fun stringFromJNI(): String
//
//    companion object {
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
//}