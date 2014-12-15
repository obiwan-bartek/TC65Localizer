/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.tc65localizer.classes;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class StrSplit {

    public String[] split(char separator, String sourceString) {
        String dataString;
        String[] arrayString;
        Vector tokenVector;
        String token, tokenTrim;
        int indexA = 0;
        int indexB = 0;
                
        dataString = sourceString.trim(); // trim on both ends
      
        if (dataString.length() == 0) {
            arrayString = new String[0];
            return (arrayString);
        } // if string is empty return empty string array
        
        tokenVector = new Vector();
               
        while (true) {
            indexB = dataString.indexOf(separator, indexA);
            if (indexB == -1) {
                token = dataString.substring(indexA); // extract from last seperator to end of dataString
                tokenVector.addElement(token);
                break;
            }
            token = dataString.substring(indexA, indexB); // extract string from between indexes
            tokenTrim = token.trim(); // trim it if it has spaces
            tokenVector.addElement(tokenTrim);
            indexA = indexB + 1; // set start to last end + 1 for next loop
        }        
        arrayString = new String[tokenVector.size()]; // make array with as many rows as there is substrings
        for (int i = 0; i < arrayString.length; i++) {
            arrayString[i] = (String) (tokenVector.elementAt(i));
        }
        return arrayString;
    }
}
