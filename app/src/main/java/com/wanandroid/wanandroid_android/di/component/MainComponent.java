
package com.wanandroid.wanandroid_android.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.wanandroid.wanandroid_android.di.module.MainModule;
import com.wanandroid.wanandroid_android.mvp.contract.MainContract;
import com.wanandroid.wanandroid_android.mvp.ui.activity.MainActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder view(MainContract.View view);
        Builder appComponent(AppComponent appComponent);
        MainComponent build();
    }
}
