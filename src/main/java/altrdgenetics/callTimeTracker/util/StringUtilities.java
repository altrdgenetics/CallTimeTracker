/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.util;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
public class StringUtilities {
    
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
