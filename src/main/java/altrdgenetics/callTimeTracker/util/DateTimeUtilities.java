/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
public class DateTimeUtilities {
    
    public static Timestamp generateTimeStamp(LocalDate date, int hour, int minute, int second) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, date.getYear());
        cal.set(Calendar.MONTH, date.getMonthValue());
        cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        
        return new Timestamp(cal.getTimeInMillis());
    }
        
    /**
     * converts MILLISECONDS to [ (HR):(MIN):(SEC) ] format
     *
     * @param millis long
     * @return String of duration
     */
    public static String convertLongToTime(long millis) {
        String duration = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return duration.trim();
    }
    
}
