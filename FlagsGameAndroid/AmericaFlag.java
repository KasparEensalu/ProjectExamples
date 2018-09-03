package com.example.kaspar.funflags;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Kaspar on 7.05.2015.
 */
public class AmericaFlag extends Button {

    public int size;

    public AmericaFlag(Context context, AttributeSet attrs) {
        super(context, attrs);
        Random rnd = new Random();
        int choice = rnd.nextInt(20) + 1;
        if (choice == 1) {
            setBackgroundResource(R.drawable.am1);
        } else if (choice == 2) {
            setBackgroundResource(R.drawable.am2);
        } else if (choice == 3) {
            setBackgroundResource(R.drawable.am3);
        } else if (choice == 4) {
            setBackgroundResource(R.drawable.am4);
        } else if (choice == 5) {
            setBackgroundResource(R.drawable.am5);
        } else if (choice == 6) {
            setBackgroundResource(R.drawable.am6);
        } else if (choice == 7) {
            setBackgroundResource(R.drawable.am7);
        } else if (choice == 8) {
            setBackgroundResource(R.drawable.am8);
        } else if (choice == 9) {
            setBackgroundResource(R.drawable.am9);
        } else if (choice == 10) {
            setBackgroundResource(R.drawable.am10);
        } else if (choice == 11) {
            setBackgroundResource(R.drawable.am11);
        } else if (choice == 12) {
            setBackgroundResource(R.drawable.am12);
        } else if (choice == 13) {
            setBackgroundResource(R.drawable.am13);
        } else if (choice == 14) {
            setBackgroundResource(R.drawable.am14);
        } else if (choice == 15) {
            setBackgroundResource(R.drawable.am15);
        } else if (choice == 16) {
            setBackgroundResource(R.drawable.am16);
        } else if (choice == 17) {
            setBackgroundResource(R.drawable.am17);
        } else if (choice == 18) {
            setBackgroundResource(R.drawable.am18);
        } else if (choice == 19) {
            setBackgroundResource(R.drawable.am19);
        } else if (choice == 20) {
            setBackgroundResource(R.drawable.am20);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(size, size);
    }
}
