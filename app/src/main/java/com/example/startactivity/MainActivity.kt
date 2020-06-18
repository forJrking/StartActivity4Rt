package com.example.startactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.forjrking.activity.library.launch4Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
//            this.launch4Result(intent, 256, callBack = {
//                override fun result(requestCode: Int, resultCode: Int, data: Intent?) {
//                    // data 为空或者requestCode不为RESULT_OK 失败
//                    when (resultCode) {
//                        Activity.RESULT_OK -> showToast(data?.getStringExtra("1"))
//                        else -> showToast("失败！！")
//                    }
//                }
//            })

            this.launch4Result(intent, 256, callBack = { requestCode, resultCode, data ->
                when (resultCode) {
                    Activity.RESULT_OK -> showToast(data?.getStringExtra("1"))
                    else -> showToast("失败！！")
                }
            })
        }
    }

    fun showToast(str: String?) {
        Toast.makeText(this, str, LENGTH_SHORT).show()
    }

}