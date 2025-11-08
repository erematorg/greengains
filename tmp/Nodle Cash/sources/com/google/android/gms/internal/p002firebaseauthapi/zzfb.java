package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzez;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfb  reason: invalid package */
final class zzfb {
    private static final zzxv zza;
    private static final zzoa<zzez, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzfa, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzfd(), zzez.class, cls);
        zzc = zznw.zza(new zzfc(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzff(), zzfa.class, cls2);
        zze = zzmq.zza(new zzfe(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzfa zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                zzvk zza2 = zzvk.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzfa.zza(zzez.zza(zza2.zzd().zzd(), zza(zzou.zzc())), zzou.zze());
                }
                throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got ".concat(String.valueOf(zza2)));
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
        }
    }

    private static zzez.zza zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzfh.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzez.zza.zza;
        }
        if (i3 == 2) {
            return zzez.zza.zzb;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    /* access modifiers changed from: private */
    public static zzez zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                return zzez.zza(zzvl.zza(zzot.zza().zze(), zzaiq.zza()).zzd(), zza(zzot.zza().zzd()));
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzvs zza(zzez.zza zza2) throws GeneralSecurityException {
        if (zzez.zza.zza.equals(zza2)) {
            return zzvs.TINK;
        }
        if (zzez.zza.zzb.equals(zza2)) {
            return zzvs.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(zza2)));
    }

    public static void zza() throws GeneralSecurityException {
        zzns zza2 = zzns.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
