package com.sv.wolf;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class About extends Activity{
	
	
	private TextView tAbout;
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about);
		tAbout=(TextView) findViewById(R.id.textAbout);
	
		tAbout.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		
}
}
