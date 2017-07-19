package com.example.alexander.weatherapp.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class LogUtils {

    private static final String common = "COMMON";
    private static final String logFileName= "ApplicationLog.txt";


    public static void write(Object msg){
        Log.e(common, msg.toString());
    }

    public static void writeLogCache(Context context, String logMessageTag, String logMessage){
        try
        {
            File logFile = new File(context.getCacheDir(),
                    logFileName);
            if (!logFile.exists() ) {
                logFile.createNewFile();
            }

            // Write the message to the log with a timestamp
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.write(String.format("%1s [%2s]:%3s\r\n",
                    getDateTimeStamp(), logMessageTag, logMessage));
            writer.close();

            LogUtils.write(" LOGS: " + logMessage);

        }
        catch (IOException e)
        {
            LogUtils.write(e);
        }
    }

    public static void writeLogCache(Context context, Class cl, String logMessage){
        writeLogCache(context,cl.getCanonicalName(),logMessage);
    }

    public static File getLogCacheFile(Context context){
        return new File(context.getCacheDir(),
                logFileName);
    }

    public static String showLogCacheFile(Context context, int lastLines){
        File logFile = new File(context.getCacheDir(),
                logFileName);
        BufferedReader reader;
        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();
        try {

            reader = new BufferedReader(new FileReader(logFile));

        String line;

        int iterations = 0;
        while ((line = reader.readLine())!=null){
            list.add(line);
        }
        reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = list.size()-1; i >=0 && i>=list.size()-lastLines; i--)
            result.append(list.get(i)).append("\n");



        return result.toString();
    }

    private static String getDateTimeStamp(){
        Date dateNow = Calendar.getInstance().getTime();
        return (DateFormat.getDateTimeInstance
                (DateFormat.SHORT, DateFormat.SHORT, Locale.ENGLISH).format(dateNow));
    }

}
