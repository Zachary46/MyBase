package com.zachary.base.http.callback;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.lzy.okgo.request.BaseRequest;
import com.zachary.base.ui.LoadingDialog;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    private LoadingDialog dialog;

    public DialogCallback(Activity activity) {
        super();
        dialog=new LoadingDialog(activity);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
