package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzfi;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfm  reason: invalid package */
public final class zzfm {
    private static final zzxv zza;
    private static final zzoa<zzfi, zzot> zzb;
    private static final zznw<zzot> zzc;
    private static final zzmu<zzfg, zzou> zzd;
    private static final zzmq<zzou> zze;

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
        zza = zzb2;
        Class<zzot> cls = zzot.class;
        zzb = zzoa.zza(new zzfl(), zzfi.class, cls);
        zzc = zznw.zza(new zzfo(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zzd = zzmu.zza(new zzfn(), zzfg.class, cls2);
        zze = zzmq.zza(new zzfq(), zzb2, cls2);
    }

    /* access modifiers changed from: private */
    public static zzfg zzb(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                zzvo zza2 = zzvo.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    return zzfg.zza(zza(zza2.zzd(), zzou.zzc()), zzou.zze());
                }
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got ".concat(String.valueOf(zza2)));
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
        }
    }

    private static zzfi zza(zzvp zzvp, zzvs zzvs) throws GeneralSecurityException {
        zzfi.zzb zzb2;
        zzfi.zzc zzc2;
        zzci zza2 = zzcp.zza(((zzvc) ((zzaje) zzvc.zza().zza(zzvp.zza().zzf()).zza(zzvp.zza().zze()).zza(zzvs.RAW).zzf())).a_());
        if (zza2 instanceof zzdz) {
            zzb2 = zzfi.zzb.zza;
        } else if (zza2 instanceof zzeq) {
            zzb2 = zzfi.zzb.zzc;
        } else if (zza2 instanceof zzgd) {
            zzb2 = zzfi.zzb.zzb;
        } else if (zza2 instanceof zzdg) {
            zzb2 = zzfi.zzb.zzd;
        } else if (zza2 instanceof zzdq) {
            zzb2 = zzfi.zzb.zze;
        } else if (zza2 instanceof zzek) {
            zzb2 = zzfi.zzb.zzf;
        } else {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing ".concat(String.valueOf(zza2)));
        }
        zzfi.zza zza3 = new zzfi.zza();
        int i3 = zzfp.zza[zzvs.ordinal()];
        if (i3 == 1) {
            zzc2 = zzfi.zzc.zza;
        } else if (i3 == 2) {
            zzc2 = zzfi.zzc.zzb;
        } else {
            throw new GeneralSecurityException(a.k("Unable to parse OutputPrefixType: ", zzvs.zza()));
        }
        return zza3.zza(zzc2).zza(zzvp.zze()).zza((zzcw) zza2).zza(zzb2).zza();
    }

    /* access modifiers changed from: private */
    public static zzfi zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                return zza(zzvp.zza(zzot.zza().zze(), zzaiq.zza()), zzot.zza().zzd());
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzvp zzb(zzfi zzfi) throws GeneralSecurityException {
        try {
            return (zzvp) ((zzaje) zzvp.zzb().zza(zzfi.zzd()).zza(zzvc.zza(zzcp.zza((zzci) zzfi.zzb()), zzaiq.zza())).zzf());
        } catch (zzajk e3) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e3);
        }
    }

    private static zzvs zza(zzfi.zzc zzc2) throws GeneralSecurityException {
        if (zzfi.zzc.zza.equals(zzc2)) {
            return zzvs.TINK;
        }
        if (zzfi.zzc.zzb.equals(zzc2)) {
            return zzvs.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(zzc2)));
    }

    public static void zza() throws GeneralSecurityException {
        zzns zza2 = zzns.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
