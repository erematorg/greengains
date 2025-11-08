package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlb  reason: invalid package */
final class zzlb implements zzli {
    private final String zza;

    public zzlb(String str) {
        this.zza = str;
    }

    public final int zza() throws GeneralSecurityException {
        return Mac.getInstance(this.zza).getMacLength();
    }

    public final byte[] zzb() throws GeneralSecurityException {
        String str = this.zza;
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case 984523022:
                if (str.equals("HmacSha256")) {
                    c3 = 0;
                    break;
                }
                break;
            case 984524074:
                if (str.equals("HmacSha384")) {
                    c3 = 1;
                    break;
                }
                break;
            case 984525777:
                if (str.equals("HmacSha512")) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return zzln.zzf;
            case 1:
                return zzln.zzg;
            case 2:
                return zzln.zzh;
            default:
                throw new GeneralSecurityException("Could not determine HPKE KDF ID");
        }
    }

    private final byte[] zza(byte[] bArr, byte[] bArr2, int i3) throws GeneralSecurityException {
        Mac zza2 = zzwv.zzb.zza(this.zza);
        if (i3 <= zza2.getMacLength() * 255) {
            byte[] bArr3 = new byte[i3];
            zza2.init(new SecretKeySpec(bArr, this.zza));
            byte[] bArr4 = new byte[0];
            int i4 = 1;
            int i5 = 0;
            while (true) {
                zza2.update(bArr4);
                zza2.update(bArr2);
                zza2.update((byte) i4);
                bArr4 = zza2.doFinal();
                if (bArr4.length + i5 < i3) {
                    System.arraycopy(bArr4, 0, bArr3, i5, bArr4.length);
                    i5 += bArr4.length;
                    i4++;
                } else {
                    System.arraycopy(bArr4, 0, bArr3, i5, i3 - i5);
                    return bArr3;
                }
            }
        } else {
            throw new GeneralSecurityException("size too large");
        }
    }

    private final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Mac zza2 = zzwv.zzb.zza(this.zza);
        if (bArr2 == null || bArr2.length == 0) {
            zza2.init(new SecretKeySpec(new byte[zza2.getMacLength()], this.zza));
        } else {
            zza2.init(new SecretKeySpec(bArr2, this.zza));
        }
        return zza2.doFinal(bArr);
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, String str2, byte[] bArr4, int i3) throws GeneralSecurityException {
        return zza(zza(zzln.zza(str, bArr2, bArr4), (byte[]) null), zzln.zza(str2, bArr3, bArr4, i3), i3);
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i3) throws GeneralSecurityException {
        return zza(bArr, zzln.zza(str, bArr2, bArr3, i3), i3);
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) throws GeneralSecurityException {
        return zza(zzln.zza(str, bArr2, bArr3), bArr);
    }
}
