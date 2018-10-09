package com.english.words;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

public class LaunchActivity extends Activity {

	RelativeLayout rlRoot;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_launch);
	        
	        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
	        startAnim();
	    }
	 
	 /**
	     * ��������
	     */
	    private void startAnim() {

	        //��������
	    	AnimationSet set= new AnimationSet(false);
	    
	    	//���䶯��
	    	AlphaAnimation alpha = new AlphaAnimation(1, 1);
	    	alpha.setDuration(3000);//����ʱ��
	    	alpha.setFillAfter(true);//���ֶ���״̬

	    	set.addAnimation(alpha);
	    	
	    	//���ö�������
	    	set.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation arg0) {

					
				}
				
				@Override
				public void onAnimationRepeat(Animation arg0) {

					
				}
				
				@Override
				public void onAnimationEnd(Animation arg0) {
					jumpNextpage();
				}
			});
	    	rlRoot.startAnimation(set);
		}
	    
	    /**
	     * ��ת��һ��ҳ��
	     */
	   private void jumpNextpage(){

			// ��ת����ҳ��
			startActivity(new Intent(LaunchActivity.this,ChoiceActivity.class));
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		    finish();
	   }
}
