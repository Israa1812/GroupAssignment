package com.israa.groupassignment.teachercontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.israa.groupassignment.R;
import com.israa.groupassignment.studentscontroller.Activity3;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    String restUrl = "http://127.0.0.1:80/rest/addstudents.php";
    String finalResult ;
    //todo:
    //  HttpParse httpParse = new HttpParse();
    HashMap<String,String> hashMap = new HashMap<>();

    EditText mEdtmaterial, mEdtTname, mEdtsalary, mEdtemail, mEdteducationalInfo;

    String edtmaterial, edtTname, edtsalary, edtemail, edteducationalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mEdtmaterial = (EditText)findViewById(R.id.edtmaterial);
        mEdtTname = (EditText)findViewById(R.id.edtTname);
        mEdtsalary = (EditText)findViewById(R.id.edtsalary);
        mEdtemail = (EditText)findViewById(R.id.edtemail);
        mEdteducationalInfo = (EditText)findViewById(R.id.edteducationalInfo);


        edtmaterial = getIntent().getStringExtra("edtmaterial");
        edtTname = getIntent().getStringExtra("edtTname");
        edtsalary = getIntent().getStringExtra("edtsalary");
        edtemail = getIntent().getStringExtra("edtemail");
        edteducationalInfo = getIntent().getStringExtra("edteducationalInfo");


        mEdtmaterial.setText(edtmaterial);
        mEdtTname.setText(edtTname);
        mEdtsalary.setText(edtsalary);

        mEdtemail.setText(edtemail);
        mEdteducationalInfo.setText(edteducationalInfo);

    }
    public void btnUpdataOnClick(View view) {
        // Getting data from EditText after button click.
        GetDataFromEditText();

        // Sending Student Name, Phone Number, Class to method to update on server.
        StudentRecordUpdate(edtmaterial,edtTname,edtsalary, edtemail, edteducationalInfo);
    }

    public void btnAddOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void btnSearchOnClick(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        edtmaterial = mEdtmaterial.getText().toString();
        edtTname = mEdtTname.getText().toString();
        edtsalary = mEdtsalary.getText().toString();
        edtemail = mEdtemail.getText().toString();
        edteducationalInfo = mEdteducationalInfo.getText().toString();

    }




    // Method to Update Student Record.
    public void StudentRecordUpdate(final String edtmaterial, final String edtTname, final String edtsalary, final String edtemail, final String edteducationalInfo){

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

                hashMap.put("material",params[0]);

                hashMap.put("Tname",params[1]);

                hashMap.put("salary",params[2]);

                hashMap.put("email",params[3]);

                hashMap.put("educationalInfo",params[4]);

                //todo
                //   finalResult = httpParse.postRequest(hashMap, restUrl);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(edtmaterial,edtTname,edtsalary,edtemail, edteducationalInfo);
    }
}