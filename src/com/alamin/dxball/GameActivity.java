package com.alamin.dxball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SensorControl.class);
        startService(intent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameCanvas(this));
    }
	@Override
	public void onDestroy(){
		DXBallActivity.sensorDisabled = true;
		Intent i = new Intent(this, SensorControl.class);
		stopService(i);
		super.onDestroy();
	}
}
