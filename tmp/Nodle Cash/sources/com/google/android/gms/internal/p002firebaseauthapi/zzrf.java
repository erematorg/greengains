package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzqf;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrf  reason: invalid package */
public final class zzrf {
    private static final zzxv zza;
    private static final zzmh<zzvs, zzqf.zzc> zzb = zzmh.zza().zza(zzvs.RAW, zzqf.zzc.zzd).zza(zzvs.TINK, zzqf.zzc.zza).zza(zzvs.LEGACY, zzqf.zzc.zzc).zza(zzvs.CRUNCHY, zzqf.zzc.zzb).zza();
    private static final zzmh<zzub, zzqf.zzb> zzc = zzmh.zza().zza(zzub.SHA1, zzqf.zzb.zza).zza(zzub.SHA224, zzqf.zzb.zzb).zza(zzub.SHA256, zzqf.zzb.zzc).zza(zzub.SHA384, zzqf.zzb.zzd).zza(zzub.SHA512, zzqf.zzb.zze).zza();
    private static final zzoa<zzqf, zzot> zzd;
    private static final zznw<zzot> zze;
    private static final zzmu<zzpw, zzou> zzf;
    private static final zzmq<zzou> zzg;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzd = zzoa.zza(new zzre(), zzqf.class, cls);
        zze = zznw.zza(new zzrh(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzf = zzmu.zza(new zzrg(), zzpw.class, cls2);
        zzg = zzmq.zza(new zzrj(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzpw zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzud zza2 = zzud.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzpw.zzb().zza(zzqf.zzd().zza(zza2.zzf().zzb()).zzb(zza2.zze().zza()).zza(zzc.zza(zza2.zze().zzb())).zza(zzb.zza(zzou.zzc())).zza()).zza(zzxw.zza(zza2.zzf().zzf(), zzcn.zza(zzcn))).zza(zzou.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing HmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
        }
    }

    /* access modifiers changed from: private */
    public static zzqf zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzue zza2 = zzue.zza(zzot.zza().zze(), zzaiq.zza());
                if (zza2.zzb() == 0) {
                    return zzqf.zzd().zza(zza2.zza()).zzb(zza2.zzf().zza()).zza(zzc.zza(zza2.zzf().zzb())).zza(zzb.zza(zzot.zza().zzd())).zza();
                }
                throw new GeneralSecurityException(a.k("Parsing HmacParameters failed: unknown Version ", zza2.zzb()));
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing HmacParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to HmacProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzns zza2 = zzns.zza();
        zza2.zza(zzd);
        zza2.zza(zze);
        zza2.zza(zzf);
        zza2.zza(zzg);
    }

    private static zzuh zzb(zzqf zzqf) throws GeneralSecurityException {
        return (zzuh) ((zzaje) zzuh.zzc().zza(zzqf.zzb()).zza(zzc.zza(zzqf.zze())).zzf());
    }
}
