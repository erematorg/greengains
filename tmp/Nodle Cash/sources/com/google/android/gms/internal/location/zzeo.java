package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;
import org.komputing.kbip44.BIP44Kt;

public final class zzeo {
    private static final SimpleDateFormat zza;
    private static final SimpleDateFormat zzb;
    @GuardedBy("sharedStringBuilder")
    private static final StringBuilder zzc = new StringBuilder(33);

    static {
        Locale locale = Locale.ROOT;
        zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", locale);
        zzb = new SimpleDateFormat("MM-dd HH:mm:ss", locale);
    }

    public static String zza(long j2) {
        return j2 >= 0 ? zza.format(new Date(j2)) : Long.toString(j2);
    }

    public static String zzb(long j2) {
        String sb;
        StringBuilder sb2 = zzc;
        synchronized (sb2) {
            sb2.setLength(0);
            zzc(j2, sb2);
            sb = sb2.toString();
        }
        return sb;
    }

    public static StringBuilder zzc(long j2, StringBuilder sb) {
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i3 == 0) {
            sb.append("0s");
            return sb;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z2 = false;
        if (i3 < 0) {
            sb.append("-");
            if (j2 != Long.MIN_VALUE) {
                j2 = -j2;
            } else {
                j2 = Long.MAX_VALUE;
                z2 = true;
            }
        }
        if (j2 >= 86400000) {
            sb.append(j2 / 86400000);
            sb.append("d");
            j2 %= 86400000;
        }
        if (true == z2) {
            j2 = 25975808;
        }
        if (j2 >= DateUtils.MILLIS_PER_HOUR) {
            sb.append(j2 / DateUtils.MILLIS_PER_HOUR);
            sb.append("h");
            j2 %= DateUtils.MILLIS_PER_HOUR;
        }
        if (j2 >= 60000) {
            sb.append(j2 / 60000);
            sb.append(BIP44Kt.BIP44_PREFIX);
            j2 %= 60000;
        }
        if (j2 >= 1000) {
            sb.append(j2 / 1000);
            sb.append("s");
            j2 %= 1000;
        }
        if (j2 > 0) {
            sb.append(j2);
            sb.append("ms");
        }
        return sb;
    }
}
