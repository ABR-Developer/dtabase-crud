package com.abr.databasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Read extends AppCompatActivity {
    Button buttonRead,buttonViewAll;
    EditText readName, readRollNumber;
    TextView readText;
    SwitchCompat switchIsActive;
    ListView listViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Toast.makeText(Read.this, "Read Records", Toast.LENGTH_SHORT).show();

        readName = findViewById(R.id.ReadName);
        readRollNumber = findViewById(R.id.ReadRollNumber);
        switchIsActive = findViewById(R.id.ReadSwitchStudent);
        readText = findViewById(R.id.ReadMessage);

        buttonRead = findViewById(R.id.buttonRead);
        listViewStudent = findViewById(R.id.ReadListViewStudent);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(readName.getText().toString(), Integer.parseInt(readRollNumber.getText().toString()), switchIsActive.isChecked());
                }
                catch (Exception e){
                    studentModel = new StudentModel(readName.getText().toString(), 0, switchIsActive.isChecked());
                }
                DBHelper dbHelper  = new DBHelper(Read.this);
                List<StudentModel> list = dbHelper.readRecords(studentModel);
                ArrayAdapter<StudentModel> arrayAdapter = new ArrayAdapter<StudentModel>
                        (Read.this, android.R.layout.simple_list_item_1,list);
                listViewStudent.setAdapter(arrayAdapter);
                readText.setText("Record finds successfully");
            }
        });

        buttonViewAll = findViewById(R.id.ReadViewAllRecords);
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Read.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}