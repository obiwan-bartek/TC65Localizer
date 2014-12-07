/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

/**
 *
 * @author Administrator
 */
public class TC65Location {

    private String latitude = " ";
    private String latitudeDir = " ";
    private String longitude = " ";
    private String longitudeDir = " ";
    private float altitude = 0;
    private float speed = 0;
    private String date = " ";
    private String time = " ";
    private String fix = "No valid Fix";
    private String satellitesUsed = " ";
    private String hdop = " ";

    public void setLatitude(String lat) {
        this.latitude = lat;
    }

    public void setLatitudeDir(String dir) {
        this.latitudeDir = dir;
    }

    public void setLongitude(String lon) {
        this.longitude = lon;
    }

    public void setLongitudeDir(String dir) {
        this.longitudeDir = dir;
    }

    public void setAltitude(String alt) {
        this.altitude = Float.parseFloat(alt);
    }

    public void setSpeed(String speed) {
        this.speed = Float.parseFloat(speed);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }
    
    public void setSatellitesUsed(String satellitesUsed) {
        this.satellitesUsed = satellitesUsed;
    }
    
    public void setHDOP(String hdop) {
        this.hdop = hdop;
    }

    public int getLatDegrees() {
        int latD = 0;

        latD = Integer.parseInt(latitude.substring(0, 2));
        return latD;
    }

    public float getLatMinutes() {
        float latM = 0f;

        latM = Float.parseFloat(latitude.substring(2, latitude.length() - 1));
        return latM;
    }

    public String getLatDir() {
        return this.latitudeDir;
    }

    public float getLatDD() {
        float latDD = 0f;
        float latD = getLatDegrees();
        float latM = getLatMinutes();

        latDD = latD + (latM / 60f);

        latDD = (float) (int) (((latDD) + 0.000005f) * 100000.0f) / 100000.0f;
        return latDD;
    }

    public int getLonDegrees() {
        int lonD = 0;

        lonD = Integer.parseInt(longitude.substring(0, 3));
        return lonD;
    }

    public float getLonMinutes() {
        float lonM = 0f;

        lonM = Float.parseFloat(longitude.substring(3, longitude.length() - 1));
        return lonM;
    }

    public String getLonDir() {
        return this.longitudeDir;
    }

    public float getLonDD() {
        float lonDD = 0f;
        float lonD = getLonDegrees();
        float lonM = getLonMinutes();

        lonDD = lonD + (lonM / 60f);

        lonDD = (float) (int) (((lonDD) + 0.000005f) * 100000.0f) / 100000.0f;
        return lonDD;
    }

    public float getAltitude() {
        return this.altitude;
    }

    public float getSpeed() {
        return (float) (int) (((speed * 1.852f) + 0.005f) * 100.0f) / 100.0f;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getFix() {
        return this.fix;
    }
    
    public String getSatellitesUsed() {
        return this.satellitesUsed;
    }
    
    public String getHDOP() {
        return this.hdop;
    }

    public boolean getFixValid() {

        if (this.fix.equalsIgnoreCase("no valid fix")) {
            return false;
        } else {
            return true;
        }
    }

}
