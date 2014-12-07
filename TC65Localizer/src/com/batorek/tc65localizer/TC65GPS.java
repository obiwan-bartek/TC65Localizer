/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import com.batorek.tc65localizer.classes.StrSplit;


/**
 *
 * @author Administrator
 */
public class TC65GPS {

    IMletMain iMletMain; // reference to instance of main class
    StrSplit strSplit; // string splitter
    
    public TC65GPS(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
        strSplit = new StrSplit();
    }
    
    public void parseNMEA(String sentence) throws Throwable{

        String[] sentenceData;
        
        sentenceData = strSplit.split(',', sentence); // split NMEA sentence to components
        
        if (sentenceData[0].equals("$GPGGA")) {
            iMletMain.tc65Location.setTime(sentenceData[1]); //UTC Time HHMMSS.SSS
            iMletMain.tc65Location.setLatitude(sentenceData[2]); //Latitude
            iMletMain.tc65Location.setLatitudeDir(sentenceData[3]); //Latitude direction
            iMletMain.tc65Location.setLongitude(sentenceData[4]); //Longitude
            iMletMain.tc65Location.setLongitudeDir(sentenceData[5]); //Longitude direction
            if (sentenceData[6].endsWith("0")) { //Fix 0:No Fix, 1:SPS Mode, 2:DiffGPS, SPS Mode, 3:PPS Mode
                iMletMain.tc65Location.setFix("No valid Fix");
            }
            if (sentenceData[6].endsWith("1")) {
                iMletMain.tc65Location.setFix("GPS SPS Mode");
            }
            if (sentenceData[6].endsWith("2")) {
                iMletMain.tc65Location.setFix("DiffGPS SPS Mode");
            }
            if (sentenceData[6].endsWith("3")) {
                iMletMain.tc65Location.setFix("GPS PPS Mode");
            }
            iMletMain.tc65Location.setSatellitesUsed(sentenceData[7]); //Satellites used 0-12
            iMletMain.tc65Location.setHDOP(sentenceData[8]); //HDOP
            iMletMain.tc65Location.setAltitude(sentenceData[9]); //Altitude in meters
            // nothing interesting there
        }

        if (sentenceData[0].equals("$GPGSA")) {

            // sentenceData[1]; //Mode1 M:Manual 2D/3D, A:Auto 2D/3D
            // sentenceData[2]; //Mode2 1:No Fix, 2:2D, 3:3D

            if (sentenceData[2].endsWith("1")) {
                iMletMain.tc65Location.setFixD("No valid Fix");
            }
            if (sentenceData[2].endsWith("2")) {
                iMletMain.tc65Location.setFixD("2D");
            }
            if (sentenceData[2].endsWith("3")) {
                iMletMain.tc65Location.setFixD("3D");
            }
            // nothing interesting there
        }

        if (sentenceData[0].endsWith("$GPRMC")) {
            iMletMain.tc65Location.setTime(sentenceData[1]); //UTC Time HHMMSS.SSS

            //sentenceData[2]; //A:Data valid V:Data not valid            
            if (sentenceData[2].endsWith("V")) {
                iMletMain.tc65Location.setFix("No valid Fix");
                iMletMain.tc65Location.setFixD("No valid Fix");
            }
            if (sentenceData[2].endsWith("A")) {
                //iMletMain.tc65Location.setFix("2D");
            }
            iMletMain.tc65Location.setLatitude(sentenceData[3]); //Latitude
            iMletMain.tc65Location.setLatitudeDir(sentenceData[4]); //Latitude direction
            iMletMain.tc65Location.setLongitude(sentenceData[5]); //Longitude
            iMletMain.tc65Location.setLongitudeDir(sentenceData[6]); //Longitude direction
            iMletMain.tc65Location.setSpeed(sentenceData[7]); //Speed in knots
            // sentenceData[8]; //Course in degrees
            iMletMain.tc65Location.setDate(sentenceData[9]); //Date DDMMYY
            // nothing interesting there
        }
    }
    
    public String validateSentence(String sentence) {        
        if (sentence.length() < 6){
            return null;
        }
        
        int senStart = sentence.indexOf("$"); //NMEA sentence start
        int senStop = sentence.indexOf("*"); //NMEA sentence stop, discard hash code
        if ((senStart < 0 || senStop < 0) || sentence.length() < senStop+3) {
            return null;
        }

        //System.out.println(sentence);
        String sentenceOut = sentence.substring(senStart, senStop); //copy (trim) only real NMEA sentence with leading '$', discard checksum

        if (!sentence.substring(senStop + 1, senStop + 3).equalsIgnoreCase(generateChecksum(sentenceOut))) {
            return null;
        }        
        return sentenceOut; // return without "*" and checksum
    }
    
    public String generateChecksum(String sentence){
        int checksum = 0;
        for (int i = 1; i < sentence.length(); i++) { // start from 1 because of $ sign
            checksum = checksum ^ sentence.charAt(i);
        }
        String result = Integer.toHexString(checksum).toUpperCase();
        
        if (result.length() == 1) { //padding
            result = "0" + result;
        }
        
        return result;       
    }
    
}
