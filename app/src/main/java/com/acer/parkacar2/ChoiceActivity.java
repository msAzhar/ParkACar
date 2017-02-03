package com.acer.parkacar2;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ChoiceActivity extends AppCompatActivity implements AsyncResponse {

    private String uygunyer;
    private TextView location_tv;
    private ImageView location_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        location_tv = (TextView) findViewById(R.id.location_tv);
        location_iv = (ImageView) findViewById(R.id.location_iv);

        DownloadFilesTask task = new DownloadFilesTask();
        task.execute("http://10.194.130.60/xampp/ParkACar/request.php");

    }


    @Override
    public void processFinish(String yerno) {

        Toast.makeText(this, yerno, Toast.LENGTH_LONG).show();
        this.uygunyer=yerno;


    }

    private class DownloadFilesTask extends AsyncTask<String, String, Void> {
        protected Void doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection e = (HttpURLConnection)url.openConnection();
                e.setReadTimeout(15000);
                e.setConnectTimeout(15000);
                e.setDoInput(true);
                e.setDoOutput(true);
                int responseCode = e.getResponseCode();
                if(responseCode == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(e.getInputStream()));
                    uygunyer = br.readLine();

                    Log.w("Yer No", uygunyer);
                } else {
                    Log.d("PostResponseAsyncTask", responseCode + "");
                }
            } catch (MalformedURLException var11) {
                Log.d("PostResponseAsyncTask", "MalformedURLException Error: " + var11.toString());
            } catch (ProtocolException var12) {
                Log.d("PostResponseAsyncTask", "ProtocolException Error: " + var12.toString());
            } catch (UnsupportedEncodingException var13) {
                Log.d("PostResponseAsyncTask", "UnsupportedEncodingException Error: " + var13.toString());
            } catch (IOException var14) {
                Log.d("PostResponseAsyncTask", "IOException Error: " + var14.toString());
            }

        return null;
        }

        protected void onProgressUpdate(String... strs) {

        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            location_tv.setText(location_tv.getText() + uygunyer);
            Uri uri = Uri.parse("android.resource://com.acer.parkacar2/drawable/yerno" + uygunyer);
            try {
                location_iv.setImageDrawable(Drawable.createFromStream(getContentResolver().openInputStream(uri), null));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
