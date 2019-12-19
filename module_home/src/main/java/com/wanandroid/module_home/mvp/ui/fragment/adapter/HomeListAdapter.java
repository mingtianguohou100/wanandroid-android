package com.wanandroid.module_home.mvp.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;
import com.wanandroid.module_home.R;
import com.wanandroid.module_home.mvp.contract.HomeContract;
import com.wanandroid.module_home.mvp.model.entity.HomeBean;

import java.util.List;

import me.jessyan.armscomponent.commonsdk.imgaEngine.config.CommonImageConfigImpl;

public class HomeListAdapter extends BaseQuickAdapter<HomeBean.DatasBean, BaseViewHolder> {
    private ImageLoader mImageLoader;
    private AppComponent mAppComponent;

    public HomeListAdapter(HomeContract.View view,@Nullable List<HomeBean.DatasBean> data) {
        super(R.layout.home_item_layout, data);
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(view.getActivity());
        mImageLoader = mAppComponent.imageLoader();
    }



    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeBean.DatasBean item) {
        helper.setText(R.id.tv, item.getTitle());
        mImageLoader.loadImage(mContext,
                CommonImageConfigImpl
                        .builder()
                        .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576753801916&di=09d8e50a861b4213043adb841b9df0b5&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2015%2F0408%2F779334da99e40adb587d0ba715eca102.jpg")
                        .imageView(helper.getView(R.id.iv))
                        .build());
    }



}
