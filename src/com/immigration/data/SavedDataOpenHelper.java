package com.immigration.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SavedDataOpenHelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "logItems";
	
	private static final String DATABASE_NAME = "myDatabase.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_DATA = "data";
	public static final String COLUMN_ID = "dataId";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DESC = "description";
	
	
//	private static final String TABLE_CREATE = 
//			"CREATE TABLE" + TABLE_DATA + "(" + 
//					COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//					COLUMN_TITLE + "TEXT," +
//					COLUMN_DESC + "TEXT" +
//					
//
//			")";
//	
//	
	private static final String TABLE_CREATE = 
			"CREATE TABLE " + TABLE_DATA + " (" +
					COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COLUMN_TITLE + " TEXT, " +
					COLUMN_DESC + " TEXT " +

					")";
	
	
	
	
	public SavedDataOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_DATA);
		onCreate(db);

	}

}
