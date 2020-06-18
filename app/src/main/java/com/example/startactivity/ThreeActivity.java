package com.example.startactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.forjrking.activity.library.ActivityExpandKt;
import com.forjrking.activity.library.AuthCallBack;

/**
 * @Description:
 * @Author: forJrking
 * @CreateDate: 2020/6/17 13:01
 * @Version: 1.0.0
 */
class ThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityExpandKt.launch4Result(this, new Intent(), 100, new AuthCallBack(){
            @Override
            protected void result(int requestCode, int resultCode, @Nullable Intent data) {

            }
        });
    }
}
