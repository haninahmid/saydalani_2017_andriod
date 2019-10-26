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

import com.example.haninahmid.saydalani.Model.productClass;
import com.example.haninahmid.saydalani.R;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<productClass> {

    private List<productClass> provinsiList;
    private Context mCtx;

    public ProductAdapter(List<productClass> P , Context c) {
        super(c, R.layout.productlaout,P);
        this.provinsiList=P;
        this.mCtx=c;
        Log.i("image","Test");
    }
    public View getView(int postion, View convertView , ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.productlaout,null,true);
        TextView name=(TextView) view.findViewById(R.id.namephart);
        TextView price=(TextView) view.findViewById(R.id.textViewprice);
        TextView company=(TextView) view.findViewById(R.id.textViewCompany);
        ImageView Imagepro =(ImageView) view.findViewById(R.id.imageViewimage);
        productClass pr=provinsiList.get(postion);
        price.setText(String.valueOf(pr.getPrice()));
        name.setText(pr.getProduct_Name());
        company.setText(pr.getCountry_of_manufacture());
    TextView elmy =(TextView) view.findViewById(R.id.textViewelmy);
    TextView elmyn =(TextView) view.findViewById(R.id.textViewelmyn);
    if(pr.getProductNamElmy().matches("")){
        elmy.setText("لايوجد");
    }
    else{
        elmy.setText(pr.getProductNamElmy());
    }


        String photo=pr.getPicture();
        Log.i("image",photo);
        byte [] decodeString = Base64.decode(photo,Base64.DEFAULT);
        Bitmap decode= BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);

        Imagepro.setImageBitmap(decode);
        return view;
    }

}
