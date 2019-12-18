package com.wanandroid.module_home.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.wanandroid.module_home.mvp.model.entity.HomeBean;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;

public interface HomeContract {
    interface View extends IView {
        void endLoadMore();
        Activity getActivity();
    }

    interface Model extends IModel {
        Observable<BaseResponse<HomeBean>> getHomeList(int page);

    }
}
