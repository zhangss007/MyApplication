package demo.myapplication.images;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import demo.myapplication.bean.ImageBean;
import demo.myapplication.utils.JsonUtils;
import demo.myapplication.utils.LogUtils;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/23
 */
public class ImageJsonUtils {

    private final static String TAG = "ImageJsonUtils";
    /**
     * 将获取到的json转换为图片列表对象
     * @param res
     * @return
     */
    public static List<ImageBean> readJsonImageBeans(String res) {
        List<ImageBean> beans = new ArrayList<ImageBean>();
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res).getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                ImageBean news = JsonUtils.deserialize(jo, ImageBean.class);
                beans.add(news);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonImageBeans error", e);
        }
        return beans;
    }
}
