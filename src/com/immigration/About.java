package com.immigration;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class About extends MainActivity {
	private static final String LOGTAG = "about text";
	TextView aboutPageView;
	Button mAgree, mDecline;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		aboutPageView = (TextView) findViewById(R.id.aboutpage);
		
		mAgree = (Button) findViewById(R.id.agree);
		mDecline = (Button) findViewById(R.id.decline);
		
		aboutPageView.setText(readFromFile());
		
		mAgree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mainActivity = new Intent(About.this,MainActivity.class);
				startActivity(mainActivity);
				
			}
		});
		
		mDecline.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				moveTaskToBack(true);
				
			}
		});
		
		
	}

	private String readFromFile() {
		InputStream input = getResources().openRawResource(R.raw.linux);
		System.out.println(input);
		
		ByteArrayOutputStream mByte = new ByteArrayOutputStream();
		
		int i ; 
		try {
			i = input.read();
			while(i != -1){
				mByte.write(i);
				i = input.read();
			}
			input.close();
		} catch (Exception e) {
			Log.i(LOGTAG, "Could not read the text file");
		}
		return mByte.toString();

	}

}
