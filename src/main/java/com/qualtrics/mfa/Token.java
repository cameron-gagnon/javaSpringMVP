package com.qualtrics.mfa;

/**
 * Created by cgagnon on 6/7/17.
 */
public class Token {

    private static String tokenVal;

    public void setTokenVal(String tokenVal){
        this.tokenVal = tokenVal;
    }

    public String getTokenVal(){
        return this.tokenVal;
    }

}
