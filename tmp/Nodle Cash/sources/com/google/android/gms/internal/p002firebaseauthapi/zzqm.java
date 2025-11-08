package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import org.web3j.ens.contracts.generated.OffchainResolverContract;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqm  reason: invalid package */
final class zzqm implements zzcf {
    private final zzoo<zzcf> zza;
    private final zzro zzb;
    private final zzro zzc;

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzop next : this.zza.zza(Arrays.copyOf(bArr, 5))) {
                try {
                    ((zzcf) next.zzd()).zza(bArr, bArr2);
                    this.zzc.zza(next.zza(), (long) bArr2.length);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            for (zzop next2 : this.zza.zze()) {
                try {
                    ((zzcf) next2.zzd()).zza(bArr, bArr2);
                    this.zzc.zza(next2.zza(), (long) bArr2.length);
                    return;
                } catch (GeneralSecurityException unused2) {
                }
            }
            this.zzc.zza();
            throw new GeneralSecurityException("invalid MAC");
        }
        this.zzc.zza();
        throw new GeneralSecurityException("tag too short");
    }

    private zzqm(zzoo<zzcf> zzoo) {
        this.zza = zzoo;
        if (zzoo.zzf()) {
            zzrp zzb2 = zznl.zza().zzb();
            zzrr zza2 = zzne.zza(zzoo);
            this.zzb = zzb2.zza(zza2, "mac", "compute");
            this.zzc = zzb2.zza(zza2, "mac", OffchainResolverContract.FUNC_VERIFY);
            return;
        }
        zzro zzro = zzne.zza;
        this.zzb = zzro;
        this.zzc = zzro;
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        try {
            byte[] zza2 = this.zza.zza().zzd().zza(bArr);
            this.zzb.zza(this.zza.zza().zza(), (long) bArr.length);
            return zza2;
        } catch (GeneralSecurityException e3) {
            this.zzb.zza();
            throw e3;
        }
    }
}
