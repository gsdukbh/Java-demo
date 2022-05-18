package org.example;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author ${USER}
 * @version TODO
 * @since on  ${DATE}
 */
public class Main {

    public interface CLibrary extends Library {
        CLibrary cLibrary = Native.load("test", CLibrary.class);
        int max(int a, int b);
        void print(String text);
        void  helloM();
        double cosh(double value);
    }

    public static void main(String[] args) {
        CLibrary.cLibrary.helloM();
        System.out.println(CLibrary.cLibrary.max(1,6));
        System.out.println(CLibrary.cLibrary.cosh(2.0));
        CLibrary.cLibrary.print("google ");
    }
}