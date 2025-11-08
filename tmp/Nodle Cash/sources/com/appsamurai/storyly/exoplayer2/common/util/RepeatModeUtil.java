package com.appsamurai.storyly.exoplayer2.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class RepeatModeUtil {
    public static final int REPEAT_TOGGLE_MODE_ALL = 2;
    public static final int REPEAT_TOGGLE_MODE_NONE = 0;
    public static final int REPEAT_TOGGLE_MODE_ONE = 1;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatToggleModes {
    }

    private RepeatModeUtil() {
    }

    public static int getNextRepeatMode(int i3, int i4) {
        for (int i5 = 1; i5 <= 2; i5++) {
            int i6 = (i3 + i5) % 3;
            if (isRepeatModeEnabled(i6, i4)) {
                return i6;
            }
        }
        return i3;
    }

    public static boolean isRepeatModeEnabled(int i3, int i4) {
        if (i3 == 0) {
            return true;
        }
        if (i3 == 1) {
            return (i4 & 1) != 0;
        }
        if (i3 != 2) {
            return false;
        }
        return (i4 & 2) != 0;
    }
}
