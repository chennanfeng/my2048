package com.example.administrator.my2048.numClass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.my2048.staticPosition;

import java.util.Random;

import static com.example.administrator.my2048.staticPosition.*;

/**
 * Created by Administrator on 2017/5/28.
 */

public class num extends View {

    public int getmNum() {
        return mNum;
    }

    public void setmNum(int mNum) {
        this.mNum = mNum;
    }

    public Point getmPoint() {
        return mPoint;
    }

    public void setmPoint(Point mPoint) {
        this.mPoint = mPoint;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int mNum;

    private Paint mTextPaint;

    private Paint mBPaint;

    private Point mPoint;

    private int index;

    public num(Context context){
        super(context,null);
        mNum = ((int)(Math.random()*100) > 50 ? 2 : 4);
        selectXY();
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(40);
        mTextPaint.setAlpha(200);
        mBPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        selectPaintColor();
        drawview(canvas);
    }

    private void selectPaintColor(){
        switch (mNum){
            case 2:
                mBPaint.setColor(colors[0]);
                mBPaint.setAlpha(100);
                break;
            case 4:
                mBPaint.setColor(colors[1]);
                mBPaint.setAlpha(100);
                break;
            case 8:
                mBPaint.setColor(colors[2]);
                mBPaint.setAlpha(100);
                break;
            case 16:
                mBPaint.setColor(colors[0]);
                mBPaint.setAlpha(100);
                break;
            case 32:
                mBPaint.setColor(colors[1]);
                mBPaint.setAlpha(100);
                break;
            case 64:
                mBPaint.setColor(colors[2]);
                mBPaint.setAlpha(100);
                break;
            case 128:
                mBPaint.setColor(colors[0]);
                mBPaint.setAlpha(100);
                break;
            case 256:
                mBPaint.setColor(colors[1]);
                mBPaint.setAlpha(100);
                break;
            case 512:
                mBPaint.setColor(colors[2]);
                mBPaint.setAlpha(100);
                break;
            case 1024:
                mBPaint.setColor(colors[0]);
                mBPaint.setAlpha(100);
                break;
            case 2048:
                mBPaint.setColor(colors[1]);
                mBPaint.setAlpha(100);
                break;
        }
    }

    private void selectXY(){
        Random random = new Random();
        int i = random.nextInt(len);
        index = nothave[i] - 1;
        haveNum[index] = 1;
        nothave[i] = 0;
        mPoint = staticPosition.POSITION[index];
        resetNotHave();
    }

    private void drawview(Canvas canvas){
        canvas.drawRect(mPoint.x - 50, mPoint.y - 50, mPoint.x + 50, mPoint.y + 50,mBPaint);
        int i = 0;
        if(mNum >= 128 && mNum <= 512){
            i = 20;
        }
        else if(mNum >= 1024 && mNum <= 2048){
            i = 30;
        }
        else if(mNum >= 16 && mNum <= 64){
            i = 10;
        }
        else if(mNum >= 2 && mNum <= 8){
            i = -3;
        }
        canvas.drawText(Integer.toString(mNum), mPoint.x - 15 - i, mPoint.y + 15, mTextPaint);
    }
}