package com.users;

import java.util.HashMap;

public class Users {
    private static HashMap<String,String> users;

    public static void init() {
        users = new HashMap<String,String>();
        users.put("admin","test123");
        users.put("root","test321");
        users.put("guest","test101");
    }

    public static boolean isValidUser(String user,String password) {
        if(users.get(user) == null){
            return false;
        } else if(users.get(user).equals(password)) {
            return true;
        }
        return false;
    }
}
