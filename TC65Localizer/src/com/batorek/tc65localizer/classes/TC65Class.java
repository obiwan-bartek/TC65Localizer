/*
 *
 * Copyright (c) 2014 Bartosz Bątorek
 * Distributed under the MIT License
 * See LICENSE.txt for further information.
 *
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
