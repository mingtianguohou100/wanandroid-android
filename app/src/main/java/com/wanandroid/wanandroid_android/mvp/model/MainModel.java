package com.wanandroid.wanandroid_android.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.wanandroid.wanandroid_android.mvp.contract.MainContract;

import javax.inject.Inject;

@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
