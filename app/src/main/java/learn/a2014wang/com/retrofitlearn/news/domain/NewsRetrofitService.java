package learn.a2014wang.com.retrofitlearn.news.domain;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import learn.a2014wang.com.retrofitlearn.news.bean.News;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 2014wang on 2016/7/30   @desc
 */
public class NewsRetrofitService implements NewsRetrofitInterface {
    private static Context mcontext;
    private OkHttpClient client;
    private NewsRetrofitInterface newsRetrofitInterface;
    private Retrofit retrofit;
    public static final int SIMPLE = 1;
    public static final int HAVEHEADER = 2;
    private static NewsRetrofitService newsretrofit;
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private NewsRetrofitService(int choose) {
        // 选择的是不包含Header的initRetrofi
        if (choose == SIMPLE) {
            initRetrofi();
            //  选择的是包含Header的NewsRetrofitInterface;
        } else if (choose == HAVEHEADER) {
            initRetrofitHeaderInterceptor();
        } else {
            initRetrofitHeaderInterceptor();
        }
    }

    public static NewsRetrofitService getNewRetrofitInstance(Context rcontext, int choose) {
        mcontext = rcontext;
        if (newsretrofit == null) {
            synchronized (NewsRetrofitService.class) {
                if (newsretrofit == null) {
                    newsretrofit = new NewsRetrofitService(choose);
                }
            }
        }
        return newsretrofit;
    }

    class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            originalRequest = originalRequest.newBuilder()
                    .addHeader("apikey", Constants.APIKEY)
                    .build();
            Response response = chain.proceed(originalRequest);
            return response;
        }
    }

    /**
     * 在OkhttpClient中加入Header拦截，将Header所需参数(APIKEY)写入
     */
    private OkHttpClient getClientWithHeaderInterceptor() {
        client = new OkHttpClient.Builder().addNetworkInterceptor(new HeaderInterceptor()).build();
        return client;
    }

    /**
     * 创建的NewsRetrofitInterface带有OkHttpClient的Header拦截,已经写入(APIKEY)
     */
    private void initRetrofitHeaderInterceptor() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .client(getClientWithHeaderInterceptor())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        newsRetrofitInterface = retrofit.create(NewsRetrofitInterface.class);
    }

    /**
     * 普通的创建方式OkHttpClient,没有添加APIKEY到Header中
     *
     * @return
     */
    private OkHttpClient getClient() {
        client = new OkHttpClient();
        return client;
    }

    /**
     * 普通的创建方式NewsRetrofitInterface,没有添加APIKEY到Header中
     */
    private void initRetrofi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        newsRetrofitInterface = retrofit.create(NewsRetrofitInterface.class);
    }

    @Override
    public Observable<News> getNewsRxjava_A(@Header("apikey") String apikey) {
        Observable<News> observable = newsRetrofitInterface.getNewsRxjava_A(apikey);
        observable.subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<News>() {
            @Override
            public void onCompleted() {
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("失败");
            }

            @Override
            public void onNext(News news) {
                System.out.println("news->" + news.toString());
            }
        });

        return observable;
    }

    @Override
    public Observable<News> getNewsRxjava_B() {
        Observable<News> observable = newsRetrofitInterface.getNewsRxjava_B();
        observable.subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<News>() {
            @Override
            public void onCompleted() {
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("失败");
            }

            @Override
            public void onNext(News news) {
                System.out.println("news->" + news);
            }
        });
        return observable;
    }

    @Override
    public Call<News> getNews_C() {
        Call<News> call = newsRetrofitInterface.getNews_C();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, retrofit2.Response<News> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return call;
    }
}

