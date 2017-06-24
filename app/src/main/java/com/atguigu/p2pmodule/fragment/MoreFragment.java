package com.atguigu.p2pmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.base.BaseFragment;
import com.atguigu.p2pmodule.bean.AppNetConfig;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MoreFragment extends BaseFragment {

    @Override
    protected String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void setContent(String json) {

    }
    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
