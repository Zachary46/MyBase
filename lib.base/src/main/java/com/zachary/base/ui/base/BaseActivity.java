package com.zachary.base.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.zachary.base.R;
import com.zachary.base.http.NetEvent;
import com.zachary.base.receiver.NetReceiver;
import com.zachary.base.ui.AppTitle;
import com.zachary.base.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 *Author:Zachary
 *Time:2017/3/20 0019 上午 11:01
 *Dec:基类
 *Call:0592-3278796
 *Email:developer@baogukeji.com
 *Web:www.baogukeji.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener {

    private AppTitle appTitle;
    private RelativeLayout mRlContent,rlNetNotice;
    private RelativeLayout rlTitle;
    private SparseArray<View> mViews;
    private NetReceiver mReceiver;
    public AppTitle getAppTitle() {
        if (null == appTitle) {
            appTitle = new AppTitle(BaseActivity.this, rlTitle);
        }
        return appTitle;
    }

    public abstract int getLayoutId();
    public abstract void initialize();
    public abstract void initListener();
    public abstract void initData();
    public void netState(boolean isConnect){

    }
    public abstract void viewClick(View v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    /*| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION*/);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   /* | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*/
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        mViews=new SparseArray<>();
        EventBus.getDefault().register(this);
        setContentView(R.layout.ac_base);
        mRlContent = (RelativeLayout) findViewById(R.id.rlContent);
        rlNetNotice = (RelativeLayout) findViewById(R.id.rlNetNotice);
        rlNetNotice.setOnClickListener(this);
        rlTitle= (RelativeLayout) findViewById(R.id.rlTitle);
        findViewById(R.id.tvRight).setOnClickListener(this);//设置右上角按钮点击事件
        View v = getLayoutInflater().inflate(getLayoutId(), mRlContent, false);
        mRlContent.addView(v);
        initReceive();
        initialize();
        initData();
        initListener();
    }

    /**
     * 初始化网络监听
     * */
    private void initReceive() {
        mReceiver = new NetReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }
    /**
     * 监听网络
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(NetEvent event){
        setNetState(event.isNet());
        netState(event.isNet());
    }

    public void setNetState(boolean netState) {
        if (rlNetNotice != null) {
            rlNetNotice.setVisibility(netState ? View.GONE : View.VISIBLE);
        }
    }

    /**通过Id找到View*/
    public <E extends View> E findView(int viewId){
        E view= (E) mViews.get(viewId);
        if (view==null){
            view= (E) findViewById(viewId);
            mViews.put(viewId,view);
        }
        return view;
    }

    /**View设置点击事件*/
    public <E extends View> void setOnClick(E view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.rlNetNotice){
            NetUtils.startToSettings(BaseActivity.this);
        }
        viewClick(v);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.tl_in_left_to_right, R.anim.tl_out_right_to_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.tl_in_left_to_right, R.anim.tl_out_right_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.tl_in_right_to_left, R.anim.tl_out_left_to_right);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
