/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
