package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzane  reason: invalid package */
public final class zzane {
    private static final zzamd zza = ((zzamd) ((zzaje) zzamd.zzc().zza(-62135596800L).zza(0).zzf()));
    private static final zzamd zzb = ((zzamd) ((zzaje) zzamd.zzc().zza(253402300799L).zza(999999999).zzf()));
    private static final zzamd zzc = ((zzamd) ((zzaje) zzamd.zzc().zza(0).zza(0).zzf()));
    private static final ThreadLocal<SimpleDateFormat> zzd = new zzang();
    @Nullable
    private static final Method zze = zzc("now");
    @Nullable
    private static final Method zzf = zzc("getEpochSecond");
    @Nullable
    private static final Method zzg = zzc("getNano");

    private static boolean zza(long j2) {
        return j2 >= -62135596800L && j2 <= 253402300799L;
    }

    private static long zzb(String str) throws ParseException {
        int indexOf = str.indexOf(58);
        if (indexOf != -1) {
            try {
                return ((Long.parseLong(str.substring(0, indexOf)) * 60) + Long.parseLong(str.substring(indexOf + 1))) * 60;
            } catch (NumberFormatException e3) {
                ParseException parseException = new ParseException("Invalid offset value: ".concat(str), 0);
                parseException.initCause(e3);
                throw parseException;
            }
        } else {
            throw new ParseException("Invalid offset value: ".concat(str), 0);
        }
    }

    @Nullable
    private static Method zzc(String str) {
        try {
            return Class.forName("java.time.Instant").getMethod(str, (Class[]) null);
        } catch (Exception unused) {
            return null;
        }
    }

    public static long zza(zzamd zzamd) {
        return zzb(zzamd).zzb();
    }

    public static zzamd zza(String str) throws ParseException {
        String str2;
        int i3;
        int indexOf = str.indexOf(84);
        if (indexOf != -1) {
            int indexOf2 = str.indexOf(90, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = str.indexOf(43, indexOf);
            }
            if (indexOf2 == -1) {
                indexOf2 = str.indexOf(45, indexOf);
            }
            if (indexOf2 != -1) {
                String substring = str.substring(0, indexOf2);
                int indexOf3 = substring.indexOf(46);
                if (indexOf3 != -1) {
                    String substring2 = substring.substring(0, indexOf3);
                    str2 = substring.substring(indexOf3 + 1);
                    substring = substring2;
                } else {
                    str2 = "";
                }
                long time = zzd.get().parse(substring).getTime() / 1000;
                if (str2.isEmpty()) {
                    i3 = 0;
                } else {
                    i3 = 0;
                    for (int i4 = 0; i4 < 9; i4++) {
                        i3 *= 10;
                        if (i4 < str2.length()) {
                            if (str2.charAt(i4) < '0' || str2.charAt(i4) > '9') {
                                throw new ParseException("Invalid nanoseconds.", 0);
                            }
                            i3 = (str2.charAt(i4) - '0') + i3;
                        }
                    }
                }
                if (str.charAt(indexOf2) != 'Z') {
                    long zzb2 = zzb(str.substring(indexOf2 + 1));
                    time = str.charAt(indexOf2) == '+' ? time - zzb2 : time + zzb2;
                } else if (str.length() != indexOf2 + 1) {
                    throw new ParseException(a.l("Failed to parse timestamp: invalid trailing data \"", str.substring(indexOf2), "\""), 0);
                }
                try {
                    if (zza(time)) {
                        if (i3 <= -1000000000 || i3 >= 1000000000) {
                            time = zzbf.zza(time, (long) (i3 / 1000000000));
                            i3 %= 1000000000;
                        }
                        if (i3 < 0) {
                            i3 += 1000000000;
                            time = zzbf.zzb(time, 1);
                        }
                        return zzb((zzamd) ((zzaje) zzamd.zzc().zza(time).zza(i3).zzf()));
                    }
                    throw new IllegalArgumentException("Timestamp is not valid. Input seconds is too large. Seconds (" + time + ") must be in range [-62,135,596,800, +253,402,300,799]. ");
                } catch (IllegalArgumentException e3) {
                    ParseException parseException = new ParseException(a.l("Failed to parse timestamp ", str, " Timestamp is out of range."), 0);
                    parseException.initCause(e3);
                    throw parseException;
                }
            } else {
                throw new ParseException("Failed to parse timestamp: missing valid timezone offset.", 0);
            }
        } else {
            throw new ParseException(a.l("Failed to parse timestamp: invalid timestamp \"", str, "\""), 0);
        }
    }

    private static zzamd zzb(zzamd zzamd) {
        long zzb2 = zzamd.zzb();
        int zza2 = zzamd.zza();
        if (zza(zzb2) && zza2 >= 0 && zza2 < 1000000000) {
            return zzamd;
        }
        throw new IllegalArgumentException("Timestamp is not valid. See proto definition for valid values. Seconds (" + zzb2 + ") must be in range [-62,135,596,800, +253,402,300,799]. Nanos (" + zza2 + ") must be in range [0, +999,999,999].");
    }

    public static /* synthetic */ SimpleDateFormat zza() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        simpleDateFormat.setCalendar(gregorianCalendar);
        return simpleDateFormat;
    }
}
