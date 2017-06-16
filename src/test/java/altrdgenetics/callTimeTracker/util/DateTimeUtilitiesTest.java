/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class DateTimeUtilitiesTest {
    
    /**
     * Test of generateTimeStamp method, of class DateTimeUtilities.
     */
    @Test
    public void testGenerateTimeStamp() {
        System.out.println("generateTimeStamp");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                
        LocalDate date = LocalDate.parse("01/01/2017", formatter);
        int hour = 10;
        int minute = 30;
        int second = 0;
        Timestamp expResult = new Timestamp(1483284600000L);
        Timestamp result = DateTimeUtilities.generateTimeStamp(date, hour, minute, second);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertLongToTime method, of class DateTimeUtilities.
     */
    @Test
    public void testConvertLongToTime() {
        System.out.println("convertLongToTime");
        long millis = 5400000L;
        String expResult = "01:30:00";
        String result = DateTimeUtilities.convertLongToTime(millis);
        assertEquals(expResult, result);
    }
    
}
