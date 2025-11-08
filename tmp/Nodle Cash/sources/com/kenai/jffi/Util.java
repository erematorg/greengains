package com.kenai.jffi;

import java.util.Locale;

public final class Util {
    private Util() {
    }

    public static boolean equalsIgnoreCase(String str, String str2, Locale locale) {
        return str.equalsIgnoreCase(str2) || str.toUpperCase(locale).equals(str2.toUpperCase(locale)) || str.toLowerCase(locale).equals(str2.toLowerCase(locale));
    }

    public static int ffi_align(int i3, int i4) {
        return ((i3 - 1) | (i4 - 1)) + 1;
    }

    public static boolean startsWithIgnoreCase(String str, String str2, Locale locale) {
        return str.startsWith(str2) || str.toUpperCase(locale).startsWith(str2.toUpperCase(locale)) || str.toLowerCase(locale).startsWith(str2.toLowerCase(locale));
    }
}
