package me.stupidme.console.account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allen on 18-4-11.
 */
public class UserInfoHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_info.db";
    public static final String TABLE_NAME = "user_info";

    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username VARCHAR(32), " +
            "email VARCHAR(32) NOT NULL UNIQUE, " +
            "password VARCHAR(32) NOT NULL" +
            ")";

    public UserInfoHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
