package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxv  reason: invalid package */
public final class zzxv {
    private final byte[] zza;

    private zzxv(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = new byte[i4];
        this.zza = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i4);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzxv)) {
            return false;
        }
        return Arrays.equals(((zzxv) obj).zza, this.zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return a.l("Bytes(", zzxl.zza(this.zza), ")");
    }

    public final int zza() {
        return this.zza.length;
    }

    public final byte[] zzb() {
        byte[] bArr = this.zza;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static zzxv zza(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            if (length > bArr.length) {
                length = bArr.length;
            }
            return new zzxv(bArr, 0, length);
        }
        throw new NullPointerException("data must be non-null");
    }
}
