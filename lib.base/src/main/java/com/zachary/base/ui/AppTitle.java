package com.zachary.base.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zachary.base.R;
import com.zachary.base.utils.StringUtils;


public class AppTitle {
    public AppCompatActivity mActivity;
    public RelativeLayout rlTitle;
    private LinearLayout llCenterCentent;
    private RelativeLayout rlBack;
    private ImageView ivBack;
    private TextView tvRight;

    public AppTitle(final AppCompatActivity activity, RelativeLayout v) {
        mActivity = activity;
        rlTitle = v;
        llCenterCentent = (LinearLayout) rlTitle.findViewById(R.id.llCenterCentent);
        rlBack= (RelativeLayout) rlTitle.findViewById(R.id.rlBack);
        ivBack= (ImageView) rlTitle.findViewById(R.id.ivBack2);
        tvRight= (TextView) rlTitle.findViewById(R.id.tvRight);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }



    public AppTitle hideTitle() {
        rlTitle.setVisibility(View.GONE);
        return this;
    }

    public AppTitle showTitle() {
        rlTitle.setVisibility(View.VISIBLE);
        return this;
    }

    public AppTitle setRightText(String s) {
        tvRight.setText(s);
        return this;
    }

    public AppTitle setTitle(int resId) {
            TextView view = new TextView(mActivity);
            view.setText(resId);
            view.setTextSize(18);
            view.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
            return addCustom(view);
    }

    public AppTitle setTitle(String resId) {
        TextView view = new TextView(mActivity);
        view.setText(resId);
        view.setTextSize(18);
        view.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        return addCustom(view);
    }

    public AppTitle setTitleOrange(String resId) {
        TextView view = new TextView(mActivity);
        view.setText(resId);
        view.setTextSize(18);
        view.setTextColor(ContextCompat.getColor(mActivity, R.color.orange));
        return addCustom(view);
    }


    public AppTitle setBackLogo(int resId) {
        ivBack.setImageResource(resId);
        return this;
    }

   public AppTitle setTitle(String title, int color ) {
        if (!StringUtils.isEmpty(title)) {
            TextView view = new TextView(mActivity);
            view.setText(title);
            view.setSingleLine();
            view.setTextSize(18);
            view.setTextColor(ContextCompat.getColor(mActivity, color));
            return addCustom(view);
        }
        return this;
    }

    public AppTitle setTitleBackGround(int color ) {
       rlTitle.setBackgroundColor(color);
       return this;
    }


    public AppTitle addCustom(View v) {
        llCenterCentent.removeAllViews();
        llCenterCentent.addView(v);
        llCenterCentent.setVisibility(View.VISIBLE);
        return this;
    }

    public AppTitle hideCustom() {
        llCenterCentent.setVisibility(View.GONE);
        return this;
    }

}
