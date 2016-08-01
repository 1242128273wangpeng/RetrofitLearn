package learn.a2014wang.com.retrofitlearn.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import learn.a2014wang.com.retrofitlearn.R;
import learn.a2014wang.com.retrofitlearn.news.adapter.NewsCategoryAdapter;
import learn.a2014wang.com.retrofitlearn.news.bean.News;
import learn.a2014wang.com.retrofitlearn.news.domain.Constants;
import learn.a2014wang.com.retrofitlearn.news.domain.NewsRetrofitService;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NewsActivity extends AppCompatActivity {
    private GridView mgridview;
    private List<News.ShowapiResBodyEntity.ChannelListEntity> entitys;
    private NewsCategoryAdapter newsCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        entitys = new ArrayList<News.ShowapiResBodyEntity.ChannelListEntity>();
        mgridview = (GridView) findViewById(R.id.gridview);
        NewsRetrofitService.getNewRetrofitInstance(this, NewsRetrofitService.SIMPLE).getNewsRxjava_A(Constants.APIKEY)
                .map(new Func1<News, List<News.ShowapiResBodyEntity.ChannelListEntity>>() {
                    @Override
                    public List<News.ShowapiResBodyEntity.ChannelListEntity> call(News news) {
                        return news.getShowapi_res_body().getChannelList();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<News.ShowapiResBodyEntity.ChannelListEntity>>() {
            @Override
            public void call(List<News.ShowapiResBodyEntity.ChannelListEntity> listEntities) {
                newsCategoryAdapter = new NewsCategoryAdapter(NewsActivity.this, listEntities);
                mgridview.setAdapter(newsCategoryAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (newsCategoryAdapter != null) {
            mgridview.setAdapter(newsCategoryAdapter);
        }
    }
}
