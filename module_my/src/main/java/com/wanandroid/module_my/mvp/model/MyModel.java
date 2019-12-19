package com.wanandroid.module_my.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.wanandroid.module_my.mvp.contract.MyContract;
import com.wanandroid.module_my.mvp.model.api.MyService;
import com.wanandroid.module_my.mvp.model.entity.VersionBean;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class MyModel extends BaseModel implements MyContract.Model {
    @Inject
    public MyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<VersionBean> getVersionInfo() {
        return mRepositoryManager
                .obtainRetrofitService(MyService.class)
                .checkVersion();
    }
}
