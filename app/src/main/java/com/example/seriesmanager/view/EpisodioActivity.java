package com.example.seriesmanager.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.seriesmanager.R;

public class EpisodioActivity extends AppCompatActivity {
    public static  final String EXTRA_SERIE = "EXTRA_SERIE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodio);
    }
}