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
public class TC65SerialReader implements Runnable{
    
    private final Thread tc65SerialReaderThread = new Thread(this, "TC65SerialReaderThread");
    

    public void run() {
        while (true){
            // Main Thread Loop
            
        }
        
    }
    
}