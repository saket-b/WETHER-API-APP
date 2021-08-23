package com.knowlege.whetherapi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

     Button get_id,wether_by_id,wether_by_name;
     ListView lv_wether_report;
     EditText city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_id=(Button)findViewById(R.id.button);
        wether_by_id=(Button)findViewById(R.id.button2);
        wether_by_name=(Button)findViewById(R.id.button3);
        lv_wether_report=(ListView)findViewById(R.id.listview);
        city_name=(EditText)findViewById(R.id.edittext);
   final WhetherDataService whetherDataService=new WhetherDataService(MainActivity.this);

        get_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                whetherDataService.getID(city_name.getText().toString(), new WhetherDataService.VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(MainActivity.this, "Returned ID= Wrong" , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String id) {
                                Toast.makeText(MainActivity.this, "Returned ID=" + id, Toast.LENGTH_SHORT).show();
                            }
                        }
                );


// Add the

            }
        });

        wether_by_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whetherDataService.getwetherbyID(city_name.getText().toString(), new WhetherDataService.ForCastByIDResPonce() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"something is wrong",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WhetherReportModel>whetherReportModels) {
                     //Toast.makeText(MainActivity.this,whetherReportModel.toString(),Toast.LENGTH_SHORT).show();
                        ArrayAdapter<String>arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,whetherReportModels);
                        lv_wether_report.setAdapter(arrayAdapter);
                    }
                });


            }
        });

        wether_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this,"click on wether by name",Toast.LENGTH_SHORT).show();
                whetherDataService.wether_report_by_name(city_name.getText().toString(), new WhetherDataService.ForCastByNAMEResPonce() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"something is wrong",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WhetherReportModel> whetherReportModels) {
                        ArrayAdapter<String>arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,whetherReportModels);
                        lv_wether_report.setAdapter(arrayAdapter);
                    }
                });
            }
        });
    }



}