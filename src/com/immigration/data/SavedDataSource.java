package com.immigration.data;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SavedDataSource {
	public static final String LOGTAG = "database";
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase 	database;
	
	private static final String[] allColumns = {
		SavedDataOpenHelper.COLUMN_ID,
		SavedDataOpenHelper.COLUMN_TITLE,
		SavedDataOpenHelper.COLUMN_DESC
	};
	
	public SavedDataSource(Context context) {
		dbhelper = new SavedDataOpenHelper(context);
	}
	public void open(){
		Log.i(LOGTAG, "Database is open");
		database = dbhelper.getWritableDatabase();
	}
	public void close(){
		Log.i(LOGTAG, "Database is closed");
		dbhelper.close();
	}
	
	public Data create(Data data){
		ContentValues content = new ContentValues();
		content.put(SavedDataOpenHelper.COLUMN_TITLE, data.getTitle());
		content.put(SavedDataOpenHelper.COLUMN_DESC, data.getDesc());
		
		long insertId = database.insert(SavedDataOpenHelper.TABLE_DATA, null, content);
		data.setId(insertId);
		return data;
		
	}
	public List<Data> findAll(){
		List<Data> dataList = new ArrayList<Data>();
		Cursor cursor = database.query(SavedDataOpenHelper.TABLE_DATA, allColumns, null, null, null, null, null);
		if(cursor.getCount() >0 ){
			while(cursor.moveToNext()){
				Data data = new Data();
				data.setId(cursor.getLong(cursor.getColumnIndex(SavedDataOpenHelper.COLUMN_ID)));
				data.setTitle(cursor.getString(cursor.getColumnIndex(SavedDataOpenHelper.COLUMN_TITLE)));
				data.setDesc(cursor.getString(cursor.getColumnIndex(SavedDataOpenHelper.COLUMN_DESC)));
				dataList.add(data);
				
			}
		}
		return dataList;
	}
}
