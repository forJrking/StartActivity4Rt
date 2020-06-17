package com.forjrking.activity.library

import android.content.Intent

/**
 * @Description:
 * @Author: forJrking
 * @CreateDate: 2020/6/17 10:40
 * @Version: 1.0.0
 */
interface AuthCallBack {
    fun result(requestCode: Int, resultCode: Int, data: Intent?)
}