package com.wanandroid.module_home.mvp.model.api;

import com.wanandroid.module_home.mvp.model.entity.HomeBean;


import io.reactivex.Observable;
import me.jessyan.armscomponent.commonservice.entity.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface HomeService {
    /**
     * 首页文章列表
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<HomeBean>> getGoldList(@Path("page") int page);
}
