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
public class TC65Preferences {
    public String serverUrl;
    public String gprsApn;
    public String gprsUser;
    public String gprsPass;
    public String serialPort;
    public int serialBaudrate;

    public TC65Preferences() {
        if (!this.loadPreferences()) {
            clearPreferences();
            loadDefaultPreferences();
            savePreferences();
        } 
    }
    
    public boolean loadPreferences() {
        // preferences read from chip flash or nvram
        return false;
    }
    
    public void savePreferences() {
        
    }
    
    public void loadDefaultPreferences() {
        this.serverUrl = "http://54.165.186.60:8080/TC65Server/SaveData.do";
        this.gprsApn = "internet";
        this.gprsUser = "internet";
        this.gprsPass = "internet";
        this.serialPort = "com0";
        this.serialBaudrate = 9600;
    }
    
    public void clearPreferences() {
        
    }    
}
