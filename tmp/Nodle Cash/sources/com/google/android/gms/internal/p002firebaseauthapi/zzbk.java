package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbk  reason: invalid package */
public final class zzbk implements zzcb {
    private final InputStream zza;

    private zzbk(InputStream inputStream) {
        this.zza = inputStream;
    }

    public static zzcb zza(byte[] bArr) {
        return new zzbk(new ByteArrayInputStream(bArr));
    }

    public final zzvg zzb() throws IOException {
        try {
            return zzvg.zza(this.zza, zzaiq.zza());
        } finally {
            this.zza.close();
        }
    }

    public final zztx zza() throws IOException {
        try {
            return zztx.zza(this.zza, zzaiq.zza());
        } finally {
            this.zza.close();
        }
    }
}
