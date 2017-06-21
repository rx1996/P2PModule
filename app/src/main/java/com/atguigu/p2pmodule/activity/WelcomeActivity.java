package com.atguigu.p2pmodule.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.common.AppManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initView();
        initData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    private void initData() {
        //跳转到主界面的第二种方法
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        },2000);
        //设置动画
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(2000);
        //设置动画监听跳转到主界面
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断账号是否登录过，如果没有登录跳转到登录界面，或者跳转到主界面
                if(isLogin()) {
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                }else {
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                }
                //清除动画
                ivWelcomeIcon.clearAnimation();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivWelcomeIcon.startAnimation(animation);
    }

    //判断是否登录过
    private boolean isLogin() {
        return true;
    }

    private void initView() {
        splashTvVersion.setText(getVersionCode());
    }

    //获取版本号
    private String getVersionCode() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
            //versionCode应用市场用来区分版本有没有更新
            int versionCode = info.versionCode;
            //versionName是给用户看的
            String versionName = info.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //如果没有版本号，默认3
        return "3";
    }
}
