/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import com.batorek.tc65localizer.classes.TC65Runnable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.CommConnection;
import javax.microedition.io.Connector;

/**
 *
 * @author Administrator
 */
public class TC65Serial extends TC65Runnable{

    IMletMain iMletMain; // reference to instance of main class
    
    CommConnection comm;
    InputStream is;
    OutputStream os;
    String sentence, sentence2;
    int input;
    
    public TC65Serial(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }
    
    public void beforeMainLoop() {
        sentence = "";
        try {
            comm = (CommConnection) Connector.open("comm:com0;baudrate=9600;blocking=off");
            is = comm.openInputStream();
            os = comm.openOutputStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mainLoop() {
        try {
            //System.out.println("mainLoop SerialReader");
            
            if (comm.getBaudRate() != 9600){
                comm.setBaudRate(9600);
            }
            
            if (is.available() > 0) {
            
                input = is.read(); //read data from COM port
                
                os.write(input); //forward the data to output
                
                //System.out.println((char) input);

                if (input != 13 && input != 10 && input != -1) { //catch CR, LF or end of stream
                    sentence = sentence + (char) input; //collect all letters (bytes) until CR, LF or end of stream is reached            
                } else if (sentence != "") {
                    sentence2 = iMletMain.tc65GPS.validateSentence(sentence); 
                    if (sentence2 != null) { // checks if sentence is valid
                        //System.out.println(sentence);          
                        
                        iMletMain.tc65GPS.parseNMEA(sentence2);
                        //os.write("OK\r\n".getBytes());
                        //System.out.println("OK");
                        iMletMain.tc65Location.printLocationData();
                    }else{
                        //os.write("Fail\r\n".getBytes());
                        //System.out.println("Fail");
                    }
                    sentence = ""; //clear sentence for new data
                    sentence2 = "";
                }

                //Thread.sleep(500);
            
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Throwable ex) {
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
