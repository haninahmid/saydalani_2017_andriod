package com.example.haninahmid.saydalani.Productbpackage;

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
import com.example.haninahmid.saydalani.Adapter.ProductAdapter;
import com.example.haninahmid.saydalani.Handler;
import com.example.haninahmid.saydalani.MainActivity;
import com.example.haninahmid.saydalani.Model.productClass;
import com.example.haninahmid.saydalani.R;
import com.example.haninahmid.saydalani.phrmacy.OpenPharmacy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowProduct extends AppCompatActivity {
    ListView listView;
    List<productClass> provinsiList;
    String idphar;
    String typepro;
    ArrayList<String> strpicture=new ArrayList<String>();
    ArrayList<String> strcompany_name=new ArrayList<String>();
    ArrayList<String> strproduct_Name=new ArrayList<String>();
    ArrayList<String> stramount=new ArrayList<String>();
    ArrayList<String> strprice=new ArrayList<String>();
    ArrayList<String> strtype=new ArrayList<String>();
    ArrayList<String> strCountry_of_manufacture=new ArrayList<String>();
    ArrayList<String> strinformation=new ArrayList<String>();
    ArrayList<String> strproduction_date=new ArrayList<String>();
    ArrayList<String> strExpiration_date=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        Intent intent = getIntent();
         final String idtv = intent.getStringExtra("type");
         String idtv2 = intent.getStringExtra("idpharm");
        idphar=idtv2;
        typepro=idtv;
        listView =(ListView)findViewById(R.id.listProducts);
        final EditText srn=(EditText) findViewById(R.id.editTextNameProduct) ;
        final EditText srnCom=(EditText) findViewById(R.id.editTextNameElmy) ;
        provinsiList=new ArrayList<>();
        showList();
        if(!idtv.matches("ادوية")){
            srnCom.setEnabled(false);
            srnCom.setHint("");
            srnCom.setVisibility(View.INVISIBLE);

        }
        final Button btnSr =(Button) findViewById(R.id.buttonreturn2) ;
        btnSr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           strpicture.clear();
           strcompany_name.clear();
                   strproduct_Name.clear();
             stramount.clear();
            strprice.clear();
             strtype.clear();
              strCountry_of_manufacture.clear();
              strinformation.clear();
             strproduction_date.clear();
                strExpiration_date.clear();
                provinsiList.clear();
                showList();
            }
        });
        final Button btnSrfil =(Button) findViewById(R.id.buttonserch2) ;
        btnSrfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idtv.matches("ادوية")){
                    if(srn.getText().toString().matches("") && srnCom.getText().toString().matches("")){
                  Toast.makeText(getApplicationContext(),"يجب كتابة اما اسم المنتج او الاسم العلمي للمنتج على الاقل للبحث",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        strpicture.clear();
                        strcompany_name.clear();
                        strproduct_Name.clear();
                        stramount.clear();
                        strprice.clear();
                        strtype.clear();
                        strCountry_of_manufacture.clear();
                        strinformation.clear();
                        strproduction_date.clear();
                        strExpiration_date.clear();
                        provinsiList.clear();
                        showListfilter(srn.getText().toString(),srnCom.getText().toString());
                    }
                }
                else if (!typepro.matches("ادوية")){
                    if(srn.getText().toString().matches("")){
                        Toast.makeText(getApplicationContext(),"يجب كتابة اسم المنتج  للبحث",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        strpicture.clear();
                        strcompany_name.clear();
                        strproduct_Name.clear();
                        stramount.clear();
                        strprice.clear();
                        strtype.clear();
                        strCountry_of_manufacture.clear();
                        strinformation.clear();
                        strproduction_date.clear();
                        strExpiration_date.clear();
                        provinsiList.clear();
                        showListfilter(srn.getText().toString(), srnCom.getText().toString());
                    }
                }


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(ShowProduct.this, DisplayProduct.class);
                myIntent.putExtra("picture", strpicture.get(position)); //Optional parameters
                myIntent.putExtra("company_name", strcompany_name.get(position)); //Optional parameters
                myIntent.putExtra("product_Name", strproduct_Name.get(position)); //Optional parameters
                myIntent.putExtra("amount", stramount.get(position)); //Optional parameters
                myIntent.putExtra("price", strprice.get(position)); //Optional parameters
                myIntent.putExtra("type", strtype.get(position)); //Optional parameters
                myIntent.putExtra("country", strCountry_of_manufacture.get(position)); //Optional parameters
                myIntent.putExtra("info", strinformation.get(position)); //Optional parameters
                myIntent.putExtra("productDate", strproduction_date.get(position)); //Optional parameters
                myIntent.putExtra("expiredDate", strExpiration_date.get(position)); //Optional parameters

                ShowProduct.this.startActivity(myIntent);
            }
        });
        Button home =(Button) findViewById(R.id.buttonhome4);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ShowProduct.this, MainActivity.class);
                ShowProduct.this.startActivity(myIntent);
            }
        });

    }
    private void showList()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2:8888/saydlani%20app%20Connect/SelectProduct.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray array=obj.getJSONArray("product");
                            for(int i=0; i<array.length();i++)
                            {
                                JSONObject provObj= array.getJSONObject(i);
                                productClass p=new productClass(
                                        provObj.getString("picture"),
                                        provObj.getString("ProductNamElmy"),
                                        provObj.getString("id"),
                                        provObj.getString("id_pharmacy"),
                                        provObj.getString("company_name"),
                                        provObj.getString("product_Name"),
                                        provObj.getDouble("amount"),
                                        provObj.getDouble("price"),
                                        provObj.getString("Type"),
                                        provObj.getString("Country_of_manufacture"),
                                        provObj.getString("informations"),
                                        provObj.getString("production_date"),
                                        provObj.getString("Expiration_date"));
                                if(p.getId_pharmacy().matches(idphar) && p.getType().matches(typepro)){
                                    provinsiList.add(p);

                                    stramount.add(String.valueOf(p.getAmount()));
                                    strpicture.add(p.getPicture());
                                    strtype.add(p.getType());
                                    strcompany_name.add(p.getCompany_name());
                                    strCountry_of_manufacture.add(p.getCountry_of_manufacture());
                                    strExpiration_date.add(p.getExpiration_date());
                                    strproduction_date.add(p.getProduction_date());
                                    strproduct_Name.add(p.getProduct_Name());
                                    strinformation.add(p.getInformation());
                                    strprice.add(String.valueOf(p.getPrice()));

                                }
                            }

                            ProductAdapter adapter = new ProductAdapter(provinsiList,getApplicationContext());
                            listView.setAdapter(adapter);
                            if(provinsiList.size()==0){
                                Toast.makeText(getApplicationContext(),"لا يوجد منتجات بهذا النوع في هذه الصيدلية",Toast.LENGTH_LONG).show();
                            }



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
    //***************************************************************************************************

    private void showListfilter(final String firstNT, final String elmyName)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2:8888/saydlani%20app%20Connect/SelectProduct.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array=obj.getJSONArray("product");
                            int flag=0;
                            for(int i=0; i<array.length();i++) {
                                JSONObject provObj = array.getJSONObject(i);
                                productClass p = new productClass(
                                        provObj.getString("picture"),
                                        provObj.getString("ProductNamElmy"),
                                        provObj.getString("id"),
                                        provObj.getString("id_pharmacy"),
                                        provObj.getString("company_name"),
                                        provObj.getString("product_Name"),
                                        provObj.getDouble("amount"),
                                        provObj.getDouble("price"),
                                        provObj.getString("Type"),
                                        provObj.getString("Country_of_manufacture"),
                                        provObj.getString("informations"),
                                        provObj.getString("production_date"),
                                        provObj.getString("Expiration_date"));
                                if (p.getId_pharmacy().matches(idphar) && p.getType().matches(typepro)) {

                                if (firstNT.matches("") || elmyName.matches("")) {
                                    if (firstNT.matches(p.getProduct_Name()) && elmyName.matches("")) {
                                        provinsiList.add(p);
                                        stramount.add(String.valueOf(p.getAmount()));
                                        strpicture.add(p.getPicture());
                                        strtype.add(p.getType());
                                        strcompany_name.add(p.getCompany_name());
                                        strCountry_of_manufacture.add(p.getCountry_of_manufacture());
                                        strExpiration_date.add(p.getExpiration_date());
                                        strproduction_date.add(p.getProduction_date());
                                        strproduct_Name.add(p.getProduct_Name());
                                        strinformation.add(p.getInformation());
                                        strprice.add(String.valueOf(p.getPrice()));
                                        flag=1;
                                    } else if (firstNT.matches("") && elmyName.matches(p.getProductNamElmy())) {
                                        provinsiList.add(p);
                                        stramount.add(String.valueOf(p.getAmount()));
                                        strpicture.add(p.getPicture());
                                        strtype.add(p.getType());
                                        strcompany_name.add(p.getCompany_name());
                                        strCountry_of_manufacture.add(p.getCountry_of_manufacture());
                                        strExpiration_date.add(p.getExpiration_date());
                                        strproduction_date.add(p.getProduction_date());
                                        strproduct_Name.add(p.getProduct_Name());
                                        strinformation.add(p.getInformation());
                                        strprice.add(String.valueOf(p.getPrice()));
                                        flag=1;
                                    }
                                } else if (firstNT.matches(p.getProduct_Name()) && elmyName.matches(p.getProductNamElmy())) {
                                    provinsiList.add(p);
                                    stramount.add(String.valueOf(p.getAmount()));
                                    strpicture.add(p.getPicture());
                                    strtype.add(p.getType());
                                    strcompany_name.add(p.getCompany_name());
                                    strCountry_of_manufacture.add(p.getCountry_of_manufacture());
                                    strExpiration_date.add(p.getExpiration_date());
                                    strproduction_date.add(p.getProduction_date());
                                    strproduct_Name.add(p.getProduct_Name());
                                    strinformation.add(p.getInformation());
                                    strprice.add(String.valueOf(p.getPrice()));
                                    flag=1;
                                }// end if
                            }//end if



                            } // end for
                            if(flag==0){
                                Toast.makeText(getApplicationContext(),"لا يوجد منتج بهذا النوع بهذا النوع من البحث",Toast.LENGTH_SHORT).show();
                                strpicture.clear();
                                strcompany_name.clear();
                                strproduct_Name.clear();
                                stramount.clear();
                                strprice.clear();
                                strtype.clear();
                                strCountry_of_manufacture.clear();
                                strinformation.clear();
                                strproduction_date.clear();
                                strExpiration_date.clear();
                                provinsiList.clear();
                            showList();
                            }

                            ProductAdapter adapter = new ProductAdapter(provinsiList,getApplicationContext());
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
