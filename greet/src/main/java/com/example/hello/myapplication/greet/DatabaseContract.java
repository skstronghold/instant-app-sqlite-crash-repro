package com.example.hello.myapplication.greet;

import android.provider.BaseColumns;

final class DatabaseContract {

  static final String LAST_MODIFIED = "LAST_MODIFIED";
  static final String JSON = "JSON";

  private DatabaseContract() {
  }

  static class WelcomeEntry implements BaseColumns {

    static final String TABLE_NAME = "WELCOME";
    static final String ID_1 = "ID_1";
    static final String ID_2 = "ID_2";
  }
}
