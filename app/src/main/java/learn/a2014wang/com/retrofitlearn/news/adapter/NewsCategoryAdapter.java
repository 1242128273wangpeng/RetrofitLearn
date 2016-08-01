package learn.a2014wang.com.retrofitlearn.news.adapter;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import learn.a2014wang.com.retrofitlearn.R;
import learn.a2014wang.com.retrofitlearn.news.bean.News;

/**
 * Created by 2014wang on 2016/8/1   @desc
 */
public class NewsCategoryAdapter extends BaseAdapter {
    private List<News.ShowapiResBodyEntity.ChannelListEntity> mlistEntities;
    private Context mcontext;
    private LayoutInflater layoutInflater;

    public NewsCategoryAdapter(Context context, List<News.ShowapiResBodyEntity.ChannelListEntity> listEntities) {
        this.mlistEntities = listEntities;
        this.mcontext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mlistEntities != null) {
            return mlistEntities.size();
        } else {
            return 0;
        }
    }

    @Override
    public News.ShowapiResBodyEntity.ChannelListEntity getItem(int position) {
        return mlistEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_newscategory, parent, false);
            viewHolder.catebut = (Button) convertView.findViewById(R.id.categorybut);
            convertView.setTag(viewHolder);
        }else{
          viewHolder  = (ViewHolder) convertView.getTag();
        }
        News.ShowapiResBodyEntity.ChannelListEntity entity = mlistEntities.get(position);
        viewHolder.catebut.setText(entity.getName());
        return convertView;
    }

    class ViewHolder {
        Button catebut;
    }
}
