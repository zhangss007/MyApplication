package demo.myapplication.weather.model;

import android.content.Context;

/**
 * Created by FT_ZSS on 2016/6/6.
 */
public interface WeatherModel {
    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);
    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);
}
