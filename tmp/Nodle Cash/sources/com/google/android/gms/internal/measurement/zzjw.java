package com.google.android.gms.internal.measurement;

import A.a;

final class zzjw extends zzkb {
    private final int zzc;
    private final int zzd;

    public zzjw(byte[] bArr, int i3, int i4) {
        super(bArr);
        zzjs.zza(i3, i3 + i4, bArr.length);
        this.zzc = i3;
        this.zzd = i4;
    }

    public final byte zza(int i3) {
        int zzb = zzb();
        if (((zzb - (i3 + 1)) | i3) >= 0) {
            return this.zzb[this.zzc + i3];
        }
        if (i3 < 0) {
            throw new ArrayIndexOutOfBoundsException(a.k("Index < 0: ", i3));
        }
        throw new ArrayIndexOutOfBoundsException(androidx.compose.animation.core.a.r("Index > length: ", i3, ", ", zzb));
    }

    public final byte zzb(int i3) {
        return this.zzb[this.zzc + i3];
    }

    public final int zzc() {
        return this.zzc;
    }

    public final int zzb() {
        return this.zzd;
    }
}
