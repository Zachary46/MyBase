package com.zachary.base.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

/*******group中的权限其中一个被授权，同一group的其它权限将自动被授权********
 *
 *group:android.permission-group.CONTACTS
        permission:android.permission.WRITE_CONTACTS
        permission:android.permission.GET_ACCOUNTS
        permission:android.permission.READ_CONTACTS

  group:android.permission-group.PHONE
        permission:android.permission.READ_CALL_LOG
        permission:android.permission.READ_PHONE_STATE
        permission:android.permission.CALL_PHONE
        permission:android.permission.WRITE_CALL_LOG
        permission:android.permission.USE_SIP
        permission:android.permission.PROCESS_OUTGOING_CALLS
        permission:com.android.voicemail.permission.ADD_VOICEMAIL

  group:android.permission-group.CALENDAR
        permission:android.permission.READ_CALENDAR
        permission:android.permission.WRITE_CALENDAR

  group:android.permission-group.CAMERA
        permission:android.permission.CAMERA

  group:android.permission-group.SENSORS
        permission:android.permission.BODY_SENSORS

  group:android.permission-group.LOCATION
        permission:android.permission.ACCESS_FINE_LOCATION
        permission:android.permission.ACCESS_COARSE_LOCATION

  group:android.permission-group.STORAGE
        permission:android.permission.READ_EXTERNAL_STORAGE
        permission:android.permission.WRITE_EXTERNAL_STORAGE

  group:android.permission-group.MICROPHONE
        permission:android.permission.RECORD_AUDIO

  group:android.permission-group.SMS
        permission:android.permission.READ_SMS
        permission:android.permission.RECEIVE_WAP_PUSH
        permission:android.permission.RECEIVE_MMS
        permission:android.permission.RECEIVE_SMS
        permission:android.permission.SEND_SMS
        permission:android.permission.READ_CELL_BROADCASTS
*/
    public static List<String> permissions=new ArrayList<>();
    public static final int PERMISSIONS_REPUEST=521;
    public static final int PERMISSION_REPUEST=520;

    public static boolean requestPermissions(List<String> temps,Activity activity){
        for (int i=0;i<temps.size();i++){
            if (ContextCompat.checkSelfPermission(activity, temps.get(i)) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(temps.get(i));
            }
        }
        if (permissions.size()!=0){
            String[] p=permissions.toArray(new String[permissions.size()]);
            ActivityCompat.requestPermissions(activity, p, PERMISSIONS_REPUEST);
            return false;
        }else {
            return true;
        }
    }

    public static boolean requestPermission(String permission,Activity activity){
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSION_REPUEST);
            return false;
        }else {
            return true;
        }
    }

   /* public static boolean requestFileWrite(Activity mActivity){
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE);
            return false;
        }else {
            return true;
        }
    }
    public static boolean requestCamera(Activity mActivity){
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA},
                    CAMERA);
            return false;
        }else {
            return true;
        }
    }

    public static boolean requestAudio(Activity mActivity){
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO);
            return false;
        }else {
            return true;
        }
    }


    public static boolean requestFileWrite(Fragment context){
        if (ContextCompat.checkSelfPermission(context.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
            return false;
        }else {
            return true;
        }
    }

    public static boolean requestCamera(Fragment context){
        if (ContextCompat.checkSelfPermission(context.getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请CAMERA权限
            context.requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA);
            return false;
        }else {
            return true;
        }
    }*/
}
