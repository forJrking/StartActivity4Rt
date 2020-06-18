package com.forjrking.activity.library

import android.content.Intent
import android.util.ArrayMap

import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

/**
 * @Description:
 * @Author: forJrking
 * @CreateDate: 2020/6/17 9:47
 * @Version: 1.0.0
 */
class InvisibleResultFragment : Fragment() {

    private val callBacks: ArrayMap<Int, WeakReference<((Int, Int, Intent?) -> Unit)>> by lazy {
        ArrayMap<Int, WeakReference<((Int, Int, Intent?) -> Unit)>>()
    }

    fun launchActivity(
        intent: Intent,
        requestCode: Int,
        callBack: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
    ) {
        callBacks[requestCode] = WeakReference(callBack)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val callBack = callBacks.remove(requestCode)?.get()
        callBack?.invoke(requestCode, resultCode, data)
    }
}