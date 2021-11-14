package com.example.seriesmanager.view.episodio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;

public class EpisodioActivity extends AppCompatActivity {
    public static  final String EXTRA_SERIE = "EXTRA_SERIE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodio);
    }
}