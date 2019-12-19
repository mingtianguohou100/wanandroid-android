package com.wanandroid.module_my.mvp.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jess.arms.di.scope.ActivityScope;

import com.wanandroid.module_my.mvp.contract.MyContract;
import com.wanandroid.module_my.mvp.model.MyModel;


import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MyModule {

    @Binds
    abstract MyContract.Model bindGoldModel(MyModel model);

    @ActivityScope
    @Provides
    static LinearLayoutManager provideLayoutManager(MyContract.View view) {
        return new LinearLayoutManager(view.getActivity(), LinearLayout.VERTICAL, false);
    }


}
