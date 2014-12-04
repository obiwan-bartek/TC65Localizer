/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import com.batorek.tc65localizer.classes.TC65Runnable;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.CommConnection;
import javax.microedition.io.Connector;

/**
 *
 * @author Administrator
 */
public class TC65SerialReader extends TC65Runnable{

    IMletMain iMletMain; // reference to instance of main class
    
    CommConnection comm;
    InputStream is;
    String sentence;
    int input;
    
    public TC65SerialReader(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }
    
    public void beforeMainLoop() {
        sentence = "";
        try {
            comm = (CommConnection) Connector.open("comm:com0;baudrate=9600;blocking=on");
            is = comm.openInputStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mainLoop() {
        try {
            System.out.println("mainLoop SerialReader");
            input = is.read(); //read data from COM port
            
            System.out.println((char) input);
            
            if (input != 13 && input != 10 && input != -1) { //catch CR, LF or end of stream
                sentence = sentence + (char)input; //collect all letters (bytes) until CR, LF or end of stream is reached            
            } else {
                if (sentence.length() > 6) { //to have non-empty string
                    int senStart = sentence.indexOf("$"); //NMEA sentence start
                    int senStop = sentence.indexOf("*"); //NMEA sentence stop, discard hash code
                    sentence = sentence.substring(senStart-1, senStop); //copy (trim) only real NMEA sentence with leading '$'
                    
                    iMletMain.tc65GPS.parseNMEA(sentence);
                }
                sentence = ""; //clear sentence for new data
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void afterMainLoop() {
        try {
            is.close();
            comm.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
        
}
