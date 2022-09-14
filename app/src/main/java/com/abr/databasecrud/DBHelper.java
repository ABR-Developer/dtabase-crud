package com.abr.databasecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBHelper extends SQLiteOpenHelper {
    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "StudentName";
    public static final String STUDENT_ROLL = "StudentRollNumber";
    public static final String STUDENT_ENROLL = "IsEnrolled";
    public static final String STUDENT_TABLE = "StudentTable";

    public DBHelper(@Nullable Context context)
    {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTableStatementOne = "CREATE TABLE CustTable(CustomerID Integer PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME_FIRST + " Text, CustomerAge Int, ActiveCustomer BOOL) ";
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + "(" +
                STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, "
                + STUDENT_ROLL + " Int, " + STUDENT_ENROLL + " BOOL) ";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    public void  addStudent(StudentModel STUDENTModel){
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, STUDENTModel.getName());
        cv.put(STUDENT_ROLL, STUDENTModel.getRollNumber());
        cv.put(STUDENT_ENROLL, STUDENTModel.isEnroll());
        db.insert(STUDENT_TABLE, null, cv);
        db.close();
        //NullCoumnHack
        //long insert =
        //if (insert == -1) { return false; }
        //else{return true;}
    }

    public void  removeStudent(int RollNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE, STUDENT_ROLL + "=?" ,new String[] {String.valueOf(RollNumber)});
        db.close();
    }

    public void updateStudent(String name , int RollNumber, boolean enroll){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_NAME, name);
        cv.put(STUDENT_ROLL, RollNumber);
        cv.put(STUDENT_ENROLL, enroll);
        try{
            db.update(STUDENT_TABLE, cv, STUDENT_ROLL + "=?", new String[] {String.valueOf(RollNumber)});
        }
        catch (Exception e){
            String error =  Objects.requireNonNull(e.getMessage()).toString();
        }
        db.close();
    }

    public boolean isStudentExist(int RollNumber){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + STUDENT_TABLE + " WHERE " + STUDENT_ROLL + " = ? ", new String[]{String.valueOf(RollNumber)});

        // moving our cursor to first position.
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public ArrayList<StudentModel> readRecords(StudentModel s){
        SQLiteDatabase db = this.getReadableDatabase();
        String querry;
        Cursor cursorCourses;
        if(s.getRollNumber() == 0 && s.getName().matches(""))
        {
            querry = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + STUDENT_ENROLL + " = ? ";
            cursorCourses = db.rawQuery(querry , new String[]{String.valueOf(s.isEnroll())});
        }
        else if(s.getRollNumber() == 0 && !s.getName().matches("")){
            querry = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + STUDENT_NAME + " = ? AND " + STUDENT_ENROLL + " = ? ";
            cursorCourses = db.rawQuery(querry, new String[]{s.getName(), String.valueOf(s.isEnroll())});
        }
        else if(s.getRollNumber() != 0 && s.getName().matches("")){
            querry = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + STUDENT_ROLL + " = ? AND " + STUDENT_ENROLL + " = ? ";
            cursorCourses = db.rawQuery(querry, new String[]{String.valueOf(s.getRollNumber()), String.valueOf(s.isEnroll())});
        }
        else{
            querry = "SELECT * FROM " + STUDENT_TABLE + " WHERE " + STUDENT_NAME + " = ? AND " + STUDENT_ROLL + " = ? AND " + STUDENT_ENROLL + " = ? ";
            cursorCourses = db.rawQuery(querry, new String[]{s.getName(), String.valueOf(s.getRollNumber()), String.valueOf(s.isEnroll())});
        }

        ArrayList<StudentModel> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1));
            } while (cursorCourses.moveToNext());
        }

        cursorCourses.close();
        db.close();
        return studentArrayList;
    }

    public ArrayList<StudentModel> getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);

        ArrayList<StudentModel> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1));
            } while (cursorCourses.moveToNext());
        }

        cursorCourses.close();
        db.close();
        return studentArrayList;
    }
}