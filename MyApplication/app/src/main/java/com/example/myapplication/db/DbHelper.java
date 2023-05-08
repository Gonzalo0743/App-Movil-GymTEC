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
     * Este metodo se encarga de crear las tablas de la base de datos cuando este sea llamado.
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
     * Este metodo se encarga de mantener el codigo bajo existencia intacto.
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
     * Este metodo se encarga de crear todos los inserts dentro de la t6abla de clientes.
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


    public Boolean LoginCheckPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from t_client where email = ? and password = ?", new String[] {email,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    /**
     * Este metodo se encarga de crear todas las inserciones en la tabla de lecciones
     * @param client_id
     * @param lesson_id
     */
    public void insertClientLesson(String client_id, int lesson_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put("client_id", client_id);
        cV.put("lesson_id", lesson_id);
        long result = db.insert(TABLE_CLIENT_LESSON, null, cV);
        if (result == -1){
            Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "ADDED SUCCESFULLY", Toast.LENGTH_SHORT).show();
        }
    }

    // Métodos Delete de tablas que los ocupan

    /**
     *
     * @param client_id
     */
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

    public Cursor readAllLessons() {
        String query = "SELECT * FROM " + TABLE_LESSON;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
