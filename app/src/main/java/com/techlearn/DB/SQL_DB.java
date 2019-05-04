package com.techlearn.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.techlearn.Model.Password;

import java.util.ArrayList;
import java.util.List;


public class SQL_DB extends SQLiteOpenHelper {

    private static final String DB_NAME = "password.db";
    private static final int DB_VERSION = 1;

    // create table
    private static final String CREATE_TABLE_ = "CREATE TABLE passwords (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "_password TEXT," +
            "_title TEXT)";

    public SQL_DB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS password");
    }

    public long insertInto(Password password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initValues = new ContentValues();
        initValues.put("_password",password.getPassword());
        initValues.put("_title",password.getTitle());

        return db.insert("passwords",null,initValues);
    }

    public List<Password > savedPassword () {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Password> list = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from " + "passwords", null);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            int id = cr.getInt(cr.getColumnIndex("_id"));
            String password = cr.getString(cr.getColumnIndex("_password"));
            String title = cr.getString(cr.getColumnIndex("_title"));

            Password password1 = new Password(id,title,password);
            list.add(password1);

            cr.moveToNext();

        }
        if (!cr.isClosed()) {
            cr.close();
        }
        return list;
    }

    public boolean deleteTitle(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("passwords", "_id" + "=" + id, null) > 0;
    }
}
