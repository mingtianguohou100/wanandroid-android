package com.wanandroid.wanandroid_android.di.module;

import android.support.v4.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.wanandroid.wanandroid_android.mvp.contract.MainContract;
import com.wanandroid.wanandroid_android.mvp.model.MainModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);

    @ActivityScope
    @Provides
    static List<Fragment> provideFragmentList() {
        return new ArrayList();
    }


}
