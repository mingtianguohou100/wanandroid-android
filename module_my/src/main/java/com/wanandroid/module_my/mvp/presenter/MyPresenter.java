package com.wanandroid.wanandroid_android.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.wanandroid.wanandroid_android.mvp.contract.MyContract;

import javax.inject.Inject;

public class MyPresenter extends BasePresenter<MyContract.Model, MyContract.View> {
    @Inject
    public MyPresenter(MyContract.Model model, MyContract.View rootView) {
        super(model, rootView);
    }


}
