package com.example.dbsqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    private static String DB_NAME = "Dumy.db";
    private static String TABLE_NAME = "Students";

    private static String SID = "sid";
    private static String NAME = "name";
    private static String ROLLNO = "roll_no";
    private static String DEGREE = "degree";


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
            cv.put(NAME, std.getName());
            cv.put(ROLLNO, std.getRollNumber());
            cv.put(DEGREE, std.getDegree());
            db.insert(TABLE_NAME, null, cv);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Student> viewAllStudents() {
        try {
            List<Student> students = new ArrayList<>();
            String sql = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(SID));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME));
                    @SuppressLint("Range") String rollNo = cursor.getString(cursor.getColumnIndex(ROLLNO));
                    @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(DEGREE));
                    students.add(new Student(name, rollNo, degree));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return students;
        }
        catch (Exception e) {
            return null;
        }
    }


    public boolean updateStudent(Student std) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(NAME, std.getName());
            cv.put(ROLLNO, std.getRollNumber());
            cv.put(DEGREE, std.getDegree());
            db.update(TABLE_NAME, cv, ROLLNO + " = ?", new String[] {std.getRollNumber()});
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
