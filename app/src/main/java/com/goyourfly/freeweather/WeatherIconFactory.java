package com.goyourfly.freeweather;

import android.content.Context;

/**
 * Created by gaoyf on 15/1/3.
 */
public class WeatherIconFactory {
    public static String getIcon(Context context,int code) {
        switch (code){
            case 0:
                return context.getString(R.string.wi_tornado);
            case 1:
                return context.getString(R.string.wi_storm_showers);
            case 2:
                return context.getString(R.string.wi_hurricane);
            case 3:
                return context.getString(R.string.wi_thunderstorm);
            case 4:
                return context.getString(R.string.wi_thunderstorm);
            case 5:
                return context.getString(R.string.wi_day_sleet_storm);
            case 6:
                return context.getString(R.string.wi_day_sleet_storm);
            case 7:
                return context.getString(R.string.wi_day_sleet_storm);
            case 8:
                return context.getString(R.string.wi_rain);
            case 9:
                return context.getString(R.string.wi_rain);
            case 10:
                return context.getString(R.string.wi_rain);
            case 11:
                return context.getString(R.string.wi_showers);
            case 12:
                return context.getString(R.string.wi_showers);
            case 13:
                return context.getString(R.string.wi_snow_wind);
            case 14:
                return context.getString(R.string.wi_snow_wind);
            case 15:
                return context.getString(R.string.wi_snow_wind);
            case 16:
                return context.getString(R.string.wi_snow);
            case 17:
                return context.getString(R.string.wi_hail);
            case 18:
                return context.getString(R.string.wi_day_sleet_storm);
            case 19:
                return context.getString(R.string.wi_dust);
            case 20:
                return context.getString(R.string.wi_fog);
            case 21:
                return context.getString(R.string.wi_smoke);
            case 22:
                return context.getString(R.string.wi_smoke);
            case 23:
                return context.getString(R.string.wi_cloudy_windy);
            case 24:
                return context.getString(R.string.wi_windy);
            case 25:
                return context.getString(R.string.wi_snowflake_cold);
            case 26:
                return context.getString(R.string.wi_cloudy);
            case 27:
                return context.getString(R.string.wi_day_cloudy);
            case 28:
                return context.getString(R.string.wi_night_cloudy);
            case 29:
                return context.getString(R.string.wi_day_cloudy);
            case 30:
                return context.getString(R.string.wi_night_cloudy);
            case 31:
                return context.getString(R.string.wi_night_clear);
            case 32:
                return context.getString(R.string.wi_day_sunny);
            case 33:
                return context.getString(R.string.wi_night_clear);
            case 34:
                return context.getString(R.string.wi_day_sunny);
            case 35:
                return context.getString(R.string.wi_hail);
            case 36:
                return context.getString(R.string.wi_hot);
            case 37:
                return context.getString(R.string.wi_thunderstorm);
            case 38:
                return context.getString(R.string.wi_thunderstorm);
            case 39:
                return context.getString(R.string.wi_thunderstorm);
            case 40:
                return context.getString(R.string.wi_showers);
            case 41:
                return context.getString(R.string.wi_snow);
            case 42:
                return context.getString(R.string.wi_snow);
            case 43:
                return context.getString(R.string.wi_snow);
            case 44:
                return context.getString(R.string.wi_day_cloudy);
            case 45:
                return context.getString(R.string.wi_thunderstorm);
            case 46:
                return context.getString(R.string.wi_snow);
            case 47:
                return context.getString(R.string.wi_thunderstorm);
            case 3200:
                return context.getString(R.string.wi_cloud_refresh);
            default:
                return context.getString(R.string.wi_cloud_refresh);
        }
    }

    public static String getWindIcon(Context context,int index){
        switch (index){
            case 0:
                return context.getString(R.string.wi_beafort_0);
            case 1:
                return context.getString(R.string.wi_beafort_1);
            case 2:
                return context.getString(R.string.wi_beafort_2);
            case 3:
                return context.getString(R.string.wi_beafort_3);
            case 4:
                return context.getString(R.string.wi_beafort_4);
            case 5:
                return context.getString(R.string.wi_beafort_5);
            case 6:
                return context.getString(R.string.wi_beafort_6);
            case 7:
                return context.getString(R.string.wi_beafort_7);
            case 8:
                return context.getString(R.string.wi_beafort_8);
            case 9:
                return context.getString(R.string.wi_beafort_9);
            case 10:
                return context.getString(R.string.wi_beafort_10);
            case 11:
                return context.getString(R.string.wi_beafort_11);
            case 12:
                return context.getString(R.string.wi_beafort_12);
            default:
                return context.getString(R.string.wi_strong_wind);
        }
    }

    /**
     * @param context
     * @param angle zero is south
     * @return
     */
    public static String getWindDirection(Context context,int angle){
        if(angle <= 15){
            return context.getString(R.string.wi_wind_south);
        }else if(angle <= 60){
            return context.getString(R.string.wi_wind_south_west);
        }else if(angle <= 105){
            return context.getString(R.string.wi_wind_west);
        }else if(angle <= 150){
            return context.getString(R.string.wi_wind_north_west);
        }else if(angle <= 195){
            return context.getString(R.string.wi_wind_north);
        }else if(angle <= 240){
            return context.getString(R.string.wi_wind_north_east);
        }else if(angle <= 285){
            return context.getString(R.string.wi_wind_east);
        }else if(angle <= 330){
            return context.getString(R.string.wi_wind_south_east);
        }else if(angle <= 360){
            return context.getString(R.string.wi_wind_south);
        }
        return null;
    }
}
