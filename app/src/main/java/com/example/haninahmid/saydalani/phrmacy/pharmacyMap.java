package com.example.haninahmid.saydalani.phrmacy;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haninahmid.saydalani.MainActivity;
import com.example.haninahmid.saydalani.Productbpackage.TypeProdunct;
import com.example.haninahmid.saydalani.R;
import com.example.haninahmid.saydalani.rating.starSqlhelper;
import com.example.haninahmid.saydalani.rating.starbarclass;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class pharmacyMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lon;
    private double lot;
   double stsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final Intent intent = getIntent();
        String lat = intent.getStringExtra("lat");
        String longt = intent.getStringExtra("long");
        lon= Double.parseDouble(longt);
        lot= Double.parseDouble(lat);

        String address = intent.getStringExtra("address");
        String email = intent.getStringExtra("email");
        final String facebook = intent.getStringExtra("facebook");
        final String id = intent.getStringExtra("id");

        String name = intent.getStringExtra("name");
        int night = Integer.parseInt(intent.getStringExtra("night"));
        String payment_method = intent.getStringExtra("payment_method");
        final String phone = intent.getStringExtra("phone");
        int Presservice = Integer.parseInt(intent.getStringExtra("service"));
        final String web = intent.getStringExtra("web");

        TextView nametv=(TextView) findViewById(R.id.textpharname);
        TextView emailtv=(TextView) findViewById(R.id.textviewemail);
        TextView textdwam=(TextView) findViewById(R.id.textViewDwam);
        TextView Opentv=(TextView) findViewById(R.id.textViewOpen);
        TextView tvpay=(TextView) findViewById(R.id.textviewpay);
        TextView addressTv=(TextView) findViewById(R.id.textViewaddress);
        ImageView imagemoon=(ImageView) findViewById(R.id.imageViewmoon);
        ImageView imagesun=(ImageView) findViewById(R.id.imageViewsun);
        ImageView imageOpen=(ImageView) findViewById(R.id.imageViewOpen);
        Button prosu =(Button) findViewById(R.id.buttonproductss);
        final Button callbutton =(Button) findViewById(R.id.buttoncall);
        final Button webbutton =(Button) findViewById(R.id.buttonweb);
        final Button facebookbutton =(Button) findViewById(R.id.buttonfacebook);
        final Button home =(Button) findViewById(R.id.buttonhome2);

        //***************************** rating

        final RatingBar rb=(RatingBar)findViewById(R.id.ratingBar);
        Boolean flg=serch(id);
        //****************************** after serch if you click rating to this phar before
        if(flg==true){
            rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                    insert(Integer.parseInt(id),rb.getRating());
                    Toast.makeText(getApplicationContext(),"شكرا لك على التقيم",Toast.LENGTH_LONG).show();
                    rb.setEnabled(false);
                    insertTOdatabase(Integer.parseInt(id),rb.getRating());
                }
            });
        }
        //************************************** if you do then disable the rating bar
        if(flg==false){
            rb.setRating((float) stsave);
            rb.setEnabled(false);
        }

        prosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(pharmacyMap.this, TypeProdunct.class);
                myIntent.putExtra("id",id); //Optional parameters
                pharmacyMap.this.startActivity(myIntent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(pharmacyMap.this, MainActivity.class);
                pharmacyMap.this.startActivity(myIntent);
            }
        });
        // call phone
        if(phone.matches("")){
            callbutton.setBackgroundResource(R.mipmap.phonered);
        }
        else{
            callbutton.setBackgroundResource(R.mipmap.phonegreen);
        }
        callbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(phone.matches("")){

                }else {
                    String number = phone;
                    Uri call = Uri.parse("tel:" + number);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            }
        });

        // end call phone

        // **************************** start web
        if(web.matches("")){
            webbutton.setBackgroundResource(R.mipmap.wenno);
        }
        else{
            webbutton.setBackgroundResource(R.mipmap.wenyes);
        }
        webbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(web.matches("")){

                }
                else {
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(web));
                    startActivity(browserIntent);
                }
            }
        });
        // **************************** end web // **************************** start web
        if(facebook.matches("")){
            facebookbutton.setBackgroundResource(R.mipmap.nofacebook);
        }
        else{
            facebookbutton.setBackgroundResource(R.mipmap.facebookyes);
        }
        facebookbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(facebook.matches("")){

                }
                else {
                    Intent faceIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
                    startActivity(faceIntent);
                }
            }
        });
        // **************************** end web
        //  textdwam.setText(night);
        if(night==1)
        {
            imagemoon.setImageResource(R.mipmap.moon);
            imagesun.setImageResource(R.mipmap.sun);
            textdwam.setText("دوام 24 ساعة");
        }
        if(night==0)
        {
            imagesun.setImageResource(R.mipmap.sun);
            textdwam.setText("دوام عادي");
        }
        if(Presservice==1)
        {
            imageOpen.setImageResource(R.mipmap.greeen);
            Opentv.setText("مفتوحة");
        }
        if(Presservice==0)
        {
            imageOpen.setImageResource(R.mipmap.rednew);
            Opentv.setText("مغلقة");
        }


        nametv.setText(name);
        emailtv.setText(email);
        tvpay.setText(payment_method);
        addressTv.setText(address);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a malrker in Sydney and move the camera
        LatLng sydney = new LatLng(lot , lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


    }

    public Boolean serch(String id){
        starSqlhelper st=new starSqlhelper(this);
        SQLiteDatabase database = st.getReadableDatabase();
        String selection=starbarclass.tablestar.COLUMN_ID+" = ? ";
        String [] args={id};
        Cursor cursor = database.query(starbarclass.tablestar.TABLE_NAME,null,selection,args,null,null,null);
        int i=0;
        Boolean flag=true;
        int idcheck=0;
        if (cursor!= null && cursor.getCount() > 0 && cursor.moveToFirst()){
            idcheck=1;
            double starco=cursor.getDouble(cursor.getColumnIndex(starbarclass.tablestar.COLUMN_STARS));
            stsave=starco;
            //do something
        }

       if(idcheck!=0){
           flag=false;
       }
       cursor.close();
       return flag;
    }

    public void insert(int id,double stars){
        starSqlhelper st=new starSqlhelper(this);
        SQLiteDatabase database=st.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(starbarclass.tablestar.COLUMN_ID,id);
        contentValues.put(starbarclass.tablestar.COLUMN_STARS,stars);
        database.insert(starbarclass.tablestar.TABLE_NAME,null,contentValues);
    }



    public  void insertTOdatabase(final int idhpar, final double ratingstar){
        String url = "http://10.0.2.2:8888/saydlani%20app%20Connect/ratingbarup.php";
        RequestQueue queue = Volley.newRequestQueue(pharmacyMap.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.i("My success",""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(pharmacyMap.this, "my error :"+error, Toast.LENGTH_LONG).show();
                Log.i("My error",""+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String, String>();
                map.put("idhpar", String.valueOf(idhpar));
                map.put("ratingstar", String.valueOf(ratingstar));


                return map;
            }
        };
        queue.add(request);


    }
}
