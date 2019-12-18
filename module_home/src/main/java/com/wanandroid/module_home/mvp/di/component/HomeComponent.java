package com.wanandroid.module_home.mvp.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.wanandroid.module_home.mvp.contract.HomeContract;
import com.wanandroid.module_home.mvp.di.module.HomeModule;
import com.wanandroid.module_home.mvp.ui.fragment.HomeFragment;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(HomeFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeComponent.Builder view(HomeContract.View view);
        HomeComponent.Builder appComponent(AppComponent appComponent);
        HomeComponent build();
    }


}
