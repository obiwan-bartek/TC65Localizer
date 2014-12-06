/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class TC65GPS {

    IMletMain iMletMain; // reference to instance of main class
    
    public TC65GPS(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }
    
    public void parseNMEA(String sentence)  {

        
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
        String sentenceOut = sentence.substring(senStart, senStop); //copy (trim) only real NMEA sentence with leading '$'

        if (!sentence.substring(senStop + 1, senStop + 3).equalsIgnoreCase(generateChecksum(sentenceOut))) {
            return null;
        }        
        return sentenceOut;
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
