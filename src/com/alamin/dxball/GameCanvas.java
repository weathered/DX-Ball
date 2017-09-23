package com.alamin.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;
import java.util.LinkedList;


public class GameCanvas extends View implements Runnable{
	GameActivity ga = null;
	LinkedList<Bricks> bricks=new LinkedList<Bricks>();
	public static int checkWidth=0;
	public static int checkHeight=0;
	public static int life;
	public static boolean gameOver;
    public static boolean newLife;
    boolean first = true, brickcolor=false;
    public static Bar bar;
    Paint paint;
    Ball dxBall;
    int count, color;
    int score = 0;
    float barWidth = 300, brickX = 0, brickY=50;
    
    public GameCanvas(Context context){
        super(context);
        ga = (GameActivity) context;
        paint = new Paint();
        bar = new Bar();
        life = 5;
        gameOver = false;
        newLife = true;
    }

    @Override
    protected void onDraw(Canvas canvas){
    	checkWidth = canvas.getWidth();
        checkHeight = canvas.getHeight();
        if(first) {
        	initGame(canvas);
        }
        if(newLife){
            initNewLife(canvas);
        }

        displayScore(canvas);
        drawObjects(canvas);
        dxBall.ifBoundary(canvas);
        dxBall.move();
        brickCollision(bricks, dxBall, canvas);
        barCollision(bar, dxBall, canvas);
        isGameOver(canvas);
        this.run();
    }
    
    public void drawObjects(Canvas canvas){
    	canvas.drawCircle(dxBall.getX(), dxBall.getY(), dxBall.getRadius(), dxBall.getPaint()); //ball
        canvas.drawRect(bar.getLeft(), bar.getTop(), bar.getRight(), bar.getBottom(), bar.getPaint()); //bar
        for(int i=0;i<bricks.size();i++){ //bricks
            canvas.drawRect(bricks.get(i).getLeft(),bricks.get(i).getTop(),bricks.get(i).getRight(),bricks.get(i).getBottom(),bricks.get(i).getPaint());
        }
    }
    
    public void isGameOver(Canvas canvas){
    	if(bricks.size()==0){
    		gameOver = true;
    	}
        if(gameOver==true){
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("Game Over, you scored "+score,checkWidth/2-200,checkHeight/2,paint);
            gameOver = false;

            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ga.finish();
        }
    }
    private void displayScore(Canvas canvas) {
        paint.setColor(Color.rgb(66, 244, 155));
        paint.setTextSize(45);
        paint.setFakeBoldText(true);
        canvas.drawText("Score: "+ score, 10, 40, paint);

        paint.setTextSize(45);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: "+life, canvas.getWidth()-140, 40, paint);
	}

	public void barCollision(Bar dxBar,Ball dxBall,Canvas canvas){
        if(((dxBall.getY()+dxBall.getRadius())>=dxBar.getTop())&&((dxBall.getY()+dxBall.getRadius())<=dxBar.getBottom())&& ((dxBall.getX())>=dxBar.getLeft())&& ((dxBall.getX())<=dxBar.getRight())) {
            dxBall.dy *= -1;
        }

    }
	
    public void brickCollision(LinkedList<Bricks> br ,Ball dxBall,Canvas canvas){
        for(int i=0;i<br.size();i++) {
            if (((dxBall.getY() - dxBall.getRadius()) <= br.get(i).getBottom()) && ((dxBall.getY() + dxBall.getRadius()) >= br.get(i).getTop()) && ((dxBall.getX() + dxBall.getRadius() ) >= br.get(i).getLeft()) && ((dxBall.getX() - dxBall.getRadius()) <= br.get(i).getRight())) {
                if(br.get(i).count == 0){
                	br.remove(i);
                }else{
                	bricks.get(i).paint.setColor(Color.rgb(255, 153, 51));
                	br.get(i).count-=1;
                }
                score+=1;
                dxBall.dy *= -1; ;
            }
        }

    }
    
    public void initGame(Canvas canvas){
        first = false;
        for(int i=0; i<24; i++){
            if(brickX>=checkWidth) {  // reset when crossed border
                brickX = 0;
                brickY += 100;
                brickcolor = !brickcolor;
            }
            if(brickcolor){
                color = Color.rgb(204, 102, 0);
                count = 1;
                brickcolor = !brickcolor;
            }
            else{
                color = Color.rgb(255, 153, 51);
                count = 0;
                brickcolor = !brickcolor;
            }
            bricks.add(new Bricks(brickX, brickY, brickX + checkWidth/7, brickY+90, color, count));
            brickX += checkWidth / 6; // increasing x for setting position for the next brick
        }

        dxBall=new Ball(checkWidth/2, checkHeight/2-100);
        
        bar.setBottom(getHeight());
        bar.setLeft((getWidth()/2) - (barWidth/2));
        bar.setRight((getWidth()/2) + (barWidth/2));
        bar.setTop(getHeight() - 30);
    }
    
    public void initNewLife(Canvas canvas){
    	newLife = false;
        dxBall=new Ball(checkWidth/2,checkHeight-100);
    }

    public void run() {
        invalidate();
    }

}