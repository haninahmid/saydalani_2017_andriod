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

import com.example.haninahmid.saydalani.Model.Provinsi;
import com.example.haninahmid.saydalani.R;

import java.util.List;

public class ProvinisiAdabter extends ArrayAdapter<Provinsi> {
    private List<Provinsi> provinsiList;
    private Context mCtx;
    public ProvinisiAdabter(List<Provinsi> P , Context c)
    {
        super(c,R.layout.listphat,P);
        this.provinsiList=P;
        this.mCtx=c;
    }

    public View getView(int postion, View convertView , ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listphat,null,true);
        TextView name=(TextView) view.findViewById(R.id.namephart);
        TextView dwam=(TextView) view.findViewById(R.id.swamtext);
        TextView OpenClose=(TextView) view.findViewById(R.id.openclose);
        TextView id=(TextView) view.findViewById(R.id.idpharim);
        ImageView onOfImage =(ImageView) view.findViewById(R.id.onofimage);
        ImageView Imagephar =(ImageView) view.findViewById(R.id.imagepharim);
        ImageView sunimage =(ImageView) view.findViewById(R.id.sunpharimage);
        ImageView moonimage =(ImageView) view.findViewById(R.id.moonpharimage);
        Provinsi provinsi = provinsiList.get(postion);
        name.setText(provinsi.getName());
        id.setText(provinsi.getId());
TextView add =(TextView) view.findViewById(R.id.textViewaddress);
        String photo=provinsi.getImage();
        byte [] decodeString = Base64.decode(photo,Base64.DEFAULT);
        Bitmap decode= BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        Imagephar.setImageBitmap(decode);

        add.setText(provinsi.getAddress());
        if(provinsi.getPres_server()==0){
            onOfImage.setImageResource(R.mipmap.rednew);
            OpenClose.setText("مغلقة");
        }
        if(provinsi.getPres_server()==1){
            onOfImage.setImageResource(R.mipmap.greeen);
            OpenClose.setText("مفتوحة");
        }
        if(provinsi.getNighty()==0){
            sunimage.setImageResource(R.mipmap.sun);
            dwam.setText("دوام عادي");
        }
        if(provinsi.getNighty()==1){
            moonimage.setImageResource(R.mipmap.moon);
            sunimage.setImageResource(R.mipmap.sun);
            dwam.setText("دوام 24 ساعة");

        }
        return view;
    }
}
