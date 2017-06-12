/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author User
 */
public class DateTimeUtilities {
    
    public static Timestamp generateTimeStamp(LocalDate date, LocalTime time) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, date.getYear());
        cal.set(Calendar.MONTH, date.getMonthValue());
        cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        cal.set(Calendar.HOUR_OF_DAY, time.getHour());
        cal.set(Calendar.MINUTE, time.getMinute());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        return new Timestamp(cal.getTimeInMillis());
    }
    
}
