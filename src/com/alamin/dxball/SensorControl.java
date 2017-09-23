package com.alamin.dxball;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

public class SensorControl extends Service implements SensorEventListener{
	
	public static SensorManager sensorManager;
	Sensor Accelerometer;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int onStartCommand(Intent intent, int flags, int startId){
		//Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		Accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
        sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_GAME);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
        return START_STICKY;
	}
	
	public void onSensorChanged(SensorEvent event){
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			float datax = event.values[0];	
			if(datax>1){
				GameCanvas.bar.moveBar(false);
			}else if (datax < -1 ){
				GameCanvas.bar.moveBar(true);
			}
		}
	}
	public void onPause(){
		this.onDestroy();
	}
	
	@Override
	public void onDestroy(){
		sensorManager.unregisterListener(this);
		Toast.makeText(getBaseContext(), "Stopped", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}