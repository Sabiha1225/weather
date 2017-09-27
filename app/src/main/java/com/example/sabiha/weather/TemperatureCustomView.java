package com.example.sabiha.weather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Sabiha on 9/26/2017.
 */

public class TemperatureCustomView  extends View {
    private String TAG = TemperatureCustomView.class.getSimpleName();
    private int color;
    private Paint circlePaint, textPaint;
    private double radius, width, height;
    private double padding = 100;
    private float sweepAngle;
    private final float MAX_ANGLE = 80f;

    public TemperatureCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circlePaint = new Paint();
        textPaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            color = a.getColor(R.styleable.TemperatureCustomView_arcColor, getResources().getColor(R.color.colorPrimary));
        } finally {
            a.recycle();
        }
    }

    public void initialize()
    {
        width = this.getMeasuredWidth()/2;
        height = this.getMeasuredHeight()/2;
        //Log.e("TempCustomView_Radius: ", "Width: "+width + "  Height:  "+height);
        radius = width > height ? height - padding : width - padding;
        //Log.e("TempCustomView_Radius: ", String.valueOf(radius));
        circlePaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        sweepAngle = 0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        circlePaint.setColor(color);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(30);
        circlePaint.setPathEffect(new DashPathEffect(new float[]{3, 10}, 0));
        RectF rectf = new RectF((float) (width - radius), (float) (height - radius), (float) (width + radius), (float) (height + radius));
        canvas.drawArc(rectf, 115, 310, false, circlePaint);

        circlePaint.setColor(Color.rgb(255,165,0));
        canvas.drawArc(rectf, 312, sweepAngle, false, circlePaint);

        textPaint.setColor(Color.rgb(255,165,0));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(22);
        String text = "TEST";
        canvas.drawText(text, (float)(radius+padding), (float)height, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        Log.e(TAG, widthMeasureSpec+"    "+heightMeasureSpec);
        initialize();
    }

    public float getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public float getMAX_ANGLE() {
        return MAX_ANGLE;
    }
}
