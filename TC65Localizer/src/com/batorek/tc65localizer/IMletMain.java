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
    
    private ModuleInitializer moduleInitializer;

    public void startApp() {
        moduleInitializer = new ModuleInitializer();
        moduleInitializer.InitializeModule();     
        
    }
    
    public void pauseApp() {
        
        notifyPaused();
    }
    
    public void destroyApp(boolean unconditional) {
        
        notifyDestroyed();
    }
}
