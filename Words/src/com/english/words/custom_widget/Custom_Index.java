package com.english.words.custom_widget;

import java.util.ArrayList;
import java.util.Arrays;

import com.english.words.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class Custom_Index extends RelativeLayout {

	private static final int MSG_SHOW = 0;
	private static final int MSG_HIDE = 1;
	private String mIndexs[] = { "#", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
	private Handler mHandler = new Handler() {

		@Override
		public void dispatchMessage(Message msg) {
			int key = msg.what;
			switch (key) {
			case MSG_SHOW:
				String str = (String) msg.obj;
				showText(str);
				break;
			case MSG_HIDE:
				hideText();
			default:
				break;
			}
			super.dispatchMessage(msg);
		}
	};

	public Custom_Index(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);

	}

	private Context mContext;

	public Custom_Index(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		// initUI();
	}

	private TextView mTextView_index;
	private LinearLayout mLayout;
	private int mHeight = 0;

	private void initUI() {
		RelativeLayout view = (RelativeLayout) this.findViewById(R.id.index);
		mLayout = (LinearLayout) view.findViewById(R.id.view_index);
		mLayout.setOnTouchListener(touchListener);
		mTextView_index = (TextView) view.findViewById(R.id.view_text);
		initIndexLayout();
	}

	/**
	 * 外部接管索引列表的touch事件,此时回调函数不起作用
	 * 
	 * @param listener
	 */
	public void setTouchListener(OnTouchListener listener) {
		// touchListener = listener;
		mLayout.setOnTouchListener(listener);
	}

	private onIndexListener mIndexListener;

	public void setOnIndexListener(onIndexListener listener) {
		mIndexListener = listener;
	}

	private OnTouchListener touchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (mHeight == 0) {
				mHeight = mLayout.getHeight() / mIndexs.length;
			}
			if (v == mLayout) {
				float y = event.getY() - mLayout.getTop();
				int index = (int) (y / mHeight);
				String key = null;
				if (index > -1 && index < mIndexs.length) {// 防止越界
					key = mIndexs[index];
				}
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (TextUtils.isEmpty(key) || key.equals("#")) {
						hideText();
					} else {
						showText(key);
					}
					if (mIndexListener != null) {
						mIndexListener.onStartSelect();
						mIndexListener.onSelect(key);
					}
					break;

				case MotionEvent.ACTION_MOVE:
					if (TextUtils.isEmpty(key) || key.equals("#")) {
						hideText();
					} else {
						showText(key);
					}
					if (mIndexListener != null) {
						mIndexListener.onSelect(key);
					}
					break;
				case MotionEvent.ACTION_UP:
					if (mIndexListener != null) {
						mIndexListener.onStopSelect();
					}
					hideText();
					break;
				}
			}
			return false;
		}
	};

	/**
	 * 更新 右侧索引栏
	 * 
	 * @param index
	 */
	public void setIndex(String index[]) {
		Arrays.sort(index);
		if (index != null) {
			mIndexs = index;
			mLayout.removeAllViews();
			initIndexLayout();
			this.invalidate();
		}
	}

	private ArrayList<TextView> mArrayList_textView;

	public void initIndexLayout() {
		if (mLayout == null)
			return;
		if (mArrayList_textView == null) {
			mArrayList_textView = new ArrayList<TextView>();
		} else {
			mArrayList_textView.clear();
		}
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 1);
		for (int i = 0; i < mIndexs.length; i++) {
			final TextView tv = new TextView(mContext);
			tv.setLayoutParams(params);
			tv.setGravity(Gravity.CENTER);
			tv.setText(mIndexs[i]);
			tv.setTextSize(12);
			mLayout.addView(tv);
			mArrayList_textView.add(tv);
		}
	}

	public void setText(String str) {
		mTextView_index.setText(str);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initUI();
	}

	private void sendMsg(int what, String obj, long timeDelay) {
		if (timeDelay <= 0)
			timeDelay = 0;
		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		mHandler.removeMessages(what);
		mHandler.sendMessageDelayed(msg, timeDelay);
	}

	public void showText() {
		mTextView_index.setVisibility(View.VISIBLE);
	}

	public void showText(String str) {
		setText(str);
		showText();
	}

	public void hideText() {
		mTextView_index.setVisibility(View.GONE);
	}

	public void delayShowText(long time) {
		sendMsg(MSG_SHOW, "", time);
	}

	public void delayHideText(long time) {
		sendMsg(MSG_HIDE, "", time);
	}

	public void delayShowText(String str, long time) {
		setText(str);
		delayShowText(time);
	}

	/**
	 * 索引选择的回调函数
	 * 
	 * @author maclay
	 * 
	 */
	public interface onIndexListener {
		/**
		 * 开始时回调
		 */
		void onStartSelect();

		/**
		 * 选择变化时回调
		 * 
		 * @param index
		 */
		void onSelect(String index);

		/**
		 * 结束时回调
		 */
		void onStopSelect();
	}
}
