package com.google.android.gms.internal.measurement;

final class zznb implements zzmj {
    private final zzml zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    public zznb(zzml zzml, String str, Object[] objArr) {
        this.zza = zzml;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        char c3 = charAt & 8191;
        int i3 = 13;
        int i4 = 1;
        while (true) {
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 >= 55296) {
                c3 |= (charAt2 & 8191) << i3;
                i3 += 13;
                i4 = i5;
            } else {
                this.zzd = c3 | (charAt2 << i3);
                return;
            }
        }
    }

    public final zzml zza() {
        return this.zza;
    }

    public final zzmw zzb() {
        int i3 = this.zzd;
        return (i3 & 1) != 0 ? zzmw.PROTO2 : (i3 & 4) == 4 ? zzmw.EDITIONS : zzmw.PROTO3;
    }

    public final boolean zzc() {
        return (this.zzd & 2) == 2;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final Object[] zze() {
        return this.zzc;
    }
}
