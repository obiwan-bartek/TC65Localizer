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
    
    public void parseNMEA(String sentence) throws IOException {
        //System.out.println(sentence);
        for (int i = 0; i <= sentence.length()-1; i++) {
            iMletMain.tc65serialReader.os.write(sentence.charAt(i));
        }   
        iMletMain.tc65serialReader.os.write(13);
        iMletMain.tc65serialReader.os.write(10);
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

        sentence = sentence.substring(senStart, senStop + 3); //copy (trim) only real NMEA sentence with leading '$'

        return sentence;
    }
    
}
