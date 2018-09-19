/*
 *
 * Copyright (c) 2014 Bartosz Bątorek
 * Distributed under the MIT License
 * See LICENSE.txt for further information.
 *
 */
package com.batorek.tc65localizer;

import com.batorek.tc65localizer.classes.TC65Runnable;

/**
 *
 * @author Administrator
 */
public class TC65SMSSupport extends TC65Runnable{

    IMletMain iMletMain; // reference to instance of main class
    
    public TC65SMSSupport(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }

    public void run() {
        Thread.currentThread().setPriority(3); // set priority under normal priority
        
    }

    public void beforeMainLoop() {
        
    }

    public void mainLoop() {
        
    }

    public void afterMainLoop() {
        
    }    
}
