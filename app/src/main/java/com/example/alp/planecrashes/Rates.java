package com.example.alp.planecrashes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Rates extends AppCompatActivity {
    private ArrayList<Person> persons;
    private ListView listView;
    private CustomListViewAdapter listViewAdapter;
    String Datas;
    private ProgressDialog progressDialog;
    private ArrayList<String> imagesURLs = new ArrayList<>();
    private String firstImgURL = "";
    TextView titleTextview;

    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cockpit);
        titleTextview= (TextView) findViewById(R.id.title8);
        titleTextview.setText("Rates by model");



        new FetchDescription().execute();


    }
    private void initialize() {

        persons = new ArrayList<Person>();
        listView = (ListView) findViewById(R.id.person_list_view);
        listViewAdapter = new CustomListViewAdapter(Rates.this,persons);
        listView.setAdapter(listViewAdapter);

    }

    private void fillArrayList(ArrayList<Person>persons){
        String currentString = Datas;
        String unwanteddata="MORE RECENT ACCIDENTS";

        String[] separated = currentString.split(":DENEME:");
        String dataEdit;
        String linkler[]={"https://upload.wikimedia.org/wikipedia/commons/9/90/Airbus_A300B4-622R%28F%29%2C_DHL_%28European_Air_Transport_-_EAT%29_AN2020188.jpg",
                "https://live.staticflickr.com/6197/6059317635_b7b39865fc_b.jpg",
                "http://www.flugzeuginfo.net/acimages/a300b4603_kp.jpg",
                "http://www.flugzeuginfo.net/acimages/airbus_a310304_karstenpalt.jpg",
                "https://omyplane.com/wp-content/uploads/2018/09/dprfhfa2llgmaxresdefault.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/6/61/Thy_a330_tc-jnj_borak.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/b/b7/ATR_42-500_Hop%21_%28HOP%29_F-GPYK_-_MSN_537_%2810276128103%29.jpg",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Boeing_727-2S7_Advanced_Champion_LAX.jpg/1280px-Boeing_727-2S7_Advanced_Champion_LAX.jpg",
                "https://barrieaircraft.com/images/boeing-737-100200-03.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/f/f0/Ethiopian_Airlines_Boeing_737-700_Onyshchenko-1.jpg",
                "http://www.pegipegi.com/travel/wp-content/uploads/2014/09/narrow-body-aircraft.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/40/WS_YYC_737_MAX_1.jpg",
                "https://amp.businessinsider.com/images/5c87ca4c2628985796741673-750-563.jpg",
                "https://live.staticflickr.com/1830/41373371690_7c8dab28ce_b.jpg",
                "http://www.industrytap.com/wp-content/uploads/2015/04/dsc_5510-2-e1428332868917.jpg",
                "https://cdn.satellitetoday.com/wp-content/uploads/2018/08/1280px-USAirways_N938UW_757.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/43/Delta_Air_Lines_B767-332_N130DL.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/0/07/United_Airlines_777_N797UA_LAX.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Northwest_N756NW_DC9.JPG/1200px-Northwest_N756NW_DC9.JPG",
                "http://www.widebodyaircraft.nl/dc10fedx.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/3/36/KLM_McDonnell_Douglas_MD-11_PH-KCK_Ingrid_Bergman.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/6/6d/McDonnell_Douglas_MD-90-30%2C_Japan_Airlines_-_JAL_AN2020984.jpg",
                "https://ih1.redbubble.net/image.441880692.4769/flat,550x550,075,f.u4.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/N466SW_LAX_%2830314755488%29.jpg/1200px-N466SW_LAX_%2830314755488%29.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/6/67/Swissair_Express_Avro_RJ85_at_ZRH_%281%29.jpg",
                "https://static.independent.co.uk/s3fs-public/thumbnails/image/2015/07/25/20/1-concorde-get.jpg",
                "http://airplanes.itsabouttravelling.com/wp-content/uploads/2017/11/n318jb-jetblue-embraer-190ar-01.jpg",
                "http://www.baaa-acro.com/sites/default/files/import/uploads/2004/10/S2-ACH.jpg",
                "http://www.flugzeuginfo.net/acimages/fokker70_kp.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/1/16/Lockheed_L-1011-385-3_TriStar_500%2C_AirLanka_AN0962210.jpg",
                "http://s3-eu-west-1.amazonaws.com/images.privatefly.com/images/aircraft/Saab-340-PrivateFly-AA1563.jpg"};

        for (int i=1; i<separated.length-1;i++) {

            Person person = new Person(""+(i) + "", "" + separated[i],linkler[i-1]);
            persons.add(person);

        }
    }


    public class FetchDescription extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Rates.this,R.style.MyProgressDialogTheme);
            progressDialog.setMessage("Loading . .");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            int i =0;
            try{
                final StringBuilder builder = new StringBuilder();
                String Blank="";
                String Explanation="";
                String dizi[] = {"Model: ","Rate: ","Flights: ","Full Loss Equivalent: ","Events: "};

                Document doc = Jsoup.connect("http://www.airsafe.com/events/models/rate_mod.htm").get();
                title = doc.title();
                Elements links = doc.select("td");

                // builder.append(title).append("\n");
                for (Element link : links) {

                    if (i==dizi.length){
                        i=0;
                        Blank=":DENEME:";


                    }else{
                        Blank="";

                    }
                    builder.append("\n").append(Blank+dizi[i]).append(link.text()+Explanation);
                    Datas= builder.toString();





                    i++;

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
            progressDialog.dismiss();


        }




    }
    private class FetchImageLogo extends AsyncTask<Void, Void, Void> {
        //Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Rates.this,R.style.MyProgressDialogTheme);
            progressDialog.setMessage("Loading . .");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String yemekURL = "http://www.airsafe.com/events/models/rate_mod.htm";

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

}
