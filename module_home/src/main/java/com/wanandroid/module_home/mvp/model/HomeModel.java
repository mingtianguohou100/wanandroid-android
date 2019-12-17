package com.wanandroid.module_home.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.wanandroid.module_home.mvp.contract.HomeContract;

public class HomeModel extends BaseModel implements HomeContract.Model {

    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
