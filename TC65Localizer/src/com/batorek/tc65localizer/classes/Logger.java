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
public class Logger {

    private int loggingLevel = 3;
    public boolean L_DEBUG;
    public boolean L_VERBOSE;
    public boolean L_WARNING;

    public Logger() {
        setLogFields();
    }
    
    private void setLogFields() {
        L_DEBUG = (loggingLevel >= 3);
        L_VERBOSE = (loggingLevel >= 2);
        L_WARNING = (loggingLevel >= 1);
    }
    
    public int getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(int loggingLevel) {
        this.loggingLevel = loggingLevel;
        setLogFields();
    }
    

    /**
     *
     *log string
     */
    public void log(String str) {
        synchronized (System.out) {
            System.out.println(Thread.currentThread().getName() + " : " + str);
        }
    }

    /**
     *
     *log exception
     */
    public void log(String str, Exception ex) {
        log(str + " ex : " + ex.getClass() + " : " + ex.getMessage());

        Thread.yield();
    }    
}
