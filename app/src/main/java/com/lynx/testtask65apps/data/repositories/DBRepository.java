package com.lynx.testtask65apps.data.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lynx.testtask65apps.data.db.DBHelper;
import com.lynx.testtask65apps.domain.dataclass.Response;
import com.lynx.testtask65apps.domain.dataclass.Speciality;
import com.lynx.testtask65apps.other.Constants.Database.SpecialityTable;
import com.lynx.testtask65apps.other.Constants.Database.WorkersTable;

import java.util.ArrayList;
import java.util.List;

public class DBRepository {

    private static final String DB_NAME = "workers_db";
    private static final int DB_VERSION = 1;

    private DBHelper dbHelper;

    public DBRepository(Context ctx) {
        this.dbHelper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
    }

    public void saveWorker(Response response) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(WorkersTable.Columns.COLUMN_FIRST_NAME, response.getFName());
        cv.put(WorkersTable.Columns.COLUMN_LAST_NAME, response.getLName());
        cv.put(WorkersTable.Columns.COLUMN_AVATAR_URL, response.getAvatrUrl());
        cv.put(WorkersTable.Columns.COLUMN_BIRTHDAY, response.getBirthday());
        cv.put(WorkersTable.Columns.COLUMN_SPEC_ID, response.getSpecialty().get(0).getId());
//        cv.put(WorkersTable.Columns.COLUMN_SPEC_TITLE, response.getSpecialty().get(0).getName());

        db.insert(WorkersTable.TABLE_NAME, null, cv);

        db.close();
    }

    public List<Speciality> getSpecList() {
        List<Speciality> specialityList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SpecialityTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Speciality speciality = new Speciality();

                speciality.setId(cursor.getString(cursor.getColumnIndex(SpecialityTable.Columns.COLUMN_ID)));
                speciality.setName(cursor.getString(cursor.getColumnIndex(SpecialityTable.Columns.COLUMN_TITLE)));

                specialityList.add(speciality);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return specialityList;
    }

    public void saveSpeciality(Speciality speciality) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SpecialityTable.Columns.COLUMN_ID, speciality.getId());
        cv.put(SpecialityTable.Columns.COLUMN_TITLE, speciality.getName());

        db.insert(SpecialityTable.TABLE_NAME, null, cv);

        db.close();
    }
}
