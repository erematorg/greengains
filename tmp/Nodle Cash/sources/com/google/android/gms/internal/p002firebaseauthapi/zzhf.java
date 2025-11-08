package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzeq;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhf  reason: invalid package */
public final class zzhf {
    private static final zzxv zza;
    private static final zzoa<zzeq, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzem, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzhe(), zzeq.class, cls);
        zzc = zznw.zza(new zzhh(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzhg(), zzem.class, cls2);
        zze = zzmq.zza(new zzhj(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzem zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                zzte zza2 = zzte.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzem.zza(zza(zzou.zzc()), zzxw.zza(zza2.zzd().zzf(), zzcn.zza(zzcn)), zzou.zze());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseKey");
        }
    }

    private static zzeq.zza zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzhi.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzeq.zza.zza;
        }
        if (i3 == 2 || i3 == 3) {
            return zzeq.zza.zzb;
        }
        if (i3 == 4) {
            return zzeq.zza.zzc;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    /* access modifiers changed from: private */
    public static zzeq zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                zzth.zza(zzot.zza().zze(), zzaiq.zza());
                return zzeq.zza(zza(zzot.zza().zzd()));
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzvs zza(zzeq.zza zza2) throws GeneralSecurityException {
        if (zzeq.zza.zza.equals(zza2)) {
            return zzvs.TINK;
        }
        if (zzeq.zza.zzb.equals(zza2)) {
            return zzvs.CRUNCHY;
        }
        if (zzeq.zza.zzc.equals(zza2)) {
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
