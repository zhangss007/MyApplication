package demo.myapplication.weather.view;

import java.util.List;

import demo.myapplication.bean.WeatherBean;

/**
 * Created by FT_ZSS on 2016/6/6.
 */
public interface WeatherView {
    void showProgress();
    void hideProgress();
    void showWeatherLayout();

    void setCity(String city);
    void setToday(String data);
    void setTemperature(String temperature);
    void setWind(String wind);
    void setWeather(String weather);
    void setWeatherImage(int res);
    void setWeatherData(List<WeatherBean> lists);

    void showErrorToast(String msg);
}
