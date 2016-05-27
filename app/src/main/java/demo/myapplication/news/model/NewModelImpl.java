package demo.myapplication.news.model;

import java.util.List;

import demo.myapplication.bean.NewsBean;
import demo.myapplication.bean.NewsDetailBean;

/**
 * Created by FT_ZSS on 2016/5/27.
 */
public class NewModelImpl implements NewModel {


    @Override
    public void loadNews(String url, int type,OnLoadNewsListListener listener) {

    }

    @Override
    public void loadNewsDetail(String docId,OnLoadNewsDetailListener listener) {

    }

    public interface OnLoadNewsListListener{

        void onSuccess(List<NewsBean> list);
        void onFailure(String msg, Exception e);
    }

    public interface OnLoadNewsDetailListener{

        void onSuccess(NewsDetailBean detailBean);
        void onFailure(String msg,Exception e);
    }
}
