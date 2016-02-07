package com.codonomics.demo.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The purpose of this class is to provide easy access to the DB.
 * The DB is created when the app is first installed.
 * When the user upgrades the app, the DB upgrades/downgrades depending on the change in the VERSION number of this class
 */
public class DBHelper extends SQLiteOpenHelper {

	//Denotes the DB for the app where data is saved. You can see this file in the disk.
	//It can even be without any extention or be any of your choice.
	//Remember Android is a Linux based File system and so file extension isn't really important.
	private static final String DB_NAME = "SQLiteStorageOptionExample.db";

	private static final int VERSION = 1; //Denotes if the DB schema has changed with app upgrade/downgrade

	public static final String APPS_DB_TABLE = "USER_INPUTS";
	public static final String[] APPS_DB_COLUMNS = {"_id","ip"};

	public DBHelper(Context context) {
		//SQLiteOpenHelper(Context context, String dbname, CursorFactory factory, int version)
		super(context, DB_NAME, null, VERSION); //null makes use  of default CursorFactory
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//USER_INPUTS is the name of the table having _id as PK and ip as TEXT coln.
		db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT);",
				APPS_DB_TABLE, APPS_DB_COLUMNS[0], APPS_DB_COLUMNS[1]));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS USER_INPUTS;");
		onCreate(db);
	}

}
