package com.codonomics.demo.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * In the Android dev. world, there is no SQL statements that an app dev writes.
 * Google provides API as an alternative that the app dev needs to use.
 * This class essentially adopts DAO pattern. See that?
 */
public class DBAdapter {
	private static final String TAG = DBAdapter.class.getSimpleName();
	private DBHelper dbHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context context) {
		dbHelper = new DBHelper(context.getApplicationContext());
	}

	public DBAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close()	{
		dbHelper.close();
	}

	public List<String> getAllUserInputs() {
		ArrayList<String> userInputs = new ArrayList<String>();
		Cursor cursor = getAllEntries();
		if(cursor.moveToFirst()) {
			do {
				userInputs.add(cursor.getString(0));
			} while(cursor.moveToNext());
		}
		return userInputs;
	}

	public long insertUserInput(String ip) {
		ContentValues cv = new ContentValues();
		cv.put(DBHelper.APPS_DB_COLUMNS[1], ip);

		//insert(String table, String nullColumnHack, ContentValues values);
		//returns row ID of the newly inserted row, or -1 if an error occurred
		return db.insert(DBHelper.APPS_DB_TABLE, null, cv);
	}

	public boolean exists(String ip) {
		//Avoid rawQuery if possible
		//final String selectQuery = String.format("select %s from %s where %s=?", DBHelper.APPS_DB_COLUMNS[1], DBHelper.APPS_DB_TABLE, DBHelper.APPS_DB_COLUMNS[1]);
		//final Cursor cursor = db.rawQuery(selectQuery, new String[] {ip});
		final String[] requiredColumns = {DBHelper.APPS_DB_COLUMNS[1]}; //Selecting the required columns
		final String selection = DBHelper.APPS_DB_COLUMNS[1] + "=?";
		final String[] selectionArgs = new String[] {ip};
		final Cursor cursor = db.query(DBHelper.APPS_DB_TABLE, requiredColumns, selection, selectionArgs, null, null, null, null);
		return cursor.getCount() > 0;
	}

	public long deleteUserInput(String ip) {
		Log.d(TAG, "About to delete "+ip);
		String whereClause = String.format("%s=?", DBHelper.APPS_DB_COLUMNS[1]);
		String[] whereArgs = {ip};
		return db.delete(DBHelper.APPS_DB_TABLE, whereClause, whereArgs);
	}
	private Cursor getAllEntries() {
		final String[] requiredColumns = {DBHelper.APPS_DB_COLUMNS[1]}; //Selecting the required columns
		//query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
		return db.query(DBHelper.APPS_DB_TABLE, requiredColumns, null, null, null, null, null);
	}
}
