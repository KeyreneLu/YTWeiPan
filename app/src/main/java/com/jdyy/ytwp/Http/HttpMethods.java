package com.jdyy.ytwp.Http;

import android.util.Log;

import com.jdyy.ytwp.Http.Api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class HttpMethods {

    public static final String BASE_URL = "http://gank.io/";
//    https://api.douban.com/v2/movie/
//    http://m1.judayouyuan.com/
//    http://gank.io/
    public static  final int DEFAULT_TIME = 5;

    private Retrofit mRetrofit;

    private ApiService mApiService;

    private HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    private static class singletonHodler{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return singletonHodler.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
//     * @param subscriber 由调用者传过来的观察者对象
//     * @param start 起始位置
//     * @param count 获取长度
     */
//    public void getTopMoview(Subscriber<MovieEntity> subscriber,int start,int count){
//        mApiService.getTopMovie(start,count)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

//    public void getTopMovie3(Subscriber<List<Subject>> subscriber, int start, int count){
//
//        mApiService.getTopMovie2(start,count)
//                .map(new HttpResultFun<List<Subject>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }


//    public void  getAllState2(Subscriber<Result> subscriber, String g, String m, String a){
//
//        mApiService.getAllState(g,m,a)
//                .map(new HttpResultFun<Result>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

//    public void getAllMessage(Subscriber<List<Result>> subscriber, String type, int count, int page){
//
//        mApiService.getMessage(type,count,page)
//                .map(new HttpResultFun<List<Result>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    private class HttpResultFun<T> implements Func1<HttpResult<T>,T>{
        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getError().equals("")){
                throw new ApiException(tHttpResult.getError());
            }
            Log.e("66666666666666666666",tHttpResult.toString());
            return tHttpResult.getResults();
        }
    }
}
