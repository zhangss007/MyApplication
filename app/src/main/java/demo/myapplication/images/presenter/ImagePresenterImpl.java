package demo.myapplication.images.presenter;

import java.util.List;

import demo.myapplication.bean.ImageBean;
import demo.myapplication.images.model.ImageModel;
import demo.myapplication.images.model.ImageModelImpl;
import demo.myapplication.images.view.ImageView;

/**
 * Created by FT_ZSS on 2016/6/6.
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener{

    private ImageModel mImageModel;
    private ImageView mImageView;

    public ImagePresenterImpl(ImageView imageView) {

        this.mImageModel = new ImageModelImpl();
        this.mImageView = imageView;
    }


    @Override
    public void loadImageList() {

        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onFailure(String msg, Exception e) {

        mImageView.showLoadFailMsg();
        mImageView.hideProgress();
    }

    @Override
    public void onSuccess(List<ImageBean> list) {

        mImageView.addImages(list);
        mImageView.hideProgress();
    }
}
