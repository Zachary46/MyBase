package com.zachary.base.http.callback;

import android.app.Activity;
import android.support.annotation.Nullable;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.zachary.base.ui.LoadingDialog;

public abstract class StringDialogCallback extends StringCallback {

    private LoadingDialog dialog;

    public StringDialogCallback(Activity activity) {
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
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
