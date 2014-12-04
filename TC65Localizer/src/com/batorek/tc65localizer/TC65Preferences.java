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

    public TC65Preferences() {
        if (!this.loadPreferences()) {
            clearPreferences();
            loadDefaultPreferences();
            savePreferences();
        } 
    }
    
    public boolean loadPreferences() {
        
        return false;
    }
    
    public void savePreferences() {
        
    }
    
    public void loadDefaultPreferences() {
        
    }
    
    public void clearPreferences() {
        
    }
    
}
