package com.alamin.dxball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DXBallActivity extends Activity {
	
	public static boolean sensorDisabled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorDisabled = false;
        setContentView(R.layout.main);
    }
    
    public void startGame(View view){
    	Intent intent = new Intent(DXBallActivity.this, GameActivity.class);
    	startActivity(intent);
    }
    
    public void stopGame(View view){
    	if(!sensorDisabled){
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}else{
    		moveTaskToBack(true); // return home
        	android.os.Process.killProcess(android.os.Process.myPid());
        	System.exit(1);
    	}	
    }
}