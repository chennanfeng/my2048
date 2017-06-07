package com.example.administrator.my2048;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Point;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.my2048.numClass.*;


import static com.example.administrator.my2048.moveHandle.*;
import static com.example.administrator.my2048.staticPosition.*;
import static com.example.administrator.my2048.staticOAnimator.*;


public class MainActivity extends AppCompatActivity {

    private int mLastX,mLastY;

    public static RelativeLayout relativeLayout;

    public static FragmentManager fm;
    public static Fragment fragment;

    public static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializationP(getWindowManager());
        fm = getFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);
        bundle = new Bundle();
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        restartGame(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!staticOAnimator.touching) {
            int x = (int) event.getX(), y = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastX = x;
                    mLastY = y;
                    break;
                case MotionEvent.ACTION_UP:
                    double cos = getAngle(x,y,mLastX,mLastY);
                    if((cos > 45 && cos < 135)) {
                        //上
                        canGet = false;
                        oneMove(12, 8, 4, 0, Y);
                        oneMove(13, 9, 5, 1, Y);
                        oneMove(14, 10, 6, 2, Y);
                        oneMove(15, 11, 7, 3, Y);
                        if(canGet == true){
                            num n = new num(this);
                            nums[n.getIndex()] = n;
                            relativeLayout.addView(nums[n.getIndex()]);
                        }
                        result(this);
                    } else if(cos > 225 && cos < 315) {
                        //下
                        canGet = false;
                        oneMove(0, 4, 8, 12, Y);
                        oneMove(1, 5, 9, 13, Y);
                        oneMove(2, 6, 10, 14, Y);
                        oneMove(3, 7, 11, 15, Y);
                        if(canGet == true){
                            num n = new num(this);
                            nums[n.getIndex()] = n;
                            relativeLayout.addView(nums[n.getIndex()]);
                        }
                        result(this);
                    } else if((cos >= 0 && cos <= 45) || (cos >= 315 && cos <= 360)) {
                        //左
                        canGet = false;
                        oneMove(3, 2, 1, 0, X);
                        oneMove(7, 6, 5, 4, X);
                        oneMove(11, 10, 9, 8, X);
                        oneMove(15, 14, 13, 12, X);
                        if(canGet == true){
                            num n = new num(this);
                            nums[n.getIndex()] = n;
                            relativeLayout.addView(nums[n.getIndex()]);
                        }
                        result(this);
                    } else if(cos >= 135 && cos <= 225) {
                        //右
                        canGet = false;
                        oneMove(0, 1, 2, 3, X);
                        oneMove(4, 5, 6, 7, X);
                        oneMove(8, 9, 10, 11, X);
                        oneMove(12, 13, 14, 15, X);
                        if(canGet == true){
                            num n = new num(this);
                            nums[n.getIndex()] = n;
                            relativeLayout.addView(nums[n.getIndex()]);
                        }
                        result(this);
                    }
            }
        }
        return super.onTouchEvent(event);
    }

    private double getAngle(int x0,int y0,int x1,int y1){
        // 两点的x、y值
        int x = x1 - x0;
        int y = y1 - y0;
        double hypotenuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        // 斜边长度
        double cos = x / hypotenuse;
        double radian = Math.acos(cos);
        // 求出弧度
        double angle = 180 / (Math.PI / radian);
        // 用弧度算出角度
        if (y < 0) {
            angle = 180 + (180 - angle);
        } else if ((y == 0) && (x < 0)) {
            angle = 180;
        }
        return angle;
    }
}
