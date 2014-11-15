package com.sv.wolf;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Judge extends Activity {

	private ArrayList<ImageButton> table = new ArrayList<ImageButton>();
	private ArrayList distributioResult;
	private int num;
	private ImageButton zoom;
	private ImageButton iButton;
	private boolean[] isAlive;
	private int currentLive;
	private TextView gameResult;
	private int currentWerewolves;
	private int maxWerewolves;
	
	//private int currentAlive;
	//private int currentWolfAlive;
	//private int maxWerewolves;
	

	private void changeCard() {
		
		for (int i = 1; i < distributioResult.size(); i++) {
			if(isAlive[i-1]){
			 if ((distributioResult.get(i).equals("werewolves")))
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

	}

	private void returnCard() {
		for (int i = 0; i < table.size(); i++)
		{
			if(isAlive[i])
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
		maxWerewolves=getIntent().getIntExtra("maxWerewolves",0);
		getImageButton();
		gameResult=(TextView) findViewById(R.id.gameResult);
		
		isAlive=new boolean[num];
		currentLive=num;
		currentWerewolves=maxWerewolves;
		for(int i=0;i<num;i++){
			isAlive[i]=true;
		}
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
		for (int tempI=1; tempI <= num; tempI++) {
			Field f;
			try {
				f = Class.forName("com.sv.wolf.R$id").getField("no" + tempI);
				iButton = (ImageButton) findViewById(f.getInt(f));
				iButton.setVisibility(View.VISIBLE);
			
				
				iButton.setOnLongClickListener(new OnLongClickListener() {
					
					

					@SuppressWarnings("unchecked")
					@Override
					public boolean onLongClick(View v) {
						
						//v.setVisibility(View.INVISIBLE);
						int index=table.indexOf(v);
 						if(isAlive[index]){
 							((ImageView) v).setImageResource(R.drawable.dead);
 							isAlive[index]=false;
 							if(distributioResult.get(index+1).equals("werewolves"))
 								currentWerewolves--;
 							currentLive--;
 						
 						}
 						if(currentWerewolves==0)
 							gameResult.setText(getResources().getString(R.string.villagerWin));
 						if(currentLive-currentWerewolves<=currentWerewolves)
 							gameResult.setText(getResources().getString(R.string.wolfWin));
 						if(currentLive==0)
 						{
 							Intent intent=new Intent(Judge.this,Punish.class);
 							startActivity(intent);
 						}
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
