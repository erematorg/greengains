package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwi  reason: invalid package */
public final class zzwi implements zzbp {
    private static final byte[] zza = new byte[0];
    private final ECPrivateKey zzb;
    private final zzwp zzc;
    private final String zzd;
    private final byte[] zze;
    private final zzwt zzf;
    private final zzwj zzg;
    private final byte[] zzh;

    private zzwi(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzwt zzwt, zzwj zzwj, byte[] bArr2) throws GeneralSecurityException {
        this.zzb = eCPrivateKey;
        this.zzc = new zzwp(eCPrivateKey);
        this.zze = bArr;
        this.zzd = str;
        this.zzf = zzwt;
        this.zzg = zzwj;
        this.zzh = bArr2;
    }

    public static zzbp zza(zzju zzju) throws GeneralSecurityException {
        ECPrivateKey zza2 = zzwr.zza(zzwl.zza.zza(zzju.zzc().zzd()), zzmd.zza(zzju.zze().zza(zzbr.zza())));
        byte[] bArr = new byte[0];
        if (zzju.zzc().zzh() != null) {
            bArr = zzju.zzc().zzh().zzb();
        }
        return new zzwi(zza2, bArr, zzwl.zza(zzju.zzc().zze()), zzwl.zzb.zza(zzju.zzc().zzf()), zzwl.zza(zzju.zzc().zzb()), zzju.zzg().zzb());
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final byte[] zzb(byte[] r10, byte[] r11) throws java.security.GeneralSecurityException {
        /*
            r9 = this;
            java.security.interfaces.ECPrivateKey r0 = r9.zzb
            java.security.spec.ECParameterSpec r0 = r0.getParams()
            java.security.spec.EllipticCurve r0 = r0.getCurve()
            com.google.android.gms.internal.firebase-auth-api.zzwt r1 = r9.zzf
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzwr.zza((java.security.spec.EllipticCurve) r0)
            int r1 = r1.ordinal()
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0028
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            int r0 = r0 * r2
            goto L_0x002a
        L_0x001e:
            java.security.GeneralSecurityException r9 = new java.security.GeneralSecurityException
            java.lang.String r10 = "unknown EC point format"
            r9.<init>(r10)
            throw r9
        L_0x0026:
            int r0 = r0 + r3
            goto L_0x002a
        L_0x0028:
            int r0 = r0 * r2
            goto L_0x0026
        L_0x002a:
            int r1 = r10.length
            if (r1 < r0) goto L_0x0057
            r1 = 0
            byte[] r3 = java.util.Arrays.copyOfRange(r10, r1, r0)
            com.google.android.gms.internal.firebase-auth-api.zzwp r2 = r9.zzc
            java.lang.String r4 = r9.zzd
            byte[] r5 = r9.zze
            com.google.android.gms.internal.firebase-auth-api.zzwj r1 = r9.zzg
            int r7 = r1.zza()
            com.google.android.gms.internal.firebase-auth-api.zzwt r8 = r9.zzf
            r6 = r11
            byte[] r11 = r2.zza(r3, r4, r5, r6, r7, r8)
            com.google.android.gms.internal.firebase-auth-api.zzwj r9 = r9.zzg
            com.google.android.gms.internal.firebase-auth-api.zzlx r9 = r9.zza(r11)
            int r11 = r10.length
            byte[] r10 = java.util.Arrays.copyOfRange(r10, r0, r11)
            byte[] r11 = zza
            byte[] r9 = r9.zza(r10, r11)
            return r9
        L_0x0057:
            java.security.GeneralSecurityException r9 = new java.security.GeneralSecurityException
            java.lang.String r10 = "ciphertext too short"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzwi.zzb(byte[], byte[]):byte[]");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzh;
        if (bArr3.length == 0) {
            return zzb(bArr, bArr2);
        }
        if (zzph.zza(bArr3, bArr)) {
            return zzb(Arrays.copyOfRange(bArr, this.zzh.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
    }
}
