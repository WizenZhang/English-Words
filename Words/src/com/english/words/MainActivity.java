package com.english.words;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import com.english.words.custom_widget.Custom_Comparator;
import com.english.words.custom_widget.Custom_Comparator_String;
import com.english.words.custom_widget.Custom_Index;
import com.english.words.custom_widget.Custom_Index.onIndexListener;
import com.english.words.custom_widget.PinyinUtil;
import com.english.words.custom_widget.testFiles;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private Custom_Index mIndex;
	private ExpandableListView mListView;
	// test value
	private ArrayList<String> mKeys = new ArrayList<String>();
	
	final private boolean [] select={true,true,false,false,false,false,false,false,false,false,false, false, false,false,false,false,false,false,false,false};
	private ListAdapter mAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mIndex = (Custom_Index) findViewById(R.id.test);
		
		boolean [] selects=(boolean [])this.getIntent().getExtras().getSerializable("bundle");
		
		//System.out.println(Arrays.toString(selects));// 输出数组
		
		mIndex.setOnIndexListener(new onIndexListener() {

			@Override
			public void onStopSelect() {
				

			}

			@Override
			public void onStartSelect() {

			}

			@Override
			public void onSelect(String index) {
				if (mKeys.contains(index)) {
					mListView.setSelectedGroup(mKeys.indexOf(index));
				}
			}
		});
		mListView = (ExpandableListView) findViewById(R.id.list);
		initTestValus(selects);

		mAdapter = new ListAdapter();
		mAdapter.updateValue(getSortValues(), mKeys);
		mListView.setAdapter(mAdapter);
		
		for (int i = 0; i < mKeys.size(); i++) {
			mListView.expandGroup(i);
		}
		
	}

	private void initTestValus(boolean [] selects) {
		String words="#综合教程（上）,";
		//System.out.println("输出数组:"+Arrays.toString(select));// 输出数组
		for (int i = 0; i < select.length; i++) {
			if (selects[i]) {
				words= words + Words.wordsArray[i];	
			}
			
		}
		String [] StringArr= words.split(",");

		for (int i = 0; i < StringArr.length; i++) {
			testFiles testFiles = new testFiles();
			testFiles.setmFile(new File(StringArr[i]));
			testFiles.mIndex = PinyinUtil.getSpell(testFiles.getName()
					.substring(0, 1));  
			mTestLocalFiles.add(testFiles);
		}
				
	}

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	switch (item.getItemId()) {  
//        case R.id.action_settings:  
//        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
//	        builder.setTitle("选择背诵单元:");
//	        builder.setIcon(R.drawable.ic_launcher);
//	        final String[] items = new String[]{"Unit1--FOCUS", "Unit1--MORE", "Unit2-FOCUS", "Unit2-MORE","Unit3--FOCUS", "Unit3--MORE", "Unit4-FOCUS", "Unit4-MORE", 
//"Unit5--FOCUS", "Unit5--MORE","Unit6-FOCUS", "Unit6-MORE","Unit7--FOCUS", "Unit7--MORE", "Unit8-FOCUS", "Unit8-MORE","Unit9--FOCUS", "Unit9--MORE", "Unit10-FOCUS", "Unit10-MORE"};/*设置多选的内容*/
//	        //final boolean[] checkedItems = new boolean[]{false, true, false,false, true, false, false,false, true, false};/*设置多选默认状态*/
//	        builder.setMultiChoiceItems(items, select, new DialogInterface.OnMultiChoiceClickListener() {/*设置多选的点击事件*/
//	            @Override
//	            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//	                //checkedItems[which] = isChecked;
//	                //select[which]=isChecked;
//	                Toast.makeText(MainActivity.this, items[which] + " 加入选择"+ isChecked + "选择"+which, Toast.LENGTH_SHORT).show();
//	            }
//	        });
//	        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//	            @Override
//	            public void onClick(DialogInterface dialog, int which) {
//	            	initTestValus();
//	            	//System.out.println(Arrays.toString(select));// 输出数组
//	                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
//	            }
//	        });
//	        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//	            @Override
//	            public void onClick(DialogInterface dialog, int which) {
//	                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
//	            }
//	        });
//	        builder.setCancelable(false);
//	        builder.show();  
//            return true;  
//        default:  
//            return super.onOptionsItemSelected(item);  
//        }  
//    }

	private ArrayList<testFiles> mTestLocalFiles = new ArrayList<testFiles>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<String, ArrayList<testFiles>> getSortValues() {

		HashMap<String, ArrayList<testFiles>> result = new HashMap<String, ArrayList<testFiles>>();

		for (int i = 0; i < mTestLocalFiles.size(); i++) {
			testFiles t = mTestLocalFiles.get(i);
			String headChar = t.getIndex();
			if (!mKeys.contains(headChar)) {
				mKeys.add(headChar);
				ArrayList<testFiles> l = new ArrayList<testFiles>();
				l.add(t);
				result.put(headChar, l);
			} else {
				result.get(headChar).add(t);
			}
		}

		Iterator iterator = result.keySet().iterator();
		Comparator comparator = new Custom_Comparator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			ArrayList<testFiles> list = result.get(key);
			Collections.sort(list, comparator);
		}
		Collections.sort(mKeys, new Custom_Comparator_String());
		return result;
	}

	protected void copy() {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class ListAdapter implements ExpandableListAdapter {
		private HashMap<String, ArrayList<testFiles>> mHashMap;
		private ArrayList<String> mKeys;

		@Override
		public void registerDataSetObserver(DataSetObserver observer) {

		}

		public void updateValue(
				HashMap<String, ArrayList<testFiles>> sortValues,
				ArrayList<String> mKeys) {
			mHashMap = sortValues;
			this.mKeys = mKeys;
		}

		@Override
		public void unregisterDataSetObserver(DataSetObserver observer) {

		}

		@Override
		public int getGroupCount() {
			return mKeys.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return mHashMap.get(mKeys.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return mKeys.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return mHashMap.get(mKeys.get(groupPosition)).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.item_elist_title, null);
			}
			TextView t = (TextView) convertView.findViewById(R.id.item_title);
			t.setText(getGroup(groupPosition).toString());
			convertView.setClickable(true);
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.item_elist_sub, null);
			}
			TextView t = (TextView) convertView.findViewById(R.id.item_sub);
			t.setText(((testFiles) getChild(groupPosition, childPosition))
					.getName());
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEmpty() {
	
			return false;
		}

		@Override
		public void onGroupExpanded(int groupPosition) {

		}

		@Override
		public void onGroupCollapsed(int groupPosition) {

		}

		@Override
		public long getCombinedChildId(long groupId, long childId) {

			return childId;
		}

		@Override
		public long getCombinedGroupId(long groupId) {
		
			return groupId;
		}

	}
	
	@Override
	public void onBackPressed() {

		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
	}
}
