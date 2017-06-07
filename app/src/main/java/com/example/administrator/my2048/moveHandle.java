package com.example.administrator.my2048;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.my2048.numClass.num;

import static com.example.administrator.my2048.MainActivity.bundle;
import static com.example.administrator.my2048.MainActivity.fm;
import static com.example.administrator.my2048.MainActivity.fragment;
import static com.example.administrator.my2048.MainActivity.relativeLayout;
import static com.example.administrator.my2048.staticPosition.InitializationH;
import static com.example.administrator.my2048.staticPosition.MAXN;
import static com.example.administrator.my2048.staticPosition.POSITION;
import static com.example.administrator.my2048.staticPosition.haveNum;
import static com.example.administrator.my2048.staticPosition.len;
import static com.example.administrator.my2048.staticPosition.nothave;
import static com.example.administrator.my2048.staticPosition.nums;
import static com.example.administrator.my2048.staticOAnimator.*;

/**
 * Created by Administrator on 2017/6/3.
 */

public class moveHandle {
    private moveHandle(){};

    public static void allMove(){

    }

    /*
    一行的移动
     */
    public static void oneMove(int n1, int n2, int n3, int n4, int direction){
        int n[] = {n1, n2, n3, n4};
        if ((nums[n[3]] == null && (nums[n[2]] != null || nums[n[1]] != null || nums[n[0]] != null)) ||             //有移动的情况才能产生新的方块
                (nums[n[2]] == null && (nums[n[1]] != null || nums[n[0]] != null)) ||
                (nums[n[1]] == null && nums[n[0]] != null)) {
            canGet = true;
        }
        int temp[] = new int[4];
        int j = 3;
        for(int i = 3; i >= 0; i--) {
            if(nums[n[i]] != null){
                temp[j] = n[i];
                j--;
            }
        }
        for(; j >= 0; j--) {
            temp[j] = -1;
        }
        for(int i = 3, k = 3; i >= 0; i--){
            if(n[i] != temp[k] && temp[k] != -1){
                translate(n[i], temp[k], direction);
            }
            k--;
        }
        if(nums[n[2]] != null){
            if(canadd(n[3],n[2])){
                addTranslate(n[3], n[2], direction);
                canGet = true;
                if (nums[n[0]] != null && nums[n[1]]  != null){
                    if(canadd(n[1], n[0])) {
                        addTranslate(n[1], n[0], direction);
                        translate(n[2], n[1], direction);
                    }
                    else {
                        translate(n[2], n[1], direction);
                        translate(n[1], n[0], direction);
                    }
                }
                else if(nums[n[1]] == null && nums[n[0]] != null){
                    translate(n[2], n[0], direction);
                }
                else if(nums[n[0]] == null && nums[n[1]] != null){
                    translate(n[2], n[1], direction);
                }
            }
            else if(nums[n[1]] != null && canadd(n[2], n[1])){
                addTranslate(n[2], n[1], direction);
                canGet = true;
                if(nums[n[0]] != null){
                    translate(n[1], n[0], direction);
                }
            }
            else if(nums[n[0]] != null && canadd(n[1], n[0])){
                addTranslate(n[1], n[0], direction);
                canGet = true;
            }
        }
    }

    private static boolean canadd(int n1, int n2){
        if(nums[n1].getmNum() == nums[n2].getmNum()) {
            return true;
        }
        return false;
    }

    /*
    移动num到另一个num的位置，并且修改索引
     */
    private static void translate(int n, int o, int direction){
        numAnimator(nums[o], POSITION[n], direction);
        nums[o].setmPoint(POSITION[n]);
        nums[o].setIndex(n);
        nums[n] = nums[o];
        nums[o] = null;
        haveNum[o] = 0;
        haveNum[n] = 1;
        for(int i = 0; i < len; i++){
            if(nothave[i] == n + 1){
                nothave[i] = o + 1;
                break;
            }
        }
    }

    /*
    相加移动
     */
    private static void addTranslate(int n, int o, int direction){
        numAnimatorAlpha(nums[o], POSITION[n], direction);
        nums[n].setmNum(nums[n].getmNum() * 2);
        nums[n].invalidate();
        nums[o] = null;
        haveNum[o] = 0;
        nothave[len] = o + 1;
        len++;
    }

    private static int winorLose(){
        for(int i = 0; i < MAXN; i++){
            if(nums[i] != null && nums[i].getmNum() == 2048){
                return 2;
            }
        }
        for(int i = 0; i < MAXN; i++){
            if(nums[i] == null){
                return 0;
            }
        }
        if(!canadd(0,1) && !canadd(1,2) && !canadd(2,3) && !canadd(4,5) && !canadd(5,6) && !canadd(6,7) &&
                !canadd(8,9) && !canadd(9,10) && !canadd(10,11) && !canadd(12,13) && !canadd(13,14) &&
                !canadd(14,15) && !canadd(0,4) && !canadd(4,8) && !canadd(8,12) && !canadd(1,5) &&
                !canadd(5,9) && !canadd(9,13) && !canadd(2,6) && !canadd(6,10) && !canadd(10,14) &&
                !canadd(3,7) && !canadd(7,11) && !canadd(11,15) ){
            return 1;
        }
        return 0;
    }

    public static void result(Activity activity){
        switch (winorLose()){
            case 1:
                winorlose = true;
                bundle.putString("text", "失败");
                showReasart(bundle);
                break;
            case 2:
                winorlose = true;
                bundle.putString("text", "胜利");
                showReasart(bundle);
                break;
        }
    }

    public static void restartGame(Context context){
        for(int i = 0; i < MAXN; i++){
            if(nums[i] != null){
                relativeLayout.removeView(nums[i]);
            }
            nums[i] = null;
        }
        InitializationH();
        winorlose = false;
        touching = false;
        num n = new num(context);
        nums[n.getIndex()] = n;
        //nums[n.getIndex()].setmNum(2048);
        relativeLayout.addView(nums[n.getIndex()]);
    }

    private static void showReasart(Bundle bundle){
        if(fragment == null){
            fragment = new selectFragment();
            fragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }
}
