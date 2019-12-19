package com.wanandroid.module_my.mvp.model.api;

import com.wanandroid.module_my.mvp.model.entity.VersionBean;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

//http://www.pigcome.com:81/version
public interface MyService {
    /**
     * 检测版本
     */
    @Headers({"Domain-Name: pigcome"})
    @GET("version")
    Observable<VersionBean> checkVersion();
}
