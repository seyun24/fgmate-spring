package org.siksnaghae.fgmate.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class DateUtil {
    private static final DateTimeFormatter formatYyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String getNowDate(){
        return LocalDateTime.now().format(formatYyyyMMdd);
    }

    public static String getMountAgoDate(){
        return LocalDateTime.now().minus(3, ChronoUnit.MONTHS).format(formatYyyyMMdd);
    }
}
