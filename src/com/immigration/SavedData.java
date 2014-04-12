package com.immigration;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.immigration.data.Data;
import com.immigration.data.SavedDataSource;

public class SavedData extends ListActivity {

	private static final String LOGTAG = "datainserted";
	SavedDataSource savedDatasource;
	List<Data> dataList;
	Button mAllData, mDisplayData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_data_layout);
		savedDatasource = new SavedDataSource(this);
		all();
	}
	
	public void all(){
		mAllData = (Button) findViewById(R.id.show_button);
		mAllData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				savedDatasource.open();
				createData();
				dataList = savedDatasource.findAll();
				refreshDisplay();
				
			}
		});
	}
	
	public void cheap(View v){
		mDisplayData = (Button) findViewById(R.id.display_button);
	}
	@Override
	protected void onResume() {
		super.onResume();
		savedDatasource.open();
	}
	@Override
	protected void onPause() {
		super.onPause();
		savedDatasource.close();
	}
	private void createData(){
		Data data = new Data();
		data.setTitle("Champion of the world");
		data.setDesc("He is the most adorable player of this centuray");
		data = savedDatasource.create(data);
		Log.i(LOGTAG, "Data created" + data.getId());
	}
	public void refreshDisplay(){
		ArrayAdapter<Data> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
		setListAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);	
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
}
