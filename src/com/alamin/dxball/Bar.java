package com.alamin.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Bar {
    float top,bottom,left,right;
    Canvas canvas = new Canvas();
    Paint paint;
    Point point;
    int x,y;


    Bar(){
        left =0;
        top=0;
        right=0;
        bottom=0;
        paint=new Paint();
        paint.setColor(Color.rgb(66, 185, 244));

    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public Paint getPaint() {
        return paint;
    }

    public float getTop() {
        return top;
    }

    public void moveBar(boolean leftPos){
        if(leftPos==true){
            if(GameCanvas.checkWidth>=right) {
            	for(int i=0; i<15; i++){
	                ++left;
	                ++right;
            	}
            }
        }
        else{
            if(0<=left) {
            	for(int i=0; i<15; i++){
	                --left;
	                --right;
            	}
            }
        }
   }
}
