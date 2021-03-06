package com.atguigu.p2pmodule.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.activity.gesture.GestureVerifyActivity;
import com.atguigu.p2pmodule.base.BaseActivity;
import com.atguigu.p2pmodule.common.AppManager;
import com.atguigu.p2pmodule.fragment.HomeFragment;
import com.atguigu.p2pmodule.fragment.InvestFragment;
import com.atguigu.p2pmodule.fragment.MoreFragment;
import com.atguigu.p2pmodule.fragment.PropertyFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.rb_main)
    RadioButton rbMain;
    @Bind(R.id.rb_invest)
    RadioButton rbInvest;
    @Bind(R.id.rb_propert)
    RadioButton rbPropert;
    @Bind(R.id.rb_more)
    RadioButton rbMore;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    public void initListener() {

        //radioGroup监听
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switchFrgment(checkedId);
            }
        });
    }

    /*
    * 切换fragment
    *
    * */
    public void switchFrgment(int checkedId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);

        switch (checkedId){
            case R.id.rb_invest:
                if (investFragment == null){
                    investFragment = new InvestFragment();
                    transaction.add(R.id.main_fl,investFragment);
                }else{
                    transaction.show(investFragment);
                }
                break;
            case R.id.rb_main:
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_fl,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
            case R.id.rb_more:
                if (moreFragment == null){
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_fl,moreFragment);
                }else{
                    transaction.show(moreFragment);
                }
                break;
            case R.id.rb_propert:
                if (propertyFragment == null){
                    propertyFragment = new PropertyFragment();
                    transaction.add(R.id.main_fl,propertyFragment);
                }else{
                    transaction.show(propertyFragment);
                }
                break;
        }
        transaction.commit();
    }

    /*
    * 隐藏fragment
    *
    * */
    private void hidden(FragmentTransaction transaction) {
        if (investFragment != null){
            transaction.hide(investFragment);
        }
        if (moreFragment != null){
            transaction.hide(moreFragment);
        }
        if (homeFragment != null){
            transaction.hide(homeFragment);
        }
        if (propertyFragment != null){
            transaction.hide(propertyFragment);
        }

    }

    public void initData() {

        //验证手势密码是否开启
        boolean toggle = get("toggle");
        if (toggle){
            startActivity(new Intent(this, GestureVerifyActivity.class));
        }

    }

    public void initView() {

        switchFrgment(R.id.rb_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    /*
    * 双击退出
    * */
    private boolean isExit = false;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            if (isExit){
                finish();
            }
            Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
            return true;
        }
        return super.onKeyUp(keyCode,event);
    }
}
