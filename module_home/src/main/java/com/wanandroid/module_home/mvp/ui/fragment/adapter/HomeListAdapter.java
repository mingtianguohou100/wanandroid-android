package com.wanandroid.module_home.mvp.ui.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroid.module_home.R;
import com.wanandroid.module_home.mvp.model.entity.HomeBean;

import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<HomeBean.DatasBean, BaseViewHolder> {

    public HomeListAdapter(@Nullable List<HomeBean.DatasBean> data) {
        super(R.layout.home_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeBean.DatasBean item) {
        helper.setText(R.id.tv, item.getTitle());
    }
}
