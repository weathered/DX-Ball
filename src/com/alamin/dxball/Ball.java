package com.alamin.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    public int gameOver=0, x, y, rad, dx, dy;
    public Paint paint;
    
    public Ball(int _x,int _y){
        x=_x+100;
        y=_y-500;
        dx=6;
        dy=-6;
        rad=32;
        paint=new Paint();
        paint.setColor(Color.rgb(244, 179, 66));
    }
    
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return rad;
    }

    public Paint getPaint() {
        return paint;
    }

    public void move(){
        x=x+dx;
        y=y+dy;
    }

    public void ifBoundary(Canvas canvas) {
    	if((x+rad)>=canvas.getWidth() || (x-rad)<=0){
            dx *= -1;
        }
        if( (y-rad)<=40){ // 40 to skip score display
            dy *= -1;
        }
        if((this.y-this.rad)>=canvas.getHeight()){
        	GameCanvas.life-=1;
        	GameCanvas.newLife=true;
        }
        if(GameCanvas.life==0){
            GameCanvas.gameOver = true;
        }
    }
}
