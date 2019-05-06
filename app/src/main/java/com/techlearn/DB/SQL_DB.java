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

    ///// Table Name and Column //////////
    private static final String TABLE_NAME = "passwords";
    private static final String ID = "_id";
    private static final String PASSWORD = "_password";
    private static final String TITLE = "_title";

    ///// Create Table //////////
    private static final String CREATE_TABLE_ =
            "CREATE TABLE " + TABLE_NAME + " (" +
             ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
             PASSWORD +"TEXT," +
             TITLE +"TEXT)";

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

    ///// Insert Into the data base if true the result > -1 //////////
    public long insertInto(Password password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initValues = new ContentValues();
        initValues.put(PASSWORD,password.getPassword());
        initValues.put(TITLE,password.getTitle());

        return db.insert(TABLE_NAME,null,initValues);
    }

    ///// Get All Saved Passwords //////////
    public List<Password > savedPassword () {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Password> list = new ArrayList<>();
        Cursor cr = db.rawQuery("select * from " + TABLE_NAME, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            int id = cr.getInt(cr.getColumnIndex(ID));
            String password = cr.getString(cr.getColumnIndex(PASSWORD));
            String title = cr.getString(cr.getColumnIndex(TITLE));

            Password password1 = new Password(id,title,password);
            list.add(password1);

            cr.moveToNext();

        }
        if (!cr.isClosed()) {
            cr.close();
        }
        return list;
    }

    ///// Delete Password By ID //////////
    public boolean deletePassword(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=" + id, null) > 0;
    }
}
