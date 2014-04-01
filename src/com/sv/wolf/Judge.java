package com.sv.wolf;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;


public class Judge extends Activity {

	private ArrayList<ImageButton> table = new ArrayList<ImageButton>();
	private ArrayList distributioResult;
	private int num;
	private ImageButton zoom;
	private ImageButton iButton;
	private ArrayList hasDead=new ArrayList();
	private int tempDead;
	private int currentAlive;

	private void changeCard() {
		for (int i = 1; i < distributioResult.size(); i++) {
			
			 if (distributioResult.get(i).equals("werewolves"))
				table.get(i - 1).setImageResource(R.drawable.werewolves);
			else if (distributioResult.get(i).equals("cupid"))
				table.get(i - 1).setImageResource(R.drawable.cupid);
			else if (distributioResult.get(i).equals("hunter"))
				table.get(i - 1).setImageResource(R.drawable.hunter);
			else if (distributioResult.get(i).equals("prophet"))
				table.get(i - 1).setImageResource(R.drawable.prophet);
			else if (distributioResult.get(i).equals("witch"))
				table.get(i - 1).setImageResource(R.drawable.witch);
			else if (distributioResult.get(i).equals("idiot"))
				table.get(i - 1).setImageResource(R.drawable.idiot);
			else 
				table.get(i - 1).setImageResource(R.drawable.villager);

		}

	}

	private void returnCard() {
		for (int i = 0; i < table.size(); i++)
		{
			
			table.get(i).setImageResource(R.drawable.unknown);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.judge);
		distributioResult = getIntent().getStringArrayListExtra("distributionResult");
		num = distributioResult.size()-1;
		zoom = (ImageButton) findViewById(R.id.zoom);
		getImageButton();
		zoom.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int key=event.getAction();
				switch(key){
					case MotionEvent.ACTION_DOWN:
						changeCard();
						break;
					case MotionEvent.ACTION_UP:
						returnCard();
						break;
				}
				
				return false;
			}
		});

	}

	private void getImageButton() {
		for (int i = 1; i <= num; i++) {
			Field f;
			try {
				f = Class.forName("com.sv.wolf.R$id").getField("no" + i);
				iButton = (ImageButton) findViewById(f.getInt(f));
				iButton.setVisibility(View.VISIBLE);
			
				
				iButton.setOnLongClickListener(new OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						((ImageView) v).setImageResource(R.drawable.dead);
						v.setVisibility(View.INVISIBLE);
						
							
						
						return false;
					}
				});
				table.add(iButton);
				
			} catch (NoSuchFieldException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
			
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			}
		
			

		}

	}

}
