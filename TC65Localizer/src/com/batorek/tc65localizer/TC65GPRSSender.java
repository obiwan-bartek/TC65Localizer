/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import com.batorek.tc65localizer.classes.TC65Runnable;
import java.io.IOException;
import java.util.Random;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Administrator
 */
public class TC65GPRSSender extends TC65Runnable {
    
    IMletMain iMletMain; // reference to instance of main class
    private String UID;

    public TC65GPRSSender(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }

    public void beforeMainLoop() {
        genUID(); // generate trace UID
    }

    public void mainLoop() {
        try {
            if (iMletMain.tc65Location.isFixValid()) {
                sendData();
            }
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void afterMainLoop() {
        
    }
    
    private void sendData() {
        //String url = prefs.getServerAddress();
        String url = iMletMain.tc65Preferences.serverUrl;
        String time = iMletMain.tc65Location.getTime();
        String date = iMletMain.tc65Location.getDate();
        String lat = String.valueOf(iMletMain.tc65Location.getLatDD());
        String lat_d = iMletMain.tc65Location.getLatDir();
        String lon = String.valueOf(iMletMain.tc65Location.getLonDD());
        String lon_d = iMletMain.tc65Location.getLonDir();
        int resp = 0;
        String url_out = "";

        UID = checkLength(UID, 20);
        date = checkLength(date, 6);
        time = checkLength(time, 6);
        lat = checkLength(lat, 9);
        lat_d = checkLength(lat_d, 1);
        lon = checkLength(lon, 10);
        lon_d = checkLength(lon_d, 1);

        try {
            do {
                url_out = (url + "?uid=" + UID + "&date=" + date + "&time=" + time
                        + "&lat=" + lat + "&lat_d=" + lat_d + "&lon=" + lon + "&lon_d=" + lon_d);
                        //+ ";baerer_type=gprs;access_point=internet;username=internet;password=internet");
                                
                HttpConnection httpConn = (HttpConnection) Connector.open(url_out);
                resp = httpConn.getResponseCode();                
                //httpConn.close();                
                if (resp == 200) {
                    //System.out.println("WYSLANO! HTTP:200");
                } else {
                    //System.out.println("ERROR");
                }
            } while (resp != 200); // do in loop until resp = HTTP:200
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void genUID() {
        Random rnd = new Random();
        long lUID = 0;

        do {
            rnd.setSeed(System.currentTimeMillis());
            lUID = rnd.nextLong();
        } while (lUID == 0); // generate until non-zero

        if (lUID < 0) {
            lUID = lUID * -1; // change sign if under 0
        }
        UID = String.valueOf(lUID);
    }
    
    private String checkLength(String str, int len) {
        if (str.length() > len) {
            str = str.substring(0, len);
        }
        return str;
    }   
}
