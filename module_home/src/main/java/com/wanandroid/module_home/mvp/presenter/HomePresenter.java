package com.wanandroid.module_home.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.wanandroid.module_home.mvp.contract.HomeContract;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }


}
