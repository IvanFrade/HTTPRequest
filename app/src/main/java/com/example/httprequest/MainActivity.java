package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements iListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_click(View v) {
        Toast.makeText(this, "Hai premuto un bottone", Toast.LENGTH_LONG).show();

        Downloader downloader = new Downloader();
        downloader.setListener(this);

        (new Thread(downloader)).start();
    }

    @Override
    public void onTaskComplete(String text) {
        ((TextView)findViewById(R.id.txt_response)).setText(text);
    }
}