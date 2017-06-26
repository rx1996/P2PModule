package com.atguigu.p2pmodule.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.base.ProductAdapter2;
import com.atguigu.p2pmodule.bean.ProductBean;
import com.atguigu.p2pmodule.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ImpAdapter2 extends ProductAdapter2<ProductBean.DataBean> {

    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    ProgressView pProgresss;

    public ImpAdapter2(Context context, List<ProductBean.DataBean> list) {
        super(context, list);
    }


    @Override
    protected void setData(ProductBean.DataBean dataBean) {
        pName.setText(dataBean.getName());
    }

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.product_item, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
