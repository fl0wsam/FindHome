package com.example.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHELPER extends SQLiteOpenHelper {

    private Context context;

    private static final String TAG = "DBHELPER";

    public DBHELPER (Context context){
        super(context, Config.DATABASE_NAME,null,Config.DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_HOUSE = "CREATE TABLE " + Config.HOUSE_TABLE_NAME +
                " (" + Config.COLUMN_HOUSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_KIJIJI_ID + " TEXT NOT NULL, "
                + Config.COLUMN_HOUSE_ADDRESS + "TEXT NOT NULL, "
                + Config.COLUMN_PHONE_NUMBER + " TEXT NOT NULL, "
                + Config.COLUMN_IMG_URL + " Text NOT NULL, "
                + Config.COLUMN_ZIP_CODE + "TEXT NOT NULL)";

        Log.d(TAG, CREATE_TABLE_HOUSE);

        db.execSQL(CREATE_TABLE_HOUSE);

        Log.d(TAG, "House db created.");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
