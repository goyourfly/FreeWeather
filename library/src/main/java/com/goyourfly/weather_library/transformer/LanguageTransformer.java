package com.goyourfly.weather_library.transformer;

/**
 * Created by gaoyf on 14/12/27.
 */
public class LanguageTransformer {
    public static String weatherTransformer(String en) {
        if (en.equals("Haze")) {
            return "雾霾";
        }
        return null;
    }
}
