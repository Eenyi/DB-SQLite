package com.example.dbsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateStudent extends AppCompatActivity implements View.OnClickListener {
    EditText name, roll_no, degree;
    DBhelper db;
    TextView status;
    Button updateSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        name = findViewById(R.id.updateName);
        roll_no = findViewById(R.id.updateRoll_no);
        degree = findViewById(R.id.updateDegree);
        updateSubmit = findViewById(R.id.updateSubmit);
        updateSubmit.setOnClickListener(this);
        status = findViewById(R.id.updateStatus);
        db = new DBhelper(UpdateStudent.this);
    }

    @Override
    public void onClick(View v) {
        Student tempStd = new Student(name.getText().toString(),
                roll_no.getText().toString(),
                degree.getText().toString());
        switch (v.getId()) {
            case R.id.updateSubmit:
                if (db.updateStudent(tempStd)) {
                    status.setText("Student Updated!");
                }
                else {
                    status.setText("Error Occurred!");
                }
                break;
        }
    }
}