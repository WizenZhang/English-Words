package com.english.words;

import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Toast;

public class ChoiceActivity extends Activity implements android.view.View.OnClickListener{

	private boolean [] select={false,false,false,false,false,false,false,false,false,false,false, false, false,false,false,false,false,false,false,false};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice);
		findViewById(R.id.btnmultichoiceitems).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnmultichoiceitems:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("ѡ���е�Ԫ:");
	        builder.setIcon(R.drawable.master_small);
	        final String[] items = new String[]{"Unit1--FOCUS", "Unit1--MORE", "Unit2-FOCUS", "Unit2-MORE","Unit3--FOCUS", "Unit3--MORE", "Unit4-FOCUS", "Unit4-MORE", 
"Unit5--FOCUS", "Unit5--MORE","Unit6-FOCUS", "Unit6-MORE","Unit7--FOCUS", "Unit7--MORE", "Unit8-FOCUS", "Unit8-MORE","Unit9--FOCUS", "Unit9--MORE", "Unit10-FOCUS", "Unit10-MORE"};/*���ö�ѡ������*/
	        //final boolean[] checkedItems = new boolean[]{false, true, false,false, true, false, false,false, true, false};/*���ö�ѡĬ��״̬*/
	        builder.setMultiChoiceItems(items, select, new DialogInterface.OnMultiChoiceClickListener() {/*���ö�ѡ�ĵ���¼�*/
	            @Override
	            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
	                //checkedItems[which] = isChecked;
	                //select[which]=isChecked;
	                //Toast.makeText(ChoiceActivity.this, items[which] + " ����ѡ��"+ isChecked + "ѡ��"+which, Toast.LENGTH_SHORT).show();
	            }
	        });
	        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            	//��ת����ҳ
						Intent intent = new Intent();
						intent.setClass(ChoiceActivity.this, MainActivity.class);
						Bundle booleanArray = new Bundle();  
						booleanArray.putSerializable("bundle", select);  
						intent.putExtras(booleanArray);
						startActivity(intent);
						//�����л����������ұ߽��룬����˳� 
						overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
	            	//System.out.println(Arrays.toString(select));// �������
						
	                Toast.makeText(ChoiceActivity.this, "�������", Toast.LENGTH_SHORT).show();
	            }
	        });
	        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                //Toast.makeText(ChoiceActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
	            }
	        });
	        builder.setCancelable(false);
	        builder.show();  
			break;
		}
		
	}
}
