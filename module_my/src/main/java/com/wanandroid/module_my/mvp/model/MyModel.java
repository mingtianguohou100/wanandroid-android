package com.wanandroid.wanandroid_android.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.wanandroid.wanandroid_android.mvp.contract.MyContract;

public class MyModel extends BaseModel implements MyContract.Model {

    public MyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
