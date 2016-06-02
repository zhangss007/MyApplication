package demo.myapplication.news.presenter;

import java.util.List;

import demo.myapplication.NewsListFragment;
import demo.myapplication.bean.NewsBean;
import demo.myapplication.commoms.Urls;
import demo.myapplication.news.model.NewModel;
import demo.myapplication.news.model.NewModelImpl;
import demo.myapplication.news.view.NewView;

/**
 * Created by FT_ZSS on 2016/5/27.
 */
public class NewsPresenterImpl implements NewsPresenter,NewModelImpl.OnLoadNewsListListener {


    private NewView mNewView;
    private NewModel mNewModel;
    public NewsPresenterImpl(NewView newView){

        mNewView = newView;
        mNewModel = new NewModelImpl();
    }

    @Override
    public void loadNews(int type, int pageIndex) {

        String url = getUrl(type,pageIndex);
        mNewModel.loadNews(url,type,this);
    }


    private String getUrl(int type, int pageIndex){

        StringBuffer sb = new StringBuffer();
        switch (type){
            case NewsListFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsListFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsListFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsListFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {

        mNewView.hideProgress();
        mNewView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {

        mNewView.hideProgress();
        mNewView.showLoadFailMsg();
    }
}
