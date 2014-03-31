package com.sv.wolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private ImageButton bStart;
	

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		bStart=(ImageButton) findViewById(R.id.imageButton1);
		bStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iSettings=new Intent(MainActivity.this,Settings.class);
				startActivity(iSettings);
				
			}
		});
			
		
	}
/*
@Override
public void onBackPressed() {
	finish();
};
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
