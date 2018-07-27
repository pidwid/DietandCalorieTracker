package com.example.android.dietandcalorietracker;

import java.util.HashMap;

public class ApplicationSessionStorage {
    private static HashMap<String,String> ApplicationData=new HashMap<String, String>();
    public static void SetSessionData(String Key,String Value){
        ApplicationData.put(Key,Value);
    }

    public static String GetSessionData(String Key){
        if(ApplicationData.containsKey(Key)) {
            return ApplicationData.get(Key);
        }
        return "Absent";
    }
}
