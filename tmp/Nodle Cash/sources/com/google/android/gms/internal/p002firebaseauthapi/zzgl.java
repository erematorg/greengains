package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzdq;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgl  reason: invalid package */
public final class zzgl {
    private static final zzxv zza;
    private static final zzoa<zzdq, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzdj, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzgk(), zzdq.class, cls);
        zzc = zznw.zza(new zzgn(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzgm(), zzdj.class, cls2);
        zze = zzmq.zza(new zzgp(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzdj zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                zzsn zza2 = zzsn.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzdj.zzb().zza(zzdq.zze().zzb(zza2.zze().zzb()).zza(zza2.zzd().zza()).zzc(16).zza(zza(zzou.zzc())).zza()).zza(zzxw.zza(zza2.zze().zzf(), zzcn.zza(zzcn))).zza(zzou.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing AesEaxcKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseKey");
        }
    }

    private static zzdq.zzb zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzgo.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzdq.zzb.zza;
        }
        if (i3 == 2 || i3 == 3) {
            return zzdq.zzb.zzb;
        }
        if (i3 == 4) {
            return zzdq.zzb.zzc;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    /* access modifiers changed from: private */
    public static zzdq zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                zzso zza2 = zzso.zza(zzot.zza().zze(), zzaiq.zza());
                return zzdq.zze().zzb(zza2.zza()).zza(zza2.zzd().zza()).zzc(16).zza(zza(zzot.zza().zzd())).zza();
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing AesEaxParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to AesEaxProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzvs zza(zzdq.zzb zzb2) throws GeneralSecurityException {
        if (zzdq.zzb.zza.equals(zzb2)) {
            return zzvs.TINK;
        }
        if (zzdq.zzb.zzb.equals(zzb2)) {
            return zzvs.CRUNCHY;
        }
        if (zzdq.zzb.zzc.equals(zzb2)) {
            return zzvs.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(zzb2)));
    }

    private static zzsr zzb(zzdq zzdq) throws GeneralSecurityException {
        if (zzdq.zzd() == 16) {
            return (zzsr) ((zzaje) zzsr.zzb().zza(zzdq.zzb()).zzf());
        }
        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports aes eax keys with tag size equal to 16 bytes.", new Object[]{Integer.valueOf(zzdq.zzd())}));
    }

    public static void zza() throws GeneralSecurityException {
        zzns zza2 = zzns.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
