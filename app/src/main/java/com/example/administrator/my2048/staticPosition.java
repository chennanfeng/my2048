package com.example.administrator.my2048;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.example.administrator.my2048.numClass.num;

/**
 * Created by Administrator on 2017/5/29.
 */

public class staticPosition {
    private staticPosition(){};

    public static final int MAXN = 16;

    public static Point POSITION[] = new Point[MAXN];

    public static int haveNum[] = new int[MAXN];

    public static int nothave[] = new int[MAXN];

    public static num nums[] = new num[MAXN];

    public static int len = MAXN;

    public static int colors[] = {Color.BLUE, Color.GREEN , Color.RED};

    public static void InitializationH(){
        len = MAXN;
        for(int i = 0; i < MAXN; i++){
            haveNum[i] = 0;
            nothave[i] = i + 1;
        }
    }

    public static void resetNotHave(){
        for(int i = 0; i < len; i++){
            if(nothave[i] == 0){
                for(int j = i;j < len - 1; j++){
                    nothave[j] = nothave[j + 1];
                }
                len--;
            }
        }
    }

    public static void InitializationP(WindowManager wm){
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        POSITION[0] = new Point(width/2-165, height/2-165);
        POSITION[1] = new Point(width/2-55, height/2-165);
        POSITION[2] = new Point(width/2+55, height/2-165);
        POSITION[3] = new Point(width/2+165, height/2-165);

        POSITION[4] = new Point(width/2-165, height/2-55);
        POSITION[5] = new Point(width/2-55, height/2-55);
        POSITION[6] = new Point(width/2+55, height/2-55);
        POSITION[7] = new Point(width/2+165, height/2-55);

        POSITION[8] = new Point(width/2-165, height/2+55);
        POSITION[9] = new Point(width/2-55, height/2+55);
        POSITION[10] = new Point(width/2+55, height/2+55);
        POSITION[11] = new Point(width/2+165, height/2+55);

        POSITION[12] = new Point(width/2-165, height/2+165);
        POSITION[13] = new Point(width/2-55, height/2+165);
        POSITION[14] = new Point(width/2+55, height/2+165);
        POSITION[15] = new Point(width/2+165, height/2+165);
    }
}
