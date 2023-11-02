package com.example.th5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "IN4.sqlite";
    private static final int DATABASE_VERSION = 1;
    List<String> create_sql_list = new ArrayList<String>();
    String sql;
    public static final String TABLE_NAME = "IN4";
    public static final String NAME = "name";
    public static final String CMND = "cmnd";
    public static final String BANGCAP = "bangcap";
    public static final String SOTHICH = "sothich";
    public static final String THONGTINBOSUNG = "thongtinbosung";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    NAME + " TEXT, " +
                    CMND + " TEXT, " +
                    BANGCAP + " TEXT, " +
                    SOTHICH + " TEXT, " +
                    THONGTINBOSUNG + " TEXT);";
    SQLiteDatabase db;
    Context context;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades here
    }
}