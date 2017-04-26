package com.example.fast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOneBtnClick(View view) {
        /*if (PluginManager.getLogin() != null) {
            PluginManager.getLogin().startActivity(this);
        }*/
        Intent intent=new Intent();
        intent.setClassName(this,"com.lib.login.activity.LoginActivity");
        startActivity(intent);
    }
}
