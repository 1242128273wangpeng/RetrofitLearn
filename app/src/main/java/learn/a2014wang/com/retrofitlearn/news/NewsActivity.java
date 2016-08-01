package learn.a2014wang.com.retrofitlearn.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import learn.a2014wang.com.retrofitlearn.R;
import learn.a2014wang.com.retrofitlearn.news.domain.Constants;
import learn.a2014wang.com.retrofitlearn.news.domain.NewsRetrofitService;

public class NewsActivity extends AppCompatActivity {
    private ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        NewsRetrofitService.getNewRetrofitInstance(this, NewsRetrofitService.SIMPLE).getNewsRxjava_A(Constants.APIKEY);
//        NewsRetrofitService.getNewRetrofitInstance(this,NewsRetrofitService.HAVEHEADER).getNewsRxjava_B();
//     NewsRetrofitService.getNewRetrofitInstance(this,NewsRetrofitService.HAVEHEADER).getNews_C();
    }
}
