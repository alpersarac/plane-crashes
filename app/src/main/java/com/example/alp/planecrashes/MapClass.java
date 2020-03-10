package com.example.alp.planecrashes;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MapClass extends AppCompatActivity {
    Button map ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapclass);
        map=(Button) findViewById(R.id.mapButton);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternetConnection()){
                    String uri = "https://www.google.com/maps/d/embed?mid=1qK3gs9eRJ0K2Q3Ogl2kJBlUv5o0";
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(Intent.createChooser(intent, "Select an application"));
                }else {
                    startActivity(new Intent(MapClass.this, MapClass.class));
                }


               /* String strUri = "https://www.google.com/maps/d/u/0/edit?mid=10umGzLYs-3jHMYhV8hd2men0aasn--I3&ll=4.058394522809145%2C0&z=2";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);*/



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
