package demo.myapplication.news.model;

import java.util.List;

import demo.myapplication.NewsListFragment;
import demo.myapplication.bean.NewsBean;
import demo.myapplication.bean.NewsDetailBean;
import demo.myapplication.commoms.Urls;
import demo.myapplication.utils.NewsJsonUtils;
import demo.myapplication.utils.OkHttpUtils;

/**
 * Created by FT_ZSS on 2016/5/27.
 */
public class NewModelImpl implements NewModel {


    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>(){
            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response,getID(type));
                listener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url,resultCallback);
    }


    @Override
    public void loadNewsDetail(final String docId, final OnLoadNewsDetailListener listener) {

        String url = getDetailUrl(docId);
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response,docId);
                listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.",e);
            }
        };
        OkHttpUtils.get(url,resultCallback);
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsListFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsListFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsListFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsListFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }
    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
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
