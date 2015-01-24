package com.goyourfly.weather_library.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by gaoyf on 15/1/2.
 */
public class WeatherUtils {
    public static int getCTemp(int fTemp){
        return (int) (5.0f/9.0f * (fTemp - 32));
    }


    public static String getExceptionMsg(Throwable throwable){
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        return writer.toString();
    }
}
