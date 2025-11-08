package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzek;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgx  reason: invalid package */
public final class zzgx {
    private static final zzxv zza;
    private static final zzoa<zzek, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzed, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzgw(), zzek.class, cls);
        zzc = zznw.zza(new zzgz(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzgy(), zzed.class, cls2);
        zze = zzmq.zza(new zzhb(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzed zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                zzsw zza2 = zzsw.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzed.zzb().zza(zzek.zzc().zza(zza2.zzd().zzb()).zza(zza(zzou.zzc())).zza()).zza(zzxw.zza(zza2.zzd().zzf(), zzcn.zza(zzcn))).zza(zzou.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseKey");
        }
    }

    private static zzek.zzb zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzha.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzek.zzb.zza;
        }
        if (i3 == 2 || i3 == 3) {
            return zzek.zzb.zzb;
        }
        if (i3 == 4) {
            return zzek.zzb.zzc;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    /* access modifiers changed from: private */
    public static zzek zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                zzsz zza2 = zzsz.zza(zzot.zza().zze(), zzaiq.zza());
                if (zza2.zzb() == 0) {
                    return zzek.zzc().zza(zza2.zza()).zza(zza(zzot.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to AesGcmSivProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzvs zza(zzek.zzb zzb2) throws GeneralSecurityException {
        if (zzek.zzb.zza.equals(zzb2)) {
            return zzvs.TINK;
        }
        if (zzek.zzb.zzb.equals(zzb2)) {
            return zzvs.CRUNCHY;
        }
        if (zzek.zzb.zzc.equals(zzb2)) {
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
