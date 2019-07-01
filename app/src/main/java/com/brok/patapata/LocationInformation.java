package com.brok.patapata;

public class LocationInformation {

    //public String time;
    public double latitude;
    public double longitude;

    public LocationInformation(){

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }



    public LocationInformation(double latitude, double longitude){
        //this.time=time;
        this.latitude=latitude;
        this.longitude=longitude;
    }
/*
    public double getLongitude() {
        return longitude;
    public String getTime(){
        return time;
    }
    public double getLatitude(){
        return latitude;
    }
    public double getLongitude(){
        return longitude;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void  setLatitude(double latitude){
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
*/
}