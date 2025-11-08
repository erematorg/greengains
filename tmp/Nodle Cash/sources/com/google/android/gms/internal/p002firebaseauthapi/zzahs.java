package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahs  reason: invalid package */
final class zzahs extends zzaib {
    private final int zzc;
    private final int zzd;

    public zzahs(byte[] bArr, int i3, int i4) {
        super(bArr);
        zzaho.zza(i3, i3 + i4, bArr.length);
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

    public final int zzg() {
        return this.zzc;
    }

    public final int zzb() {
        return this.zzd;
    }

    public final void zza(byte[] bArr, int i3, int i4, int i5) {
        System.arraycopy(this.zzb, zzg(), bArr, 0, i5);
    }
}
