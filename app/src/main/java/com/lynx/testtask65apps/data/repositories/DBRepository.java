package com.lynx.testtask65apps.data.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lynx.testtask65apps.data.db.DBHelper;
import com.lynx.testtask65apps.domain.dataclass.Speciality;
import com.lynx.testtask65apps.domain.dataclass.Worker;
import com.lynx.testtask65apps.other.Constants.Database.SpecialityTable;
import com.lynx.testtask65apps.other.Constants.Database.WorkersTable;
import com.lynx.testtask65apps.other.utils.CorrectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBRepository {

    private static final String DB_NAME = "workers_db";
    private static final int DB_VERSION = 3;

    private final DBHelper dbHelper;

    public DBRepository(final Context ctx) {
        this.dbHelper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
    }

    public void saveWorker(final Worker worker) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final Gson gson = new Gson();

        final ContentValues cv = new ContentValues();
        final List<String> idList = new ArrayList<>();

        for (Speciality speciality : worker.getSpecialty()) {
            if (!idList.contains(speciality.getId())) idList.add(speciality.getId());
        }

        cv.put(WorkersTable.Columns.COLUMN_FIRST_NAME, CorrectUtils.nameToRequiredLook(worker.getFName()));
        cv.put(WorkersTable.Columns.COLUMN_LAST_NAME, CorrectUtils.nameToRequiredLook(worker.getLName()));
        cv.put(WorkersTable.Columns.COLUMN_AVATAR_URL, worker.getAvatarUrl());
        cv.put(WorkersTable.Columns.COLUMN_BIRTHDAY, CorrectUtils.birthdayToRequiredLook(worker.getBirthday()));
        cv.put(WorkersTable.Columns.COLUMN_SPEC_IDS, gson.toJson(idList));

        db.insert(WorkersTable.TABLE_NAME, null, cv);

        db.close();
    }

    public List<Speciality> getSpecList() {
        final List<Speciality> specialityList = new ArrayList<>();

        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        final Cursor cursor = db.rawQuery("SELECT * FROM " + SpecialityTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                final Speciality speciality = new Speciality();

                speciality.setId(cursor.getString(cursor.getColumnIndex(SpecialityTable.Columns.COLUMN_ID)));
                speciality.setName(cursor.getString(cursor.getColumnIndex(SpecialityTable.Columns.COLUMN_TITLE)));

                specialityList.add(speciality);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return specialityList;
    }

    public void saveSpecialities(final List<Speciality> specialityList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        for (Speciality speciality : specialityList) {
            cv.put(SpecialityTable.Columns.COLUMN_ID, speciality.getId());
            cv.put(SpecialityTable.Columns.COLUMN_TITLE, speciality.getName());
            db.insert(SpecialityTable.TABLE_NAME, null, cv);
        }

        db.close();
    }

    public List<Worker> getWorkersList(final String specId) {
        final List<Worker> workerList = new ArrayList<>();

        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        final Cursor cursor = db.rawQuery("SELECT * FROM " + WorkersTable.TABLE_NAME, null);

        final Gson gson = new Gson();
        final Type type = new TypeToken<List<String>>() {
        }.getType();

        boolean isContains;

        if (cursor.moveToFirst()) {
            do {
                isContains = true;

                final ArrayList<Speciality> specialityList = new ArrayList<>();

                final List<String> idList = gson.fromJson(cursor.getString(cursor.getColumnIndex(WorkersTable.Columns.COLUMN_SPEC_IDS)), type);
                if (!idList.contains(specId)) isContains = false;

                for (String id : idList) {
                    specialityList.add(getSpecialityById(id));
                }

                final Worker worker = new Worker();

                worker.setFName(cursor.getString(cursor.getColumnIndex(WorkersTable.Columns.COLUMN_FIRST_NAME)));
                worker.setLName(cursor.getString(cursor.getColumnIndex(WorkersTable.Columns.COLUMN_LAST_NAME)));
                worker.setAvatrUrl(cursor.getString(cursor.getColumnIndex(WorkersTable.Columns.COLUMN_AVATAR_URL)));
                worker.setBirthday(cursor.getString(cursor.getColumnIndex(WorkersTable.Columns.COLUMN_BIRTHDAY)));

                worker.setSpecialty(specialityList);

                if (isContains) workerList.add(worker);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return workerList;
    }

    private Speciality getSpecialityById(final String id) {
        final Speciality speciality = new Speciality();

        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        final Cursor cursor = db.rawQuery("SELECT * FROM " + SpecialityTable.TABLE_NAME + " WHERE " + SpecialityTable.Columns.COLUMN_ID + " = ?", new String[]{id});

        if (cursor.moveToFirst()) {
            speciality.setId(id);
            speciality.setName(cursor.getString(cursor.getColumnIndex(SpecialityTable.Columns.COLUMN_TITLE)));
        }

        db.close();

        return speciality;
    }

    // На случай появления primary key у работника

//    public void saveWorkerList(List<Worker> responseList) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        Gson gson = new Gson();
//
//        db.beginTransaction();
//
//        /*//delete
//        db.execSQL(String.format("DELETE FROM " + WorkersTable.TABLE_NAME + " WHERE " + WorkersTable.Columns.COLUMN_AVATAR_URL + " NOT IN (%s)", toUrls(responseList)) + ";");
//
//        // update
//        ContentValues cv = new ContentValues();
//        for (int i = 0; i < responseList.size(); i++) {
////            cv.put(WorkersTable.Columns.COLUMN_SPEC_IDS, responseList.get(i).);
//            cv.put(WorkersTable.Columns.COLUMN_BIRTHDAY, responseList.get(i).getBirthday());
//            cv.put(WorkersTable.Columns.COLUMN_FIRST_NAME, responseList.get(i).getFName());
//            cv.put(WorkersTable.Columns.COLUMN_LAST_NAME, responseList.get(i).getLName());
//            cv.put(WorkersTable.Columns.COLUMN_AVATAR_URL, responseList.get(i).getAvatarUrl());
//            db.update(WorkersTable.TABLE_NAME, cv, WorkersTable.Columns.COLUMN_AVATAR_URL + " = ?", new String[]{responseList.get(i).getAvatarUrl()});
//        }*/
//
//        //insert or ignore
//        StringBuilder values = new StringBuilder();
//
//        List<String> idList = new ArrayList<>();
//
//        for (int i = 0; i < responseList.size(); i++) {
//            for (Speciality speciality : responseList.get(i).getSpecialty()) {
//                if (!idList.contains(speciality.getId())) idList.add(speciality.getId());
//            }
//            if (i != 0) values.append(",");
//            values.append("('")
//                    .append(nameToRequiredLook(responseList.get(i).getFName())).append("','")
//                    .append(nameToRequiredLook(responseList.get(i).getLName())).append("','")
//                    .append(responseList.get(i).getAvatarUrl()).append("','")
//                    .append(responseList.get(i).getBirthday()).append("','")
//                    .append(gson.toJson(idList))
//                    .append("')");
//        }
//        db.execSQL("INSERT OR IGNORE INTO " + WorkersTable.TABLE_NAME +
//                "(" + WorkersTable.Columns.COLUMN_FIRST_NAME + ","
//                + WorkersTable.Columns.COLUMN_LAST_NAME + ","
//                + WorkersTable.Columns.COLUMN_AVATAR_URL + ","
//                + WorkersTable.Columns.COLUMN_BIRTHDAY + ","
//                + WorkersTable.Columns.COLUMN_SPEC_IDS
//                + ")" +
//                " VALUES " + values + ";");
//
//        db.setTransactionSuccessful();
//        db.endTransaction();
//
//        db.close();
//    }

    public void deleteTable(final String tableName) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(tableName, null, null);

        db.close();
    }

    public boolean isEmpty() {
        boolean isEmpty = true;

        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        final Cursor cursor = db.rawQuery("SELECT * FROM " + SpecialityTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) isEmpty = false;

        cursor.close();
        db.close();

        return isEmpty;
    }
}
