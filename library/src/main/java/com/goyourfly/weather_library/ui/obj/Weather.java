package com.goyourfly.weather_library.ui.obj;

import java.util.List;

/**
 * Created by gaoyf on 14/12/27.
 */
public class Weather {
    public int woeid;
    public int code;
    public String weatherName;
    public float temp;
    public int windSpeed;
    public int windDirection;
    public int humidity;
    public String sunrise;
    public String sunset;
    public String date;

    public List<Forecast> forecasts;
}
