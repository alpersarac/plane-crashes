package com.example.alp.planecrashes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> persons;
    private ListView listView;
    private CustomListViewAdapter listViewAdapter;
    String Datas="";
    private ProgressDialog progressDialog;
    private ArrayList<String> imagesURLs = new ArrayList<>();
    private String firstImgURL = "",unwanteddata="";
    TextView titleTextview;

    String title="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        titleTextview= (TextView) findViewById(R.id.title);

        titleTextview.setText("MORE RECENT ACCIDENTS");

        new FetchImageLogo().execute();
        new FetchDescription().execute();


    }
        private void initialize() {

            persons = new ArrayList<Person>();
            listView = (ListView) findViewById(R.id.person_list_view);
            listViewAdapter = new CustomListViewAdapter(MainActivity.this,persons);
            listView.setAdapter(listViewAdapter);

        }

        private void fillArrayList(ArrayList<Person>persons){
            String currentString = Datas;
            String[] separated = currentString.split("Date");
                for (int i=1; i<separated.length;i++) {
                    if (i==separated.length){

                        unwanteddata=separated[separated.length];
                        String[] separated2 = unwanteddata.split("Back to Home Page");
                        separated[separated.length]=separated2[0];

                    }

                    Person person = new Person("Crashes More"+(" "+i) + "", "Date" + separated[i],imagesURLs.get(i));
                    persons.add(person);

                }

        }


        public class FetchDescription extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {

                try{
                    final StringBuilder builder = new StringBuilder();

                    Document doc = Jsoup.connect("http://www.planecrashinfo.com/recent.htm").get();
                    title = doc.title();
                    Elements links = doc.select("tr");
                    // builder.append(title).append("\n");
                    for (Element link : links) {

                            builder.append("\n").append("").append(link.text());
                            Datas= builder.toString();





                    }

                }catch (Exception e){

                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                initialize();
                fillArrayList(persons);


            }




        }
        private class FetchImageLogo extends AsyncTask<Void, Void, Void> {
        //Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this,R.style.MyProgressDialogTheme);
            progressDialog.setMessage("Loading . .");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String yemekURL = "http://www.planecrashinfo.com/recent.htm";

            try {
                imagesURLs = getAllImages(yemekURL);

                // Can not get images URLs
                if (imagesURLs == null) return null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Display the first image...
            // you have to put your code in runOnUiThread() to reach UI components
            final StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < imagesURLs.size(); i++) {
                // ToDo: you need to create [RecyclerView] to display all images
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(MainActivity.this)
                            .load(imagesURLs.get(i))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(imageView);
                }
            });*/

                stringBuilder.append("img").append(String.valueOf(i + 1)).append(": ")
                        .append(imagesURLs.get(i)).append("\n");
            }

            // See -> Logcat
            Log.d("Debug_Images", stringBuilder.toString());

            return null;
        }

        ArrayList<String> getAllImages(String url) {
            Document doc;

            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                // ToDo "Can not connect the URL, Make sure that the URL starts with [http://...]"
                return null;
            }

            // Get the first image URL
            Element link = doc.select("img").first();
            firstImgURL = link.attr("abs:src");

            // Get all images URLs
            Elements media = doc.select("[src]");
            for (Element img : media) {
                if (img.tagName().equals("img")) {
                    // Add images url to the list
                    imagesURLs.add(img.attr("abs:src"));
                }
            }

            return imagesURLs;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        /*logo_layout = (LinearLayout) findViewById(R.id.logo_layout);
        ImageView img_logo = (ImageView) findViewById(R.id.img_logo);
        logo_layout.setVisibility(View.VISIBLE);
        img_logo.setImageBitmap(bitmap);*/
            progressDialog.dismiss();
        }
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
    void CheckConnection(){
        if (isInternetAvailable()==false){


        }else{


            Toast.makeText(getApplicationContext(), "CONNECTION PROBLEM PLEASE       CHECK YOUR CONNECTION" ,Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, HomePage.class));

        }
    }
}

