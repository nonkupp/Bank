package com.example.testfinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BANK = "Bank";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";
    public static final String COL_AMOUNT="amount";

    private static final String SQL_CREATE_BANK =
            "CREATE TABLE " + TABLE_BANK + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            +COL_PHONE + " TEXT, "
            + COL_AMOUNT + " TEXT )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BANK);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "ผMR. Steave");
        cv.put(COL_PHONE, "123-456-7");
        cv.put(COL_AMOUNT, 100000);
        db.insert(TABLE_BANK,null,cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
