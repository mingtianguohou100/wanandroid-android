package com.wanandroid.module_home.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanandroid.module_home.R;
import com.wanandroid.module_home.R2;
import com.wanandroid.module_home.mvp.contract.HomeContract;
import com.wanandroid.module_home.mvp.di.component.DaggerHomeComponent;
import com.wanandroid.module_home.mvp.presenter.HomePresenter;
import com.wanandroid.module_home.mvp.ui.fragment.adapter.HomeListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.HOME_FRAGMENT)
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R2.id.homeFragmentSrl)
    SmartRefreshLayout mHomeFragmentSrl;
    @BindView(R2.id.homeFragmentRv)
    RecyclerView mHomeFragmentRv;

    @Inject
    HomeListAdapter mHomeListAdapter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mHomeFragmentSrl.setOnRefreshListener(this);
        mHomeFragmentSrl.setOnLoadMoreListener(this);
        mPresenter.requestDatas(true);
        mHomeFragmentRv.setAdapter(mHomeListAdapter);
        mHomeFragmentRv.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void endLoadMore() {
        mHomeFragmentSrl.finishLoadMore();
    }

    @Override
    public void hideLoading() {
        mHomeFragmentSrl.finishRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.requestDatas(true);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.requestDatas(false);
    }



}
