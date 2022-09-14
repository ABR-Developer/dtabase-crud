package com.abr.databasecrud;

import androidx.annotation.NonNull;

public class StudentModel {
    private String name;
    private int rollNumber;
    private boolean isEnroll;

    @NonNull
    @Override
    public String toString() {
        return "StudentModel{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", isEnroll=" + isEnroll +
                '}';
    }

    public StudentModel(String name, int rollNumber, boolean isEnroll) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.isEnroll = isEnroll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public boolean isEnroll() {
        return isEnroll;
    }

    public void setEnroll(boolean enroll) {
        isEnroll = enroll;
    }
}
