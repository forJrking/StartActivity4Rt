package com.forjrking.activity.library;

import android.content.Intent;

import androidx.annotation.Nullable;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/**
 * @Description: 主要给java使用
 * @Author: forJrking
 * @CreateDate: 2020/6/18 10:24
 * @Version: 1.0.0
 */
public abstract class AuthCallBack implements Function3<Integer, Integer, Intent, Unit> {

    @Override
    public Unit invoke(Integer requestCode, Integer resultCode, Intent intent) {
        result(requestCode, resultCode, intent);
        return null;
    }

    /**
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data  intent
     */
    protected abstract void result(int requestCode, int resultCode, @Nullable Intent data);
}
