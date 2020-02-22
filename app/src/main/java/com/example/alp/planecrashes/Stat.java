package com.example.alp.planecrashes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Stat extends AppCompatActivity {

    TextView titleTextview;
    ImageView imageview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stat);

        titleTextview= (TextView) findViewById(R.id.title4);
        titleTextview.setText("STATISTICS");





    }
}
