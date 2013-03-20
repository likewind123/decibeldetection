package com.example.recordmusic;


import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;


public class Saudioclient extends Thread
{
	protected AudioRecord m_in_rec ; 
    protected int         m_in_buf_size ;
    protected byte []     m_in_bytes ;
    protected boolean     m_keep_running ;
    protected int		  leng;
    private   float         value;
    
    public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	protected LinkedList<byte[]>  m_in_q ;
  
    public void run()
 {
    	int r=0;
    	leng =0;
    	float value=0;
    	int temp0=0;
    	int temp1=0;
    	int temp2=0;
    	int temp3=0;
      try
      {
          byte [] bytes_pkg ;
             m_in_rec.startRecording() ;
             byte[] buffer = new byte[m_in_buf_size];
             leng=buffer.length;
             for(int i=0;i<leng;i++)
            	 buffer[i]=0;
             int i=0;
             while (m_keep_running) { 
            	 temp3++;
            	  r = m_in_rec.read(buffer, 0, m_in_buf_size); 
            	  bytes_pkg=buffer.clone();
            	  m_in_q.add(bytes_pkg);
//            	  Log.d("spl", String.valueOf(r));
             }
            	 int v = 0; 
            	 temp0=buffer[0];
            	 temp1=buffer[7];
            	 temp2=buffer[14];
            	 int c=0;
            	 for( i=0;i<temp3;i++)
            		 
            	 {
            		 bytes_pkg=m_in_q.get(i).clone();
            		 int b=0;
            		 while(b<bytes_pkg.length&&c<8000)
            		 {
            		     //Log.d("第"+i+"行"+b+"个位置的值为：", String.valueOf(bytes_pkg[b]));
            		     	int bb=bytes_pkg[b];
            			 	b++;
            		     	c++;
            		        value=bb*bb;
            		 }
            		 
            	 }
            	 Log.d("总共录入多少个数据", String.valueOf(c));
            	 // 将 buffer 内容取出，进行平方和运算 
            	
            	 
//            	 Log.d("spl000", String.valueOf(temp0)); 
//            	 Log.d("spl100", String.valueOf(temp1)); 
//            	 Log.d("spl200", String.valueOf(temp2)); 
//            	 Log.d("spl300", String.valueOf(temp3)); 
//            	 Log.d("spl123", String.valueOf(leng));
//            	 Log.d("spl", String.valueOf(v)); 
//            	 Log.d("spl", String.valueOf(r)); 
//            	 Log.d("spl", String.valueOf(v / (float) temp3)); 
            	 value=Math.abs(v / (float) temp3);
             
            	 Log.d("值为",String.valueOf(value)); 
             
             
//             while(m_keep_running)
//             {
//                 m_in_rec.read(m_in_bytes, 0, m_in_buf_size) ;
//                 bytes_pkg = m_in_bytes.clone() ;
//                
//                    m_in_q.add(bytes_pkg) ;
//             }
     
             m_in_rec.stop() ;
             m_in_rec = null ;
             m_in_bytes = null ;
      
        
      }
      catch(Exception e)
      {
       e.printStackTrace();
      }
    }
   
    public void init()
    {
     m_in_buf_size =  AudioRecord.getMinBufferSize(8000,
                        AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_PCM_16BIT);
   
  m_in_rec = new AudioRecord(MediaRecorder.AudioSource.MIC,
  8000,
  AudioFormat.CHANNEL_IN_MONO,
  AudioFormat.ENCODING_PCM_16BIT,
  m_in_buf_size) ;
  
  m_in_bytes = new byte [m_in_buf_size] ;
  
  m_keep_running = true ;
  m_in_q=new LinkedList<byte[]>();
  
    

    }
    public void sstop()
    {
    	m_keep_running = false ;
    }
    
    
   
    public void free()
 {
    	int value = 0;
    	int i=0;
    	byte[] a = null;
  m_keep_running = false ;
  
//  	while(m_in_q.get(i)!=null)
//  	{
//  		a=m_in_q.get(i);
//  		
//  		i++;
//  	}
//  	for(int j=0;j<i;j++)
//  	{
//  		value+=a[j];
//  	}
//  	value=value/i;
        try {
            Thread.sleep(1000) ;
        } catch(Exception e) {
            Log.d("sleep exceptions...\n","") ;
        }
       // return value;
 }
    

	
	
}
