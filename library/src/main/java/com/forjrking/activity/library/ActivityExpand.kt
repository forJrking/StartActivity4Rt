package com.forjrking.activity.library

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/**
 * @Description: Activity扩展工具类
 * @Author: forJrking
 * @CreateDate: 2020/6/17 10:36
 * @Version: 1.0.0
 */

private const val FRAGMENT_TAG = "#TAG_LAUNCH4RESULT"

/**扩展函数支持 FragmentActivity和子类 注意不支持Activity*/
fun FragmentActivity.launch4Result(intent: Intent, requestCode: Int, callBack: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit) {
    getInvisibleFragment(this)?.launchActivity(intent, requestCode, callBack)
}

private fun getInvisibleFragment(activity: FragmentActivity): InvisibleResultFragment? {
    val fragmentManager: FragmentManager = activity.supportFragmentManager
    val existedFragment: Fragment? = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
    return if (existedFragment != null) {
        existedFragment as InvisibleResultFragment
    } else {
        val invisibleFragment = InvisibleResultFragment()
        fragmentManager.beginTransaction().add(invisibleFragment, FRAGMENT_TAG).commit()
        fragmentManager.executePendingTransactions()
        invisibleFragment
    }
}