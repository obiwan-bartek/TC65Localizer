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
public class TC65GPS {

    IMletMain iMletMain; // reference to instance of main class
    
    public TC65GPS(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }
    
    public void parseNMEA(String sentence) {
        System.out.println(sentence);
    }
    
}
