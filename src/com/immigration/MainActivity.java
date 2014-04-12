package com.immigration;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity{
	
	private static final int GPS_ERRODIALOG_REQUEST = 9001;
	private static final double IVANHOE_LAT = -37.759127;
	private static final double IVANHOE_LONG = 145.0344860;
	private static final float DEFAULT_ZOOM = 50;
	
	GoogleMap mMap;
	Button facebookButton;
	VideoView myVideo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(serviceOk()){
			setContentView(R.layout.activity_main);
			//playVideo();
			if(initMap()){
				mMap.setMyLocationEnabled(true);
				gotoLocation(IVANHOE_LAT, IVANHOE_LONG, DEFAULT_ZOOM);
			}
		}
	
		facebookButton = (Button) findViewById(R.id.facebook_button);
		facebookButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				shareWithFacebook(v);
				
			}
		});
		
	}
	
	private void playVideo() {
		myVideo = (VideoView) findViewById(R.id.myVideo);
		String path = "android.resources://comp.api/" + R.raw.barahi_temple;
		myVideo.setVideoURI(Uri.parse(path));
		MediaController newMediaController = new MediaController(this);
		newMediaController.setAnchorView(myVideo);
		myVideo.setMediaController(newMediaController);
		myVideo.start();
		myVideo.requestFocus();
		
	}

	private boolean serviceOk() {
		int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(isAvailable == ConnectionResult.SUCCESS){
			return true;
		}
		else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRODIALOG_REQUEST);
				dialog.show();
			}
		else{
			Toast.makeText(this, "Can't connect to google playservices", Toast.LENGTH_LONG).show();
		}
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
//		
//		SearchManager searchMgr = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//		SupportMenuItem searchMenuItem = (SupportMenuItem) menu.findItem(R.id.search);
//		SearchView searchView = (SearchView) searchMenuItem.getActionView();
//		searchView.setSearchableInfo(searchMgr.getSearchableInfo(getComponentName()));
//		
//		searchMenuItem.setSupportOnActionExpandListener(new MenuItemCompat.OnActionExpandListener() {
//			
//			@Override
//			public boolean onMenuItemActionExpand(MenuItem arg0) {
//			
//				return false;
//			}
//			
//			@Override
//			public boolean onMenuItemActionCollapse(MenuItem arg0) {
//				return false;
//			}
//		});
//		
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.about:
			Intent about = new Intent(this, About.class);
			startActivity(about);
			return true;

		case R.id.immigration:
			Intent immigration = new Intent(this, Immigration.class);
			startActivity(immigration);
			return true;
		
		case R.id.blog:
			Intent blog = new Intent(this,Blog.class);
			startActivity(blog);
			return true;
			
		case R.id.google_liscense:
			Intent liscense = new Intent(this, Liscense_Google.class);
			startActivity(liscense);
			return true;
			
		case R.id.saved_data:
			Intent savedData = new Intent(this, SavedData.class);
			startActivity(savedData);
			return true;
			
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void shareWithFacebook(View v){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "https://graph.facebook.com/shankar.giri");
		startActivity(Intent.createChooser(intent,"Share Facebook"));
	}
	
	private boolean initMap() {
		if(mMap == null){
			SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			mMap = mapFrag.getMap();
		}
		return (mMap != null);
	}
	

	private void gotoLocation(double ivanhoeLat, double ivanhoeLong,
			float defaultZoom) {
		LatLng longlat = new LatLng(ivanhoeLat, ivanhoeLong);
		CameraUpdate update = CameraUpdateFactory.newLatLng(longlat);
		mMap.moveCamera(update);
		mapMarker(longlat);
				
		
	}

	private void mapMarker(LatLng longlat) {
		MarkerOptions options = new MarkerOptions()
				.title("1 Dalveen Rd, Ivanhone 3073")
				.position(longlat);
		mMap.addMarker(options);
	}
	


}
