/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer.classes;

import com.batorek.tc65localizer.IMletMain;

/**
 *
 * @author Administrator
 */
public abstract class TC65Class {
    IMletMain iMletMain; // reference to instance of main class

//    public TC65Class() {
//    }    
    
    public TC65Class(IMletMain iMletMain) {
        this.iMletMain = iMletMain;
    }    
    
}
