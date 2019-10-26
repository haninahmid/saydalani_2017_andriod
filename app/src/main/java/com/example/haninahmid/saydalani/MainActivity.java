package com.example.haninahmid.saydalani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haninahmid.saydalani.phrmacy.ListPharActivity;
import com.example.haninahmid.saydalani.phrmacy.NightOpenPharmacy;
import com.example.haninahmid.saydalani.phrmacy.OpenPharmacy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPhar =(Button) findViewById(R.id.buttonphar);
        Button buttonOpen=(Button) findViewById(R.id.buttonOpen);
        Button buttonOpenNight=(Button) findViewById(R.id.buttonNight);
        buttonPhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListPharActivity.class));

            }
        });
        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OpenPharmacy.class));

            }
        });
        buttonOpenNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NightOpenPharmacy.class));

            }
        });
        Button ser =(Button) findViewById(R.id.button2);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, searchProductActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    Button tf =(Button) findViewById(R.id.button3);
        tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Ratingpharmacy.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


    }
    }

