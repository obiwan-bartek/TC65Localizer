/*
 *
 * Copyright (c) 2014 Bartosz Bątorek
 * Distributed under the MIT License
 * See LICENSE.txt for further information.
 *
 */
package com.batorek.tc65localizer.classes;

/**
 *
 * @author Administrator
 */
public abstract class TC65Runnable implements Runnable{
    private boolean interrupted;
    private final Thread thread;

    public TC65Runnable() {      
        this.interrupted = false;
        String className;
        className = this.getClass().getName();
        this.thread = new Thread(this, className);
    }
    
    public boolean isInterrupted() {
        return this.interrupted;
    }
    
    public void start() {
        this.interrupted = false;
        this.thread.start();
    }
    
    public void stop() throws InterruptedException {
        this.interrupted = true;
        thread.interrupt();
        thread.join();
    }

    public void run() {        
        this.beforeMainLoop();        
        while(!this.isInterrupted()){
            this.mainLoop();
        }        
        this.afterMainLoop();
    }
    
    /**
     *Before main loop -  runs once.
     */
    public abstract void beforeMainLoop();
    
    /**
     *Main method looped in run().
     */
    public abstract void mainLoop();
    
    /**
     *After main loop - runs once.
     */
    public abstract void afterMainLoop();    
    
}
