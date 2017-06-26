package com.atguigu.p2pmodule.adapter;

import android.content.Context;

import com.atguigu.p2pmodule.base.ProductAdapter3;
import com.atguigu.p2pmodule.holder.BaseHolder;
import com.atguigu.p2pmodule.holder.ProductHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ImpAdapter3 extends ProductAdapter3 {

    public ImpAdapter3(Context context, List list) {
        super(context, list);
    }

    @Override
    protected BaseHolder getViewHolder() {
        return new ProductHolder();
    }
}
