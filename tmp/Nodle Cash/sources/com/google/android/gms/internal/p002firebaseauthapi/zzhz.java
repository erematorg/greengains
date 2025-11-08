package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzgd;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhz  reason: invalid package */
public final class zzhz {
    private static final zzxv zza;
    private static final zzoa<zzgd, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzfx, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzhy(), zzgd.class, cls);
        zzc = zznw.zza(new zzib(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzia(), zzfx.class, cls2);
        zze = zzmq.zza(new zzid(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzfx zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                zzvv zza2 = zzvv.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzfx.zza(zza(zzou.zzc()), zzxw.zza(zza2.zzd().zzf(), zzcn.zza(zzcn)), zzou.zze());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
        }
    }

    private static zzgd.zza zza(zzvs zzvs) throws GeneralSecurityException {
        int i3 = zzic.zza[zzvs.ordinal()];
        if (i3 == 1) {
            return zzgd.zza.zza;
        }
        if (i3 == 2 || i3 == 3) {
            return zzgd.zza.zzb;
        }
        if (i3 == 4) {
            return zzgd.zza.zzc;
        }
        throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
    }

    /* access modifiers changed from: private */
    public static zzgd zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                if (zzvy.zza(zzot.zza().zze(), zzaiq.zza()).zza() == 0) {
                    return zzgd.zza(zza(zzot.zza().zzd()));
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzvs zza(zzgd.zza zza2) throws GeneralSecurityException {
        if (zzgd.zza.zza.equals(zza2)) {
            return zzvs.TINK;
        }
        if (zzgd.zza.zzb.equals(zza2)) {
            return zzvs.CRUNCHY;
        }
        if (zzgd.zza.zzc.equals(zza2)) {
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
