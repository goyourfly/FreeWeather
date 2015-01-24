package com.goyourfly.freeweather;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyf on 15/1/4.
 */
public class CustomWeightLayout extends FrameLayout {
    private List<Integer> perWidth = new ArrayList<>();
    private int totalWidth = 0;

    public CustomWeightLayout(Context context) {
        super(context);
    }

    public CustomWeightLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWeightLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        totalWidth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LinearLayout child = (LinearLayout) getChildAt(i);
            TextView textView = (TextView) child.findViewById(R.id.forecast_temp);
            TextView textView2 = (TextView) child.findViewById(R.id.forecast_day);
            String text = textView.getText().toString();
            String text2 = textView2.getText().toString();
            Paint paint = textView.getPaint();
            Paint paint2 = textView2.getPaint();
            int perChildWidth = (int) paint.measureText(text);
            int perChildWidth2 = (int) paint2.measureText(text2);
            int width = perChildWidth > perChildWidth2 ? perChildWidth : perChildWidth2;
            perWidth.add(width);
            totalWidth += width;

            child.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount == 0) return;
        int perChildSpace = (getWidth() - totalWidth) / (childCount - 1);
        int left = 0;
        for (int i = 0; i < childCount; i++) {
            LinearLayout child = (LinearLayout) getChildAt(i);
            int perChildWidth = perWidth.get(i);
            child.layout(left, 0, left + perChildWidth, getHeight());
            left += perChildWidth + perChildSpace;
        }
    }
}
