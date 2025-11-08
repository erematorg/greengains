package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwp  reason: invalid package */
public final class zzwp {
    private ECPrivateKey zza;

    public zzwp(ECPrivateKey eCPrivateKey) {
        this.zza = eCPrivateKey;
    }

    public final byte[] zza(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, int i3, zzwt zzwt) throws GeneralSecurityException {
        byte[] zza2 = zzwh.zza(bArr, zzwr.zza(this.zza, zzwr.zza(this.zza.getParams(), zzwt, bArr)));
        Mac zza3 = zzwv.zzb.zza(str);
        if (i3 <= zza3.getMacLength() * 255) {
            if (bArr2 == null || bArr2.length == 0) {
                zza3.init(new SecretKeySpec(new byte[zza3.getMacLength()], str));
            } else {
                zza3.init(new SecretKeySpec(bArr2, str));
            }
            byte[] bArr4 = new byte[i3];
            zza3.init(new SecretKeySpec(zza3.doFinal(zza2), str));
            byte[] bArr5 = new byte[0];
            int i4 = 1;
            int i5 = 0;
            while (true) {
                zza3.update(bArr5);
                zza3.update(bArr3);
                zza3.update((byte) i4);
                bArr5 = zza3.doFinal();
                if (bArr5.length + i5 < i3) {
                    System.arraycopy(bArr5, 0, bArr4, i5, bArr5.length);
                    i5 += bArr5.length;
                    i4++;
                } else {
                    System.arraycopy(bArr5, 0, bArr4, i5, i3 - i5);
                    return bArr4;
                }
            }
        } else {
            throw new GeneralSecurityException("size too large");
        }
    }
}
