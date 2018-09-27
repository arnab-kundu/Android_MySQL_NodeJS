package com.example.akundu.android_mysql_nodejs.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DigitalView extends View {

    int width, height, padding, innerPadding, gap = 2;
    Paint paint;
    int number = 0;

    public DigitalView(Context context) {
        super(context);
    }

    public DigitalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        padding = 5 * width / 100;
        innerPadding = 15 * width / 100;

        //Log.d("msg", "width" + width);
        //Log.d("msg", "height" + height);
        //Log.d("msg", "padding" + padding);
        //Log.d("msg", "innerPadding" + innerPadding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        /*Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);


        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathBottomLeftLine = new Path();
        pathBottomLeftLine.moveTo(padding, height / 2 + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + padding + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomLeftLine.lineTo(padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomLeftLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);


        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);*/
        if (number == 0)
            zero(canvas, paint);
        else if (number == 1)
            one(canvas, paint);
        else if (number == 2)
            two(canvas, paint);
        else if (number == 3)
            three(canvas, paint);
        else if (number == 4)
            four(canvas, paint);
        else if (number == 5)
            five(canvas, paint);
        else if (number == 6)
            six(canvas, paint);
        else if (number == 7)
            seven(canvas, paint);
        else if (number == 8)
            eight(canvas, paint);
        else if (number == 9)
            nine(canvas, paint);
        else
            zero(canvas, paint);
        invalidate();
    }

    void zero(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);

        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathBottomLeftLine = new Path();
        pathBottomLeftLine.moveTo(padding, height / 2 + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + padding + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomLeftLine.lineTo(padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomLeftLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);

        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    public void one(Canvas canvas, Paint paint) {
        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);


        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);

    }

    void two(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);

        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathBottomLeftLine = new Path();
        pathBottomLeftLine.moveTo(padding, height / 2 + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + padding + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomLeftLine.lineTo(padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomLeftLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);
    }

    void three(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);


        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);


        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    void four(Canvas canvas, Paint paint) {
        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);


        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    void five(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);
    }

    void six(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);


        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathBottomLeftLine = new Path();
        pathBottomLeftLine.moveTo(padding, height / 2 + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + padding + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomLeftLine.lineTo(padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomLeftLine, paint);

        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    void seven(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);

        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    void eight(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);

        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathBottomLeftLine = new Path();
        pathBottomLeftLine.moveTo(padding, height / 2 + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + padding + gap);
        pathBottomLeftLine.lineTo(innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomLeftLine.lineTo(padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomLeftLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);

        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    void nine(Canvas canvas, Paint paint) {
        Path pathTopLine = new Path();
        pathTopLine.moveTo(padding, padding);
        pathTopLine.lineTo(width - padding, padding);
        pathTopLine.lineTo(width - innerPadding, innerPadding);
        pathTopLine.lineTo(innerPadding, innerPadding);
        canvas.drawPath(pathTopLine, paint);

        Path pathBottomLine = new Path();
        pathBottomLine.moveTo(padding, height - padding);
        pathBottomLine.lineTo(width - padding, height - padding);
        pathBottomLine.lineTo(width - innerPadding, height - innerPadding);
        pathBottomLine.lineTo(innerPadding, height - innerPadding);
        canvas.drawPath(pathBottomLine, paint);

        Path pathMiddleLine = new Path();
        pathMiddleLine.moveTo(padding, height / 2);
        pathMiddleLine.lineTo(innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 - padding);
        pathMiddleLine.lineTo(width - padding, height / 2);
        pathMiddleLine.lineTo(width - innerPadding, height / 2 + padding);
        pathMiddleLine.lineTo(innerPadding, height / 2 + padding);
        canvas.drawPath(pathMiddleLine, paint);

        Path pathTopLeftLine = new Path();
        pathTopLeftLine.moveTo(padding, padding + gap);
        pathTopLeftLine.lineTo(innerPadding, innerPadding + gap);
        pathTopLeftLine.lineTo(innerPadding, height / 2 - padding - gap);
        pathTopLeftLine.lineTo(padding, height / 2 - gap);
        canvas.drawPath(pathTopLeftLine, paint);

        Path pathTopRightLine = new Path();
        pathTopRightLine.moveTo(width - padding, padding + gap);
        pathTopRightLine.lineTo(width - innerPadding, innerPadding + gap);
        pathTopRightLine.lineTo(width - innerPadding, height / 2 - padding - gap);
        pathTopRightLine.lineTo(width - padding, height / 2 - gap);
        canvas.drawPath(pathTopRightLine, paint);

        Path pathBottomRightLine = new Path();
        pathBottomRightLine.moveTo(width - padding, height / 2 + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + padding + gap);
        pathBottomRightLine.lineTo(width - innerPadding, height / 2 + height / 2 - innerPadding - gap);
        pathBottomRightLine.lineTo(width - padding, height / 2 + height / 2 - padding - gap);
        canvas.drawPath(pathBottomRightLine, paint);
    }

    public void drawOne() {
        invalidate();
        number = 1;
    }

    public void drawTwo() {
        invalidate();
        number = 2;
    }

    public void drawThree() {
        invalidate();
        number = 3;
    }

    public void drawFour() {
        invalidate();
        number = 4;
    }

    public void drawFive() {
        invalidate();
        number = 5;
    }

    public void drawSix() {
        invalidate();
        number = 6;
    }

    public void drawSeven() {
        invalidate();
        number = 7;
    }

    public void drawEight() {
        invalidate();
        number = 8;
    }

    public void drawNine() {
        invalidate();
        number = 9;
    }

    public void draw(int number) {
        this.number = number;
    }
}
