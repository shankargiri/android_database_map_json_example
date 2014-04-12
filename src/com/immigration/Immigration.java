package com.immigration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Immigration extends ListActivity {
	
	ListView lv;
	
	private static final String LOGTAG = "jsonFiles";
	private final String FIRSTNAME = "firstName";
	private final String LASTNAME = "lastName";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.immigration_layout);
		registerForContextMenu(getListView());
		getJasonFile();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.delete:
			Toast.makeText(this, "You clicked on delete options", Toast.LENGTH_LONG).show();
			return true;
			
		case R.id.edit:
			Toast.makeText(this, "You clicked on edit options", Toast.LENGTH_LONG).show();
			return true;
		case R.id.save:
			Toast.makeText(this, "You clicked on save options", Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	public String getJasonFile(){
		String json = null;
	
	try {
		InputStream inputStream = getResources().openRawResource(R.raw.sample);
		int size = inputStream.available();
		byte[] buffer = new byte[size];
		inputStream.read(buffer);
		inputStream.close();
		json = new String(buffer, "UTF-8");

		jsonUpdate(json);
		//System.out.println(jsonArray);
		
	} catch (IOException e) {
		Log.i(LOGTAG, "Not able to read json file");
	}catch(Exception e){
		Log.i(LOGTAG, "Exception error: ", e);
	}
	return json;

	}

	public void jsonUpdate(String json) {	
		try{
		JSONObject jobjt = new JSONObject(json);
		JSONArray jsonArray = jobjt.getJSONArray("employees");
		
		ArrayList<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		
		for(int i = 0; i<jsonArray.length(); i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			
			String firstName = jsonObj.getString(FIRSTNAME);
			firstName = Html.fromHtml(firstName).toString();
			
			String lastName = jsonObj.getString(LASTNAME);
			lastName = Html.fromHtml(lastName).toString();
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(FIRSTNAME, firstName);
			map.put(LASTNAME, lastName);
			jsonList.add(map);
		}
			String[] keys = {FIRSTNAME, LASTNAME};
			int[] ids = {android.R.id.text1, android.R.id.text2};
			
			SimpleAdapter adapter = new SimpleAdapter(this, jsonList, android.R.layout.simple_list_item_2, keys, ids);
			setListAdapter(adapter);
		}catch (JSONException e) {
			Log.i(LOGTAG, "Jason data could not be binded into simple adapter", e);
		}
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

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	}

