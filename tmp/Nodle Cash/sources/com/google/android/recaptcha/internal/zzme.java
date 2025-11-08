package com.google.android.recaptcha.internal;

import com.appsamurai.storyly.exoplayer2.common.C;

public final class zzme {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;

    static {
        zzia zzi = zzib.zzi();
        zzi.zze(-315576000000L);
        zzi.zzd(-999999999);
        zza = (zzib) zzi.zzj();
        zzia zzi2 = zzib.zzi();
        zzi2.zze(315576000000L);
        zzi2.zzd(999999999);
        zzb = (zzib) zzi2.zzj();
        zzia zzi3 = zzib.zzi();
        zzi3.zze(0);
        zzi3.zzd(0);
        zzc = (zzib) zzi3.zzj();
    }

    public static zzib zza(long j2) {
        int i3;
        int i4 = (int) (j2 % C.NANOS_PER_SECOND);
        long j3 = j2 / C.NANOS_PER_SECOND;
        if (i4 <= -1000000000 || i4 >= 1000000000) {
            j3 = zzgb.zza(j3, (long) (i4 / 1000000000));
            i4 %= 1000000000;
        }
        if (j3 > 0 && i4 < 0) {
            i4 += 1000000000;
            j3--;
        }
        if (j3 < 0 && i4 > 0) {
            i4 -= 1000000000;
            j3++;
        }
        zzia zzi = zzib.zzi();
        zzi.zze(j3);
        zzi.zzd(i4);
        zzib zzib = (zzib) zzi.zzj();
        long zzg = zzib.zzg();
        int zzf = zzib.zzf();
        if (zzg >= -315576000000L && zzg <= 315576000000L && ((long) zzf) >= -999999999 && zzf < 1000000000 && ((zzg >= 0 && zzf >= 0) || (i3 <= 0 && zzf <= 0))) {
            return zzib;
        }
        throw new IllegalArgumentException("Duration is not valid. See proto definition for valid values. Seconds (" + zzg + ") must be in range [-315,576,000,000, +315,576,000,000]. Nanos (" + zzf + ") must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds");
    }
}
