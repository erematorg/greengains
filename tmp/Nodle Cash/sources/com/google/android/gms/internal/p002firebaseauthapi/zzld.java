package com.google.android.gms.internal.p002firebaseauthapi;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzld  reason: invalid package */
public final class zzld {
    private static final byte[] zza = new byte[0];
    private final zzle zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    @GuardedBy("this")
    private BigInteger zzg = BigInteger.ZERO;

    private zzld(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzle zzle) {
        this.zzf = bArr;
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzle;
    }

    public static zzld zza(byte[] bArr, zzlk zzlk, zzlh zzlh, zzli zzli, zzle zzle, byte[] bArr2) throws GeneralSecurityException {
        byte[] zza2 = zzlh.zza(bArr, zzlk);
        byte[] bArr3 = zzln.zza;
        byte[] zza3 = zzln.zza(zzlh.zza(), zzli.zzb(), zzle.zzc());
        byte[] bArr4 = zzln.zzl;
        byte[] bArr5 = zza;
        byte[] zza4 = zzwh.zza(bArr3, zzli.zza(bArr4, bArr5, "psk_id_hash", zza3), zzli.zza(bArr4, bArr2, "info_hash", zza3));
        zzli zzli2 = zzli;
        byte[] zza5 = zzli.zza(zza2, bArr5, "secret", zza3);
        byte[] bArr6 = zza4;
        byte[] bArr7 = zza3;
        byte[] zza6 = zzli2.zza(zza5, bArr6, JwtUtilsKt.DID_METHOD_KEY, bArr7, zzle.zza());
        byte[] zza7 = zzli2.zza(zza5, bArr6, "base_nonce", bArr7, zzle.zzb());
        zzle.zzb();
        BigInteger bigInteger = BigInteger.ONE;
        return new zzld(bArr, zza6, zza7, bigInteger.shiftLeft(96).subtract(bigInteger), zzle);
    }

    private final synchronized byte[] zza() throws GeneralSecurityException {
        byte[] zza2;
        zza2 = zzwh.zza(this.zze, zzmd.zza(this.zzg, this.zzb.zzb()));
        if (this.zzg.compareTo(this.zzc) < 0) {
            this.zzg = this.zzg.add(BigInteger.ONE);
        } else {
            throw new GeneralSecurityException("message limit reached");
        }
        return zza2;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zza(this.zzd, zza(), bArr, bArr2);
    }
}
