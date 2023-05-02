package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GymTec.db";
    public static final String TABLE_CLIENT = "t_client";
    public static final String TABLE_SERVICE = "t_service";
    public static final String TABLE_LESSON = "t_lesson";
    public static final String TABLE_CLIENT_LESSON = "t_client_lesson";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_CLIENT + "(" +
                        "client_id TEXT PRIMARY KEY NOT NULL," +
                        "address TEXT," +
                        "weight INTEGER," +
                        "imc REAL," +
                        "fname TEXT NOT NULL," +
                        "sname TEXT," +
                        "password TEXT NOT NULL," +
                        "bdate TEXT," +
                        "email TEXT)");
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_SERVICE + "(" +
                        "service_id TEXT PRIMARY KEY NOT NULL," +
                        "service_description TEXT NOT NULL)");
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_LESSON + "(" +
                        "lesson_id TEXT PRIMARY KEY NOT NULL," +
                        "quotas INTEGER NOT NULL," +
                        "search_begin TEXT," +
                        "search_end TEXT," +
                        "start_date TEXT," +
                        "end_date TEXT," +
                        "branch_name TEXT NOT NULL," +
                        "instructor_id TEXT NOT NULL," +
                        "service_id TEXT NOT NULL)");
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_CLIENT_LESSON + "(" +
                        "client_id TEXT NOT NULL," +
                        "lesson_id TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENT);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SERVICE);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LESSON);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENT_LESSON);
        onCreate(sqLiteDatabase);
    }
}
