package com.atguigu.p2pmodule.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.adapter.ImpAdapter3;
import com.atguigu.p2pmodule.base.BaseFragment;
import com.atguigu.p2pmodule.bean.AppNetConfig;
import com.atguigu.p2pmodule.bean.ProductBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/26.
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.lv)
    ListView lv;

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    public void setContent(String json) {
        super.setContent(json);
        ProductBean productBean = JSON.parseObject(json, ProductBean.class);

        lv.setAdapter(new ImpAdapter3(getActivity(),productBean.getData()));
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

}
