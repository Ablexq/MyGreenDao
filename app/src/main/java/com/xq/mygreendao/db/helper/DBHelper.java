package com.xq.mygreendao.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper工具类
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test2.db";
    public static int DATABASE_VERSION = 1;

    private static DBHelper mDH = null;

    public static synchronized DBHelper getInstance(Context context) {
        if (mDH == null) {
            mDH = new DBHelper(context);
        }
        return mDH;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTableUtil.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTableUtil.TABLE_NAME);
        onCreate(db);
    }
}
