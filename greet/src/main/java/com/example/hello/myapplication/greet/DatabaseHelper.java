package com.example.hello.myapplication.greet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 2;
  private static final String DATABASE_NAME = "Cache.db";

  private static final String BLOB_TYPE = " BLOB";
  private static final String VARCHAR35_TYPE = " VARCHAR(35)";
  private static final String DATETIME_TYPE = " DATETIME";
  private static final String DEFAULT = " DEFAULT ";
  private static final String TIMESTAMP_DEFAULT = " (STRFTIME('%Y-%m-%dT%H:%M:%f', 'NOW'))";
  private static final String COMMA_SEP = ",";
  private static final String CREATE_TABLE = "CREATE TABLE ";
  private static final String CREATE_INDEX = "CREATE INDEX ";
  private static final String PRIMARY_KEY = " PRIMARY KEY,";
  private static final String ON = " ON ";
  private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

  private static final String CREATE_WELCOME_TABLE =
      CREATE_TABLE + DatabaseContract.WelcomeEntry.TABLE_NAME + " ("
      + DatabaseContract.WelcomeEntry._ID + VARCHAR35_TYPE + PRIMARY_KEY
      + DatabaseContract.JSON + BLOB_TYPE + COMMA_SEP
      + DatabaseContract.WelcomeEntry.ID_1 + VARCHAR35_TYPE + COMMA_SEP
      + DatabaseContract.WelcomeEntry.ID_2 + VARCHAR35_TYPE + COMMA_SEP
      + DatabaseContract.LAST_MODIFIED + DATETIME_TYPE + DEFAULT
      + TIMESTAMP_DEFAULT + " )";

  private static final String CREATE_WELCOME_MODIFIED_INDEX =
      CREATE_INDEX + DatabaseContract.WelcomeEntry.TABLE_NAME + "_"
      + DatabaseContract.LAST_MODIFIED + ON
      + DatabaseContract.WelcomeEntry.TABLE_NAME + "("
      + DatabaseContract.WelcomeEntry.ID_1 + COMMA_SEP + DatabaseContract.LAST_MODIFIED + ")";

  private static final String DELETE_WELCOME_TABLE
      = DROP_TABLE + DatabaseContract.WelcomeEntry.TABLE_NAME;

  DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_WELCOME_TABLE);
    sqLiteDatabase.execSQL(CREATE_WELCOME_MODIFIED_INDEX);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    sqLiteDatabase.execSQL(DELETE_WELCOME_TABLE);
    onCreate(sqLiteDatabase);
  }

  @Override
  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    onUpgrade(db, oldVersion, newVersion);
  }
}
