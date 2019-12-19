package com.wanandroid.module_my.mvp.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.blankj.utilcode.util.AppUtils;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.wanandroid.module_my.mvp.contract.MyContract;
import com.wanandroid.module_my.mvp.model.api.Api;
import com.wanandroid.module_my.mvp.model.entity.VersionBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.armscomponent.commonsdk.utils.Utils;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class MyPresenter extends BasePresenter<MyContract.Model, MyContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;


    @Inject
    public MyPresenter(MyContract.Model model, MyContract.View rootView) {
        super(model, rootView);
    }


    /**
     * 内部网络请求版本信息例子
     **/
    public void requestVersionInfo() {
        RetrofitUrlManager.getInstance().putDomain("pigcome", Api.BASE_URL);
        mModel.getVersionInfo()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<VersionBean>(mErrorHandler) {
                    @Override
                    public void onNext(VersionBean data) {
                        mRootView.versionData(data);
                    }

                });
    }


    /**
     * 直接使用框架请求版本信息例子
     **/
    public void defultRequestVersionInfo() {
        String url = "http://www.pigcome.com:81/version";
        AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestMethod(HttpRequestMethod.GET)
                .setRequestUrl(url)
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(DownloadBuilder downloadBuilder, String result) {
                        Log.e("asldjlkasd}",result);
                        //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                        //如果是最新版本直接return null
                        Gson gson = new Gson();
                        VersionBean versionBean = gson.fromJson(result, VersionBean.class);
                        String newApkVersion = versionBean.getData().getVersion();
                        String downloadUrl = versionBean.getData().getAddress();
                        String versionMessage = versionBean.getData().getContext();
                        int check = Utils.compareVersion(newApkVersion, AppUtils.getAppVersionName());

                        Log.e("asdalskjfklasf",check+","+versionBean.toString());
                        UIData uiData = UIData
                                .create()
                                .setDownloadUrl(downloadUrl)
                                .setTitle("升级")
                                .setContent(versionMessage);
                        if (check == 1) {
                            return uiData;
                        } else {
                            return null;
                        }

                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        Log.e("asdasfqwf${}",message);
                    }
                })
                .executeMission(mRootView.getActivity());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        AllenVersionChecker.getInstance().cancelAllMission();
    }


}
