package com.example.haninahmid.saydalani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.haninahmid.saydalani.Adapter.searchadabterpro;
import com.example.haninahmid.saydalani.Model.serchproductclass;
import com.example.haninahmid.saydalani.phrmacy.pharmacyMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class searchProductActivity extends AppCompatActivity {
    ListView listView;
    List<serchproductclass> provinsiList;


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
        setContentView(R.layout.activity_search_product);
       final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
       final EditText name=(EditText)findViewById(R.id.editTextN);

       Button btnSubmit = (Button) findViewById(R.id.button6);
        listView =(ListView)findViewById(R.id.listsearchpro);

        final TextView tvElmy=(TextView)findViewById(R.id.editTextmontgelmy) ;
        final TextView editTextcountry=(TextView)findViewById(R.id.editTextcountry) ;
        final TextView editTextaddress=(TextView)findViewById(R.id.editTextadd) ;
        final Switch st=(Switch)findViewById(R.id.switch4);

        provinsiList=new ArrayList<serchproductclass>();

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int flg=0;
                if(st.isChecked()){
                 flg=1;
                }
             if(tvElmy.getText().toString().matches("") && name.getText().toString().matches("")){
                 Toast.makeText(getApplicationContext(),"يجب تعبيئة اسم المنتج العلمي او التجاري على الاقل",Toast.LENGTH_SHORT).show();
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
                 String elmy=tvElmy.getText().toString();
                 String address=editTextaddress.getText().toString() ;
                 String country=editTextcountry.getText().toString() ;

    showList(name.getText().toString(), String.valueOf(spinner1.getSelectedItem()),elmy,address,country, flg);
             }
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(searchProductActivity.this, pharmacyMap.class);
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
                searchProductActivity.this.startActivity(myIntent);
            }
        });

    }
    private void showList(final String names, final String spinner ,final String elmy, final String address,final String country, final int open)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2:8888/saydlani%20app%20Connect/ProductSerch.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray array=obj.getJSONArray("product");
                            for(int i=0; i<array.length();i++)
                            {
                                JSONObject provObj= array.getJSONObject(i);
                                serchproductclass p=new serchproductclass(
                                        provObj.getString("product_Name"),
                                        provObj.getString("ProductNamElmy"),
                                        provObj.getString("price"),
                                        provObj.getString("Country_of_manufacture"),
                                        provObj.getString("picture"),
                                        provObj.getString("Type"),

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
                                if(open==1){
                                if(p.getPres_server()==1 && p.getType().matches(spinner)){


                                    if(p.getProduct_Name().matches(names) &&  elmy.matches("")) {


                                        if (p.getAddress().matches(address) && p.getCountry_of_manufacture().matches(country)) {
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
                                        if (address.matches(p.getAddress()) && country.matches("")) {
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
                                        if (address.matches("") && country.matches(p.getCountry_of_manufacture())) {
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
                                        if (address.matches("") && country.matches("")) {


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
                                    }
                                    if(names.matches("") &&  elmy.matches(p.getProductNamElmy())) {


                                        if (p.getAddress().matches(address) && p.getCountry_of_manufacture().matches(country)) {
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
                                        if (address.matches(p.getAddress()) && country.matches("")) {
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
                                        if (address.matches("") && country.matches(p.getCountry_of_manufacture())) {
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
                                        if (address.matches("") && country.matches("")) {
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
                                    }
                                    //****************** together
                                    if(names.matches(p.getProduct_Name()) &&  elmy.matches(p.getProductNamElmy())) {

                                        if (p.getAddress().matches(address) && p.getCountry_of_manufacture().matches(country)) {
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
                                        if (address.matches(p.getAddress()) && country.matches("")) {
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
                                        if (address.matches("") && country.matches(p.getCountry_of_manufacture())) {
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
                                        if (address.matches("") && country.matches("")) {
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
                                    }
                                    }

                                }
                                //********************** if not open
                                else  if(p.getType().matches(spinner)){

                                    if(p.getProduct_Name().matches(names) &&  elmy.matches("")) {
                                        if (p.getAddress().matches(address) && p.getCountry_of_manufacture().matches(country)) {
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
                                        if (address.matches(p.getAddress()) && country.matches("")) {
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
                                        if (address.matches("") && country.matches(p.getCountry_of_manufacture())) {
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
                                        if (address.matches("") && country.matches("")) {

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
                                    }
                                    if(names.matches("") &&  elmy.matches(p.getProductNamElmy())) {

                                        if (p.getAddress().matches(address) && p.getCountry_of_manufacture().matches(country)) {
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
                                        if (address.matches(p.getAddress()) && country.matches("")) {
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
                                        if (address.matches("") && country.matches(p.getCountry_of_manufacture())) {
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
                                        if (address.matches("") && country.matches("")) {

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
                                    }
                                    //****************** together
                                    if(names.matches(p.getProduct_Name()) &&  elmy.matches(p.getProductNamElmy())) {

                                        if (p.getAddress().matches(address) && p.getCountry_of_manufacture().matches(country)) {
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
                                        if (address.matches(p.getAddress()) && country.matches("")) {
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
                                        if (address.matches("") && country.matches(p.getCountry_of_manufacture())) {
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
                                        if (address.matches("") && country.matches("")) {
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
                                    }

                                }


                            }

                            searchadabterpro adapter = new searchadabterpro(provinsiList,getApplicationContext());
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
