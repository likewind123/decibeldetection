package com.example.recordmusic;


import java.util.StringTokenizer;

import android.app.Activity;
import android.media.AudioFormat; 
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
 
   
 

    protected Saudioclient     m_recorder ;
    private  Button start,end;
    private  TextView tv1;
    private  EditText et1;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.button1);
        end=(Button) findViewById(R.id.button2);
        tv1=(TextView) findViewById(R.id.textView1);
        et1=(EditText) findViewById(R.id.editText1);
        m_recorder = new Saudioclient() ;
        
        
        start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
				 m_recorder.init() ;
	             m_recorder.start() ;
	             try
	             {
	            	 Thread.sleep(1000) ;
	            	 Log.d("开始止",String.valueOf(11111));
	            	 m_recorder.sstop();
	            	 Log.d("停止完成",String.valueOf(11111));
	            	
	            	 
	             }catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
        
        end.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				 tv1.setText(m_recorder.free()+"db") ;
				m_recorder.free();
				 float a=m_recorder.getValue();
            	 String b=null;
            	 b="分贝为:"+a;
            	 et1.setText(b);
				 m_recorder = null ;
			}
		});
    }
   


   

}



