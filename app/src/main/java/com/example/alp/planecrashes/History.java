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

public class History extends AppCompatActivity {
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
        titleTextview.setText("Accident History to airlines");
        Photos =(ImageView)findViewById(R.id.person_image);



        new FetchDescription().execute();



    }
    private void initialize() {

        persons = new ArrayList<Person>();
        listView = (ListView) findViewById(R.id.person_list_view);
        listViewAdapter = new CustomListViewAdapter(History.this,persons);
        listView.setAdapter(listViewAdapter);

    }

    private void fillArrayList(ArrayList<Person>persons){
        String currentString = Datas;


        String[] separated = currentString.split(":DENEME:");
        String linkler2[]={"https://i.pinimg.com/originals/02/c5/bb/02c5bb0d2f53355a3933db6e1c1f7282.jpg",
                "http://liveandletsfly.boardingarea.com/wp-content/uploads/2017/04/Sukhoi-SuperJet-100.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/e/e5/Aerol%C3%ADneas_Argentinas_Boeing_747-200B_LV-MLR_ZRH_May_1981.png",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/XA-ZIM-2008-09-13-YVR.jpg/1200px-XA-ZIM-2008-09-13-YVR.jpg",
                "https://amp.businessinsider.com/images/59930f5bb0e0b5921a8b4e31-750-563.png",
                "https://www.aircanada.com/content/dam/aircanada/portal/images/galleries/thumbnail/new-livery-1.jpg",
                "https://image.jimcdn.com/app/cms/image/transf/dimension=1070x10000:format=jpg/path/scb3c95bbd722dc02/image/i19c59862beb11b68/version/1454644534/airchina-747-8i.jpg",
                "https://www.aeroinside.com/img/aircrafts/aircraft-boeing-737-800-registration-EC-JBK-8afeb2f7bd_b.jpg",
                "https://skift.com/wp-content/uploads/2019/02/KLM-Boeing-737-e1550608072469.jpg",
                "https://i.ndtvimg.com/i/2017-07/air-india_650x400_41499592069.jpg",
                "https://images.aerlingus.com/resrc-origin/c=ar29x17/s=h237,xof50,yof50/s=w230,pd2.6/o=85/https://www.aerlingus.com/media/images/content/fleet-new-ATR-72-alt.jpg",
                "https://resources.stuff.co.nz/content/dam/images/1/9/z/o/w/k/image.related.StuffLandscapeSixteenByNine.710x400.1bjney.png/1462922737091.jpg",
                "https://www.newsday.co.zw/wp-content/uploads/2015/05/airzimbabwe.jpg",
                "https://smedia2.intoday.in/btmt/images/stories/airasia-660_101617011826.jpg",
                "http://1.bp.blogspot.com/-Wx1FN3vwjoo/T-IRth5qrzI/AAAAAAAAFXU/HSypDMzhnXU/s1600/SNA+June+2+&+3+2012+019.JPG",
                "http://bigislandnow.com/wp-content/uploads/2013/02/Alaska_Airlines_Boeing_737-890_N548AS-e1361478381564.jpg",
                "https://simpleflying.com/wp-content/uploads/2018/11/Screenshot-2018-11-24-at-10.22.47-700x466.png",
                "https://skift.com/wp-content/uploads/2017/05/AllegiantA320MSN7664-e1493850529599.jpg",
                "https://media.phillyvoice.com/media/images/02_020918_Airport_Carroll.2e16d0ba.fill-735x490.jpg",
                "https://cdn.airplane-pictures.net/images/uploaded-images/2011/2/13/121833.jpg",
                "http://www.airlinereporter.com/wp-content/uploads/2014/08/ja830a-3.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/3/35/Airbus_A320-232%2C_Asiana_Airlines_AN1912836.jpg",
                "https://news.gtp.gr/wp-content/uploads/2018/05/Austrian-Airlines-Boeing-B777-OE-LPF-2.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/d/da/Boeing_727-21%2C_Avianca_AN2024581.jpg",
                "https://airlines-airports.com/wp-content/uploads/2017/03/BMI-British-midland-international-airline.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/e/eb/British_Airways_Concorde_G-BOAC_03.jpg",
                "https://airlinegeeks.com/wp-content/uploads/2016/06/402_in_flight-e1466631803259-678x381.jpg",
                "https://cdn4.img.sputniknews.com/images/106817/67/1068176765.png",
                "https://www.airlineratings.com/wp-content/uploads/uploads/A320_CEBU_PACIFIC.jpg",
                "https://www.sunshineskies.com/uploads/4/3/7/6/43764233/587618_orig.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/45/China_Airlines_Airbus_A300B4-622R_Pichugin-2.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/7/7a/McDonnell_Douglas_MD-11%2C_China_Eastern_Airlines_AN0220927.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/b/b8/China_Southern_Airlines_Boeing_737-300_B-2911_DLC_Feb_2012.png",
                "https://upload.wikimedia.org/wikipedia/commons/9/90/British_Aerospace_BAe-3101_Jetstream_31%2C_United_Express_%28Westair_Commuter_Airlines%29_AN1076269.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/46/McDonnell_Douglas_DC-10-30%2C_AOM_French_Airlines_%28Cubana%29_AN0062654.jpg",
                "https://airlinesfleet.com/wp-content/uploads/2018/06/Delta-Air-Lines-Boeing-737-832-N381DN-Old-Livery-@LAX.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/1/19/Dragon_Air_A320-200.JPG",
                "https://www.telegraph.co.uk/content/dam/business/2016/06/11/98210944_-xlarge_trans_NvBQzQNjv4Bqulmngyk1nEPNRM5Qu5tZfj4elo1Kbvb_7TKnuZEfv-I.jpg",
                "https://cdn.cultofmac.com/wp-content/uploads/2018/05/EgyptAir_Airbus_A320_SU-GCC_on_finals_at_Ataturk_Airport-780x558.jpg",
                "https://www.jewishpress.com/wp-content/uploads/El-Al-Boeing-An-El-Al-Israel-Airlines-Boeing-737-858.-Credit-Andre-Wadman-Wikimedia-Commons.jpg",
                "https://images.gulfnews.com/polopoly-images/2009/11/17/1.528669_4032966268.jpg",
                "https://worldairlinenews.files.wordpress.com/2014/01/era-alaska-dhc-8-100-n887ea-13-nanookseralr.jpg",
                "http://jetnews.com.mx/wp-content/uploads/2017/04/expressjet-.jpg",
                "https://media.self.com/photos/57d8bdce4b76f0f832a0ef44/master/pass/finnair-cooking-oil-powered.jpg",
                "http://aeropx.com/files/styles/thumbnail/public/photos/20130728174826.jpg?itok=11AOdMYx",
                "http://www.traveller.com.au/content/dam/images/2/9/h/h/b/image.related.articleLeadwide.620x349.29dxq.png/1353995872473.jpg",
                "https://cdn.planespotters.net/photo/646000/280/n181gj-gojet-airlines-bombardier-crj-900lr-cl-600-2d24_PlanespottersNet_646801_095f44e0ce.jpg",
                "https://cdn.planespotters.net/photo/646000/280/n181gj-gojet-airlines-bombardier-crj-900lr-cl-600-2d24_PlanespottersNet_646801_095f44e0ce.jpg",
                "http://800noticias.com/cms/wp-content/uploads/2016/02/aerolinea-gol.jpg",
                "https://worldairlinenews.files.wordpress.com/2014/06/hainan-787-8-b-2739-07nose-pae-joel-chusidlrw.jpg",
                "https://atwonline.com/site-files/atwonline.com/files/imagecache/large_img/uploads/2017/05/hawaiian-airlines-new-livery.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/1/1e/N449QX_LAX_%2831294957790%29.jpg",
                "https://www.iberia.com/wcs/imagenes/experiencia_iberia/flota/flota_iberia/A320/A320_1.jpg",
                "https://pixels-cache.icelandair.com/upload/w_1200%2Ch_630%2Cg_auto%2Cc_fill%2Cf_auto%2Cq_auto/icelandair/bltdac9da760fed9463.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/9/9a/A300_Iran_Air_EP-IBT_THR_May_2010.jpg",
                "https://www.jal.com/assets/img/outline/aircraft/pic_aircraft_001.jpg",
                "https://www.serbiaincoming.com/wp-content/uploads/2013/01/jat-airways.jpg",
                "https://toronto.citynews.ca/wp-content/blogs.dir/sites/10/2013/01/JazzAir.jpg",
                "https://www.jetairways.com/Images/cards/cargo-network-650x500.jpg",
                "https://millionmilesecrets.com/wp-content/uploads/2016/08/10_Secrets_To_Better_JetBlue_Travel_01.jpg",
                "http://www.traveller.com.au/content/dam/images/1/m/2/n/l/g/image.related.articleLeadwide.620x349.1m2niz.png/1457406966943.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/5/5e/Kenya_Airways_Boeing_737-800_5Y-KYA_JNB_Jan_2007.png",
                "https://blog.klm.com/assets/uploads/2017/06/IMG_5538-768x510.jpg",
                "https://static.tacdn.com/img2/flights/airlines/airline_travelers_choice/airline_hero_images/8729104.jpg",
                "https://2.bp.blogspot.com/-jST1L1mSIN0/V1wvdvJ4qJI/AAAAAAAACY4/9K2-jS5XhKg5oea4ozbX5hWb9atUvPK7ACLcB/s1600/Korean%2BAir%2BA330.jpg",
                "https://www.albalad.co/uploads/2017/11/kuwait.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/LAN_A343_CC-CQA.JPG/1200px-LAN_A343_CC-CQA.JPG",
                "http://www.dangcongsan.vn/DATA/0/2018/10/kiem_tra-09_39_32_564.jpg",
                "https://static.dezeen.com/uploads/2018/02/lufthansa-new-logo-design-hero-852x479.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/48/Boeing_777-200ER_Malaysia_AL_%28MAS%29_9M-MRO_-_MSN_28420_404_%289272090094%29.jpg",
                "https://cdn.airlines-inform.ru/upload/iblock/d33/Mesa-Airlines.jpg",
                "https://cdn.airplane-pictures.net/images/uploaded-images/2017/7/16/933019as.jpg",
                "https://www.omanair.com/sites/default/files/content/flying_with_us/images/b737_800_2.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/40/Boeing_777-240%28LR%29_Pakistan_International_Airlines_%28PIA%29_AP-BGZ%2C_YYZ_Toronto%2C_ON_%28Lester_B._Pearson_International_Airport%29%2C_Canada_PP1379441685.jpg",
                "https://cdn.businesstraveller.com/wp-content/uploads/fly-images/771307/csm_First_A321_to_PAL_1_02_95cf43aa78-916x516.jpg",
                "https://farm3.staticflickr.com/2515/3967906035_cbf8f506b4_b.jpg",
                "https://www.travelwithkidz.com.au/wp-content/uploads/2017/11/SM_GENERIC_-Qantas-plane-at-Auckland-Airport_EDITORIAL-ONLY_shutterstock_Mark-Baskett_-3023244.jpg",
                "https://www.qatarairways.com/content/dam/images/renditions/horizontal-2/brand/aircraft/a320/a320-aircraft-h2.jpg",
                "https://skift.com/wp-content/uploads/2014/04/republic.jpg",
                "http://thepinthemapproject.com/wp-content/uploads/2015/08/royal-air-maroc-e1440281375397.jpg",
                "https://1.bp.blogspot.com/-6gBaXkdVD8U/W5TDAV7oV4I/AAAAAAAAm3Q/_BebqPzM_mkLIO2FiQ-pXcQq4FPnPVxBgCLcBGAs/s1600/rj.jpg",
                "http://waterfordwhispersnews.com/wp-content/uploads/2017/04/shutterstock_469580993-e1491911738421.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/SAS_Airbus_A340_in_Stockholm.jpg/1200px-SAS_Airbus_A340_in_Stockholm.jpg",
                "https://samchui.com/wp-content/uploads/2019/03/Saudi-Boeing-787-9-759x500.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Shenzhen_Airlines_Boeing_737-800.jpg/1200px-Shenzhen_Airlines_Boeing_737-800.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/N801SA_Shuttle_America_Dash_8-300.jpg/220px-N801SA_Shuttle_America_Dash_8-300.jpg",
                "https://cdn.blueswandaily.com//2018/02/Singapore-Airlines-Aircraft-787-10-2000x1200.jpg",
                "http://1.bp.blogspot.com/-3UTfz_Y2F2c/UAIGK3daNWI/AAAAAAAAGGM/f01LKvXu-qw/s1600/Skywest+Airlines+A320-231+VH-FNP.jpg",
                "https://www.domesticflights-southafrica.co.za/wp-content/uploads/2018/04/South-African-Airways-Airbus-A330-300.jpg",
                "https://4brf13430svm3bnu053zbxvg-wpengine.netdna-ssl.com/wp-content/uploads/2017/09/Southwest-Airlines-Review-from-Upgraded-Points-e1506536455337.jpg",
                "https://4brf13430svm3bnu053zbxvg-wpengine.netdna-ssl.com/wp-content/uploads/2017/08/Spirit-Airlines-Plane-In-Flight.jpg",
                "https://i.pinimg.com/originals/cb/0b/aa/cb0baac1b97291e3cde8ed87126636c7.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/8/82/TACA_International_Airlines_Boeing_737-300_JetPix.jpg",
                "https://cdn.planespotters.net/photo/741000/original/pt-msd-tam-linhas-areas-airbus-a330-243_PlanespottersNet_741398_f99dc19092.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/0/01/TAP_Portugal_A319-100_CS-TTL_FRA_2011-12-10.png",
                "https://onemileatatime.com/wp-content/uploads/2015/03/Thai-Airways.jpg",
                "https://www.flughafen-graz.at/typo3temp/_processed_/7/f/csm_TK_A320_TC-JPB_0_320_c_THY_01_9a8b1b35a2.jpg",
                "https://aviationvoice.com/wp-content/uploads/2018/01/Trans-States-Airlines-Rolls-Out-44000-Signing-Bonus-for-Pilots.jpg",
                "https://static1.squarespace.com/static/513bcb31e4b0df53688faccb/58951bae8419c20eb9ed3840/5235c7bae4b074566343ef43/1379256569471/Transaero%2520B747.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/42/Ukraine_International_Airlines_Boeing_737-800_KvW.jpg",
                "https://timedotcom.files.wordpress.com/2017/04/united.jpg",
                "https://www.vietnamairlines.com/~/media/ContentImage/TravelInfo/ChuyenBayMoUoc.jpg",
                "https://g.foolcdn.com/editorial/images/455043/airline-virgin-america-airbus-a320-va.jpg",
                "https://www.godsavethepoints.com/wp-content/uploads/2018/06/Exterior.jpg",
                "https://edge.alluremedia.com.au/m/g/2017/04/iStock-521691568.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/e/e6/Vueling_Airlines_Airbus_A320-232.jpg",
                "https://airlinegeeks.com/wp-content/uploads/2018/05/main.3.1-678x381.jpg",


        };
        for (int i=1; i<separated.length;i++) {


            Person person = new Person(""+(i) + "", separated[i],linkler2[i-1]);
            persons.add(person);

        }
    }


    public class FetchDescription extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(History.this,R.style.MyProgressDialogTheme);
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
                String dizi[] = {"AIRLINE: ","COUNTRY: ","BEGAN OPERATION: ","AVERAGE AGE OF FLEET: ","PASSENGER VOLUME:","LAST FATAL ACCIDENT: ",
                        "FATAL ACCIDENTS LAST 10 YRS: ","FATAL ACCIDENTS LAST 20 YRS: ","EXPLANATION: "};

                Document doc = Jsoup.connect("http://www.planecrashinfo.com/accidents.htm").get();
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

            progressDialog = new ProgressDialog(History.this,R.style.MyProgressDialogTheme);
            progressDialog.setMessage("Loading . .");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String yemekURL = "http://www.planecrashinfo.com/accidents.htm";

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
