package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzpq;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqu  reason: invalid package */
public final class zzqu {
    private static final zzxv zza;
    private static final zzoa<zzpq, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzpj, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzqx(), zzpq.class, cls);
        zzc = zznw.zza(new zzqw(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzqz(), zzpj.class, cls2);
        zze = zzmq.zza(new zzqy(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzpj zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzrx zza2 = zzrx.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzpj.zzb().zza(zzpq.zzd().zza(zza2.zze().zzb()).zzb(zza2.zzd().zza()).zza(zza(zzou.zzc())).zza()).zza(zzxw.zza(zza2.zze().zzf(), zzcn.zza(zzcn))).zza(zzou.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseKey");
        }
    }

    /* access modifiers changed from: private */
    public static zzpq zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzry zza2 = zzry.zza(zzot.zza().zze(), zzaiq.zza());
                return zzpq.zzd().zza(zza2.zza()).zzb(zza2.zzd().zza()).zza(zza(zzot.zza().zzd())).zza();
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing AesCmacParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to AesCmacProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzpq.zzb zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzrb.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzpq.zzb.zza;
        }
        if (i3 == 2) {
            return zzpq.zzb.zzb;
        }
        if (i3 == 3) {
            return zzpq.zzb.zzc;
        }
        if (i3 == 4) {
            return zzpq.zzb.zzd;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    private static zzsb zzb(zzpq zzpq) {
        return (zzsb) ((zzaje) zzsb.zzb().zza(zzpq.zzb()).zzf());
    }

    private static zzvs zza(zzpq.zzb zzb2) throws GeneralSecurityException {
        if (zzpq.zzb.zza.equals(zzb2)) {
            return zzvs.TINK;
        }
        if (zzpq.zzb.zzb.equals(zzb2)) {
            return zzvs.CRUNCHY;
        }
        if (zzpq.zzb.zzd.equals(zzb2)) {
            return zzvs.RAW;
        }
        if (zzpq.zzb.zzc.equals(zzb2)) {
            return zzvs.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(zzb2)));
    }

    public static void zza() throws GeneralSecurityException {
        zzns zza2 = zzns.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
