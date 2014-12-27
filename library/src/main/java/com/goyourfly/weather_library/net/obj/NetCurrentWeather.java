package com.goyourfly.weather_library.net.obj;

/**
 * Created by gaoyf on 14/12/27.
 */
public class NetCurrentWeather {
    public String base;
    public NetCoord coord;
    public NetSys sys;
    public NetWeather[] weather;
    public NetMain main;
    public NetWind wind;
    public NetClouds clouds;
    public long dt;
    public int id;
    public String name;
    public int cod;
}
