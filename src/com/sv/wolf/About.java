package com.sv.wolf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

public class About extends Activity{
	
	private ImageButton about;
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about);
		about=(ImageButton) findViewById(R.id.about);
		
}
}
