package com.example.haninahmid.saydalani.Adapter;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haninahmid.saydalani.Model.serchproductclass;
import com.example.haninahmid.saydalani.R;

import java.util.List;

public class searchadabterpro extends ArrayAdapter<serchproductclass> {
    private List<serchproductclass> provinsiList;
    private Context mCtx;
    public searchadabterpro(List<serchproductclass> P , Context c) {
        super(c, R.layout.searchproductadabter,P);
        this.provinsiList=P;
        this.mCtx=c;
    }
    public View getView(int postion, View convertView , ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.searchproductadabter,null,true);
        serchproductclass pro = provinsiList.get(postion);
        TextView name=(TextView) view.findViewById(R.id.tvNameProvphar);

        TextView pname=(TextView) view.findViewById(R.id.tvnameproduct);
        TextView pnameelmy=(TextView) view.findViewById(R.id.tvnameelmy);
        TextView id=(TextView) view.findViewById(R.id.tvIDProvph);
        TextView tvprice=(TextView) view.findViewById(R.id.tvprice);
        TextView tvpcountry=(TextView) view.findViewById(R.id.tvpcountry);

        TextView add=(TextView) view.findViewById(R.id.textView15);
        ImageView onOfImage =(ImageView) view.findViewById(R.id.imageView2phar);
        ImageView Imagephar =(ImageView) view.findViewById(R.id.imageViewphar);


        name.setText(pro.getName());
        id.setText(pro.getId());
       add.setText(pro.getAddress());
       pnameelmy.setText(pro.getProductNamElmy());
        pname.setText(pro.getProduct_Name());
        tvprice.setText(pro.getPrice());
        tvpcountry.setText(pro.getCountry_of_manufacture());


        String photo=pro.getPicture();
        Log.i("image",photo);
        byte [] decodeString = Base64.decode(photo,Base64.DEFAULT);
        Bitmap decode= BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);

        Imagephar.setImageBitmap(decode);
        if(pro.getPres_server()==0){
            onOfImage.setImageResource(R.mipmap.rednew);

        }
        if(pro.getPres_server()==1){
            onOfImage.setImageResource(R.mipmap.greeen);

        }

        return view;
    }
}
