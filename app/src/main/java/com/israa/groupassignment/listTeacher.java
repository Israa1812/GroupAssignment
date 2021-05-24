package com.israa.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class listTeacher extends AppCompatActivity {
    // ArrayList<Student> studentArrayList = new ArrayList<>();
    ListView listView;
    EditText edtName;
    Button btnSearch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teacher);
        setupViews();
        populateListView("");
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if(!(conn instanceof HttpURLConnection))
            throw new IOException("Not an Http Connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK){
                in = httpConn.getInputStream();
            }
        } catch (Exception ex){
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException(("Error connecting"));
        }
        return in;
    }

    private String DownloadText(String URL){
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try{
            in = OpenHttpConnection(URL);
        } catch(IOException e){
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try{
            while ((charRead = isr.read(inputBuffer))>0){
                //Conv chars to String
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e){
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }
    private class DownloadTextTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls){
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(String result){
            // edtName = findViewById(R.id.edtName);
            //edtName.setText(result);
            populateListView(result);
        }
    }
    private void populateListView(String result) {
        ArrayList<String> arrayList = new ArrayList<String>();


       /*ArrayList<Student> studentArrayList = createStudents(result);
        for (int i=0; i<arrayList.size();i++){
            arrayList.add(studentArrayList.get(i).getName());
            arrayList.add(""+i);
       }*/

        if(!result.trim().equals("")) {
            String[] results = result.split(":");
            for (int i = 0; i < results.length; i++) {
                arrayList.add(results[i]);
            }
        }

        //arrayList.add(result);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, studentArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(listTeacher.this, "Clicked item: " +position +" " +arrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupViews() {
        listView = findViewById(R.id.listView);
        // edtName = findViewById(R.id.edtName);
        btnSearch2 = findViewById(R.id.btnSearch2);
    }
    public void btnSearchOnClick(View view) {
        //edtName = findViewById(R.id.edtName);

        //String url = "http://10.0.2.2/rest/info.php?cat=" +edtName.getText();
        String url = "http://10.0.2.2/rest/getAllteacher.php";
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 123);
        } else{
            listTeacher.DownloadTextTask runner = new listTeacher.DownloadTextTask();
            runner.execute(url);
        }
    }

    public void btnChooseOnClick(View view) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }
}