package com.example.hello.myapplication.greet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();

    database.beginTransaction();
    try {
      String tabId = String.valueOf(ThreadLocalRandom.current().nextLong());
      for (int i = 0; i < 50; i++) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(DatabaseContract.JSON, String.valueOf(ThreadLocalRandom.current().nextLong()));
        String id = String.valueOf(ThreadLocalRandom.current().nextLong());
        insertValues.put(DatabaseContract.WelcomeEntry.ID_2, id);
        insertValues.put(DatabaseContract.WelcomeEntry.ID_1, tabId);
        insertValues.put(DatabaseContract.WelcomeEntry._ID, id);
        database.insertWithOnConflict(DatabaseContract.WelcomeEntry.TABLE_NAME, null,
                                      insertValues, SQLiteDatabase.CONFLICT_IGNORE);
      }
      database.setTransactionSuccessful();
    } finally {
      database.endTransaction();
    }

    database.delete(DatabaseContract.WelcomeEntry.TABLE_NAME, null, null);
  }
}
