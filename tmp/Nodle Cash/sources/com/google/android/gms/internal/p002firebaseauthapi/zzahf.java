package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzahf;
import com.google.android.gms.internal.p002firebaseauthapi.zzahh;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahf  reason: invalid package */
public abstract class zzahf<MessageType extends zzahf<MessageType, BuilderType>, BuilderType extends zzahh<MessageType, BuilderType>> implements zzakp {
    protected int zza = 0;

    public final byte[] a_() {
        try {
            byte[] bArr = new byte[zzl()];
            zzaim zzb = zzaim.zzb(bArr);
            zza(zzb);
            zzb.zzb();
            return bArr;
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a byte array threw an IOException (should never happen)."), e3);
        }
    }

    public int zza(zzalh zzalh) {
        int zzi = zzi();
        if (zzi != -1) {
            return zzi;
        }
        int zza2 = zzalh.zza(this);
        zzb(zza2);
        return zza2;
    }

    public void zzb(int i3) {
        throw new UnsupportedOperationException();
    }

    public int zzi() {
        throw new UnsupportedOperationException();
    }

    public final zzaho zzj() {
        try {
            zzahx zzc = zzaho.zzc(zzl());
            zza(zzc.zzb());
            return zzc.zza();
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a ByteString threw an IOException (should never happen)."), e3);
        }
    }

    public final void zza(OutputStream outputStream) throws IOException {
        zzaim zza2 = zzaim.zza(outputStream, zzaim.zzf(zzl()));
        zza(zza2);
        zza2.zzc();
    }
}
