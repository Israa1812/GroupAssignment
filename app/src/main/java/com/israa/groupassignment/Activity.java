package com.israa.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.israa.groupassignment.teachercontroller.MainActivity3;
import com.israa.groupassignment.studentscontroller.Activity3;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
    }

    public void btnstudentsOnClick(View view) {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    public void btnteachersOnClick(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}