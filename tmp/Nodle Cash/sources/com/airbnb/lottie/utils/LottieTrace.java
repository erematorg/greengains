package com.airbnb.lottie.utils;

import A.a;
import androidx.core.os.TraceCompat;
import com.reown.foundation.util.jwt.JwtUtilsKt;

public class LottieTrace {
    private static final int MAX_DEPTH = 5;
    private int depthPastMaxDepth = 0;
    private final String[] sections = new String[5];
    private final long[] startTimeNs = new long[5];
    private int traceDepth = 0;

    public void beginSection(String str) {
        int i3 = this.traceDepth;
        if (i3 == 5) {
            this.depthPastMaxDepth++;
            return;
        }
        this.sections[i3] = str;
        this.startTimeNs[i3] = System.nanoTime();
        TraceCompat.beginSection(str);
        this.traceDepth++;
    }

    public float endSection(String str) {
        int i3 = this.depthPastMaxDepth;
        if (i3 > 0) {
            this.depthPastMaxDepth = i3 - 1;
            return 0.0f;
        }
        int i4 = this.traceDepth - 1;
        this.traceDepth = i4;
        if (i4 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        } else if (str.equals(this.sections[i4])) {
            TraceCompat.endSection();
            return ((float) (System.nanoTime() - this.startTimeNs[this.traceDepth])) / 1000000.0f;
        } else {
            throw new IllegalStateException(a.n(android.support.v4.media.session.a.w("Unbalanced trace call ", str, ". Expected "), this.sections[this.traceDepth], JwtUtilsKt.JWT_DELIMITER));
        }
    }
}
