package com.ibm.contentupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerviewAdapter adapter;
    private TextView movie,series;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        viewPager = findViewById(R.id.viewpager);
        adapter = new PagerviewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        movie = findViewById(R.id.movie);
        series = findViewById(R.id.series);

    }
}
