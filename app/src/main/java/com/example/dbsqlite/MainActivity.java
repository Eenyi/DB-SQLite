package com.example.dbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button add, update, delete, view_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        update = findViewById(R.id.update);
        update.setOnClickListener(this);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        view_all = findViewById(R.id.view_all);
        view_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.add:
                intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
                break;
            case R.id.delete:
                intent = new Intent(MainActivity.this, DeleteSudent.class);
                startActivity(intent);
                break;
            case R.id.update:
                intent = new Intent(MainActivity.this, UpdateStudent.class);
                startActivity(intent);
                break;
            case R.id.view_all:
                intent = new Intent(MainActivity.this, ViewAllStudents.class);
                startActivity(intent);
                break;
        }
    }
}