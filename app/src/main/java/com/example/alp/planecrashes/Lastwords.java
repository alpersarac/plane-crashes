package com.example.alp.planecrashes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Lastwords extends AppCompatActivity {
    private ArrayList<Person> persons;
    private ListView listView;
    private CustomListViewAdapter listViewAdapter;
    String Datas="";
    private ProgressDialog progressDialog;
    private ArrayList<String> imagesURLs = new ArrayList<>();
    private String firstImgURL = "";
    TextView titleTextview;
    ImageView Photos;

    String title="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        titleTextview= (TextView) findViewById(R.id.title6);
        titleTextview.setText("LAST WORDS");
        Photos =(ImageView)findViewById(R.id.person_image);



        new FetchDescription().execute();



    }
    private void initialize() {

        persons = new ArrayList<Person>();
        listView = (ListView) findViewById(R.id.person_list_view);
        listViewAdapter = new CustomListViewAdapter(Lastwords.this,persons);
        listView.setAdapter(listViewAdapter);

    }

    private void fillArrayList(ArrayList<Person>persons){
        String currentString = Datas;


        String[] separated = currentString.split(":DENEME:");
        for (int i=1; i<separated.length;i++) {


            Person person = new Person("Last word "+(i) + "", separated[i],"");
            persons.add(person);

        }
    }


    public class FetchDescription extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Lastwords.this,R.style.MyProgressDialogTheme);
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
                String dizi[] = {"resource: ","DATE: ","AIRLINE: ","FLIGHT: ","LAST WORD: - "};

                Document doc = Jsoup.connect("http://www.planecrashinfo.com/lastwords.htm").get();
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

            progressDialog = new ProgressDialog(Lastwords.this,R.style.MyProgressDialogTheme);
            progressDialog.setMessage("Loading . .");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String yemekURL = "http://www.planecrashinfo.com/lastwords.htm";

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
