package com.wanandroid.module_my.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.wanandroid.module_my.R;
import com.wanandroid.module_my.R2;
import com.wanandroid.module_my.mvp.contract.MyContract;
import com.wanandroid.module_my.mvp.di.component.DaggerMyComponent;
import com.wanandroid.module_my.mvp.model.entity.VersionBean;
import com.wanandroid.module_my.mvp.presenter.MyPresenter;
import butterknife.OnClick;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.MY_FRAGMENT)
public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMyComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @OnClick({R2.id.btnCheckUpd, R2.id.btnInternationalization, R2.id.btnTheme})
    void onCLick(View v) {
        switch (v.getId()) {
            case R2.id.btnCheckUpd:
                mPresenter.defultRequestVersionInfo();
                mPresenter.requestVersionInfo();
                break;
            case R2.id.btnInternationalization:

                break;
            case R2.id.btnTheme:

                break;
        }
    }

    @Override
    public void versionData(VersionBean data) {
        String apkDownAddress= data.getData().getAddress();
        String newApkVersion= data.getData().getVersion();
        String versionMessage= data.getData().getContext();

    }



}
