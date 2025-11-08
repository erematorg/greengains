package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzjo;
import com.google.android.gms.internal.p002firebaseauthapi.zztr;
import com.google.android.gms.internal.p002firebaseauthapi.zztv;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkw  reason: invalid package */
public final class zzkw {
    private static final zzxv zza;
    private static final zzxv zzb;
    private static final zzoa<zzjo, zzot> zzc;
    private static final zznw<zzot> zzd;
    private static final zzmu<zzjt, zzou> zze;
    private static final zzmq<zzou> zzf;
    private static final zzmu<zzju, zzou> zzg;
    private static final zzmq<zzou> zzh;
    private static final zzmh<zzvs, zzjo.zze> zzi;
    private static final zzmh<zzub, zzjo.zzd> zzj = zzmh.zza().zza(zzub.SHA1, zzjo.zzd.zza).zza(zzub.SHA224, zzjo.zzd.zzb).zza(zzub.SHA256, zzjo.zzd.zzc).zza(zzub.SHA384, zzjo.zzd.zzd).zza(zzub.SHA512, zzjo.zzd.zze).zza();
    private static final zzmh<zztw, zzjo.zzb> zzk = zzmh.zza().zza(zztw.NIST_P256, zzjo.zzb.zza).zza(zztw.NIST_P384, zzjo.zzb.zzb).zza(zztw.NIST_P521, zzjo.zzb.zzc).zza(zztw.CURVE25519, zzjo.zzb.zzd).zza();
    private static final zzmh<zzti, zzjo.zzc> zzl = zzmh.zza().zza(zzti.UNCOMPRESSED, zzjo.zzc.zzb).zza(zzti.COMPRESSED, zzjo.zzc.zza).zza(zzti.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, zzjo.zzc.zzc).zza();

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zza = zzb2;
        zzxv zzb3 = zzph.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
        zzb = zzb3;
        Class<zzot> cls = zzot.class;
        zzc = zzoa.zza(new zzkv(), zzjo.class, cls);
        zzd = zznw.zza(new zzky(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zze = zzmu.zza(new zzkx(), zzjt.class, cls2);
        zzf = zzmq.zza(new zzla(), zzb3, cls2);
        zzg = zzmu.zza(new zzkz(), zzju.class, cls2);
        zzh = zzmq.zza(new zzlc(), zzb2, cls2);
        zzmk zza2 = zzmh.zza().zza(zzvs.RAW, zzjo.zze.zzc).zza(zzvs.TINK, zzjo.zze.zza);
        zzvs zzvs = zzvs.LEGACY;
        zzjo.zze zze2 = zzjo.zze.zzb;
        zzi = zza2.zza(zzvs, zze2).zza(zzvs.CRUNCHY, zze2).zza();
    }

    /* access modifiers changed from: private */
    public static zzju zzc(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            try {
                zztr zza2 = zztr.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    zzts zzd2 = zza2.zzd();
                    zzjo zza3 = zza(zzou.zzc(), zzd2.zzb());
                    return zza3.zzd().equals(zzjo.zzb.zzd) ? zzju.zza(zzjt.zza(zza3, zzxv.zza(zzd2.zzf().zzf()), zzou.zze()), zzxw.zza(zza2.zze().zzf(), zzcn.zza(zzcn))) : zzju.zza(zzjt.zza(zza3, new ECPoint(zzmd.zza(zzd2.zzf().zzf()), zzmd.zza(zzd2.zzg().zzf())), zzou.zze()), zzxu.zza(zzmd.zza(zza2.zze().zzf()), zzcn.zza(zzcn)));
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to EciesProtoSerialization.parsePrivateKey: ", zzou.zzf()));
        }
    }

    /* access modifiers changed from: private */
    public static zzjt zzd(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
            try {
                zzts zza2 = zzts.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    zzjo zza3 = zza(zzou.zzc(), zza2.zzb());
                    if (!zza3.zzd().equals(zzjo.zzb.zzd)) {
                        return zzjt.zza(zza3, new ECPoint(zzmd.zza(zza2.zzf().zzf()), zzmd.zza(zza2.zzg().zzf())), zzou.zze());
                    }
                    if (zza2.zzg().zzb() == 0) {
                        return zzjt.zza(zza3, zzxv.zza(zza2.zzf().zzf()), zzou.zze());
                    }
                    throw new GeneralSecurityException("Y must be empty for X25519 points");
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to EciesProtoSerialization.parsePublicKey: ", zzou.zzf()));
        }
    }

    /* access modifiers changed from: private */
    public static zzjo zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            try {
                return zza(zzot.zza().zzd(), zztn.zza(zzot.zza().zze(), zzaiq.zza()).zzc());
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing EciesParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to EciesProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static int zza(zzjo.zzb zzb2) throws GeneralSecurityException {
        if (zzjo.zzb.zza.equals(zzb2)) {
            return 33;
        }
        if (zzjo.zzb.zzb.equals(zzb2)) {
            return 49;
        }
        if (zzjo.zzb.zzc.equals(zzb2)) {
            return 67;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType ".concat(String.valueOf(zzb2)));
    }

    private static zzjo zza(zzvs zzvs, zzto zzto) throws GeneralSecurityException {
        zzjo.zza zza2 = zzjo.zzc().zza(zzi.zza(zzvs)).zza(zzk.zza(zzto.zzf().zzd())).zza(zzj.zza(zzto.zzf().zze())).zza(zzcp.zza(((zzvc) ((zzaje) zzvc.zza().zza(zzto.zzb().zzd().zzf()).zza(zzvs.RAW).zza(zzto.zzb().zzd().zze()).zzf())).a_())).zza(zzxv.zza(zzto.zzf().zzf().zzf()));
        if (!zzto.zzf().zzd().equals(zztw.CURVE25519)) {
            zza2.zza(zzl.zza(zzto.zza()));
        } else if (!zzto.zza().equals(zzti.COMPRESSED)) {
            throw new GeneralSecurityException("For CURVE25519 EcPointFormat must be compressed");
        }
        return zza2.zza();
    }

    private static zzto zzb(zzjo zzjo) throws GeneralSecurityException {
        zztv.zza zza2 = zztv.zza().zza(zzk.zza(zzjo.zzd())).zza(zzj.zza(zzjo.zze()));
        if (zzjo.zzh() != null && zzjo.zzh().zza() > 0) {
            zza2.zza(zzaho.zza(zzjo.zzh().zzb()));
        }
        zztv zztv = (zztv) ((zzaje) zza2.zzf());
        try {
            zzvc zza3 = zzvc.zza(zzcp.zza(zzjo.zzb()), zzaiq.zza());
            zztj zztj = (zztj) ((zzaje) zztj.zza().zza((zzvc) ((zzaje) zzvc.zza().zza(zza3.zzf()).zza(zzvs.TINK).zza(zza3.zze()).zzf())).zzf());
            zzjo.zzc zzf2 = zzjo.zzf();
            if (zzf2 == null) {
                zzf2 = zzjo.zzc.zza;
            }
            return (zzto) ((zzaje) zzto.zzc().zza(zztv).zza(zztj).zza(zzl.zza(zzf2)).zzf());
        } catch (zzajk e3) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e3);
        }
    }

    public static /* synthetic */ zzou zza(zzju zzju, zzcn zzcn) {
        zztr.zza zza2 = zztr.zzb().zza(0).zza(zza((zzjt) ((zzkr) zzju.zzb())));
        if (zzju.zzc().zzd().equals(zzjo.zzb.zzd)) {
            zza2.zza(zzaho.zza(zzju.zzf().zza(zzcn.zza(zzcn))));
        } else {
            zza2.zza(zzaho.zza(zzmd.zza(zzju.zze().zza(zzcn.zza(zzcn)), zza(zzju.zzc().zzd()))));
        }
        return zzou.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", ((zztr) ((zzaje) zza2.zzf())).zzj(), zzuw.zzb.ASYMMETRIC_PRIVATE, zzi.zza(zzju.zzc().zzg()), zzju.zza());
    }

    private static zzts zza(zzjt zzjt) throws GeneralSecurityException {
        if (zzjt.zzb().zzd().equals(zzjo.zzb.zzd)) {
            return (zzts) ((zzaje) zzts.zzc().zza(0).zza(zzb(zzjt.zzb())).zza(zzaho.zza(zzjt.zzd().zzb())).zzb(zzaho.zza).zzf());
        }
        int zza2 = zza(zzjt.zzb().zzd());
        ECPoint zze2 = zzjt.zze();
        if (zze2 != null) {
            return (zzts) ((zzaje) zzts.zzc().zza(0).zza(zzb(zzjt.zzb())).zza(zzaho.zza(zzmd.zza(zze2.getAffineX(), zza2))).zzb(zzaho.zza(zzmd.zza(zze2.getAffineY(), zza2))).zzf());
        }
        throw new GeneralSecurityException("NistCurvePoint was null for NIST curve");
    }

    public static void zza() throws GeneralSecurityException {
        zzns zza2 = zzns.zza();
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
        zza2.zza(zzf);
        zza2.zza(zzg);
        zza2.zza(zzh);
    }
}
