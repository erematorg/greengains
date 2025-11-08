package com.google.android.recaptcha.internal;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nullable;

public final class zzmg {
    public static final zzlj zza;
    public static final zzlj zzb;
    public static final zzlj zzc;
    private static final ThreadLocal zzd = new zzmf();
    @Nullable
    private static final Method zze = zzd("now");
    @Nullable
    private static final Method zzf = zzd("getEpochSecond");
    @Nullable
    private static final Method zzg = zzd("getNano");

    static {
        zzli zzi = zzlj.zzi();
        zzi.zze(-62135596800L);
        zzi.zzd(0);
        zza = (zzlj) zzi.zzj();
        zzli zzi2 = zzlj.zzi();
        zzi2.zze(253402300799L);
        zzi2.zzd(999999999);
        zzb = (zzlj) zzi2.zzj();
        zzli zzi3 = zzlj.zzi();
        zzi3.zze(0);
        zzi3.zzd(0);
        zzc = (zzlj) zzi3.zzj();
    }

    public static zzlj zza(zzlj zzlj) {
        long zzg2 = zzlj.zzg();
        int i3 = (zzg2 > -62135596800L ? 1 : (zzg2 == -62135596800L ? 0 : -1));
        int zzf2 = zzlj.zzf();
        if (i3 >= 0 && zzg2 <= 253402300799L && zzf2 >= 0 && zzf2 < 1000000000) {
            return zzlj;
        }
        throw new IllegalArgumentException("Timestamp is not valid. See proto definition for valid values. Seconds (" + zzg2 + ") must be in range [-62,135,596,800, +253,402,300,799]. Nanos (" + zzf2 + ") must be in range [0, +999,999,999].");
    }

    public static zzlj zzb(long j2) {
        int i3 = (int) ((j2 % 1000) * 1000000);
        long j3 = j2 / 1000;
        if (i3 <= -1000000000 || i3 >= 1000000000) {
            j3 = zzgb.zza(j3, (long) (i3 / 1000000000));
            i3 %= 1000000000;
        }
        if (i3 < 0) {
            i3 += 1000000000;
            j3 = zzgb.zzb(j3, 1);
        }
        zzli zzi = zzlj.zzi();
        zzi.zze(j3);
        zzi.zzd(i3);
        zzlj zzlj = (zzlj) zzi.zzj();
        zza(zzlj);
        return zzlj;
    }

    public static String zzc(zzlj zzlj) {
        zza(zzlj);
        long zzg2 = zzlj.zzg();
        int zzf2 = zzlj.zzf();
        StringBuilder sb = new StringBuilder();
        sb.append(((SimpleDateFormat) zzd.get()).format(new Date(zzg2 * 1000)));
        if (zzf2 != 0) {
            sb.append(JwtUtilsKt.JWT_DELIMITER);
            sb.append(zzf2 % 1000000 == 0 ? String.format(Locale.ENGLISH, "%1$03d", new Object[]{Integer.valueOf(zzf2 / 1000000)}) : zzf2 % 1000 == 0 ? String.format(Locale.ENGLISH, "%1$06d", new Object[]{Integer.valueOf(zzf2 / 1000)}) : String.format(Locale.ENGLISH, "%1$09d", new Object[]{Integer.valueOf(zzf2)}));
        }
        sb.append("Z");
        return sb.toString();
    }

    @Nullable
    private static Method zzd(String str) {
        try {
            return Class.forName("java.time.Instant").getMethod(str, (Class[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
