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

    private val callBacks: ArrayMap<Int, WeakReference<AuthCallBack>> by lazy { ArrayMap<Int, WeakReference<AuthCallBack>>() }

    fun launchActivity(intent: Intent, requestCode: Int, callBack: AuthCallBack) {
        callBacks[requestCode] = WeakReference(callBack)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val callBack: AuthCallBack? = callBacks.remove(requestCode)?.get()
        callBack?.result(requestCode, resultCode, data)
    }
}