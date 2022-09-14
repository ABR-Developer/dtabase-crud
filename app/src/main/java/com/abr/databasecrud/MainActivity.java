package com.abr.databasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonCreateActivity,buttonRemoveActivity,buttonUpdateActivity, buttonReadActivity;
    ListView listViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreateActivity = findViewById(R.id.buttonCreateActivity);
        buttonCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Create.class);
                startActivity(intent);
            }
        });

        buttonReadActivity = findViewById(R.id.buttonReadActivity);
        buttonReadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this , Read.class);
                startActivity(intent);
            }
        });

        buttonUpdateActivity = findViewById(R.id.buttonUpdateActivity);
        buttonUpdateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Update.class);
                startActivity(intent);
            }
        });

        buttonRemoveActivity = findViewById(R.id.buttonDeleteActivity);
        buttonRemoveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Delete.class);
                startActivity(intent);
            }
        });

        listViewStudent = findViewById(R.id.ViewAllStudents);
        getAllStudents();
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            StudentModel studentModel;
//
//            @Override
//            public void onClick(View v) {
//                try {
//                    studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
//                    Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e){
//                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//                DBHelper dbHelper  = new DBHelper(MainActivity.this);
//                dbHelper.addStudent(studentModel);
//                getAllStudents();
//            }
//        });
//
//        buttonRemove.setOnClickListener(new View.OnClickListener() {
//            StudentModel studentModel;
//
//            @Override
//            public void onClick(View v) {
//                DBHelper dbHelper  = new DBHelper(MainActivity.this);
//                dbHelper.removeStudent(1);
//            }
//        });
//
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            StudentModel studentModel;
//
//            @Override
//            public void onClick(View v) {
//                DBHelper dbHelper  = new DBHelper(MainActivity.this);
//                dbHelper.updateStudent("studentModel" , 9);
//            }
//        });
//
//        buttonViewAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getAllStudents();
//            }
//        });
    }

    public void getAllStudents(){
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        List<StudentModel> list = dbHelper.getAllStudents();
        ArrayAdapter<StudentModel> arrayAdapter = new ArrayAdapter<StudentModel>
                (MainActivity.this, android.R.layout.simple_list_item_1,list);
        listViewStudent.setAdapter(arrayAdapter);
    }
}