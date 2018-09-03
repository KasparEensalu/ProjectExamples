package com.example.kaspar.funflags;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Kaspar on 7.05.2015.
 */
public class AsiaFlag extends Button {

    public int size;

    public AsiaFlag(Context context, AttributeSet attrs) {
        super(context, attrs);
        Random rnd = new Random();
        int choice = rnd.nextInt(20) + 1;
        if (choice == 1) {
            setBackgroundResource(R.drawable.as1);
        } else if (choice == 2) {
            setBackgroundResource(R.drawable.as2);
        } else if (choice == 3) {
            setBackgroundResource(R.drawable.as3);
        } else if (choice == 4) {
            setBackgroundResource(R.drawable.as4);
        } else if (choice == 5) {
            setBackgroundResource(R.drawable.as5);
        } else if (choice == 6) {
            setBackgroundResource(R.drawable.as6);
        } else if (choice == 7) {
            setBackgroundResource(R.drawable.as7);
        } else if (choice == 8) {
            setBackgroundResource(R.drawable.as8);
        } else if (choice == 9) {
            setBackgroundResource(R.drawable.as9);
        } else if (choice == 10) {
            setBackgroundResource(R.drawable.as10);
        } else if (choice == 11) {
            setBackgroundResource(R.drawable.as11);
        } else if (choice == 12) {
            setBackgroundResource(R.drawable.as12);
        } else if (choice == 13) {
            setBackgroundResource(R.drawable.as13);
        } else if (choice == 14) {
            setBackgroundResource(R.drawable.as14);
        } else if (choice == 15) {
            setBackgroundResource(R.drawable.as15);
        } else if (choice == 16) {
            setBackgroundResource(R.drawable.as16);
        } else if (choice == 17) {
            setBackgroundResource(R.drawable.as17);
        } else if (choice == 18) {
            setBackgroundResource(R.drawable.as18);
        } else if (choice == 19) {
            setBackgroundResource(R.drawable.as19);
        } else if (choice == 20) {
            setBackgroundResource(R.drawable.as20);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(size, size);
    }
}
