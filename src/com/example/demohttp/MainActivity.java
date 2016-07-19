package com.example.demohttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button btn;
	private TextView txt;
	private String strRe = "";
	private String url ="http://360.hao245.com/";
	private Handler handler;
	private int HTTPGETSENDMES =0x11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt =(TextView)findViewById(R.id.text);
		btn =(Button)findViewById(R.id.btn);
		Log.i("iiiiiiiiii", "======================initUI：");
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread() {
					public void run() {
						Log.i("iiiiiiiiii", "======================run：");
					strRe=HttpUtils.sendHttpGetRequest(url);
					Log.i("iiiiiiiiii", "=====================after=run："+strRe);
					Message msg=new Message();
					msg.obj=strRe;
					msg.what=HTTPGETSENDMES;
					handler.sendMessage(msg);
					};
				}.start();
			}
		});
		
		
		handler =new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0x11:
					//由于是网页端，采取解析html的方式解码显示在textview上面
					txt.setText(Html.fromHtml(""+strRe));
					break;

				default:
					break;
				}
			}
		};
	}
	
}
