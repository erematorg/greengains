package com.google.gson.internal;

import A.a;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class PreJava9DateFormatProvider {
    private PreJava9DateFormatProvider() {
    }

    private static String getDatePartOfDateTimePattern(int i3) {
        if (i3 == 0) {
            return "EEEE, MMMM d, yyyy";
        }
        if (i3 == 1) {
            return "MMMM d, yyyy";
        }
        if (i3 == 2) {
            return "MMM d, yyyy";
        }
        if (i3 == 3) {
            return "M/d/yy";
        }
        throw new IllegalArgumentException(a.k("Unknown DateFormat style: ", i3));
    }

    private static String getTimePartOfDateTimePattern(int i3) {
        if (i3 == 0 || i3 == 1) {
            return "h:mm:ss a z";
        }
        if (i3 == 2) {
            return "h:mm:ss a";
        }
        if (i3 == 3) {
            return "h:mm a";
        }
        throw new IllegalArgumentException(a.k("Unknown DateFormat style: ", i3));
    }

    public static DateFormat getUsDateTimeFormat(int i3, int i4) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(i3) + StringUtils.SPACE + getTimePartOfDateTimePattern(i4), Locale.US);
    }
}
