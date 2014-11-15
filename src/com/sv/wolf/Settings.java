package com.sv.wolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends Activity {

	private Spinner selectPeoNum;
	private static final String[] numSelected = {  "6", "7", "8", "9",
			"10", "11", "12", "13", "14", "15" ,"16"};
	private ArrayAdapter adapter;
	private ImageButton go;
	private Intent intent;
	private String final_Num;
	private CheckBox witch;
	private CheckBox hunter;
	private CheckBox cupid;
	private CheckBox idiot;
	private NumberPicker numPick;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		selectPeoNum = (Spinner) findViewById(R.id.spinner);
		witch = (CheckBox) findViewById(R.id.hasWitch);
		hunter = (CheckBox) findViewById(R.id.hasHunter);
		cupid = (CheckBox) findViewById(R.id.hasCupid);
		idiot = (CheckBox) findViewById(R.id.hasIdiot);
		
		
		selectPeoNum
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						final_Num = numSelected[arg2];

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		go = (ImageButton) findViewById(R.id.go);
		adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
				numSelected);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		selectPeoNum.setAdapter(adapter);
		intent = new Intent(Settings.this, Identity.class);

		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int sum;
				int maxWerewolves;
				if (Integer.parseInt(final_Num) < 8)
					maxWerewolves = 1;
				else if (Integer.parseInt(final_Num) < 13)
					maxWerewolves = 2;
				else
					maxWerewolves = 3;
				sum = maxWerewolves + 1;
				if (witch.isChecked())
					sum = sum + 1;
				if (hunter.isChecked())
					sum = sum + 1;
				if (cupid.isChecked())
					sum = sum + 1;
				if (idiot.isChecked())
					sum = sum + 1;
				if (sum <= Integer.parseInt(final_Num)) {
					intent.putExtra("hasWitch", witch.isChecked());
					intent.putExtra("hasHunter", hunter.isChecked());
					intent.putExtra("hasCupid", cupid.isChecked());
					intent.putExtra("hasIdiot", idiot.isChecked());

					intent.putExtra("finalNum", final_Num);
					startActivity(intent);
				}else
					DisplayToast();
					
				

			}

			
			
		});
		
	}
	private void DisplayToast() {
		Toast.makeText(this,getResources().getString(R.string.overNum),Toast.LENGTH_SHORT).show();
		
	}	

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
/*
 * public void onBackPressed() {
 * 
 * Intent intent=new Intent(Settings.this,MainActivity.class);
 * startActivity(intent);
 * 
 * 
 * };
 * 
 * @Override public boolean onCreateOptionsMenu(Menu menu) { // TODO
 * Auto-generated method stub getMenuInflater().inflate(R.menu.main, menu);
 * return true; // return super.onCreateOptionsMenu(menu); } }
 */
