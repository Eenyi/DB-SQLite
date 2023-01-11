package com.example.dbsqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Dumy.db";
    private static final String TABLE_NAME = "Students";

    private static final String SID = "sid";
    private static final String NAME = "name";
    private static final String ROLLNO = "roll_no";
    private static final String DEGREE = "degree";


    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                " SID Integer PRIMARY KEY AUTOINCREMENT," +
                " NAME Text," +
                " ROLLNO Text," +
                " DEGREE Text" +
                ");";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addStudent(Student std) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("NAME", std.getName());
            cv.put("ROLLNO", std.getRollNumber());
            cv.put("DEGREE", std.getDegree());
            db.insert(TABLE_NAME, null, cv);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Student> viewAllStudents() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery("Select * from "+ TABLE_NAME,null);
            ArrayList<Student> studentArrayList = new ArrayList<>();
            if (cr.moveToFirst()) {
                do {
                    studentArrayList.add(new Student(cr.getString(1), cr.getString(2), cr.getString(3)));
                } while (cr.moveToNext());
            }
            return studentArrayList;
        }
        catch (Exception e) {
            return null;
        }
    }


    public boolean updateStudent(Student std) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("NAME", std.getName());
            cv.put("ROLLNO", std.getRollNumber());
            cv.put("DEGREE", std.getDegree());
            String query = "update table students set Name ="+std.getName();
            db.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean deleteStudent(String roll_no) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, ROLLNO + " = ?", new String[] {roll_no});
            db.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
