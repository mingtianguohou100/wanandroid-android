package com.wanandroid.module_my.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.wanandroid.module_my.mvp.model.entity.VersionBean;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;

public interface MyContract {
    interface View extends IView {
        void versionData(VersionBean data);

        Activity getActivity();
    }

    interface Model extends IModel {
        Observable<VersionBean> getVersionInfo();

    }
}
