package com.sv.wolf;

import android.app.Activity;
import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ImageView;
import android.widget.TextView;

public class Punish extends Activity implements SensorEventListener{
	
	private TextView temp;
	private SensorManager mSensorManager;
	private Vibrator vibrator;
	private int i=0;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.punish);
	temp= (TextView) findViewById(R.id.temp);
	mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
	vibrator=(Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
	mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  SensorManager.SENSOR_DELAY_NORMAL);  
	
	
}

 @Override
protected void onStop() {
	mSensorManager.unregisterListener(this);
	super.onStop();
}
@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {

	
}
@Override
public void onSensorChanged(SensorEvent event) {
	int sensorType=event.sensor.getType();
	float[] values=event.values;
	if(sensorType==Sensor.TYPE_ACCELEROMETER){
		if((Math.abs(values[0])>14||Math.abs(values[1])>14||Math.abs(values[2])>14)){ 
			i++;
			temp.setText(i+"");
		}
		}
	}
		
	
}


