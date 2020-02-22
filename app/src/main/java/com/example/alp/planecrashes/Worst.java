package com.example.alp.planecrashes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Worst extends AppCompatActivity {
    private ArrayList<Person> persons;
    private ListView listView;
    private CustomListViewAdapter listViewAdapter;
    String Datas;
    private ProgressDialog progressDialog;
    private ArrayList<String> imagesURLs = new ArrayList<>();
    private String firstImgURL = "";
    TextView titleTextview;
    ImageView Photos;

    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        titleTextview= (TextView) findViewById(R.id.title6);
        titleTextview.setText("WORST 100 CRASHES");
        Photos =(ImageView)findViewById(R.id.person_image);
        final InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id_worst));
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
        mInterstitialAd.loadAd(adRequestInter);


        new FetchDescription().execute();



    }
    private void initialize() {

        persons = new ArrayList<Person>();
        listView = (ListView) findViewById(R.id.person_list_view);
        listViewAdapter = new CustomListViewAdapter(Worst.this,persons);
        listView.setAdapter(listViewAdapter);

    }

    private void fillArrayList(ArrayList<Person>persons){
        String currentString = Datas;


        String[] separated = currentString.split(":DENEME:");
        String linkler[]={"http://www.planecrashinfo.com/images/n2001%2009%2011-2.jpg",
                "http://www.planecrashinfo.com/images/n1977%2003%2027-1.jpg",
                "https://pbs.twimg.com/media/Cpppl60WYAAjkz0.jpg",
                "http://www.planecrashinfo.com/images/n1996%2011%2012-2.jpg",
                "http://www.planecrashinfo.com/images/n1974%2003%2003-1.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1980%2008%2019.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1979%2005%2025-1.jpg",
                "http://www.planecrashinfo.com/images/n1988%2012%2021.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2001%2011%2012-1.jpg",
                "http://www.planecrashinfo.com/images/n1994%2004%2026.jpg",
                "http://www.planecrashinfo.com/images/n1991%2007%2011.jpg",
                "http://www.planecrashinfo.com/images/n2918-04-11.jpg",
                "http://www.planecrashinfo.com/images/n1979%2011%2028-2.jpg",
                "http://www.planecrashinfo.com/images/n1985%2012%2012.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1997%2009%2026.jpg",
                "http://www.planecrashinfo.com/images/n1996%2007%2017.jpg",
                "http://www.planecrashinfo.com/images/n1998%2009%2002.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1997%2008%2006-1.jpg",
                "http://www.planecrashinfo.com/images/n1998%2001%2008.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2015%2010%2031.jpg",
                "http://www.planecrashinfo.com/images/n1991%2005%2026.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1998%2002%2016.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2007%2007%2017.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2001%2009%2011-3.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1987%2005%2009.jpg",
                "http://www.planecrashinfo.com/images/n1978%2011%2015.jpg",
                "http://www.planecrashinfo.com/images/n1983%2011%2027.jpg",
                "http://www.planecrashinfo.com/images/n1981%2012%2001.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1979%2008%2011.jpg",
                "http://www.planecrashinfo.com/images/n1989%2006%2007.jpg",
                "http://www.planecrashinfo.com/images/n1976%2009%2010.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1989%2009%2019.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2006%2008%2022.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2009%2007%2015.jpg",
                "http://www.planecrashinfo.com/images/n1992%2009%2028.jpg",
                "http://www.planecrashinfo.com/images/n1986%2003%2031.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2005%2008%2016.jpg",
                "http://www.planecrashinfo.com/images/n1995%2012%2020.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2010%2005%2020.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1987%2008%2016-2.jpg",
                "http://www.planecrashinfo.com/images/n1972%2008%2014.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1972%2012%2003.jpg",
                "http://www.planecrashinfo.com/images/n1975%2004%2004.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n1982%2007%2009.jpg",
                "http://www.planecrashinfo.com/images/n2008%2008%2020.jpg",
                "http://www.planecrashinfo.com/images/n2009%2006%2030.jpg",
                "http://www.planecrashinfo.com/images/n2010%2007%2028.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2002%2005%2004.jpg",
                "http://www.planecrashinfo.com/images/n1985%2002%2019.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2001%2007%2004.jpg",
                "http://www.planecrashinfo.com/images/n1989%2002%2008.jpg",
                "http://www.planecrashinfo.com/images/n1978%2009%2025-1.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2005%2009%2005.jpg",
                "http://www.planecrashinfo.com/images/n1996%2008%2029.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg",
                "http://www.planecrashinfo.com/images/n2003%2012%2025.jpg",
                "http://www.planecrashinfo.com/images/n1982%2006%2008.jpg",
                "http://www.planecrashinfo.com/images/n1985%2008%2002.jpg",
                "http://www.planecrashinfo.com/images/n1960%2012%2016-2.jpg",
                "http://www.planecrashinfo.com/images/n1966%2002%2004.jpg",
                "https://www.eecs.utk.edu/wp-content/uploads/2016/02/Rosenberg_EECS.jpg"

        };





        for (int i=1; i<separated.length;i++) {


            Person person = new Person(""+(i) + "", separated[i],linkler[i-1]);
            persons.add(person);

        }
    }


    public class FetchDescription extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Worst.this,R.style.MyProgressDialogTheme);
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
                String dizi[] = {"","Worst Crash Number: ","Fatal: ","Date: "," Location: "," Carrier: ","Type: "};

                Document doc = Jsoup.connect("http://www.planecrashinfo.com/worst100.htm").get();
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

            progressDialog = new ProgressDialog(Worst.this,R.style.MyProgressDialogTheme);
            progressDialog.setMessage("Loading . .");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String yemekURL = "http://www.planecrashinfo.com/worst100.htm";

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
