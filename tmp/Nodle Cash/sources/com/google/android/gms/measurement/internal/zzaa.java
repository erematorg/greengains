package com.google.android.gms.measurement.internal;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

abstract class zzaa {
    String zza;
    int zzb;
    Boolean zzc;
    Boolean zzd;
    Long zze;
    Long zzf;

    public zzaa(String str, int i3) {
        this.zza = str;
        this.zzb = i3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        if (r3 != null) goto L_0x0082;
     */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r8, com.google.android.gms.internal.measurement.zzfn.zzd r9, double r10) {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            boolean r0 = r9.zzh()
            r1 = 0
            if (r0 == 0) goto L_0x0114
            com.google.android.gms.internal.measurement.zzfn$zzd$zzb r0 = r9.zza()
            com.google.android.gms.internal.measurement.zzfn$zzd$zzb r2 = com.google.android.gms.internal.measurement.zzfn.zzd.zzb.UNKNOWN_COMPARISON_TYPE
            if (r0 != r2) goto L_0x0014
            goto L_0x0114
        L_0x0014:
            com.google.android.gms.internal.measurement.zzfn$zzd$zzb r0 = r9.zza()
            com.google.android.gms.internal.measurement.zzfn$zzd$zzb r2 = com.google.android.gms.internal.measurement.zzfn.zzd.zzb.BETWEEN
            if (r0 != r2) goto L_0x0029
            boolean r0 = r9.zzl()
            if (r0 == 0) goto L_0x0028
            boolean r0 = r9.zzk()
            if (r0 != 0) goto L_0x0030
        L_0x0028:
            return r1
        L_0x0029:
            boolean r0 = r9.zzi()
            if (r0 != 0) goto L_0x0030
            return r1
        L_0x0030:
            com.google.android.gms.internal.measurement.zzfn$zzd$zzb r0 = r9.zza()
            com.google.android.gms.internal.measurement.zzfn$zzd$zzb r3 = r9.zza()
            if (r3 != r2) goto L_0x0065
            java.lang.String r3 = r9.zzf()
            boolean r3 = com.google.android.gms.measurement.internal.zzol.zzb((java.lang.String) r3)
            if (r3 == 0) goto L_0x0064
            java.lang.String r3 = r9.zze()
            boolean r3 = com.google.android.gms.measurement.internal.zzol.zzb((java.lang.String) r3)
            if (r3 != 0) goto L_0x004f
            goto L_0x0064
        L_0x004f:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0064 }
            java.lang.String r4 = r9.zzf()     // Catch:{ NumberFormatException -> 0x0064 }
            r3.<init>(r4)     // Catch:{ NumberFormatException -> 0x0064 }
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0064 }
            java.lang.String r9 = r9.zze()     // Catch:{ NumberFormatException -> 0x0064 }
            r4.<init>(r9)     // Catch:{ NumberFormatException -> 0x0064 }
            r9 = r3
            r3 = r1
            goto L_0x007b
        L_0x0064:
            return r1
        L_0x0065:
            java.lang.String r3 = r9.zzd()
            boolean r3 = com.google.android.gms.measurement.internal.zzol.zzb((java.lang.String) r3)
            if (r3 != 0) goto L_0x0070
            return r1
        L_0x0070:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0114 }
            java.lang.String r9 = r9.zzd()     // Catch:{ NumberFormatException -> 0x0114 }
            r3.<init>(r9)     // Catch:{ NumberFormatException -> 0x0114 }
            r9 = r1
            r4 = r9
        L_0x007b:
            if (r0 != r2) goto L_0x0080
            if (r9 != 0) goto L_0x0082
            return r1
        L_0x0080:
            if (r3 == 0) goto L_0x0114
        L_0x0082:
            int[] r2 = com.google.android.gms.measurement.internal.zzu.zzb
            int r0 = r0.ordinal()
            r0 = r2[r0]
            r2 = 0
            r5 = 1
            if (r0 == r5) goto L_0x0106
            r6 = 2
            if (r0 == r6) goto L_0x00f8
            r7 = 3
            if (r0 == r7) goto L_0x00ae
            r10 = 4
            if (r0 == r10) goto L_0x0099
            goto L_0x0114
        L_0x0099:
            if (r9 != 0) goto L_0x009c
            return r1
        L_0x009c:
            int r9 = r8.compareTo(r9)
            if (r9 < 0) goto L_0x00a9
            int r8 = r8.compareTo(r4)
            if (r8 > 0) goto L_0x00a9
            r2 = r5
        L_0x00a9:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
            return r8
        L_0x00ae:
            if (r3 == 0) goto L_0x0114
            r0 = 0
            int r9 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r9 == 0) goto L_0x00ec
            java.math.BigDecimal r9 = new java.math.BigDecimal
            r9.<init>(r10)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r0.<init>(r6)
            java.math.BigDecimal r9 = r9.multiply(r0)
            java.math.BigDecimal r9 = r3.subtract(r9)
            int r9 = r8.compareTo(r9)
            if (r9 <= 0) goto L_0x00e7
            java.math.BigDecimal r9 = new java.math.BigDecimal
            r9.<init>(r10)
            java.math.BigDecimal r10 = new java.math.BigDecimal
            r10.<init>(r6)
            java.math.BigDecimal r9 = r9.multiply(r10)
            java.math.BigDecimal r9 = r3.add(r9)
            int r8 = r8.compareTo(r9)
            if (r8 >= 0) goto L_0x00e7
            r2 = r5
        L_0x00e7:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
            return r8
        L_0x00ec:
            int r8 = r8.compareTo(r3)
            if (r8 != 0) goto L_0x00f3
            r2 = r5
        L_0x00f3:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
            return r8
        L_0x00f8:
            if (r3 == 0) goto L_0x0114
            int r8 = r8.compareTo(r3)
            if (r8 <= 0) goto L_0x0101
            r2 = r5
        L_0x0101:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
            return r8
        L_0x0106:
            if (r3 == 0) goto L_0x0114
            int r8 = r8.compareTo(r3)
            if (r8 >= 0) goto L_0x010f
            r2 = r5
        L_0x010f:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
            return r8
        L_0x0114:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaa.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzfn$zzd, double):java.lang.Boolean");
    }

    public abstract int zza();

    public abstract boolean zzb();

    public abstract boolean zzc();

    @VisibleForTesting
    public static Boolean zza(String str, zzfn.zzf zzf2, zzgi zzgi) {
        String zze2;
        List<String> list;
        Preconditions.checkNotNull(zzf2);
        if (str == null || !zzf2.zzj() || zzf2.zzb() == zzfn.zzf.zzb.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        zzfn.zzf.zzb zzb2 = zzf2.zzb();
        zzfn.zzf.zzb zzb3 = zzfn.zzf.zzb.IN_LIST;
        if (zzb2 == zzb3) {
            if (zzf2.zza() == 0) {
                return null;
            }
        } else if (!zzf2.zzi()) {
            return null;
        }
        zzfn.zzf.zzb zzb4 = zzf2.zzb();
        boolean zzg = zzf2.zzg();
        if (zzg || zzb4 == zzfn.zzf.zzb.REGEXP || zzb4 == zzb3) {
            zze2 = zzf2.zze();
        } else {
            zze2 = zzf2.zze().toUpperCase(Locale.ENGLISH);
        }
        String str2 = zze2;
        if (zzf2.zza() == 0) {
            list = null;
        } else {
            List<String> zzf3 = zzf2.zzf();
            if (!zzg) {
                ArrayList arrayList = new ArrayList(zzf3.size());
                for (String upperCase : zzf3) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                zzf3 = Collections.unmodifiableList(arrayList);
            }
            list = zzf3;
        }
        return zza(str, zzb4, zzg, str2, list, zzb4 == zzfn.zzf.zzb.REGEXP ? str2 : null, zzgi);
    }

    private static Boolean zza(String str, zzfn.zzf.zzb zzb2, boolean z2, String str2, List<String> list, String str3, zzgi zzgi) {
        if (str == null) {
            return null;
        }
        if (zzb2 == zzfn.zzf.zzb.IN_LIST) {
            if (list == null || list.isEmpty()) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z2 && zzb2 != zzfn.zzf.zzb.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zzu.zza[zzb2.ordinal()]) {
            case 1:
                if (str3 == null) {
                    return null;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z2 ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    if (zzgi != null) {
                        zzgi.zzu().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                if (list == null) {
                    return null;
                }
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    public static Boolean zza(double d2, zzfn.zzd zzd2) {
        try {
            return zza(new BigDecimal(d2), zzd2, Math.ulp(d2));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Boolean zza(long j2, zzfn.zzd zzd2) {
        try {
            return zza(new BigDecimal(j2), zzd2, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Boolean zza(String str, zzfn.zzd zzd2) {
        if (!zzol.zzb(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzd2, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    public static Boolean zza(Boolean bool, boolean z2) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z2);
    }
}
