package com.israa.groupassignment.teachercontroller;

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

import com.israa.groupassignment.Activity;
import com.israa.groupassignment.R;
import com.israa.groupassignment.studentscontroller.Activity2;
import com.israa.groupassignment.studentscontroller.Activity3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText edtmaterial;
    private  EditText edtTname;
    private  EditText edtsalary;
    private  EditText edtemail;
    private  EditText edteducationalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setUpViews();
    }

    private void setUpViews() {
        edtmaterial = findViewById(R.id.edtmaterial);
        edtTname = findViewById(R.id.edtTname);
        edtsalary = findViewById(R.id.edtsalary);
        edtemail = findViewById(R.id.edtemail);
        edteducationalInfo = findViewById(R.id.edteducationalInfo);
    }
    private String processRequest (String restUrl)throws UnsupportedEncodingException {
        String material = edtmaterial.getText().toString();
        String Tname = edtTname.getText().toString();
        String salary = edtsalary.getText().toString();
        String email = edtemail.getText().toString();
        String educationalInfo = edteducationalInfo.getText().toString();
/*
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
*/
        String data = URLEncoder.encode("material" , "UTF-8")
                + "=" + URLEncoder.encode(material ,"UTF-8");

        data+= "&" + URLEncoder.encode("Tname" ,"UTF-8")
                + "=" +URLEncoder.encode(Tname,"UTF-8");

        data += "&" + URLEncoder.encode("salary" ,"UTF-8")
                + "=" +URLEncoder.encode(salary,"UTF-8");

        data += "&" + URLEncoder.encode("email" ,"UTF-8")
                + "=" +URLEncoder.encode(email,"UTF-8");

        data += "&" + URLEncoder.encode("educationalInfo" ,"UTF-8")
                + "=" +URLEncoder.encode(educationalInfo,"UTF-8");

        String text = "";
        BufferedReader reader=null;
        //Send data
        try{
            //Defined URL where to send data
            URL url = new URL(restUrl);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {

                reader.close();
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        // Show response on activity
        return text;

    }




    private class SendPostRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return processRequest(urls[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(com.israa.groupassignment.teachercontroller.MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void btnAddOnClick(View view) {
        String restUrl = "http://10.0.2.2/rest/addteachers.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            com.israa.groupassignment.teachercontroller.MainActivity.SendPostRequest runner = new com.israa.groupassignment.teachercontroller.MainActivity.SendPostRequest();
            runner.execute(restUrl);
        }
    }

    public void btnUpdataOnClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }

    public void btnSearchOnClick(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);

    }
    public void btnChooseOnClick(View view) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }

}