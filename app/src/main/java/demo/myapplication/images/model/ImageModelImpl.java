package demo.myapplication.images.model;

import java.util.List;

import demo.myapplication.bean.ImageBean;
import demo.myapplication.commoms.Urls;
import demo.myapplication.images.ImageJsonUtils;
import demo.myapplication.utils.OkHttpUtils;

/**
 * Created by FT_ZSS on 2016/6/6.
 */
public class ImageModelImpl implements ImageModel {

    @Override
    public void loadImageList(final OnLoadImageListListener listListener) {
        String url = Urls.IMAGES_URL;
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> iamgeBeanList = ImageJsonUtils.readJsonImageBeans(response);
                listListener.onSuccess(iamgeBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listListener.onFailure("load image list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    public interface OnLoadImageListListener{
        void onSuccess(List<ImageBean> list);
        void onFailure(String msg,Exception e);
    }
}
