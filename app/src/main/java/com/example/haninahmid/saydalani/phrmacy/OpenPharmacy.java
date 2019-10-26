package com.example.haninahmid.saydalani.phrmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.haninahmid.saydalani.Adapter.ProvinisiAdabter;
import com.example.haninahmid.saydalani.Handler;
import com.example.haninahmid.saydalani.MainActivity;
import com.example.haninahmid.saydalani.Model.Provinsi;
import com.example.haninahmid.saydalani.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OpenPharmacy extends AppCompatActivity {
    ListView listView;
    List<Provinsi> provinsiList;
    ArrayList<String> strId=new ArrayList<String>();
    ArrayList<String> strname=new ArrayList<String>();
    ArrayList<String> strphone=new ArrayList<String>();
    ArrayList<String> strweb=new ArrayList<String>();
    ArrayList<String> strface=new ArrayList<String>();
    ArrayList<String> stremail=new ArrayList<String>();
    ArrayList<String> straddress=new ArrayList<String>();
    ArrayList<String> strpayment_method=new ArrayList<String>();
    ArrayList<String> strlatitude=new ArrayList<String>();
    ArrayList<String> strlongitude=new ArrayList<String>();
    ArrayList<String> strnighty=new ArrayList<String>();
    ArrayList<String> strPresservice=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pharmacy);
        listView =(ListView)findViewById(R.id.listOpen);
        provinsiList=new ArrayList<>();
        showList();
        Button re = (Button) findViewById(R.id.buttonreturnOp);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provinsiList.clear();
                straddress.clear();
                stremail.clear();
                strface.clear();
                strId.clear();
                strlatitude.clear();
                strlongitude.clear();
                strname.clear();
                strnighty.clear();
                strpayment_method.clear();
                strphone.clear();
                strPresservice.clear();
                strweb.clear();
                showList();
            }
        });
        Button ser = (Button) findViewById(R.id.buttonserchOp);
        final EditText phName=(EditText)findViewById(R.id.editTextNameOp);
        final EditText addressName=(EditText)findViewById(R.id.editTextaddressOp);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phName.getText().toString().matches("") && addressName.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"يجب كتابة اما اسم الصيدلية اول العنوان على الاقل للبحث",Toast.LENGTH_SHORT).show();

                }
                else {
                    provinsiList.clear();

                    straddress.clear();
                    stremail.clear();
                    strface.clear();
                    strId.clear();
                    strlatitude.clear();
                    strlongitude.clear();
                    strname.clear();
                    strnighty.clear();
                    strpayment_method.clear();
                    strphone.clear();
                    strPresservice.clear();
                    strweb.clear();
                    showListSearch(phName.getText().toString(),addressName.getText().toString());
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(OpenPharmacy.this, pharmacyMap.class);
                myIntent.putExtra("address", straddress.get(position)); //Optional parameters
                myIntent.putExtra("email", stremail.get(position)); //Optional parameters
                myIntent.putExtra("facebook", strface.get(position)); //Optional parameters
                myIntent.putExtra("id", strId.get(position)); //Optional parameters
                myIntent.putExtra("lat", strlatitude.get(position)); //Optional parameters
                myIntent.putExtra("long", strlongitude.get(position)); //Optional parameters
                myIntent.putExtra("name", strname.get(position)); //Optional parameters
                myIntent.putExtra("night", strnighty.get(position)); //Optional parameters
                myIntent.putExtra("payment_method", strpayment_method.get(position)); //Optional parameters
                myIntent.putExtra("phone", strphone.get(position)); //Optional parameters
                myIntent.putExtra("service", strPresservice.get(position)); //Optional parameters
                myIntent.putExtra("web", strweb.get(position)); //Optional parameters
                OpenPharmacy.this.startActivity(myIntent);
            }
        });

        Button home =(Button) findViewById(R.id.buttonhome3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(OpenPharmacy.this, MainActivity.class);
                OpenPharmacy.this.startActivity(myIntent);
            }
        });
    }
    private void showList()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2:8888/saydlani%20app%20Connect/openNow.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray array=obj.getJSONArray("pharm");
                            for(int i=0; i<array.length();i++)
                            {
                                JSONObject provObj= array.getJSONObject(i);
                                Provinsi p=new Provinsi(
                                        provObj.getString("image"),
                                        provObj.getString("id"),
                                        provObj.getString("name"),
                                        provObj.getString("Address"),
                                        provObj.getString("email"),
                                        provObj.getString("website"),
                                        provObj.getString("facebook"),
                                        provObj.getString("payment_method"),
                                        provObj.getString("telepon"),
                                        provObj.getDouble("latitude"),
                                        provObj.getDouble("longitude"),
                                        provObj.getInt("nighty"),
                                        provObj.getInt("Pres_service"));
                                provinsiList.add(p);
                                straddress.add(p.getAddress());
                                stremail.add(p.getEmail());
                                strface.add(p.getFacebook());
                                strId.add(p.getId());
                                strlatitude.add(String.valueOf(p.getLatitude()));
                                strlongitude.add(String.valueOf(p.getLongitude()));
                                strname.add(p.getName());
                                strnighty.add(String.valueOf(p.getNighty()));
                                strpayment_method.add(p.getPaymthod());
                                strphone.add(p.getTelepon());
                                strPresservice.add(String.valueOf(p.getPres_server()));
                                strweb.add(p.getWebsite());

                            }
                            ProvinisiAdabter adapter = new ProvinisiAdabter(provinsiList,getApplicationContext());
                            listView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        })
        {
        };
        Handler.getInstance(getApplicationContext()).addTorequestQue(stringRequest);

    }

    private void showListSearch(final String name,final String adress)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2:8888/saydlani%20app%20Connect/openNow.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            int flag=0;
                            JSONObject obj = new JSONObject(response);
                            JSONArray array=obj.getJSONArray("pharm");
                            for(int i=0; i<array.length();i++)
                            {
                                JSONObject provObj= array.getJSONObject(i);
                                Provinsi p=new Provinsi(
                                        provObj.getString("image"),
                                        provObj.getString("id"),
                                        provObj.getString("name"),
                                        provObj.getString("Address"),
                                        provObj.getString("email"),
                                        provObj.getString("website"),
                                        provObj.getString("facebook"),
                                        provObj.getString("payment_method"),
                                        provObj.getString("telepon"),
                                        provObj.getDouble("latitude"),
                                        provObj.getDouble("longitude"),
                                        provObj.getInt("nighty"),
                                        provObj.getInt("Pres_service"));

                                if(name.matches(p.getName()) && adress.matches(p.getAddress())){
                                    provinsiList.add(p);

                                    straddress.add(p.getAddress());
                                    stremail.add(p.getEmail());
                                    strface.add(p.getFacebook());
                                    strId.add(p.getId());
                                    strlatitude.add(String.valueOf(p.getLatitude()));
                                    strlongitude.add(String.valueOf(p.getLongitude()));
                                    strname.add(p.getName());
                                    strnighty.add(String.valueOf(p.getNighty()));
                                    strpayment_method.add(p.getPaymthod());
                                    strphone.add(p.getTelepon());
                                    strPresservice.add(String.valueOf(p.getPres_server()));
                                    strweb.add(p.getWebsite());
                                    flag=1;
                                }

                                else  if(name.matches("") || adress.matches("")) {

                                    if (name.matches("") && adress.matches(p.getAddress())) {
                                        provinsiList.add(p);

                                        straddress.add(p.getAddress());
                                        stremail.add(p.getEmail());
                                        strface.add(p.getFacebook());
                                        strId.add(p.getId());
                                        strlatitude.add(String.valueOf(p.getLatitude()));
                                        strlongitude.add(String.valueOf(p.getLongitude()));
                                        strname.add(p.getName());
                                        strnighty.add(String.valueOf(p.getNighty()));
                                        strpayment_method.add(p.getPaymthod());
                                        strphone.add(p.getTelepon());
                                        strPresservice.add(String.valueOf(p.getPres_server()));
                                        strweb.add(p.getWebsite());
                                        flag=1;
                                    }
                                    else if (name.matches(p.getName()) && adress.matches("")) {
                                        provinsiList.add(p);

                                        straddress.add(p.getAddress());
                                        stremail.add(p.getEmail());
                                        strface.add(p.getFacebook());
                                        strId.add(p.getId());
                                        strlatitude.add(String.valueOf(p.getLatitude()));
                                        strlongitude.add(String.valueOf(p.getLongitude()));
                                        strname.add(p.getName());
                                        strnighty.add(String.valueOf(p.getNighty()));
                                        strpayment_method.add(p.getPaymthod());
                                        strphone.add(p.getTelepon());
                                        strPresservice.add(String.valueOf(p.getPres_server()));
                                        strweb.add(p.getWebsite());
                                        flag=1;
                                    }

                                }




                            }
                            if(flag==0){
                                Toast.makeText(getApplicationContext(),"لا يوجد صيدلية بهذا النوع من البحث",Toast.LENGTH_SHORT).show();
                                provinsiList.clear();
                                straddress.clear();
                                stremail.clear();
                                strface.clear();
                                strId.clear();
                                strlatitude.clear();
                                strlongitude.clear();
                                strname.clear();
                                strnighty.clear();
                                strpayment_method.clear();
                                strphone.clear();
                                strPresservice.clear();
                                strweb.clear();
                                showList();
                            }
                            ProvinisiAdabter adapter = new ProvinisiAdabter(provinsiList,getApplicationContext());
                            listView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        })
        {
        };
        Handler.getInstance(getApplicationContext()).addTorequestQue(stringRequest);

    }
}
