package com.goyourfly.freeweather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Weather Icon View allows you to create Weather Icon for your Android application
 * It's based on Erik Flowers project located at: https://github.com/erikflowers/weather-icons
 * This project is open-source and can be found at: https://github.com/pwittchen/WeatherIconView
 * @author Piotr Wittchen
 */
public class WeatherIconView extends TextView {
    private final static String PATH_TO_WEATHER_FONT = "fonts/weathericons-regular-webfont.ttf";
    private Typeface weatherFont;

    public WeatherIconView(Context context) {
        super(context);
        initialize(context);
    }

    public WeatherIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public WeatherIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    private void initialize(Context context) {
        if (isInEditMode()) {
            return;
        }
        weatherFont = Typeface.createFromAsset(context.getAssets(), PATH_TO_WEATHER_FONT);
        setTypeface(weatherFont);
    }
}
