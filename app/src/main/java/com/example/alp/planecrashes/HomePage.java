package com.example.alp.planecrashes;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;


public class HomePage extends AppCompatActivity {
   public ImageView recently_crashes,more_crashes,stat,worst,history,unusual,lastwords2,map,cockpit,soon;
    private TimePickerDialog timePickerDialog;
    final static int REQUEST_CODE = 1;
    String WebHtml;
    private static final String FILE_NAME = "websiteinformation.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);






        final InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id_home_page));
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
        mInterstitialAd.loadAd(adRequestInter);

            soon=(ImageView)findViewById(R.id.soon);
            soon.setBackgroundResource(R.drawable.ic_coming);

            cockpit=(ImageView)findViewById(R.id.cockpit);
            cockpit.setBackgroundResource(R.drawable.ic_plane);

            recently_crashes = (ImageView) findViewById(R.id.recenty_crashes);
            recently_crashes.setBackgroundResource(R.drawable.ic_recent_logo);

            more_crashes = (ImageView) findViewById(R.id.morecrashes);
            more_crashes.setBackgroundResource(R.drawable.ic_more);

            stat = (ImageView) findViewById(R.id.stat);
            stat.setBackgroundResource(R.drawable.ic_statistic);

            history = (ImageView) findViewById(R.id.history);
            history.setBackgroundResource(R.drawable.ic_history);

            worst = (ImageView) findViewById(R.id.worst);
            worst.setBackgroundResource(R.drawable.ic_worst);

            unusual = (ImageView) findViewById(R.id.unusual);
            unusual.setBackgroundResource(R.drawable.ic_weird);

            lastwords2 = (ImageView) findViewById(R.id.lastwords);
            lastwords2.setBackgroundResource(R.drawable.ic_lastword);

            map = (ImageView) findViewById(R.id.map);
            map.setBackgroundResource(R.drawable.ic_map);



            cockpit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (CheckInternetConnection()) {
                    startActivity(new Intent(HomePage.this, Rates.class));
                } else {
                    startActivity(new Intent(HomePage.this, HomePage.class));
                }


                 }
             });
            recently_crashes.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, Recents.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });
            map.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, MapClass.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });
            lastwords2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, Lastwords.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });
            unusual.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, Unusual.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });
            history.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, History.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });
            worst.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, Worst.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });
            stat.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, Stat.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });

            more_crashes.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {

                    if (CheckInternetConnection()) {
                        startActivity(new Intent(HomePage.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(HomePage.this, HomePage.class));
                    }


                }
            });

    }

    public boolean CheckInternetConnection(){
        ConnectivityManager manager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if (networkInfo!= null&& networkInfo.isConnected()){
            return true;
        }else{

            LayoutInflater li = getLayoutInflater();
            View layout = li.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.custom_toast_layout_id));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);//setting the view of custom toast layout
            toast.show();



            return false;
        }
    }

}
