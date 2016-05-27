package demo.myapplication.news.view;

import java.util.List;

import demo.myapplication.bean.NewsBean;

/**
 * Created by FT_ZSS on 2016/5/27.
 */
public interface  NewView {

    void showProgress();
    void addNews(List<NewsBean> list);
    void hideProgress();
    void showLoadFailMsg();
}
