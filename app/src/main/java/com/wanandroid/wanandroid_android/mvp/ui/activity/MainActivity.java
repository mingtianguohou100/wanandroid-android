package com.wanandroid.wanandroid_android.mvp.ui.activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.wanandroid.module_home.mvp.ui.fragment.HomeFragment;
import com.wanandroid.module_my.mvp.ui.fragment.MyFragment;
import com.wanandroid.wanandroid_android.R;
import com.wanandroid.wanandroid_android.di.component.DaggerMainComponent;
import com.wanandroid.wanandroid_android.mvp.contract.MainContract;
import com.wanandroid.wanandroid_android.mvp.presenter.MainPresenter;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.main_rg)
    RadioGroup main_rg;
    @Inject
    List<Fragment> mFragmentList;
    HomeFragment mHomeFragment;
    MyFragment mMyFragment;
    private Fragment mLast;
    private int mFragmentPosition;
    private long mPressedTime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        mHomeFragment= (HomeFragment) ARouter.getInstance().build(RouterHub.HOME_FRAGMENT).navigation();
        mMyFragment=(MyFragment) ARouter.getInstance().build(RouterHub.MY_FRAGMENT).navigation();
        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mMyFragment);
        switchFragmnet(0);

        main_rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    /**
     * 切换主界面
     **/
    private void switchFragmnet(int position) {
        mFragmentPosition = position;
        switcFragmenthLogic(mLast, mFragmentList.get(mFragmentPosition), getSupportFragmentManager().beginTransaction());
    }

    /**
     * 当前的Fragment没有add过就add
     * add过，就show,同时隐藏上一个Fraagment
     */
    public void switcFragmenthLogic(Fragment from, Fragment to, FragmentTransaction fragmentTransaction) {
        if (from != to) {
            mLast = to;
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    fragmentTransaction.hide(from);
                }
                //添加to
                if (to != null) {
                    fragmentTransaction.add(R.id.main_frlayout_content, to).commitAllowingStateLoss();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    fragmentTransaction.hide(from);
                }
                //显示to
                if (to != null) {
                    fragmentTransaction.show(to).commitAllowingStateLoss();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
//        RxDialog rxd=new RxDialog(this);
//        rxd.show();
        //获取第一次按键时间
        long mNowTime = System.currentTimeMillis();
        //比较两次按键时间差
        if ((mNowTime - mPressedTime) > 2000) {
            ArmsUtils.makeText(getApplicationContext(),
                    "再按一次退出" + ArmsUtils.getString(getApplicationContext(), R.string.public_app_name));
            mPressedTime = mNowTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                switchFragmnet(0);
                break;
            case R.id.rb_my:
                switchFragmnet(1);
                break;
        }
    }
}
