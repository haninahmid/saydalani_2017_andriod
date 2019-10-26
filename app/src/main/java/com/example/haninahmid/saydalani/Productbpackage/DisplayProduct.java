package com.example.haninahmid.saydalani.Productbpackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haninahmid.saydalani.Adapter.ProductAdapter;
import com.example.haninahmid.saydalani.MainActivity;
import com.example.haninahmid.saydalani.Model.productClass;
import com.example.haninahmid.saydalani.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplayProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);
        Intent intent = getIntent();
        String picturest = intent.getStringExtra("picture");
        String coname = intent.getStringExtra("company_name");
        String Pname = intent.getStringExtra("product_Name");
        String amountst = intent.getStringExtra("amount");
        String pricest = intent.getStringExtra("price");
        String typest = intent.getStringExtra("type");
        String countryst = intent.getStringExtra("country");
        String infost = intent.getStringExtra("info");
        String productDatest = intent.getStringExtra("productDate");
        String expiredDatest = intent.getStringExtra("expiredDate");
        TextView namep =(TextView) findViewById(R.id.textViewName);
        TextView cname=(TextView) findViewById(R.id.textViewCmade);
        TextView amountp =(TextView) findViewById(R.id.textViewAmount);
        TextView price =(TextView) findViewById(R.id.textViewpriceprice);
        TextView bdate =(TextView) findViewById(R.id.textViewBdate);
        TextView edate =(TextView) findViewById(R.id.textViewEdate);
        TextView type =(TextView) findViewById(R.id.textViewtype);
        TextView info =(TextView) findViewById(R.id.textViewinfo);
        TextView country =(TextView) findViewById(R.id.textViewCounty);
        ImageView pic=(ImageView) findViewById(R.id.imageViewPpicture);
        byte [] decodeString = Base64.decode(picturest,Base64.DEFAULT);
        Bitmap decode= BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);

        pic.setImageBitmap(decode);
        namep.setText(Pname);
        cname.setText(coname);
        amountp.setText(amountst);
        price.setText(pricest);
        type.setText(typest);
        country.setText(countryst);
        info.setText(infost);
        bdate.setText(productDatest);
        edate.setText(expiredDatest);
        Button home=(Button)findViewById(R.id.buttonhome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DisplayProduct.this, MainActivity.class);

                DisplayProduct.this.startActivity(myIntent);

            }
        });

    }
}
