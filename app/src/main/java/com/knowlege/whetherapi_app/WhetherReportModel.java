package com.knowlege.whetherapi_app;

public class WhetherReportModel {

    private  int id;
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

    public WhetherReportModel() {
    }

    public WhetherReportModel(int id, String whether_state_name, String whether_report_abbr, String wind_Direction_com, String created, String applicable_date, float min_temp, float max_temp, float wind_speed, float wind_direction, int air_pressure, int humidity, float visibilty, int predictablity) {
        this.id = id;
        this.whether_state_name = whether_state_name;
        this.whether_report_abbr = whether_report_abbr;
        Wind_Direction_com = wind_Direction_com;
        this.created = created;
        this.applicable_date = applicable_date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibilty = visibilty;
        this.predictablity = predictablity;
    }

    @Override
    public String toString() {
        return  "Date:"+ created +'\n'+whether_state_name+'\n'+"min_temp=" + min_temp +"\n"+
                "max_temp=" + max_temp ;



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhether_state_name() {
        return whether_state_name;
    }

    public void setWhether_state_name(String whether_state_name) {
        this.whether_state_name = whether_state_name;
    }

    public String getWhether_report_abbr() {
        return whether_report_abbr;
    }

    public void setWhether_report_abbr(String whether_report_abbr) {
        this.whether_report_abbr = whether_report_abbr;
    }

    public String getWind_Direction_com() {
        return Wind_Direction_com;
    }

    public void setWind_Direction_com(String wind_Direction_com) {
        Wind_Direction_com = wind_Direction_com;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public int getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(int air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getVisibilty() {
        return visibilty;
    }

    public void setVisibilty(float visibilty) {
        this.visibilty = visibilty;
    }

    public int getPredictablity() {
        return predictablity;
    }

    public void setPredictablity(int predictablity) {
        this.predictablity = predictablity;
    }
}
