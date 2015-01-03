package com.goyourfly.freeweather;

/**
 * Created by gaoyf on 15/1/3.
 */
public class WeatherIconFactory {
    public static int getIcon(int code) {
        if(code < 2){
            return R.drawable.weather_21;
        }else if(code < 5){
            return R.drawable.weather_1;
        }else if(code < 9){
            return R.drawable.weather_6;
        }else if(code < 13){
            return R.drawable.weather_10;
        }else if(code < 17){
            return R.drawable.weather_5;
        }else if(code < 18){
            return R.drawable.weather_4;
        }else if(code < 19){
            return R.drawable.weather_7;
        }else if(code < 23){
            return R.drawable.weather_15;
        }else if(code < 26){
            return R.drawable.weather_21;
        }else if(code < 27){
            return R.drawable.weather_3;
        }else if(code < 28){
            return R.drawable.weather_18;
        }else if(code < 29){
            return R.drawable.weather_19;
        }else if(code < 30){
            return R.drawable.weather_18;
        }else if(code < 31){
            return R.drawable.weather_19;
        }else if(code < 32){
            return R.drawable.weather_24;
        }else if(code < 33){
            return R.drawable.weather_25;
        }else if(code < 34){
            return R.drawable.weather_24;
        }else if(code < 35){
            return R.drawable.weather_25;
        }else if(code < 36){
            return R.drawable.weather_6;
        }else if(code < 37){
            return R.drawable.weather_5;
        }else if(code < 38){
            return R.drawable.weather_1;
        }else if(code < 39){
            return R.drawable.weather_2;
        }else if(code < 40){
            return R.drawable.weather_2;
        }else if(code < 41){
            return R.drawable.weather_8;
        }else if(code < 44){
            return R.drawable.weather_20;
        } else if (code < 45) {
            return R.drawable.weather_3;
        } else if (code < 46) {
            return R.drawable.weather_13;
        } else if (code < 47) {
            return R.drawable.weather_7;
        } else if (code < 48) {
            return R.drawable.weather_1;
        }

        return -1;
    }
}
