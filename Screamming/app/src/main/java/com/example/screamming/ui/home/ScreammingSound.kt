package com.example.screamming.ui.home

import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.widget.Toast


/* SoundMeter Class */
class SoundMeter(mContext: Context?) {
    /* Context */
    private var mContext: Context? = null

    /* AudioRecord */
    private var mAudioRecord: AudioRecord? = null

    /* Integer */
    private var minSize = 0

    /* Start Audio Method */
    fun start() {
        /* AudioRecord */
        minSize = AudioRecord.getMinBufferSize(
//            8000,
            8000,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        mAudioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
//            8000,
            8000,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            minSize
        )
        mAudioRecord!!.startRecording()

//        /* Toast */Toast.makeText(mContext, "Music Mode Start.", Toast.LENGTH_SHORT).show()
    }

    /* Stop Audio Method */
    fun stop() {
        if (mAudioRecord != null) {
            /* AudioRecord */
            mAudioRecord!!.stop()
            mAudioRecord!!.release()
            mAudioRecord = null

//            /* Toast */Toast.makeText(mContext, "Music Mode Off.", Toast.LENGTH_SHORT).show()
        }
    }/* Short */

    /* Integer */

    /* Amplitude Method */
    val amplitude: Double
        get() {
            /* Short */
            val mBuffer = ShortArray(minSize)
            mAudioRecord!!.read(mBuffer, 0, minSize)

            /* Integer */
            var max = 0
            for (mShort in mBuffer) {
                if (Math.abs(mShort.toInt()) > max) {
                    max = Math.abs(mShort.toInt())
                }
            }
            return max.toDouble()
        }

    init {
        this.mContext = mContext
    }
}