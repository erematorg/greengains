package com.google.android.recaptcha.internal;

import A.a;

final class zzgq extends zzgt {
    private final int zzc;

    public zzgq(byte[] bArr, int i3, int i4) {
        super(bArr);
        zzgw.zzk(0, i4, bArr.length);
        this.zzc = i4;
    }

    public final byte zza(int i3) {
        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException;
        int i4 = this.zzc;
        if (((i4 - (i3 + 1)) | i3) >= 0) {
            return this.zza[i3];
        }
        if (i3 < 0) {
            String k2 = a.k("Index < 0: ", i3);
            throw arrayIndexOutOfBoundsException;
        }
        arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(androidx.compose.animation.core.a.r("Index > length: ", i3, ", ", i4));
        throw arrayIndexOutOfBoundsException;
    }

    public final byte zzb(int i3) {
        return this.zza[i3];
    }

    public final int zzc() {
        return 0;
    }

    public final int zzd() {
        return this.zzc;
    }

    public final void zze(byte[] bArr, int i3, int i4, int i5) {
        System.arraycopy(this.zza, 0, bArr, 0, i5);
    }
}
