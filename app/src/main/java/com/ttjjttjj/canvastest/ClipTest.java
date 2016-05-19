package com.ttjjttjj.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/5/19 0019.
 *
 * Canvas提供了ClipPath, ClipRect, ClipRegion 等方法來裁剪，
 * 通過Path, Rect ,Region 的不同組合，Android幾乎可以支持任意現狀的裁剪區域。
 *
 * android.graphics包中定義了Point, Rect, Path, Region 這幾種幾何形狀，
 * Path可以為有圓弧，橢圓，二次曲線，三次曲線，線段，矩形等基本幾何圖形或是由這些基本幾何圖形組合而成，
 * Path可以為開放或是閉合曲線。Rect提供了定義矩形的簡潔方法。
 * Region則支持通過區域的「加」，「減」，「並」，「異或」等邏輯運算由多個Region組合而成。
 * Region.Op定義了Region支持的區域間運算種類。
 *
 * Clipping 介紹有Region運算來為Canvs定義剪裁區域後，同一幅圖最後顯示的效果。
 * canvas.save();和canvas.restore();用於保存和恢復Canvas的狀態屬性。
 */
public class ClipTest extends View{

    private Paint mPaint;
    private Path mPath;

    public ClipTest(Context context) {
        this(context, null);
        initData();
    }

    public ClipTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initData();
    }

    public ClipTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initData(){

        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);

        canvas.save();
        canvas.scale(2, 2);//增加放大，由于手机分辨率太高了，每个都放大一下
        canvas.translate(10, 30);//(10, 10)位置调整一下，手机分辨率原因
        drawScene(canvas);
        canvas.restore();

        /** 裁剪了為兩個矩形相減 Region.Op.DIFFERENCE */
        canvas.save();
        canvas.scale(2, 2);
        canvas.translate(160, 30);//(160,10)
        //先裁剪一个rect
        canvas.clipRect(10, 10, 90, 90);
        //再裁剪一个rect，两个相减
        canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

        /** 裁剪了圆 */
        canvas.save();
        canvas.scale(2, 2);
        canvas.translate(10, 160);//(10, 160)
        mPath.reset();
        canvas.clipPath(mPath); // makes the clip empty
        mPath.addCircle(50, 50, 50, Path.Direction.CCW);
        //裁剪一个圆
        canvas.clipPath(mPath, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();

        /** 裁剪了两个矩形的并集 Region.Op.UNION */
        canvas.save();
        canvas.scale(2, 2);
        canvas.translate(160, 160);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
        drawScene(canvas);
        canvas.restore();

        /** 裁剪了两个矩形的异或集 Region.Op.XOR */
        canvas.save();
        canvas.scale(2, 2);
        canvas.translate(10, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();

        /** 裁剪为兩個矩形的逆向差集 Region.Op.REVERSE_DIFFERENCE */
        canvas.save();
        canvas.scale(2, 2);
        canvas.translate(160, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

    }

    /**
     * 定義了繪圖的內容：一條紅線，一個綠圓，和「Clipping」文字。
     * @param canvas
     */
    private void drawScene(Canvas canvas){

        canvas.clipRect(0, 0, 100, 100);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 100, 30, mPaint);
    }

}
