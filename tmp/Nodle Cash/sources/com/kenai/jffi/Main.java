package com.kenai.jffi;

import java.io.PrintStream;

public class Main {
    public static void main(String[] strArr) {
        try {
            PrintStream printStream = System.out;
            printStream.printf("jffi jar version=%d.%d.%d\n", new Object[]{Integer.valueOf(Foreign.VERSION_MAJOR), Integer.valueOf(Foreign.VERSION_MINOR), Integer.valueOf(Foreign.VERSION_MICRO)});
            Foreign instance = Foreign.getInstance();
            printStream.printf("jffi stub version=%d.%d.%d\n", new Object[]{Integer.valueOf(v(instance, 16)), Integer.valueOf(v(instance, 8)), Integer.valueOf(v(instance, 0))});
            printStream.println("memory fault protection enabled=" + Foreign.isMemoryProtectionEnabled());
            printStream.println("stub arch=" + instance.getArch());
            printStream.printf("JNI version=%#x\n", new Object[]{Integer.valueOf(instance.getJNIVersion())});
        } catch (Throwable th) {
            PrintStream printStream2 = System.err;
            printStream2.println("Error: " + th);
        }
    }

    private static int v(Foreign foreign, int i3) {
        return (foreign.getVersion() >> i3) & 255;
    }
}
