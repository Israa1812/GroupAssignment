package com.israa.groupassignment.studentscontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.israa.groupassignment.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText edtRooms;
    private  EditText edtSname;
    private  EditText edtrate;
    private  EditText edtemail;
    private  EditText edtfamilyinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews() {
        edtRooms = findViewById(R.id.edtRooms);
        edtSname = findViewById(R.id.edtSname);
        edtrate = findViewById(R.id.edtrate);
        edtemail = findViewById(R.id.edtemail);
        edtfamilyinfo = findViewById(R.id.edtfamilyinfo);
    }
    private String processRequest (String restUrl)throws UnsupportedEncodingException{
        String Rooms = edtRooms.getText().toString();
        String Sname = edtSname.getText().toString();
        String rate = edtrate.getText().toString();
        String email = edtemail.getText().toString();
        String familyinfo = edtfamilyinfo.getText().toString();

        String data = URLEncoder.encode("Rooms" , "UTF-8")
                + "=" + URLEncoder.encode(Rooms ,"UTF-8");

        data+= "&" + URLEncoder.encode("Sname" ,"UTF-8")
                + "=" +URLEncoder.encode(Sname,"UTF-8");

        data += "&" + URLEncoder.encode("rate" ,"UTF-8")
                + "=" +URLEncoder.encode(rate,"UTF-8");

        data += "&" + URLEncoder.encode("email" ,"UTF-8")
                + "=" +URLEncoder.encode(email,"UTF-8");

        data += "&" + URLEncoder.encode("familyinfo" ,"UTF-8")
                + "=" +URLEncoder.encode(familyinfo,"UTF-8");

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

            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void btnAddOnClick(View view) {
        String restUrl = "http://192.168.0.112:80/rest/addstudents.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }
    }

    public void btnUpdataOnClick(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);

    }

    public void btnSearchOnClick(View view) {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);

    }


}