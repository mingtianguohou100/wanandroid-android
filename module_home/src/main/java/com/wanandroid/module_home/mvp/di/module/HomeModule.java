package com.wanandroid.module_home.mvp.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;
import com.wanandroid.module_home.mvp.contract.HomeContract;
import com.wanandroid.module_home.mvp.model.HomeModel;
import com.wanandroid.module_home.mvp.model.entity.HomeBean;
import com.wanandroid.module_home.mvp.ui.fragment.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class HomeModule {
    @Binds
    abstract HomeContract.Model bindGoldModel(HomeModel model);

    @ActivityScope
    @Provides
    static LinearLayoutManager provideLayoutManager(HomeContract.View view) {
        return new LinearLayoutManager(view.getActivity(), LinearLayout.VERTICAL, false);
    }

    @ActivityScope
    @Provides
    static List<HomeBean.DatasBean> provideGoldHomeData() {
        return new ArrayList();
    }



    @ActivityScope
    @Provides
    static HomeListAdapter provideGoldHomeAdapter(HomeContract.View view,List<HomeBean.DatasBean> data) {
        return new HomeListAdapter(view,data);
    }
}
