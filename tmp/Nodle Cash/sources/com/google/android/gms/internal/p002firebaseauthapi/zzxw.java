package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxw  reason: invalid package */
public final class zzxw {
    private final zzxv zza;

    private zzxw(zzxv zzxv) {
        this.zza = zzxv;
    }

    public final int zza() {
        return this.zza.zza();
    }

    public static zzxw zza(byte[] bArr, zzcn zzcn) {
        if (zzcn != null) {
            return new zzxw(zzxv.zza(bArr));
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    public static zzxw zza(int i3) {
        return new zzxw(zzxv.zza(zzow.zza(i3)));
    }

    public final byte[] zza(zzcn zzcn) {
        if (zzcn != null) {
            return this.zza.zzb();
        }
        throw new NullPointerException("SecretKeyAccess required");
    }
}
