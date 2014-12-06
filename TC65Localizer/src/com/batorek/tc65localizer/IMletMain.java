/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import com.batorek.tc65localizer.classes.Logger;
import javax.microedition.midlet.*;

/**
 * @author Bartosz Batorek
 */
public class IMletMain extends MIDlet {
    
    public Logger logger;
    
    public ModuleInitializer moduleInitializer;
    public TC65Preferences tc65Preferences;
    public TC65Location tc65Location;
    public TC65GPS tc65GPS;
    public TC65Serial tc65Serial;
    public TC65GPRSSender tc65GPRSSender;
    public TC65SMSSupport tc65SMSSupport;
    
    public void startApp() {
        logger = new Logger();
        
        moduleInitializer = new ModuleInitializer();
        moduleInitializer.InitializeModule();
        
        if (moduleInitializer.isInitialized()) {
            tc65Preferences = new TC65Preferences(); //loading preferences is done in class constructor
            tc65Location = new TC65Location();
            tc65GPS = new TC65GPS(this);
            tc65Serial = new TC65Serial(this);
            tc65GPRSSender = new TC65GPRSSender(this);           
            tc65SMSSupport = new TC65SMSSupport(this);           
            
            tc65Serial.start();
            
            //tc65GPRSSender.start();
            
            //tc65SMSSupport.start();            
        }        
    }
    
    public void pauseApp() {
        
        notifyPaused();
    }
    
    public void destroyApp(boolean unconditional) {
        try {
            tc65Serial.stop();
            
            tc65GPRSSender.stop();
            
            tc65SMSSupport.stop();
        
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        notifyDestroyed();
        
    }
}
