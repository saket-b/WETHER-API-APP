package com.knowlege.whetherapi_app;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WhetherDataService {

    public WhetherDataService(Context context) {
        this.context = context;
    }
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String id);
    }
    Context context;
    String urlwithname ="https://www.metaweather.com/api/location/search/?query=";
    String urlwithID="https://www.metaweather.com/api/location/";
  String id;
    public void getID(String string, final VolleyResponseListener volleyResponseListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url2=urlwithname+string; //this url from Metawether APi.
         id="";
         JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject object = response.getJSONObject(0);
                    id = object.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, "ID=" + id, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("something wrong");
            }
        }
        );
        Mysingleton.getInstance(context).addToRequestQueue(request);

    }

    public interface ForCastByIDResPonce {
        void onError(String message);

        void onResponse(List<WhetherReportModel>whetherReportModels);
    }

    public void getwetherbyID(String ID, final ForCastByIDResPonce forCastByIDResPonce)
    {

     String url3=urlwithID+ID;
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              //Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                try {
                    JSONArray whether_list=response.getJSONArray("consolidated_weather");

                    final List<WhetherReportModel> whetherReportModels=new ArrayList<>();
                  /*  private  int id;
                    private  String whether_state_name;
                    private  String  whether_report_abbr;
                    private  String  Wind_Direction_com;
                    private  String created;
                    private  String applicable_date;
                    private  float min_temp;
                    private float max_temp;
                    private  float wind_speed;
                    private  float wind_direction;
                    private  int air_pressure;
                    private  int humidity;
                    private  float visibilty;
                    private  int predictablity;
*/
                   for(int i=0;i<whether_list.length();i++) {
                       JSONObject first_day_from_Api = (JSONObject) whether_list.get(i);
                       WhetherReportModel one_day_wether_report=new WhetherReportModel();
                       one_day_wether_report.setId(first_day_from_Api.getInt("id"));
                       one_day_wether_report.setWhether_state_name(first_day_from_Api.getString("weather_state_name"));
                       one_day_wether_report.setWhether_report_abbr(first_day_from_Api.getString("weather_state_abbr"));
                       one_day_wether_report.setWind_Direction_com(first_day_from_Api.getString("wind_direction_compass"));
                       one_day_wether_report.setCreated(first_day_from_Api.getString("created"));
                       one_day_wether_report.setCreated(first_day_from_Api.getString("applicable_date"));
                       one_day_wether_report.setMin_temp(first_day_from_Api.getLong("min_temp"));
                       one_day_wether_report.setMax_temp(first_day_from_Api.getLong("max_temp"));
                       one_day_wether_report.setWind_speed(first_day_from_Api.getLong("wind_speed"));
                       one_day_wether_report.setWind_direction(first_day_from_Api.getLong("wind_direction"));
                       one_day_wether_report.setAir_pressure(first_day_from_Api.getInt("air_pressure"));
                       one_day_wether_report.setHumidity(first_day_from_Api.getInt("humidity"));
                       one_day_wether_report.setVisibilty(first_day_from_Api.getLong("visibility"));
                       one_day_wether_report.setPredictablity(first_day_from_Api.getInt("predictability"));

                       whetherReportModels.add(one_day_wether_report);
                   }
                       forCastByIDResPonce.onResponse(whetherReportModels);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
        Toast.makeText(context,"something is wrong",Toast.LENGTH_SHORT).show();
            }
        }
        );
        Mysingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }

    public interface ForCastByNAMEResPonce {
        void onError(String message);

        void onResponse(List<WhetherReportModel>whetherReportModels);
    }


    public  void wether_report_by_name(String name, final ForCastByNAMEResPonce forCastByNAMEResPonce)
    {
         getID(name, new VolleyResponseListener() {
             @Override
             public void onError(String message) {
                 Toast.makeText(context,"something is wrong",Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onResponse(String id) {
              getwetherbyID(id, new ForCastByIDResPonce() {
                  @Override
                  public void onError(String message) {
                      Toast.makeText(context,"something is wrong",Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onResponse(List<WhetherReportModel> whetherReportModels) {
                     forCastByNAMEResPonce.onResponse(whetherReportModels);

                  }
              });
             }
         });
    }


}
