package com.lynx.testtask65apps.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lynx.testtask65apps.other.Constants.Database;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(final Context context, final String name, final SQLiteDatabase.CursorFactory factory, final int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(Database.WorkersTable.Queries.TABLE_CREATE);
        db.execSQL(Database.SpecialityTable.Queries.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL(Database.WorkersTable.Queries.TABLE_DROP);
        db.execSQL(Database.SpecialityTable.Queries.TABLE_DROP);
        onCreate(db);
    }
}
