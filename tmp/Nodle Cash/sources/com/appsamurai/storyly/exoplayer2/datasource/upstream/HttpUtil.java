package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpUtil {
    private static final Pattern CONTENT_RANGE_WITH_SIZE = Pattern.compile("bytes (?:(?:\\d+-\\d+)|\\*)/(\\d+)");
    private static final Pattern CONTENT_RANGE_WITH_START_AND_END = Pattern.compile("bytes (\\d+)-(\\d+)/(?:\\d+|\\*)");
    private static final String TAG = "HttpUtil";

    private HttpUtil() {
    }

    @Nullable
    public static String buildRangeRequestHeader(long j2, long j3) {
        if (j2 == 0 && j3 == -1) {
            return null;
        }
        StringBuilder u3 = a.u("bytes=", j2, "-");
        if (j3 != -1) {
            u3.append((j2 + j3) - 1);
        }
        return u3.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getContentLength(@androidx.annotation.Nullable java.lang.String r10, @androidx.annotation.Nullable java.lang.String r11) {
        /*
            java.lang.String r0 = "Inconsistent headers ["
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r2 = "]"
            java.lang.String r3 = "HttpUtil"
            if (r1 != 0) goto L_0x0025
            long r4 = java.lang.Long.parseLong(r10)     // Catch:{ NumberFormatException -> 0x0011 }
            goto L_0x0027
        L_0x0011:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "Unexpected Content-Length ["
            r1.<init>(r4)
            r1.append(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r3, r1)
        L_0x0025:
            r4 = -1
        L_0x0027:
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 != 0) goto L_0x009a
            java.util.regex.Pattern r1 = CONTENT_RANGE_WITH_START_AND_END
            java.util.regex.Matcher r1 = r1.matcher(r11)
            boolean r6 = r1.matches()
            if (r6 == 0) goto L_0x009a
            r6 = 2
            java.lang.String r6 = r1.group(r6)     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.Object r6 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r6)     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x0086 }
            long r6 = java.lang.Long.parseLong(r6)     // Catch:{ NumberFormatException -> 0x0086 }
            r8 = 1
            java.lang.String r1 = r1.group(r8)     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.Object r1 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x0086 }
            long r8 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            long r6 = r6 - r8
            r8 = 1
            long r6 = r6 + r8
            r8 = 0
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x0063
            r4 = r6
            goto L_0x009a
        L_0x0063:
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x009a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0086 }
            r1.<init>(r0)     // Catch:{ NumberFormatException -> 0x0086 }
            r1.append(r10)     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.String r10 = "] ["
            r1.append(r10)     // Catch:{ NumberFormatException -> 0x0086 }
            r1.append(r11)     // Catch:{ NumberFormatException -> 0x0086 }
            r1.append(r2)     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.String r10 = r1.toString()     // Catch:{ NumberFormatException -> 0x0086 }
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r10)     // Catch:{ NumberFormatException -> 0x0086 }
            long r4 = java.lang.Math.max(r4, r6)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x009a
        L_0x0086:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "Unexpected Content-Range ["
            r10.<init>(r0)
            r10.append(r11)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r3, r10)
        L_0x009a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.datasource.upstream.HttpUtil.getContentLength(java.lang.String, java.lang.String):long");
    }

    public static long getDocumentSize(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = CONTENT_RANGE_WITH_SIZE.matcher(str);
        if (matcher.matches()) {
            return Long.parseLong((String) Assertions.checkNotNull(matcher.group(1)));
        }
        return -1;
    }
}
