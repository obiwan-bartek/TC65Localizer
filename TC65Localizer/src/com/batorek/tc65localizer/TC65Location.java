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
    private String fixD = "No valid Fix";
    private int satellitesUsed = 0;
    private float hdop = 0.0f;

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
        if (!alt.equals("") && !alt.equals(" ")) {
            this.altitude = Float.parseFloat(alt);
        }
    }

    public void setSpeed(String speed) {
        if (!speed.equals("") && !speed.equals(" ")) {
            this.speed = Float.parseFloat(speed);
        }
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
    
    public void setFixD(String fixD) {
        this.fixD = fixD;
    }
    
    public void setSatellitesUsed(String satellitesUsed) {
        if (!satellitesUsed.equals("") && !satellitesUsed.equals(" ")) {
            this.satellitesUsed = Integer.parseInt(satellitesUsed);
        }
    }
    
    public void setHDOP(String hdop) {
        if (!hdop.equals("") && !hdop.equals(" ")) {
            this.hdop = Float.parseFloat(hdop);
        }
    }

    public int getLatDegrees() {                
        int latD = 0;
        
        if (this.latitude.length() > 3) {
            latD = Integer.parseInt(this.latitude.substring(0, 2));
        }
        return latD;
    }

    public float getLatMinutes() {
        float latM = 0f;
        
        if (this.latitude.length() > 4) {
            latM = Float.parseFloat(this.latitude.substring(2, this.latitude.length() - 1));
        }
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

        if (this.longitude.length() > 4) {
            lonD = Integer.parseInt(this.longitude.substring(0, 3));
        }
        return lonD;
    }

    public float getLonMinutes() {
        float lonM = 0f;

        if (this.longitude.length() > 5) {
            lonM = Float.parseFloat(this.longitude.substring(3, this.longitude.length() - 1));
        }
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
    
    public String getFixD() {
        return this.fixD;
    }
    
    public int getSatellitesUsed() {
        return this.satellitesUsed;
    }
    
    public float getHDOP() {
        return this.hdop;
    }

    public boolean isFixValid() {

        if (this.fix.equalsIgnoreCase("no valid fix") || this.fixD.equalsIgnoreCase("no valid fix")) {
            return false;
        } else {
            return true;
        }
    }
    
    public void printLocationData() {
        String temp;
        System.out.println("##############");
        
        temp = getLatDir() + " " + String.valueOf(getLatDegrees()) + "* " + Float.toString(getLatMinutes()) + "'   |   "
                + getLonDir() + " " + String.valueOf(getLonDegrees()) + "* " + Float.toString(getLonMinutes());
        
        System.out.println(temp);
        
        temp = "Date: " + getDate() + "   Time: " + getTime() + "   Speed: " + Float.toString(getSpeed()) + "   Alt: " + Float.toString(getAltitude())
                + "   Sat: " + String.valueOf(getSatellitesUsed()) + "   HDOP: " + Float.toString(getHDOP());
        
        System.out.println(temp);
        
        temp = "Lat: " + Float.toString(getLatDD()) + "   Lon: " + Float.toString(getLonDD()) + "   Fix: " + getFix() + "   FixD: " + getFixD();
        
        System.out.println(temp);
        
        System.out.println("##############");
    }

}
