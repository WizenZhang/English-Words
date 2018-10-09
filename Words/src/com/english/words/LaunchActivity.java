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
	     * 开启动画
	     */
	    private void startAnim() {

	        //动画集合
	    	AnimationSet set= new AnimationSet(false);
	    
	    	//渐变动画
	    	AlphaAnimation alpha = new AlphaAnimation(1, 1);
	    	alpha.setDuration(3000);//动画时间
	    	alpha.setFillAfter(true);//保持动画状态

	    	set.addAnimation(alpha);
	    	
	    	//设置动画监听
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
	     * 跳转下一个页面
	     */
	   private void jumpNextpage(){

			// 跳转到主页面
			startActivity(new Intent(LaunchActivity.this,ChoiceActivity.class));
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		    finish();
	   }
}
