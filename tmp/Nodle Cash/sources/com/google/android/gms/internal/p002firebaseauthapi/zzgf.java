package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzuh;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgf  reason: invalid package */
public final class zzgf {
    private static final zzxv zza;
    private static final zzoa<zzdg, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzcz, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzge(), zzdg.class, cls);
        zzc = zznw.zza(new zzgh(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzgg(), zzcz.class, cls2);
        zze = zzmq.zza(new zzgj(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzcz zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzsc zza2 = zzsc.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                } else if (zza2.zzd().zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys inner AES CTR keys are accepted");
                } else if (zza2.zze().zza() == 0) {
                    return zzcz.zzb().zza(zzdg.zzf().zza(zza2.zzd().zzf().zzb()).zzb(zza2.zze().zzf().zzb()).zzc(zza2.zzd().zze().zza()).zzd(zza2.zze().zze().zza()).zza(zza(zza2.zze().zze().zzb())).zza(zza(zzou.zzc())).zza()).zza(zzxw.zza(zza2.zzd().zzf().zzf(), zzcn.zza(zzcn))).zzb(zzxw.zza(zza2.zze().zzf().zzf(), zzcn.zza(zzcn))).zza(zzou.zze()).zza();
                } else {
                    throw new GeneralSecurityException("Only version 0 keys inner HMAC keys are accepted");
                }
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseKey");
        }
    }

    private static zzdg.zzc zza(zzub zzub) throws GeneralSecurityException {
        int i3 = zzgi.zzb[zzub.ordinal()];
        if (i3 == 1) {
            return zzdg.zzc.zza;
        }
        if (i3 == 2) {
            return zzdg.zzc.zzb;
        }
        if (i3 == 3) {
            return zzdg.zzc.zzc;
        }
        if (i3 == 4) {
            return zzdg.zzc.zzd;
        }
        if (i3 == 5) {
            return zzdg.zzc.zze;
        }
        throw new GeneralSecurityException(a.k("Unable to parse HashType: ", zzub.zza()));
    }

    private static zzdg.zzb zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzgi.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzdg.zzb.zza;
        }
        if (i3 == 2 || i3 == 3) {
            return zzdg.zzb.zzb;
        }
        if (i3 == 4) {
            return zzdg.zzb.zzc;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    /* access modifiers changed from: private */
    public static zzdg zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzsf zza2 = zzsf.zza(zzot.zza().zze(), zzaiq.zza());
                if (zza2.zzd().zzb() == 0) {
                    return zzdg.zzf().zza(zza2.zzc().zza()).zzb(zza2.zzd().zza()).zzc(zza2.zzc().zze().zza()).zzd(zza2.zzd().zzf().zza()).zza(zza(zza2.zzd().zzf().zzb())).zza(zza(zzot.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzuh zzb(zzdg zzdg) throws GeneralSecurityException {
        zzub zzub;
        zzuh.zza zza2 = zzuh.zzc().zza(zzdg.zze());
        zzdg.zzc zzg = zzdg.zzg();
        if (zzdg.zzc.zza.equals(zzg)) {
            zzub = zzub.SHA1;
        } else if (zzdg.zzc.zzb.equals(zzg)) {
            zzub = zzub.SHA224;
        } else if (zzdg.zzc.zzc.equals(zzg)) {
            zzub = zzub.SHA256;
        } else if (zzdg.zzc.zzd.equals(zzg)) {
            zzub = zzub.SHA384;
        } else if (zzdg.zzc.zze.equals(zzg)) {
            zzub = zzub.SHA512;
        } else {
            throw new GeneralSecurityException("Unable to serialize HashType ".concat(String.valueOf(zzg)));
        }
        return (zzuh) ((zzaje) zza2.zza(zzub).zzf());
    }

    private static zzvs zza(zzdg.zzb zzb2) throws GeneralSecurityException {
        if (zzdg.zzb.zza.equals(zzb2)) {
            return zzvs.TINK;
        }
        if (zzdg.zzb.zzb.equals(zzb2)) {
            return zzvs.CRUNCHY;
        }
        if (zzdg.zzb.zzc.equals(zzb2)) {
            return zzvs.RAW;
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
