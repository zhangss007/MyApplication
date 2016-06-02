package demo.myapplication.news.presenter;

import android.content.Context;

import demo.myapplication.bean.NewsDetailBean;
import demo.myapplication.news.model.NewModel;
import demo.myapplication.news.model.NewModelImpl;
import demo.myapplication.news.view.NewsDetailView;

/**
 * Created by FT_ZSS on 2016/6/2.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewModelImpl.OnLoadNewsDetailListener {

    public Context mContext;
    private NewsDetailView mNewsDetailView;
    private NewModel mNewModel;

    public NewsDetailPresenterImpl(Context context, NewsDetailView mNewsDetailView){

        this.mContext = context;
        this.mNewsDetailView = mNewsDetailView;
        mNewModel = new NewModelImpl();
    }

    @Override
    public void loadNewsDetail(String docId) {

        mNewsDetailView.showProgress();
        mNewModel.loadNewsDetail(docId,this);
    }

    @Override
    public void onSuccess(NewsDetailBean detailBean) {

        if(detailBean != null) {
            mNewsDetailView.showNewsDetialContent(detailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
