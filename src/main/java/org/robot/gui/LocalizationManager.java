package org.robot.gui;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private static Locale curLocale = new Locale("en");
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("msg", curLocale);

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

    public static void setLocale(Locale locale){
        curLocale = locale;
        resourceBundle = ResourceBundle.getBundle("msg", curLocale);
    }

    public static Locale getCurLocale(){
        return curLocale;
    }

}
