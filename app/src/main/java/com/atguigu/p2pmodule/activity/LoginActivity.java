package com.atguigu.p2pmodule.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.common.AppManager;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppManager.getInstance().addActivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }
}
