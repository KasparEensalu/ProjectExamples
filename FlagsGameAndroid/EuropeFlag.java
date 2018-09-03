package com.example.kaspar.funflags;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Kaspar on 1.05.2015.
 */
public class EuropeFlag extends Button {

    public int size;

    public EuropeFlag(Context context, AttributeSet attrs) {
        super(context, attrs);
        Random rnd = new Random();
        int choice = rnd.nextInt(20) + 1;
        if (choice == 1) {
            setBackgroundResource(R.drawable.e1);
        } else if (choice == 2) {
            setBackgroundResource(R.drawable.e2);
        } else if (choice == 3) {
            setBackgroundResource(R.drawable.e3);
        } else if (choice == 4) {
            setBackgroundResource(R.drawable.e4);
        } else if (choice == 5) {
            setBackgroundResource(R.drawable.e5);
        } else if (choice == 6) {
            setBackgroundResource(R.drawable.e6);
        } else if (choice == 7) {
            setBackgroundResource(R.drawable.e7);
        } else if (choice == 8) {
            setBackgroundResource(R.drawable.e8);
        } else if (choice == 9) {
            setBackgroundResource(R.drawable.e9);
        } else if (choice == 10) {
            setBackgroundResource(R.drawable.e10);
        } else if (choice == 11) {
            setBackgroundResource(R.drawable.e11);
        } else if (choice == 12) {
            setBackgroundResource(R.drawable.e12);
        } else if (choice == 13) {
            setBackgroundResource(R.drawable.e13);
        } else if (choice == 14) {
            setBackgroundResource(R.drawable.e14);
        } else if (choice == 15) {
            setBackgroundResource(R.drawable.e15);
        } else if (choice == 16) {
            setBackgroundResource(R.drawable.e16);
        } else if (choice == 17) {
            setBackgroundResource(R.drawable.e17);
        } else if (choice == 18) {
            setBackgroundResource(R.drawable.e18);
        } else if (choice == 19) {
            setBackgroundResource(R.drawable.e19);
        } else if (choice == 20) {
            setBackgroundResource(R.drawable.e20);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(size, size);
    }
}
