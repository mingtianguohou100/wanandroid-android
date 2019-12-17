package com.wanandroid.wanandroid_android.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.wanandroid.wanandroid_android.mvp.contract.MainContract;

import javax.inject.Inject;

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {


    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }


}
