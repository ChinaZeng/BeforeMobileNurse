package com.shine.mobilenurse.net;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zzw on 2016/11/21.
 * 描述:
 */

public interface ApiService {

//    /**
//     * @GET 表明方法是 get请求
//     * "/api" 请求的接口,请注意前面的/符号
//     * @Query 表示这是一个参数
//     * Call<ResponseBody> :Call是必须的,ResponseBody是Retrofit默认的返回数据类型,也就是String体
//     */
//
//    @GET("/api")
//    Call<ResponseBean> getApi(@Query("pa1") String va1, @Query("ba1") String va2);
//    //getApi方法,等效于: http://192.168.1.12:8082/api?pa1=va1&ba1=va2
//
//
//    /**
//     * @POST 请求方式post
//     * @Body 表示将requestBean对象转成成json string作为参数传递给后台
//     */
//    @POST("/api")
//    Call<ResponseBean> postApi(@Body RequestBean requestBean);
//
//
//    /**
//     * @QueryMap 表示将map类型的params对象,转成键值对的形式作为参数传递给后台
//     */
//    @GET("/api")
//    Call<ResponseBody> getApiString(@QueryMap Map<String, String> params);
//
//    @POST("/api")
//    Call<ResponseBody> postApiString(@Body RequestBean requestBean);
//
//    /**
//     * Observable 是RxJava的关键点,其他不变
//     */
//    @GET("/api")
//    Observable<ResponseBody> getRxApiString(@QueryMap Map<String, String> params);
//
//    @POST("/api")
//    Observable<ResponseBean> postRxApiString(@Body RequestBean requestBean);
}
