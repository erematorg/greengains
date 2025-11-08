package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzew  reason: invalid package */
public final class zzew implements zzbh {
    private static final byte[] zza = new byte[0];
    private static final Set<String> zzb;
    private final String zzc;
    private final zzci zzd;
    private final zzbh zze;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzb = Collections.unmodifiableSet(hashSet);
    }

    @Deprecated
    private zzew(zzvc zzvc, zzbh zzbh) throws GeneralSecurityException {
        if (zzb.contains(zzvc.zzf())) {
            this.zzc = zzvc.zzf();
            this.zzd = zzcp.zza(((zzvc) ((zzaje) zzvc.zza(zzvc).zza(zzvs.RAW).zzf())).a_());
            this.zze = zzbh;
            return;
        }
        throw new IllegalArgumentException(a.l("Unsupported DEK key type: ", zzvc.zzf(), ". Only Tink AEAD key types are supported."));
    }

    public static zzbh zza(zzcw zzcw, zzbh zzbh) throws GeneralSecurityException {
        try {
            return new zzew(zzvc.zza(zzcp.zza((zzci) zzcw), zzaiq.zza()), zzbh);
        } catch (zzajk e3) {
            throw new GeneralSecurityException(e3);
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzbu zza2 = zzni.zza().zza(this.zzd, (Integer) null);
        byte[] zzb2 = this.zze.zzb(((zzou) zzns.zza().zza(zza2, zzou.class, zzbr.zza())).zzd().zzf(), zza);
        byte[] zzb3 = ((zzbh) zznp.zza().zza(zza2, zzbh.class)).zzb(bArr, bArr2);
        return ByteBuffer.allocate(zzb2.length + 4 + zzb3.length).putInt(zzb2.length).put(zzb2).put(zzb3).array();
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i3 = wrap.getInt();
            if (i3 <= 0 || i3 > bArr.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] bArr3 = new byte[i3];
            wrap.get(bArr3, 0, i3);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            return ((zzbh) zznp.zza().zza(zzns.zza().zza(zzou.zza(this.zzc, zzaho.zza(this.zze.zza(bArr3, zza)), zzuw.zzb.SYMMETRIC, zzvs.RAW, (Integer) null), zzbr.zza()), zzbh.class)).zza(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e3) {
            throw new GeneralSecurityException("invalid ciphertext", e3);
        }
    }
}
