# 使startActivityForResult的api回调简易化

> 本库用于简化`startActivityForResult(..)``onActivityResult(..)`繁琐的回调处理

## 使用

#### kotlin

```kotlin
val intent = Intent(this, SecondActivity::class.java)
this.launch4Result(intent, 256, callBack = { requestCode, resultCode, data ->
    when (resultCode) {
        Activity.RESULT_OK -> showToast(data?.getStringExtra("ok"))
        else -> showToast("失败！！")
    }
})
```

#### java

```java
ActivityExpandKt.launch4Result(this, new Intent(), 100, new AuthCallBack() {
    @Override
    public void result(int requestCode, int resultCode, @Nullable Intent data){
        //TODO
    }
});
```



## 原理

利用空的`Fragment`调用 `startActivityForResult(..)`这样会回调宿主Activity和自己的`onActivityResult(..)` ,绑定到宿主上面即可

```kotlin
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
```

利用`kotlin`扩展函数对 `FragmentActivity`增加`launch4Result(...)`函数

### 参考

[Fragment中调用startActivityForResult的那些坑](https://yq.aliyun.com/articles/680562)

[巧妙的用Fragment实现回调~一波骚操作](https://mp.weixin.qq.com/s/muLyKEcLEALDmGZPBRtj5A)

