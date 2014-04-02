package com.sv.wolf;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Identity extends Activity {

	/*
	 * preNum是每一个玩家的编号，默认为1
	 * num指玩家的总人数
	 * witch,hunter,idiot,cupid分别指玩家的编号（从1到num)
	 * hasCupid,hasWithc,hasHunter,hasIdiot 如何玩家钩选了相应的职业，则为true
	 * ArrayList werewolves，狼人玩家的编号
	 * returnDialog按返回键时的确认对话框
	 * ArrayList<String> distributionResult最终的分配结果
	 */
	private TextView desc;
	private TextView preNumDesc;
	private int num;
	private boolean isClosed = true;
	private int prophet;
	private ArrayList werewolves = new ArrayList();
	private ImageButton imageButton;
	
	private int preNum = 1;
	private int witch;
	private int hunter;
	private int idiot;
	private boolean hasCupid;
	private int cupid;
	private boolean hasWitch;
	private boolean hasHunter;
	private boolean hasIdiot;
	private Dialog returnDialog;
	private ArrayList<String>distributionResult;
	private int maxWerewolves;
	

	private void distribution() {
		
		Random random = new Random();
		distributionResult=new ArrayList<String>();
		
		if (num < 8)
			maxWerewolves = 1;
		else if (num < 13)
			maxWerewolves = 2;
		else
			maxWerewolves = 3;
		ArrayList temp = new ArrayList();
		for (int i = 1; i <= num; i++)
			temp.add(i);
		for( int i=0;i<num+1;i++)
			distributionResult.add("empty");
		int i = random.nextInt(temp.size());
		werewolves.add(temp.get(i));
		distributionResult.set((Integer) temp.get(i),"werewolves");
		temp.remove(i);
		while (werewolves.size() < maxWerewolves) {
			i = random.nextInt(temp.size());
			werewolves.add(temp.get(i));
			distributionResult.set((Integer) temp.get(i)-1,"werewolves");
			temp.remove(i);
		}
		i = random.nextInt(temp.size());
		prophet = (Integer) temp.get(i);
		distributionResult.set((Integer) temp.get(i),"prophet");
		temp.remove(i);
		if (hasWitch) {
			i = random.nextInt(temp.size());
			witch = (Integer) temp.get(i);
			distributionResult.set((Integer) temp.get(i),"witch");
			temp.remove(i);
		}
		if (hasHunter) {
			i = random.nextInt(temp.size());
			hunter = (Integer) temp.get(i);
			distributionResult.set((Integer) temp.get(i),"hunter");
			temp.remove(i);
		}
		if (hasIdiot) {
			i = random.nextInt(temp.size());
			idiot = (Integer) temp.get(i);
			distributionResult.set((Integer) temp.get(i),"idiot");
			temp.remove(i);
		}
		if (hasCupid) {
			i = random.nextInt(temp.size());
			cupid = (Integer) temp.get(i);
			distributionResult.set((Integer) temp.get(i),"cupid");
			temp.remove(i);
		}
		for(int j=1;j<distributionResult.size();j++){
			if(distributionResult.get(j).equals("empty"))
				distributionResult.set(j, "villager");
				
		}
		

	}

	private void changeCard(int preNum) {
		if (werewolves.contains(preNum))
			// imageButton.setImageDrawable(getResources().getDrawable(R.drawable.werewolves));
			imageButton.setImageResource(R.drawable.werewolves);
		else if (preNum == witch)
			imageButton.setImageResource(R.drawable.witch);
		else if (preNum == prophet)
			imageButton.setImageResource(R.drawable.prophet);
		else if (preNum == cupid)
			imageButton.setImageResource(R.drawable.cupid);
		else if (preNum == hunter)
			imageButton.setImageResource(R.drawable.hunter);
		else if(preNum==idiot)
			imageButton.setImageResource(R.drawable.idiot);

		else
			// imageButton.setImageDrawable(getResources().getDrawable(R.drawable.villager));
			imageButton.setImageResource(R.drawable.villager);
	}

	private void returnCard() {
		// imageButton.setImageDrawable(getResources().getDrawable(R.drawable.unknown));
		imageButton.setImageResource(R.drawable.unknown);
	}

	private void descIden(int temNum) {
		if (werewolves.contains(temNum))
			desc.setText(getResources().getString(R.string.werewolves));
		else if (temNum == prophet)
			desc.setText(getResources().getString(R.string.prophet));
		else if (temNum == witch)
			desc.setText(getResources().getString(R.string.witch));
		else if (temNum == hunter)
			desc.setText(getResources().getString(R.string.hunter));
		else if (temNum == cupid)
			desc.setText(getResources().getString(R.string.cupid));
		else if(temNum==idiot)
			desc.setText(getResources().getString(R.string.idiot));
		else
			desc.setText(getResources().getString(R.string.villager));

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.identity);
		desc = (TextView) findViewById(R.id.desc);
		preNumDesc = (TextView) findViewById(R.id.preNumDesc);
		imageButton = (ImageButton) findViewById(R.id.card);
		
		num = Integer.parseInt(getIntent().getStringExtra("finalNum"));
		
		// 判定是否存在某种身份
		getHasIde();

		// 分配玩家身份
		distribution();

		preNumDesc.setText(preNum + "号的身份");

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (preNum <= num) {
					if (isClosed) {
						Rotate3dAnimation ra=new Rotate3dAnimation(0,90,200,200,0f,true);
						ra.setDuration(300);
						ra.setFillAfter(false);
					
					  	ra.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								
								changeCard(preNum);
								Rotate3dAnimation ra2=new Rotate3dAnimation(-90,0,200,200,0f,true);
								ra2.setDuration(400);
								ra2.setFillAfter(false);
								ra2.setAnimationListener(new AnimationListener() {
									
									@Override
									public void onAnimationStart(Animation arg0) {
										
										
									}
									
									@Override
									public void onAnimationRepeat(Animation arg0) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void onAnimationEnd(Animation arg0) {
										// TODO Auto-generated method stub
										
									}
								});
								imageButton.startAnimation(ra2);
								
							}
						});
						
						imageButton.startAnimation(ra);
						
						
						isClosed = false;
						descIden(preNum);

					} else {
						returnCard();
						preNum++;
						desc.setText(getResources().getString(R.string.unknown));

						isClosed = true;
					}
					if (preNum <= num) {
						
						 preNumDesc.setText(preNum + "号的身份");
						
					} else{
						preNumDesc.setText( "");
						returnCard();
						desc.setText(getResources().getString(R.string.identityEnd));
					}
				}else{
					returnCard();
					Intent intent=new Intent(Identity.this,Judge.class);
					intent.putStringArrayListExtra("distributionResult", distributionResult);
					intent.putExtra("maxWerewolves", maxWerewolves);
					startActivity(intent);
				}
				
			}

		});

	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onBackPressed() {
		
		returnDialog = new AlertDialog.Builder(Identity.this).setTitle("提示")
				.setMessage("点击确定将重新开始选人，是否是重新开始")
				.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent=new Intent(Identity.this,Settings.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
				}).setNegativeButton("否",null).show();
		
	}
	
	

	private void getHasIde() {
		hasWitch = getIntent().getBooleanExtra("hasWitch", false);
		hasHunter = getIntent().getBooleanExtra("hasHunter", false);
		hasCupid = getIntent().getBooleanExtra("hasCupid", false);
		hasIdiot = getIntent().getBooleanExtra("hasIdiot", false);

	}
}
