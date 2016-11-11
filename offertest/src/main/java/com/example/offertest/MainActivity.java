package com.example.offertest;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    Button button;
    GradientDrawable drawable;
    int screenWidth = 0;
    int screenHeight = 0;
    int lastX = 0;
    int lastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;

        button = (Button) findViewById(R.id.btn_hh);
        drawable = (GradientDrawable) button.getBackground();

        button.setOnTouchListener(this);
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 随机改变颜色
        Random random = new Random();
        int ranColor = 0;
        int ea=event.getAction();
        Log.i("TAG", "Touch:"+ea);


        switch(ea){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            /**
             * layout(l,t,r,b)
             * l  Left position, relative to parent
             t  Top position, relative to parent
             r  Right position, relative to parent
             b  Bottom position, relative to parent
             * */
            case MotionEvent.ACTION_MOVE:
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;


                if(left < 0){
                    left = 0;
                    right = left + v.getWidth();
                    ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                    drawable.setColor(ranColor);
                }

                if(right > screenWidth){
                    right = screenWidth;
                    left = right - v.getWidth();
                    ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                    drawable.setColor(ranColor);
                }

                if(top < 0){
                    top = 0;
                    bottom = top + v.getHeight();
                    ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                    drawable.setColor(ranColor);
                }

                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                    ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                    drawable.setColor(ranColor);
                }

                v.layout(left, top, right, bottom);

                Log.i("", "position：" + left +", " + top + ", " + right + ", " + bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return false;
    }
}
