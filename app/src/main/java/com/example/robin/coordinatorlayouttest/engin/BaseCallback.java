package com.example.robin.coordinatorlayouttest.engin;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 基本的回调类
 * 本来可以定义为接口, 但是为了写 Type, 只能定义为了抽象类
 */
public abstract class BaseCallback<T> {

    Type mType; // type 用于方便 json的解析

    // 把 type 转换成对应的类
    private static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    // 构造的时候获得 type 的 class
    protected BaseCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }

    /**
     * 访问网络之前调用, 比如弹一个对话框 或者 进度条
     */
//    public abstract void onRequestBefore();
    public abstract void onLinNetBefore();

    /**
     * 请求失败调用(网络或者服务器连接问题)
     */
//    public abstract void onFailure(Request request, Exception e);
    public abstract void onFailure(Call call, Exception e);

    /**
     * 请求成功而且没有错误的时候调用
     */
//    public abstract void onSuccess(Response response, Object o);
    public abstract void onSuccess(Call call, Response response, T t);

    /**
     * 请求成功但是有错误的时候调用, 例如 json 解析错误 或者 IO 错误..等
     */
//    public abstract void onError(Response response, int errorCode, Exception e);
    public abstract void onError(Call call, Response response, Exception e);

}
