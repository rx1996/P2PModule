package com.atguigu.p2pmodule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2pmodule.R;
import com.atguigu.p2pmodule.base.BaseFragment;
import com.atguigu.p2pmodule.bean.AppNetConfig;
import com.atguigu.p2pmodule.bean.IndexBean;
import com.atguigu.p2pmodule.utils.HttpUtils;
import com.atguigu.p2pmodule.utils.UIUtils;
import com.atguigu.p2pmodule.view.ProgressView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/20.
 */

public class HomeFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @Bind(R.id.proView)
    ProgressView proView;

    @Override
    protected String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void setContent(String json) {
        //解析数据
        IndexBean indexBean = JSON.parseObject(json,IndexBean.class);
        initBanner(indexBean);
        initProgressView(indexBean);
    }

    @Override
    protected void initTitle() {
//        baseTitle.setText("首页");

    }



    private List<String> list = new ArrayList<>();
    public void initData() {
        loadNet();
    }

    private void loadNet() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
//            //请求成功
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//                try {
//                    parseJson(content);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //解析数据
////                IndexBean indexBean = JSON.parseObject(content, IndexBean.class);
////                Log.d("content", "onSuccess: "+indexBean.getProInfo().getName());
//            }
//
//            //请求失败
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//            }
//        });
//        HttpUtils.getInstance().get(AppNetConfig.INDEX,
//                new HttpUtils.OnHttpClientListener() {
//
//                    @Override
//                    public void onSuccess(String json) {
//                        IndexBean bean = JSON.parseObject(json, IndexBean.class);
//                        initBanner(bean);
//                        initProgressView(bean);
//                    }
//
//                    @Override
//                    public void onFailure(String message) {
//
//                    }
//                });
    }

    private void initProgressView(IndexBean bean) {
        String progress = bean.getProInfo().getProgress();

        proView.setSweepAngle(Integer.parseInt(progress));
    }

    //手动解析数据
    private void parseJson(String content) throws JSONException {
        //创建对象
        IndexBean bean = new IndexBean();
        //创建集合
        List<IndexBean.ImageArrBean> list = new ArrayList<>();
        //解析最外层的对象
        JSONObject object = new JSONObject(content);
        //获取对象中的第一个数组
        JSONArray imageArr = object.getJSONArray("imageArr");
        //遍历元素
        for(int i = 0; i < imageArr.length(); i++) {
            //创建集合里面的子元素
            IndexBean.ImageArrBean imageArrBean = new IndexBean.ImageArrBean();
            //获取元素中的对象
            JSONObject jsonObject = imageArr.getJSONObject(i);
            //解析数组中对象的元素
            String id = jsonObject.getString("ID");
            imageArrBean.setID(id);
            String imapaurl = jsonObject.getString("IMAPAURL");
            imageArrBean.setIMAPAURL(imapaurl);
            String imaurl = jsonObject.getString("IMAURL");
            imageArrBean.setIMAURL(imaurl);
            //把数组元素的对象添加到集合中
            list.add(imageArrBean);
        }
        bean.setImageArr(list);
        //获取对象中的第二个元素
        JSONObject proInfo = object.getJSONObject("proInfo");
        String name = proInfo.getString("name");
        int id = proInfo.getInt("id");
        Log.d("json", "parseJson: "+name);
    }

    private void initBanner(IndexBean indexBean) {
        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();
        for (int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            list.add(AppNetConfig.BASE_URL+imaurl);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /*
    * 加载图片类
    *
    * */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
