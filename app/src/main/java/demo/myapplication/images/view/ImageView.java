package demo.myapplication.images.view;

import java.util.List;

import demo.myapplication.bean.ImageBean;

/**
 * Created by FT_ZSS on 2016/6/6.
 */
public interface ImageView {

    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
