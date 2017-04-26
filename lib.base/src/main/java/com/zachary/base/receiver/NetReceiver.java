package com.zachary.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.zachary.base.http.NetEvent;
import com.zachary.base.utils.NetUtils;
import com.zachary.base.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 *Author:Zachary
 *Time:2017/4/19 0019 下午 2:27
 *Dec:监听网络状态改变
 *Call:0592-3278796
 *Email:developer@baogukeji.com
 *Web:www.baogukeji.com
 */
public class NetReceiver extends BroadcastReceiver {
	//防止每次进入页面都吐司“网络已连接”
	private boolean isDisconnect=false;
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
			boolean isConnected = NetUtils.isNetworkConnected(context);
			if (isConnected) {
				EventBus.getDefault().post(new NetEvent(true));
				if (isDisconnect){
					ToastUtil.showToast(context,"网络已连接");
				}
			} else {
				isDisconnect=true;
				EventBus.getDefault().post(new NetEvent(false));
				ToastUtil.showToast(context,"网络已断开");
			}
		}
	}

}
