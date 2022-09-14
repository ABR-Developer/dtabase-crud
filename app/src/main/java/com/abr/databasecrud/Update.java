package com.abr.databasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    Button buttonUpdate,buttonViewAll;
    EditText updateName, RollNumber;
    TextView updateText;
    SwitchCompat switchIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toast.makeText(Update.this, "Update Records", Toast.LENGTH_SHORT).show();

        updateName = findViewById(R.id.UpdateName);
        RollNumber = findViewById(R.id.UpdateRollNumber);
        switchIsActive = findViewById(R.id.UpdateSwitchStudent);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        updateText = findViewById(R.id.UpdateMessage);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(RollNumber.getText().toString().matches(""))
                {
                    Toast.makeText(Update.this, "Please enter a roll number first", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHelper dbHelper  = new DBHelper(Update.this);
                    if(dbHelper.isStudentExist(Integer.parseInt(RollNumber.getText().toString()))){
                        dbHelper.updateStudent(updateName.getText().toString() , Integer.parseInt((RollNumber.getText().toString())),switchIsActive.isChecked());
                        updateText.setText("Record Updated Successfully");
                    }
                    else{
                        updateText.setText("No Record find with roll number "+ RollNumber.getText().toString());
                    }
                }
            }
        });

        buttonViewAll = findViewById(R.id.UpdateViewAllRecords);
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}