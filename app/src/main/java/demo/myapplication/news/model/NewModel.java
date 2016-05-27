package demo.myapplication.news.model;

/**
 * Created by FT_ZSS on 2016/5/27.
 */
public interface NewModel {

    void loadNews(String url, int type,NewModelImpl.OnLoadNewsListListener listener);
    void loadNewsDetail(String docId,NewModelImpl.OnLoadNewsDetailListener listener);
}
