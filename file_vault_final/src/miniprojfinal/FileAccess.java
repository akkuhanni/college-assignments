
package miniprojfinal;

import java.io.File;

/**
 *
 * @author Akshata Sheth
 */
public class FileAccess {
    private static Object FILEFRAME;
      public static void fileaccess(String d) {
          System.out.println("hello");
         System.out.println(d);
        File scriptFile = new File(d);
       // System.out.println("Current f"
               // + "ile permissions:");
      //  System.out.println("Can Execute? "+scriptFile.canExecute());
        System.out.println("Can Read "+scriptFile.canRead());
        System.out.println("Can Write "+scriptFile.canWrite());
       // scriptFile.setExecutable(true);
        scriptFile.setReadable(true);
        scriptFile.setWritable(false);
        System.out.println("Now file permissions:");
      //  System.out.println("Can Execute? "+scriptFile.canExecute());
        System.out.println("Can Read? "+scriptFile.canRead());
        System.out.println("Can Write? "+scriptFile.canWrite());
       // if(scriptFile.canRead()=="true")
       // {
      //      System.out.println("access permitted for read only");
       // } 
       
        System.out.println("Access Denied");
        //FILEFRAME.java.setvisible(true);
        
        
    }
    
}
