package com.google.android.recaptcha.internal;

final class zzkp implements zzkb {
    private final zzke zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    public zzkp(zzke zzke, String str, Object[] objArr) {
        this.zza = zzke;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        char c3 = charAt & 8191;
        int i3 = 1;
        int i4 = 13;
        while (true) {
            int i5 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 >= 55296) {
                c3 |= (charAt2 & 8191) << i4;
                i4 += 13;
                i3 = i5;
            } else {
                this.zzd = c3 | (charAt2 << i4);
                return;
            }
        }
    }

    public final zzke zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    public final int zzc() {
        int i3 = this.zzd;
        if ((i3 & 1) != 0) {
            return 1;
        }
        return (i3 & 4) == 4 ? 3 : 2;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final Object[] zze() {
        return this.zzc;
    }
}
