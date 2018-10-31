package com.lynx.testtask65apps.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lynx.testtask65apps.other.Constants.Database;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database.WorkersTable.Queries.TABLE_CREATE);
        db.execSQL(Database.SpecialityTable.Queries.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Database.WorkersTable.Queries.TABLE_DROP);
        db.execSQL(Database.SpecialityTable.Queries.TABLE_DROP);
        onCreate(db);
    }
}
