/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer;

import com.siemens.icm.io.ATCommand;
import com.siemens.icm.io.ATCommandFailedException;
import com.siemens.icm.io.ATCommandListener;

/**
 *
 * @author Administrator
 */
public class ModuleInitializer implements ATCommandListener{
    IMletMain iMletMain; // reference to instance of main class
    
    private boolean initialized = false;
    private ATCommand atc;

    public ModuleInitializer(IMletMain iMletMain) throws ATCommandFailedException {
        this.iMletMain = iMletMain;
        this.atc = new ATCommand(false);
    }    

    private void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public boolean isInitialized() {
        return initialized;
    }    
    
    public void InitializeModule() throws ATCommandFailedException {
        //InitializeAutostart();
        InitializeGPRS();
        setInitialized(true);
    }
    
    private void InitializeAutostart() throws ATCommandFailedException {

        String resp = this.atc.send("AT^SCFG=\"Userware/Autostart/Delay\",\"\",\"100\""); // Autostart conf
        System.out.println("Autostart delay CONF: " + resp);
        
        resp = this.atc.send("AT^SCFG=\"Userware/Autostart/AppName\",\"\",\"a:/TC65Localizer.jad\""); // Autostart conf
        System.out.println("Autostart AppName CONF: " + resp);
        
        resp = this.atc.send("AT^SCFG=\"Userware/Autostart\",\"\",\"1\""); // Autostart conf
        System.out.println("Autostart CONF: " + resp);
    }
    
    private void InitializeGPRS() throws ATCommandFailedException {
        String apn = iMletMain.tc65Preferences.gprsApn;
        String user = iMletMain.tc65Preferences.gprsUser;
        String pass = iMletMain.tc65Preferences.gprsPass;        
        
        String resp = this.atc.send("AT^SJNET=\"gprs\",\"" + apn + "\",\"" + user + "\",\"" + pass + "\",\"\",0\r"); // GPRS conf
        System.out.println("GPRS CONF: " + resp);
        resp = this.atc.send("AT+CGATT=1\r"); // Attach GPRS
        System.out.println("GPRS Attach: " + resp);
    }

    public void ATEvent(String Event) {
        
    }

    public void RINGChanged(boolean SignalState) {
        
    }

    public void DCDChanged(boolean SignalState) {
        
    }

    public void DSRChanged(boolean SignalState) {
        
    }

    public void CONNChanged(boolean SignalState) {
        
    }
    
}
