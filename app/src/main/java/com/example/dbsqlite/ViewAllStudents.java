package com.example.dbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ViewAllStudents extends AppCompatActivity {
    ListView listView;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);
        listView = findViewById(R.id.studentsList);
        db = new DBhelper(ViewAllStudents.this);
        List<Student> students = db.viewAllStudents();
        Log.d("STudents","asdkasd");
        students.forEach(student ->
            Log.d("Name: ", student.getName()));
        ArrayAdapter arrayAdapter = new ArrayAdapter<Student>(ViewAllStudents.this, android.R.layout.simple_list_item_1,students);
        listView.setAdapter(arrayAdapter);
    }
}