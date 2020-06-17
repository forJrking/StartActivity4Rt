# StartActivicyForResult 简易化

> 本库用于简化`startActivity``onActivityResult`繁琐的回调处理

## 使用

#### kotlin

```kotlin
val intent = Intent(this, SecondActivity::class.java)
this.launch4Result(intent, requestCode, object : AuthCallBack {
     override fun result(requestCode: Int, resultCode: Int, data: Intent?) {
         // data 为空或者requestCode不为RESULT_OK 失败
         when (resultCode) {
             Activity.RESULT_OK -> showToast(data?.getStringExtra("key"))
             else -> showToast("失败！！")
         }
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
	// WeakReference防止传入的匿名内部类导致内存泄漏
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
```

利用`kotlin`扩展函数对 `FragmentActivity`增加`launch4Result(...)`函数

### 参考

[Fragment中调用startActivityForResult的那些坑](https://yq.aliyun.com/articles/680562)

