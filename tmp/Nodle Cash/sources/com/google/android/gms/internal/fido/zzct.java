package com.google.android.gms.internal.fido;

import A.a;

final class zzct extends zzcw {
    private final int zzc;
    private final int zzd;

    public zzct(byte[] bArr, int i3, int i4) {
        super(bArr);
        zzcz.zzj(i3, i3 + i4, bArr.length);
        this.zzc = i3;
        this.zzd = i4;
    }

    public final byte zza(int i3) {
        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException;
        int i4 = this.zzd;
        if (((i4 - (i3 + 1)) | i3) >= 0) {
            return this.zza[this.zzc + i3];
        }
        if (i3 < 0) {
            String k2 = a.k("Index < 0: ", i3);
            throw arrayIndexOutOfBoundsException;
        }
        arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(androidx.compose.animation.core.a.r("Index > length: ", i3, ", ", i4));
        throw arrayIndexOutOfBoundsException;
    }

    public final byte zzb(int i3) {
        return this.zza[this.zzc + i3];
    }

    public final int zzc() {
        return this.zzc;
    }

    public final int zzd() {
        return this.zzd;
    }

    public final void zze(byte[] bArr, int i3, int i4, int i5) {
        System.arraycopy(this.zza, this.zzc, bArr, 0, i5);
    }
}
