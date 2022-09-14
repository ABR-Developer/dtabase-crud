package com.abr.databasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends AppCompatActivity {
    Button buttonDelete,buttonViewAll;
    EditText RollNumber;
    TextView deleteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Toast.makeText(Delete.this, "Delete a Record", Toast.LENGTH_SHORT).show();

        RollNumber = findViewById(R.id.DeleteRollNumber);

        buttonDelete = findViewById(R.id.buttonDelete);
        deleteText = findViewById(R.id.DeleteMessage);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(RollNumber.getText().toString().matches(""))
                {
                    Toast.makeText(Delete.this, "Please enter a roll number first", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHelper dbHelper  = new DBHelper(Delete.this);
                    if(dbHelper.isStudentExist(Integer.parseInt(RollNumber.getText().toString()))) {
                        dbHelper.removeStudent(Integer.parseInt(RollNumber.getText().toString()));
                        deleteText.setText("Record Deleted Successfully");
                    }
                    else{
                        deleteText.setText("No Record find with roll number "+ RollNumber.getText().toString());
                    }
                }
            }
        });

        buttonViewAll= findViewById(R.id.DeleteViewAllRecord);
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delete.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}