package com.ttjjttjj.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/5/11 0011.
 *
 * 这个demo展示了 canvas的translate ,scale , rotate, skew
 *
 * 其中在ondraw中自己手动切换一下，可以看到不同的效果
 */
public class CanvasTest extends View {

    private int mWidth, mHeight;
    private Paint mPaint = new Paint();

    public CanvasTest(Context context) {
        this(context, null);
    }

    public CanvasTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        translateTest(canvas);

//        scaleTest(canvas);

//        simpleTest(canvas);

//        circleTest(canvas);

        skewTest(canvas);




    }

    /**
     * canvas translate 的操作
     * @param canvas
     */
    private void translateTest(Canvas canvas){

        mPaint.setColor(Color.BLACK);
        /** canvas.translate 是画布的移动*/
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.BLUE);
        /** 相对上一个移动的位置移动 */
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }

    /**
     * canvas scale 操作
     * @param canvas
     */
    private void scaleTest(Canvas canvas){

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        /** 将坐标系原点移动到画布正中心 */
        canvas.translate(mWidth/2, mHeight/2);
        Rect rect = new Rect(0 , -400, 400, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect, mPaint);

        //缩小一半
//        canvas.scale(0.5f, 0.5f);
        // 画布缩放 -- 缩放中心向右偏移了200个单位
//        canvas.scale(0.5f, 0.5f, 200, 0);
        //当缩放比例为负数的时候会根据缩放中心轴进行翻转
//        canvas.scale(-0.5f, -0.5f);

        // 画布缩放  <-- 缩放中心向右偏移了200个单位
        canvas.scale(-0.5f, -0.5f, 200, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect, mPaint);

    }

    /**
     * 循环缩放 实现一种效果
     * @param canvas
     */
    private void simpleTest(Canvas canvas){

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域

        for (int i=0; i<=20; i++)
        {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,mPaint);
        }

    }

    private void circleTest(Canvas canvas){

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0, 0, 400, mPaint);          // 绘制两个圆形
        canvas.drawCircle(0, 0, 380, mPaint);

        for (int i=0; i<=360; i+=10){               // 绘制圆形之间的连接线
            canvas.drawLine(0, 380, 0, 400, mPaint);
            canvas.rotate(10);
        }
    }


    private void skewTest(Canvas canvas){

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0,0,200,200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);                       // 在x轴倾斜45度 <-- tan45 = 1

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }






}
