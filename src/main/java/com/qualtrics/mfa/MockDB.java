package com.qualtrics.mfa;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cgagnon on 6/8/17.
 */
@Service
public class MockDB {

    private static Map<String, User> lookupTable = new HashMap<String, User>();


    public static boolean isValidUser(String username){
       return lookupTable.containsKey(username);
    }

    public static boolean requiresMFA(String username){
        User user = lookupTable.get(username);
        return user.isMFARequired();
    }

    public static boolean enrolledInMFA(String username){
        User user = lookupTable.get(username);
        return user.isMFAEnrolled();
    }

    public static User getUser(String username){
        return lookupTable.get(username);
    }

    public static void addUser(String username, String password){
        User user = new User(username, password, false, false);
        lookupTable.put(username, user);
    }

    public static void addUser(String username, String password, boolean MFARequired){
        User user = new User(username, password, MFARequired, false);
        lookupTable.put(username, user);
    }

    public static void addUser(String username, String password, boolean MFARequired, boolean MFAEnrolled){
        User user = new User(username, password, MFARequired, MFAEnrolled);
        lookupTable.put(username, user);
    }

}
