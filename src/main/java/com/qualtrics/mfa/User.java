package com.qualtrics.mfa;

/**
 * Created by cgagnon on 6/8/17.
 */
public class User {

    String username;
    String password;
    boolean MFARequired;
    boolean MFAEnrolled;

    public User(String username, String password, boolean MFARequired, boolean MFAEnrolled) {
        this.username = username;
        this.password = password;
        this.MFARequired = MFARequired;
        this.MFAEnrolled = MFAEnrolled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isMFARequired() {
        return MFARequired;
    }

    public boolean isMFAEnrolled() {
        return MFAEnrolled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMFARequired(boolean MFARequired) {
        this.MFARequired = MFARequired;
    }

    public void setMFAEnrolled(boolean MFAEnrolled) {
        this.MFAEnrolled = MFAEnrolled;
    }

}
