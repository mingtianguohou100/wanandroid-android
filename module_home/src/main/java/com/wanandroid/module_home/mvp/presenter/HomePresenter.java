package com.wanandroid.module_home.mvp.presenter;

import android.util.Log;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.wanandroid.module_home.mvp.contract.HomeContract;
import com.wanandroid.module_home.mvp.model.entity.HomeBean;
import com.wanandroid.module_home.mvp.ui.fragment.adapter.HomeListAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    HomeListAdapter mHomeListAdapter;
    @Inject
    List<HomeBean.DatasBean> mData;

    private int lastPage = 1;
    private int preEndIndex;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }


    public void requestDatas(final boolean pullToRefresh) {
        if (pullToRefresh) lastPage = 1;//下拉刷新默认只请求第一页

        mModel.getHomeList(lastPage)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
//                .doOnSubscribe(disposable -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.startLoadMore();//显示上拉加载更多的进度条
//                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh)
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
                    else
                        mRootView.endLoadMore();//隐藏上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseResponse<HomeBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<HomeBean> data) {
                        List<HomeBean.DatasBean> homeBean = data.getResults().getDatas();
                        lastPage = lastPage + 1;
                        if (pullToRefresh) mData.clear();//如果是下拉刷新则清空列表
                        preEndIndex = homeBean.size();//更新之前列表总长度,用于确定加载更多的起始位置
                        mData.addAll(homeBean);
                        if (pullToRefresh)
                            mHomeListAdapter.notifyDataSetChanged();
                        else
                            mHomeListAdapter.notifyItemRangeInserted(preEndIndex, homeBean.size());
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
