package com.immigration;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class Liscense_Google extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_liscense_layout);

		displayGoogleLiscense();
	}

	public void displayGoogleLiscense() {
		String liscense = GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this);
		TextView liscenseView = (TextView) findViewById(R.id.google_liscense);
		if(liscense != null){
			liscenseView.setText(liscense);
		}
		else{
			Toast.makeText(this, "Google play service not availlable", Toast.LENGTH_LONG).show();
		}
	}
}
