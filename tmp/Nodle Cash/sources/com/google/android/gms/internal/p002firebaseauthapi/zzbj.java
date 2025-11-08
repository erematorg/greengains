package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zztx;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbj  reason: invalid package */
public final class zzbj implements zzce {
    private final OutputStream zza;

    private zzbj(OutputStream outputStream) {
        this.zza = outputStream;
    }

    public static zzce zza(OutputStream outputStream) {
        return new zzbj(outputStream);
    }

    public final void zza(zztx zztx) throws IOException {
        try {
            ((zztx) ((zzaje) ((zztx.zza) zztx.zzn()).zza().zzf())).zza(this.zza);
        } finally {
            this.zza.close();
        }
    }

    public final void zza(zzvg zzvg) throws IOException {
        try {
            zzvg.zza(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
