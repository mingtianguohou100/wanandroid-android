package com.wanandroid.module_home.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.wanandroid.module_home.mvp.contract.HomeContract;
import com.wanandroid.module_home.mvp.model.api.HomeService;
import com.wanandroid.module_home.mvp.model.entity.HomeBean;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;
@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<BaseResponse<HomeBean>> getHomeList(int page) {
        return mRepositoryManager
                .obtainRetrofitService(HomeService.class)
                .getGoldList(1);
    }

}
