package com.abr.databasecrud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class Create extends AppCompatActivity {
    Button create,CreateViewAll;
    EditText addName, addRollNumber;
    TextView createText;
    SwitchCompat switchIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Toast.makeText(Create.this, "Add new Record", Toast.LENGTH_SHORT).show();
        addName = findViewById(R.id.CreateName);
        addRollNumber = findViewById(R.id.CreateRollNumber);
        switchIsActive = findViewById(R.id.CreateSwitchStudent);
        create = findViewById(R.id.buttonCreateRecord);
        createText = findViewById(R.id.CreateMessage);
        CreateViewAll = findViewById(R.id.CreateViewAllRecords);

        create.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(addName.getText().toString(), Integer.parseInt(addRollNumber.getText().toString()), switchIsActive.isChecked());
                    createText.setText("Record Added Successfully");
                }
                catch (Exception e){
                    createText.setText("Something went wrong.");
                }
                DBHelper dbHelper  = new DBHelper(Create.this);
                dbHelper.addStudent(studentModel);
            }
        });

        CreateViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}