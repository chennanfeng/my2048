package com.example.administrator.my2048;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.administrator.my2048.numClass.num;

import static com.example.administrator.my2048.R.id.restart;
import static com.example.administrator.my2048.moveHandle.restartGame;
import static com.example.administrator.my2048.staticPosition.nums;

/**
 * Created by Administrator on 2017/5/30.
 */

public class staticOAnimator {
    private staticOAnimator(){};

    public static final int X = 0;
    public static final int Y = 1;

    public static boolean touching = false;

    public static boolean canGet = false;

    public static boolean winorlose = false;

    public static void numAnimator(final num num, Point toPoint, int direction){
        if(direction == 0) {
            ObjectAnimator animator = ObjectAnimator.ofInt(num, "mPoint",0,toPoint.x - num.getmPoint().x);
            animator.setDuration(100);
            final int curx = num.getmPoint().x;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point p = new Point((int) animation.getAnimatedValue() + curx, num.getmPoint().y);
                    num.setmPoint(p);
                    num.invalidate();
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    touching = false;
                    if(winorlose == true){
                        touching = true;
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    touching = true;
                }
            });
            animator.start();
        }else if(direction == 1) {
            ObjectAnimator animator = ObjectAnimator.ofInt(num, "mPoint",0,toPoint.y - num.getmPoint().y);
            animator.setDuration(100);
            final int cury = num.getmPoint().y;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point p = new Point(num.getmPoint().x, (int) animation.getAnimatedValue() + cury);
                    num.setmPoint(p);
                    num.invalidate();
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    touching = false;
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    touching = true;
                }
            });
            animator.start();
        }
    }

    public static void numAnimatorAlpha(final num num, Point toPoint, int direction){
        if(direction == 0) {
            ObjectAnimator animator = ObjectAnimator.ofInt(num, "mPoint",0,toPoint.x - num.getmPoint().x);
            ObjectAnimator animatorA = ObjectAnimator.ofFloat(num, "alpha",1f,0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animator).with(animatorA);
            animatorSet.setDuration(100);
            final int curx = num.getmPoint().x;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point p = new Point((int) animation.getAnimatedValue() + curx, num.getmPoint().y);
                    num.setmPoint(p);
                    num.invalidate();
                }
            });
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    touching = false;
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    touching = true;
                }
            });
            animatorSet.start();
        }else if(direction == 1) {
            ObjectAnimator animator = ObjectAnimator.ofInt(num, "mPoint",0,toPoint.y - num.getmPoint().y);
            ObjectAnimator animatorA = ObjectAnimator.ofFloat(num, "alpha",1f,0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animator).with(animatorA);
            animatorSet.setDuration(100);
            final int cury = num.getmPoint().y;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point p = new Point(num.getmPoint().x, (int) animation.getAnimatedValue() + cury);
                    num.setmPoint(p);
                    num.invalidate();
                }
            });
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    touching = false;
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    touching = true;
                }
            });
            animatorSet.start();
        }
    }

}
