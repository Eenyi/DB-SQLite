package com.example.dbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddStudent extends AppCompatActivity implements View.OnClickListener{
    EditText name, roll_no, degree;
    DBhelper db;
    TextView status;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name = findViewById(R.id.stdName);
        roll_no = findViewById(R.id.stdRoll_no);
        degree = findViewById(R.id.stdDegree);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        status = findViewById(R.id.status);
        db = new DBhelper(this);
    }

    @Override
    public void onClick(View v) {
        Student tempStd = new Student(name.getText().toString(),
                roll_no.getText().toString(),
                degree.getText().toString());
        switch (v.getId()) {
            case R.id.submit:
                if (db.addStudent(tempStd)) {
                    status.setText("New Student Added!");
                }
                else {
                    status.setText("Error Occurred!");
                }
                break;
        }
    }
}