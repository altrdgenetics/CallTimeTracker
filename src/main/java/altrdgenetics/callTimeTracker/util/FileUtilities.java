/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.util;

import altrdgenetics.callTimeTracker.Global;
import java.io.File;

/**
 *
 * @author User
 */
public class FileUtilities {
    
    public static void setGlobalDBPath(){
        File f = new File(System.getProperty("java.class.path"));
        File dir = f.getAbsoluteFile().getParentFile();
        String path = dir.toString();
        Global.setDBPath(path);
    }
}
