package com.alamin.dxball;

import android.graphics.Paint;

public class Bricks {
    float top,bottom,left,right;
    public int count;
    Paint paint;

    Bricks(float _left, float _top, float _right, float _bottom, int _color, int _count){

        left = _left;
        top = _top;
        right = _right;
        bottom = _bottom;
        count = _count;
        paint = new Paint();
        paint.setColor(_color);
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
    
    public float getBottom() {
        return bottom;
    }

    public void setLeft(float left) {
        this.left = left;
    }
    
    public float getLeft() {
        return left;
    }

    public void setRight(float right) {
        this.right = right;
    }
    
    public float getRight() {
        return right;
    }

    public void setTop(float top) {
        this.top = top;
    }
    
    public float getTop() {
        return top;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setCount(int count){
    	this.count=count;
    }
    
    public int getCount(){
    	return count;
    }
}
