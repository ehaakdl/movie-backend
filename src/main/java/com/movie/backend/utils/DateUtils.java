package com.movie.backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_2 = "yyyyMMdd";

    public static Date parseDate(String dateString, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.parse(dateString);

    }

}
