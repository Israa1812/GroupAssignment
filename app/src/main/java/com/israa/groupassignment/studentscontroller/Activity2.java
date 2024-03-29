package com.israa.groupassignment.studentscontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.israa.groupassignment.R;

import java.util.HashMap;


public class Activity2 extends AppCompatActivity {

    String finalResult ;
    //todo:
    //  HttpParse httpParse = new HttpParse();
    HashMap<String,String> hashMap = new HashMap<>();

    EditText mEdtRooms, mEdtSname, mEdtrate, mEdtemail, mEdtfamilyinfo;

    String edtRooms, edtSname, edtrate, edtemail, edtfamilyinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mEdtRooms = (EditText)findViewById(R.id.edtRooms);
        mEdtSname = (EditText)findViewById(R.id.edtSname);
        mEdtrate = (EditText)findViewById(R.id.edtrate);
        mEdtemail = (EditText)findViewById(R.id.edtemail);
        mEdtfamilyinfo = (EditText)findViewById(R.id.edtfamilyinfo);


        edtRooms = getIntent().getStringExtra("edtRooms");
        edtSname = getIntent().getStringExtra("edtSname");
        edtrate = getIntent().getStringExtra("edtrate");
        edtemail = getIntent().getStringExtra("edtemail");
        edtfamilyinfo = getIntent().getStringExtra("edtfamilyinfo");



        mEdtRooms.setText(edtRooms);
        mEdtSname.setText(edtSname);
        mEdtrate.setText(edtrate);

        mEdtemail.setText(edtemail);
        mEdtfamilyinfo.setText(edtfamilyinfo);

    }
    public void btnUpdataOnClick(View view) {
        // Getting data from EditText after button click.
        GetDataFromEditText();

        // Sending Student Name, Phone Number, Class to method to update on server.
       // StudentRecordUpdate(edtRooms,edtSname,edtrate, edtemail, edtfamilyinfo);

        String restUrl = "http://10.0.2.2/rest/updateStudents.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            Activity2.StudentRecordUpdateClass runner = new Activity2.StudentRecordUpdateClass();
            runner.execute(restUrl);
        }
        /*
        if(!edtemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(edtemail).matches()){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }*/

    }

    public void btnAddOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void btnSearchOnClick(View view) {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        edtRooms = mEdtRooms.getText().toString();
        edtSname = mEdtSname.getText().toString();
        edtrate = mEdtrate.getText().toString();
        edtemail = mEdtemail.getText().toString();
        edtfamilyinfo = mEdtfamilyinfo.getText().toString();

    }




    // Method to Update Student Record.
   // public void StudentRecordUpdate(final String edtRooms, final String edtSname, final String edtrate, final String edtemail, final String edtfamilyinfo){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("StudentRoom",params[0]);

                hashMap.put("StudentName",params[1]);

                hashMap.put("StudentRate",params[2]);

                hashMap.put("StudentEmail",params[3]);

                hashMap.put("StudentFamilyInfo",params[4]);

                //todo
                //   finalResult = httpParse.postRequest(hashMap, restUrl);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        //studentRecordUpdateClass.execute(edtRooms,edtSname,edtrate,edtemail, edtfamilyinfo);

    }
//}