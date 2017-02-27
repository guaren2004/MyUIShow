package com.example.robin.coordinatorlayouttest.engin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.widget.ImageView;

import com.example.robin.coordinatorlayouttest.utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装 OkHttp 的类
 * (采用了静态内部类的单例模式)
 */
public class NetManager {

    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    private Handler mHandler;

    // 单例模式 步骤一: 私有化构造函数, 并在里面做一些初始化操作
    // OkHttpClient 在这里初始化, 也为单例模式
    private NetManager() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

        mGson = new Gson();

        // Looper.getMainLooper(): 获得主线程的 Looper(轮训器)
        // 在 Looper 初始化的时候也初始化了主线程的 MessageQueue(消息队列)
        // 每个线程只有一个 Looper 和 MessageQueue
        mHandler = new Handler(Looper.getMainLooper());
    }

    // 单例模式 步骤二: 静态内部类创建实例
    private static class NetManagerHolder {
        private static final NetManager instance = new NetManager();
    }

    // 单例模式 步骤三: 外部获取实例的唯一方法
    public static NetManager getInstance() {
        return NetManagerHolder.instance;
    }

    /**
     * 对外公开的基本 get 方法
     *
     * @param url          访问地址
     * @param baseCallback 基本回调类
     */
    public void get(String url, BaseCallback baseCallback) {
        final Request request = initRequest(url, null, HttpMethodType.GET); // 先初始化 request
        linkNet(request, baseCallback); // 再开始访问网络
    }

    /**
     * 对外公开的基本 post 方法
     *
     * @param url          访问地址
     * @param body         请求体
     * @param baseCallback 基本回调类
     */
    public void post(String url, RequestBody body, BaseCallback baseCallback) {
        final Request request = initRequest(url, body, HttpMethodType.POST);
        linkNet(request, baseCallback);
    }

    /**
     * 创建一个表单形式的 RequestBody
     *
     * @param params 一个存放键值对参数的 ArrayMap
     */
    public RequestBody buildFormRequestBody(ArrayMap<String, String> params) {
        final FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    /**
     * 初始化一个 get 和 post 方法都通用的 Request
     *
     * @param url  访问地址
     * @param body post 提交需要的 RequestBody
     * @param type 定义一个枚举类: 指明提交请求的方法
     */
    private Request initRequest(String url, RequestBody body, HttpMethodType type) {
        final Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (type == HttpMethodType.GET) {
            builder.get();
        } else if (type == HttpMethodType.POST) {
            builder.post(body);
        }
        return builder.build();
    }

    /**
     * 定义一个枚举类: 指明提交请求的方法
     */
    private enum HttpMethodType {
        GET,
        POST
    }

    /**
     * 封装一个开始访问网络的方法
     *
     * @param request      请求
     * @param baseCallback 基本回调类
     */
    private void linkNet(Request request, final BaseCallback baseCallback) {

        // 在开始访问网络之前所做的事, 比如弹出对话框等
        baseCallback.onLinNetBefore();

        // 注意: call.enqueue 是异步执行网络请求
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 由于 call.enqueue 方法是在子线程中执行, 所以处理的时候需要转到主线程
                toMainThreadOnFailure(call, e, baseCallback);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    final String str = response.body().string();
                    final Type type = baseCallback.mType;
                    if (type == String.class) {
                        toMainThreadOnSuccessful(call, response, str, baseCallback);
                    } else {
                        final Object object = mGson.fromJson(str, type);
                        toMainThreadOnSuccessful(call, response, object, baseCallback);
                    }
                } catch (JsonParseException | IOException e) {
                    toMainThreadOnError(call, response, e, baseCallback);
                }
            }
        });
    }

    /**
     * get 单纯根据 url 加载图片到指定 ImageView, 并设置默认图片的资源 ID
     *
     * @param view         指定 ImageView
     * @param url          图片 Url 地址
     * @param defaultResId 默认图片的资源 ID
     * @param bmScale Bitmap 的缩放比例
     */
    public void loadImage(final ImageView view, final String url, final int defaultResId, final float bmScale) {
        final Request request = initRequest(url, null, HttpMethodType.GET);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                setDefaultView(view, defaultResId);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                try {
                    is = response.body().byteStream();
                    try {
                        is.reset();
                    } catch (IOException e) {
                        response = getSync(url);
                        is = response.body().byteStream();
                    }
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);
                    final int bmNewWidth = (int) (bitmap.getWidth() * bmScale);
                    final int bmNewHeight = (int) (bitmap.getHeight() * bmScale);
                    final Bitmap newBitmap = Util.scaleBitmap(bitmap, bmNewWidth, bmNewHeight, true);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(newBitmap);
                        }
                    });

                } catch (Exception e) {
                    setDefaultView(view, defaultResId);
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    /**
     * 同步的 get 请求
     */
    private Response getSync(String url) throws IOException {
        final Request request = initRequest(url, null, HttpMethodType.GET);
        final Call call = mOkHttpClient.newCall(request);
        return call.execute();
    }

    /**
     * 设置默认图片
     *
     * @param view         ImageView
     * @param defaultResId 资源 ID
     */
    private void setDefaultView(final ImageView view, final int defaultResId) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                view.setImageResource(defaultResId);
            }
        });
    }

    /**
     * 当访问网络失败的时候, 利用 Handler.post(Runnable r) 将处理代码转至主线程的方法
     *
     * @param call         OkHttp 的 call 对象, 可以从中获取和处理一些关于 request 的事情
     * @param e            IO 异常
     * @param baseCallback 基本回调类
     */
    private void toMainThreadOnFailure(final Call call, final IOException e, final BaseCallback baseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                baseCallback.onFailure(call, e);
            }
        });
    }

    /**
     * 当访问网络成功的时候(响应码 response.code() 在 [200..300) ), 利用 Handler.post(Runnable r) 将处理代码转至主线程的方法
     * 注意: 这个方法的返回值前, 单独申明了一个泛型, 并且后面的 BaseCallback<T> 也加上了泛型, 并且泛型一样
     * 解决了一个警告: UnChecked call to "......" as member of the raw type
     *
     * @param call         OkHttp 的 call 对象, 可以从中获取和处理一些关于 request 的事情
     * @param response     访问成功获得响应对象, 可以从中获取响应头或响应体, 响应码
     * @param baseCallback 基本回调类
     */
    private <T> void toMainThreadOnSuccessful(final Call call, final Response response, final T t, final BaseCallback<T> baseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                baseCallback.onSuccess(call, response, t);
            }
        });
    }

    /**
     * 当访问网络成功, 但是响应有错误(响应码 response.code() 不在 [200..300) ) , 利用 Handler.post(Runnable r) 将处理代码转至主线程的方法
     *
     * @param call         OkHttp 的 call 对象, 可以从中获取和处理一些关于 request 的事情
     * @param response     访问成功获得响应对象, 可以从中获取响应头或响应体, 响应码
     * @param baseCallback 基本回调类
     */
    private void toMainThreadOnError(final Call call, final Response response, final Exception e, final BaseCallback baseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                baseCallback.onError(call, response, e);
            }
        });
    }
}
