package com.ripple.vmprovisioning.security;

public class NameCheckUtil {

    public static boolean isTargetNameSameAsLoggedInUser(String loggedInUser, String targetUserName){

        if(loggedInUser == null || loggedInUser.isEmpty()) {
            return false;
        }
        if(targetUserName == null || targetUserName.isEmpty()) {
            return false;
        }
        return loggedInUser.equals(targetUserName);

    }
}
