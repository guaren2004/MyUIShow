//package com.example.robin.coordinatorlayouttest.engin;
//
//import android.os.Handler;
//import android.os.Looper;
//import android.support.v4.util.ArrayMap;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonParseException;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// * 这个类用来辅助OKHttp
// */
//public class OkHttpHelper {
//
//    /**
//     * 采用单例模式使用OkHttpClient
//     */
//    private static OkHttpHelper mOkHttpHelperInstance;
//    private static OkHttpClient mClientInstance;
//    private Handler mHandler;
//    private Gson mGson;
//
//    /**
//     * 单例模式，私有构造函数，构造函数里面进行一些初始化
//     */
//    private OkHttpHelper() {
//        mClientInstance = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .build();
//
//        mGson = new Gson();
//
//        mHandler = new Handler(Looper.getMainLooper());
//    }
//
//    /**
//     * 获取实例
//     */
//    public static OkHttpHelper getInstance() {
//
//        if (mOkHttpHelperInstance == null) {
//            synchronized (OkHttpHelper.class) {
//                if (mOkHttpHelperInstance == null) {
//                    mOkHttpHelperInstance = new OkHttpHelper();
//                }
//            }
//        }
//        return mOkHttpHelperInstance;
//    }
//
//    /**
//     * 封装一个request方法，不管post或者get方法中都会用到
//     */
//    private void request(final Request request, final BaseCallback callback) {
//
//        //在请求之前所做的事，比如弹出对话框等
////        callback.onRequestBefore();
//
//        mClientInstance.newCall(request).enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //返回失败
//                callbackFailure(request, callback, e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    //返回成功回调
//                    String resString = response.body().string();
//
//                    if (callback.mType == String.class) {
//                        //如果我们需要返回String类型
//                        callbackSuccess(response, resString, callback);
//                    } else {
//                        //如果返回的是其他类型，则利用Gson去解析
//                        try {
//                            Object o = mGson.fromJson(resString, callback.mType);
//                            callbackSuccess(response, o, callback);
//                        } catch (JsonParseException e) {
//                            e.printStackTrace();
//                            callbackError(response, callback, e);
//                        }
//                    }
//
//                } else {
//                    //返回错误
//                    callbackError(response, callback, null);
//                }
//            }
//        });
//    }
//
//    /**
//     * 在主线程中执行的回调
//     */
//    private void callbackSuccess(final Response response, final Object o, final BaseCallback callback) {
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                callback.onSuccess(response, o);
//            }
//        });
//    }
//
//    /**
//     * 在主线程中执行的回调
//     */
//    private void callbackError(final Response response, final BaseCallback callback, final Exception e) {
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                callback.onError(response, response.code(), e);
//            }
//        });
//    }
//
//    /**
//     * 在主线程中执行的回调
//     */
//    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                callback.onFailure(request, e);
//            }
//        });
//    }
//
//    /**
//     * 对外公开的get方法
//     */
//    public void get(String url, BaseCallback callback) {
//        Request request = buildRequest(url, null, HttpMethodType.GET);
//        request(request, callback);
//    }
//
//    /**
//     * 对外公开的post方法
//     */
//    public void post(String url, RequestBody requestBody, BaseCallback callback) {
//        Request request = buildRequest(url, requestBody, HttpMethodType.POST);
//        request(request, callback);
//    }
//
//    /**
//     * 构建请求对象
//     */
//    private Request buildRequest(String url, RequestBody requestBody, HttpMethodType type) {
//        Request.Builder builder = new Request.Builder();
//        builder.url(url);
//        if (type == HttpMethodType.GET) {
//            builder.get();
//        } else if (type == HttpMethodType.POST) {
//            builder.post(requestBody);
//        }
//        return builder.build();
//    }
//
//    /**
//     * 通过Map的键值对构建请求对象的body
//     */
//    public RequestBody buildFormRequestBody(ArrayMap<String, String> params) {
//
//        final FormBody.Builder builder = new FormBody.Builder();
//
//        if (params != null) {
//            for (Map.Entry<String, String> entity : params.entrySet()) {
//                builder.add(entity.getKey(), entity.getValue());
//            }
//        }
//        return builder.build();
//    }
//
//    /**
//     * 这个枚举用于指明是哪一种提交方式
//     */
//    private enum HttpMethodType {
//        GET,
//        POST
//    }
//
//}
