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
public class ModuleInitializer {
    
    private boolean initialized = false;

    private void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public boolean isInitialized() {
        return initialized;
    }    
    
    public void InitializeModule(){
        
        for (int i = 0; i < 10; i++) {
            System.out.println(String.valueOf(i) + "Main code running...");            
        }
        setInitialized(true);
    }
    
}