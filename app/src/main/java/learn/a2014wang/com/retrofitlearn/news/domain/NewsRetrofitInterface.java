package learn.a2014wang.com.retrofitlearn.news.domain;


import learn.a2014wang.com.retrofitlearn.news.bean.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by 2014wang on 2016/7/30   @desc
 */
public interface NewsRetrofitInterface {
    /**
     * 直接用retrofit去加入header,不需要用initRetrofitHeaderInterceptor
     * @param apikey
     * @return
     */
    @GET("channel_news")
    Observable<News> getNewsRxjava_A(@Header("apikey") String apikey);

    /**
     *    第二种方式加入header
     */
    @GET("channel_news")
    Observable<News> getNewsRxjava_B();

    /**
     *   第三种方式在OkHttpClient中的Interceptor中已经加入header,需要用initRetrofitHeaderInterceptor
     */
    @GET("channel_news")
    Call<News> getNews_C();
}
