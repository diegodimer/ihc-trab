package com.example.ppgc.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.ppgc.database.dbmodel.User;

public class UserRepository {

    private DatabaseHelper db;

    public UserRepository(){

    }

    public UserRepository(DatabaseHelper dbHelper) {
        this.db = dbHelper;
    }

    @SuppressLint("Range")
    public User getUser(String login, String password) {
        SQLiteDatabase readableDb = this.db.getReadableDatabase();
        String query = "SELECT * FROM User WHERE Login = " + login + " AND Password = " + password;
        User user;

        Cursor cursor = readableDb.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
            user = new User(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(User.COLUMN_PHONE)),
                    cursor.getInt(cursor.getColumnIndex(User.COLUMN_ADVISOR)));

        }
        else {
            user = new User();
        }

        return user;
    }
}
