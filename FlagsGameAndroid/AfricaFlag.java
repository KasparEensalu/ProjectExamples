package com.example.kaspar.funflags;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Kaspar on 2.05.2015.
 */
public class AfricaFlag extends Button {

    public int size;

    public AfricaFlag(Context context, AttributeSet attrs) {
        super(context, attrs);
        Random rnd = new Random();
        int choice = rnd.nextInt(20) + 1;
        if (choice == 1) {
            setBackgroundResource(R.drawable.af1);
        } else if (choice == 2) {
            setBackgroundResource(R.drawable.af2);
        } else if (choice == 3) {
            setBackgroundResource(R.drawable.af3);
        } else if (choice == 4) {
            setBackgroundResource(R.drawable.af4);
        } else if (choice == 5) {
            setBackgroundResource(R.drawable.af5);
        } else if (choice == 6) {
            setBackgroundResource(R.drawable.af6);
        } else if (choice == 7) {
            setBackgroundResource(R.drawable.af7);
        } else if (choice == 8) {
            setBackgroundResource(R.drawable.af8);
        } else if (choice == 9) {
            setBackgroundResource(R.drawable.af9);
        } else if (choice == 10) {
            setBackgroundResource(R.drawable.af10);
        } else if (choice == 11) {
            setBackgroundResource(R.drawable.af11);
        } else if (choice == 12) {
            setBackgroundResource(R.drawable.af12);
        } else if (choice == 13) {
            setBackgroundResource(R.drawable.af13);
        } else if (choice == 14) {
            setBackgroundResource(R.drawable.af14);
        } else if (choice == 15) {
            setBackgroundResource(R.drawable.af15);
        } else if (choice == 16) {
            setBackgroundResource(R.drawable.af16);
        } else if (choice == 17) {
            setBackgroundResource(R.drawable.af17);
        } else if (choice == 18) {
            setBackgroundResource(R.drawable.af18);
        } else if (choice == 19) {
            setBackgroundResource(R.drawable.af19);
        } else if (choice == 20) {
            setBackgroundResource(R.drawable.af20);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(size, size);
    }
}