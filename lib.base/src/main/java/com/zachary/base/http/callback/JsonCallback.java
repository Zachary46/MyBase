package com.zachary.base.http.callback;


import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;
import com.zachary.base.http.Convert;
import com.zachary.base.http.HttpBean;
import com.zachary.base.http.HttpTempBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
    }

    @Override
    public T convertSuccess(Response response) throws Exception {

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];

        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");

        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        JsonReader jsonReader = new JsonReader(response.body().charStream());
        if (typeArgument == Void.class) {
            HttpTempBean httpTempBean = Convert.fromJson(jsonReader, HttpTempBean.class);
            response.close();
            return (T) httpTempBean.toLzyResponse();
        } else if (rawType == HttpBean.class) {
            HttpBean httpBean = Convert.fromJson(jsonReader, type);
            response.close();
            int code = httpBean.Status;

            if (code == 1) {
                return (T) httpBean;
            }else if (code == 2) {
                throw new IllegalStateException("请求失败");
            }else if (code == 3) {
                throw new IllegalStateException("缺少SessionId");
            } else if (code == -100) {
                throw new IllegalStateException("表单参数错误");
            } else if (code == -500) {
                throw new IllegalStateException("异常错误");
            } else if (code == 5) {
                throw new IllegalStateException("逻辑错误");
            } else {
                throw new IllegalStateException("错误代码：" + code + "，错误信息：" + httpBean.Message);
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }
    }
}