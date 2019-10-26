package com.example.haninahmid.saydalani.Productbpackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haninahmid.saydalani.R;

public class TypeProdunct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_produnct);
        Button women=(Button)findViewById(R.id.buttonwomen);
        Button men=(Button)findViewById(R.id.buttonmen);
        Button children=(Button)findViewById(R.id.buttonchildrent);
        Button another=(Button)findViewById(R.id.buttonanother);
        Button machine=(Button)findViewById(R.id.buttonmachin);
        Button medcine=(Button)findViewById(R.id.buttonmedcine);
        Intent intent = getIntent();
        final String idtv = intent.getStringExtra("id");

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TypeProdunct.this, ShowProduct.class);
                String typest="منتجات نسائية";
                myIntent.putExtra("type",typest); //Optional parameter
                myIntent.putExtra("idpharm",idtv); //Optional parameter
                TypeProdunct.this.startActivity(myIntent);

            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TypeProdunct.this, ShowProduct.class);
                String typest="منتجات رجالية";
                myIntent.putExtra("type",typest); //Optional parameter
                myIntent.putExtra("idpharm",idtv); //Optional parameter
                TypeProdunct.this.startActivity(myIntent);

            }
        });
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TypeProdunct.this, ShowProduct.class);
                String typest="اطفال";
                myIntent.putExtra("type",typest); //Optional parameter
                myIntent.putExtra("idpharm",idtv); //Optional parameter
                TypeProdunct.this.startActivity(myIntent);

            }
        });
        another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TypeProdunct.this, ShowProduct.class);
                String typest="اخرى";
                myIntent.putExtra("type",typest); //Optional parameter
                myIntent.putExtra("idpharm",idtv); //Optional parameter
                TypeProdunct.this.startActivity(myIntent);

            }
        });
        machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TypeProdunct.this, ShowProduct.class);
                String typest="الات";
                myIntent.putExtra("type",typest); //Optional parameter
                myIntent.putExtra("idpharm",idtv); //Optional parameter
                TypeProdunct.this.startActivity(myIntent);

            }
        });
        medcine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(TypeProdunct.this, ShowProduct.class);
                String typest="ادوية";
                myIntent.putExtra("type",typest); //Optional parameter
                myIntent.putExtra("idpharm",idtv); //Optional parameter
                TypeProdunct.this.startActivity(myIntent);
            }
        });

    }
}
