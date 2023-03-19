package webpage_tools;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintTools {
    private static final String UTF8 = "UTF-8";
    
    public static void print(Object obj) {
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.err.out), true, UTF8));
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void println(Object obj) {
        print(obj);
    }
    
    public static void print(Object... objects) {
        for(Object object : objects) print(object);
    }

    public static String getUTF8() {
        return UTF8;
    }
}
