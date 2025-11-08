package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

public final class zzah {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzag zzc;

    public final zzah zza(Object obj, Object obj2) {
        int i3 = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i4 = i3 + i3;
        if (i4 > length) {
            this.zza = Arrays.copyOf(objArr, zzaa.zza(length, i4));
        }
        zzw.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i5 = this.zzb;
        int i6 = i5 + i5;
        objArr2[i6] = obj;
        objArr2[i6 + 1] = obj2;
        this.zzb = i5 + 1;
        return this;
    }

    public final zzai zzb() {
        zzag zzag = this.zzc;
        if (zzag == null) {
            zzaq zzg = zzaq.zzg(this.zzb, this.zza, this);
            zzag zzag2 = this.zzc;
            if (zzag2 == null) {
                return zzg;
            }
            throw zzag2.zza();
        }
        throw zzag.zza();
    }
}
