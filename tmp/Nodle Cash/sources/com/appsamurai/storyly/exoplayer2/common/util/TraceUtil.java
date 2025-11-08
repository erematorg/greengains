package com.appsamurai.storyly.exoplayer2.common.util;

import android.os.Trace;
import androidx.annotation.RequiresApi;

public final class TraceUtil {
    private TraceUtil() {
    }

    public static void beginSection(String str) {
        if (Util.SDK_INT >= 18) {
            beginSectionV18(str);
        }
    }

    @RequiresApi(18)
    private static void beginSectionV18(String str) {
        Trace.beginSection(str);
    }

    public static void endSection() {
        if (Util.SDK_INT >= 18) {
            endSectionV18();
        }
    }

    @RequiresApi(18)
    private static void endSectionV18() {
        Trace.endSection();
    }
}
