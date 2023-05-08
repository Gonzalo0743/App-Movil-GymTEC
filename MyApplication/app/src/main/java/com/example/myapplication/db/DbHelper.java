package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GymTec.db";
    public static final String TABLE_CLIENT = "t_client";
    public static final String TABLE_SERVICE = "t_service";
    public static final String TABLE_LESSON = "t_lesson";
    public static final String TABLE_CLIENT_LESSON = "t_client_lesson";
    private Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_CLIENT + "(" +
                        "client_id TEXT PRIMARY KEY NOT NULL," +
                        "address TEXT," +
                        "weight INTEGER," +
                        "imc INTEGER," +
                        "fname TEXT NOT NULL," +
                        "fLname TEXT," +
                        "sLname TEXT," +
                        "password TEXT NOT NULL," +
                        "bdate TEXT," +
                        "email TEXT)");
        db.execSQL(
                "CREATE TABLE " + TABLE_SERVICE + "(" +
                        "service_id TEXT PRIMARY KEY NOT NULL," +
                        "service_description TEXT NOT NULL)");
        db.execSQL(
                "CREATE TABLE " + TABLE_LESSON + "(" +
                        "lesson_id INTEGER PRIMARY KEY NOT NULL," +
                        "quotas INTEGER NOT NULL," +
                        "search_begin TEXT," +
                        "search_end TEXT," +
                        "start_date TEXT," +
                        "end_date TEXT," +
                        "branch_name TEXT NOT NULL," +
                        "instructor_id TEXT NOT NULL," +
                        "service_id TEXT NOT NULL,"+
                        " FOREIGN KEY (service_id) REFERENCES t_service(service_id))");
        db.execSQL(
                "CREATE TABLE " + TABLE_CLIENT_LESSON + "(" +
                        "client_id TEXT NOT NULL," +
                        "lesson_id INTEGER NOT NULL," +
                        " FOREIGN KEY (client_id) REFERENCES t_client(client_id)," +
                        " FOREIGN KEY (lesson_id) REFERENCES t_lesson(lesson_id))"
        );
    }

    /**
     *
     * @param sqLiteDatabase The database.
     * @param i The old database version.
     * @param i1 The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT_LESSON);
        onCreate(sqLiteDatabase);
    }

    //Métodos Insert de tablas que los ocupan

    /**
     *
     * @param client_id
     * @param address
     * @param weight
     * @param imc
     * @param fname
     * @param fLname
     * @param sLname
     * @param bdate
     * @param email
     */
    public void insertClient(String client_id, String address, int weight, int imc, String fname, String fLname, String sLname, String password, String bdate, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();

        cV.put("client_id", client_id);
        cV.put("address", address);
        cV.put("weight", weight);
        cV.put("imc", imc);
        cV.put("fname", fname);
        cV.put("fLname", fLname);
        cV.put("sLname", sLname);
        cV.put("password", password);
        cV.put("bdate", bdate);
        cV.put("email", email);
        long result = db.insert(TABLE_CLIENT, null, cV);
        if (result == -1){
            Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "ADDED SUCCESFULLY", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *
     * @param client_id
     * @param lesson_id
     */
    public void insertClientLesson(String client_id, int lesson_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put("client_id", client_id);
        cV.put("lesson_id", lesson_id);
        db.insert(TABLE_CLIENT_LESSON, null, cV);
        db.close();
    }

    // Métodos Delete de tablas que los ocupan
    public void deleteClient(String client_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENT, "client_id=?", new String[]{String.valueOf(client_id)});
        db.close();
    }

    public void deleteClientLesson(String client_id, int lesson_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENT_LESSON, "client_id=? AND lesson_id=?", new String[]{client_id, String.valueOf(lesson_id)});
        db.close();
    }

    // Métodos Get de tablas que los ocupan
//    public List<String> getService() {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        //String[] columns = {"service_id"};
//
//        Cursor cursor = db.query(TABLE_SERVICE, null, null, null, null, null, null);
//
//        List<String> data = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            String service_id = cursor.getString(cursor.getColumnIndex("service_id"));
//            data.add(service_id);
//        }
//
//        cursor.close();
//
//        return data;
//    }

//    public ArrayList<DataModel> getDataByName(String name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<DataModel> dataList = new ArrayList<>();
//        Cursor cursor = db.query(TABLE_NAME, null, "name=?", new String[]{name}, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndex(ID));
//                String nameValue = cursor.getString(cursor.getColumnIndex(NAME));
//                int age = cursor.getInt(cursor.getColumnIndex(AGE));
//                DataModel data = new DataModel(id, nameValue, age);
//                dataList.add(data);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return dataList;
//    }

//    public ArrayList<String> getDataByColumn(String tableName, String[] columns) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<String> data = new ArrayList<>();
//
//        Cursor cursor = db.query(tableName, columns, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            for (int i = 0; i < columns.length; i++) {
//                String columnData = cursor.getString(cursor.getColumnIndex(columns[i]));
//                data.add(columnData);
//            }
//        }
//        cursor.close();
//        db.close();
//        return data;
//    }

    /**
     * 
     * @param table
     * @return
     */
    public Cursor getAllTable(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);
        return cursor;
    }

}
