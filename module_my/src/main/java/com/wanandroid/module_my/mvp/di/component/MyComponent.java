package com.wanandroid.module_my.mvp.di.component;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.wanandroid.module_my.mvp.contract.MyContract;
import com.wanandroid.module_my.mvp.di.module.MyModule;
import com.wanandroid.module_my.mvp.model.MyModel;
import com.wanandroid.module_my.mvp.ui.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;


@ActivityScope
@Component(modules = MyModule.class, dependencies = AppComponent.class)
public interface MyComponent {

    void inject(MyFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyComponent.Builder view(MyContract.View view);

        MyComponent.Builder appComponent(AppComponent appComponent);

        MyComponent build();
    }


}
