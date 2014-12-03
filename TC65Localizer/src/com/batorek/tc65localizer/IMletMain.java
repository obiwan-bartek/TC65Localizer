/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import javax.microedition.midlet.*;

/**
 * @author Bartosz Batorek
 */
public class IMletMain extends MIDlet {
    
    public ModuleInitializer moduleInitializer;
    public TC65GPRSSender tc65GPRSSender;
    public TC65GPS tc65GPS;
    public TC65Location tc65Location;
    public TC65Preferences tc65Preferences;
    public TC65SMSSupport tc65SMSSupport;
    public TC65SerialReader tc65serialReader;
    

    public void startApp() {
        moduleInitializer = new ModuleInitializer();
        moduleInitializer.InitializeModule();
        
        if (moduleInitializer.isInitialized()) {
            tc65GPRSSender = new TC65GPRSSender();
            tc65GPS = new TC65GPS();
            tc65Location = new TC65Location();
            tc65Preferences = new TC65Preferences();
            tc65SMSSupport = new TC65SMSSupport();
            tc65serialReader = new TC65SerialReader();
            
        }        
    }
    
    public void pauseApp() {
        
        notifyPaused();
    }
    
    public void destroyApp(boolean unconditional) {
        
        notifyDestroyed();
    }
}
