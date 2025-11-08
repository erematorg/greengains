package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.browser.trusted.c;
import com.google.android.gms.internal.p002firebaseauthapi.zzjv;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjz  reason: invalid package */
public final class zzjz {
    private static final zzxv zza;
    private static final zzxv zzb;
    private static final zzoa<zzjv, zzot> zzc;
    private static final zznw<zzot> zzd;
    private static final zzmu<zzki, zzou> zze;
    private static final zzmq<zzou> zzf;
    private static final zzmu<zzka, zzou> zzg;
    private static final zzmq<zzou> zzh;
    private static final zzmh<zzvs, zzjv.zzf> zzi;
    private static final zzmh<zzul, zzjv.zzd> zzj = zzmh.zza().zza(zzul.DHKEM_P256_HKDF_SHA256, zzjv.zzd.zza).zza(zzul.DHKEM_P384_HKDF_SHA384, zzjv.zzd.zzb).zza(zzul.DHKEM_P521_HKDF_SHA512, zzjv.zzd.zzc).zza(zzul.DHKEM_X25519_HKDF_SHA256, zzjv.zzd.zzd).zza();
    private static final zzmh<zzuj, zzjv.zze> zzk = zzmh.zza().zza(zzuj.HKDF_SHA256, zzjv.zze.zza).zza(zzuj.HKDF_SHA384, zzjv.zze.zzb).zza(zzuj.HKDF_SHA512, zzjv.zze.zzc).zza();
    private static final zzmh<zzui, zzjv.zza> zzl = zzmh.zza().zza(zzui.AES_128_GCM, zzjv.zza.zza).zza(zzui.AES_256_GCM, zzjv.zza.zzb).zza(zzui.CHACHA20_POLY1305, zzjv.zza.zzc).zza();

    static {
        zzxv zzb2 = zzph.zzb("type.googleapis.com/google.crypto.tink.HpkePrivateKey");
        zza = zzb2;
        zzxv zzb3 = zzph.zzb("type.googleapis.com/google.crypto.tink.HpkePublicKey");
        zzb = zzb3;
        Class<zzot> cls = zzot.class;
        zzc = zzoa.zza(new zzkc(), zzjv.class, cls);
        zzd = zznw.zza(new zzkb(), zzb2, cls);
        Class<zzou> cls2 = zzou.class;
        zze = zzmu.zza(new zzke(), zzki.class, cls2);
        zzf = zzmq.zza(new zzkd(), zzb3, cls2);
        zzg = zzmu.zza(new zzkg(), zzka.class, cls2);
        zzh = zzmq.zza(new zzkf(), zzb2, cls2);
        zzmk zza2 = zzmh.zza().zza(zzvs.RAW, zzjv.zzf.zzc).zza(zzvs.TINK, zzjv.zzf.zza);
        zzvs zzvs = zzvs.LEGACY;
        zzjv.zzf zzf2 = zzjv.zzf.zzb;
        zzi = zza2.zza(zzvs, zzf2).zza(zzvs.CRUNCHY, zzf2).zza();
    }

    /* access modifiers changed from: private */
    public static zzka zzc(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            try {
                zzus zza2 = zzus.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    zzuv zzd2 = zza2.zzd();
                    zzjv zza3 = zza(zzou.zzc(), zzd2.zzb());
                    return zzka.zza(zzki.zza(zza3, zza(zza3.zze(), zzd2.zzf().zzf()), zzou.zze()), zzxw.zza(zzmd.zza(zzmd.zza(zza2.zze().zzf()), zzln.zza(zza3.zze())), zzcn.zza(zzcn)));
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing HpkePrivateKey failed");
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to HpkeProtoSerialization.parsePrivateKey: ", zzou.zzf()));
        }
    }

    /* access modifiers changed from: private */
    public static zzki zzd(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzou.zzf().equals("type.googleapis.com/google.crypto.tink.HpkePublicKey")) {
            try {
                zzuv zza2 = zzuv.zza(zzou.zzd(), zzaiq.zza());
                if (zza2.zza() == 0) {
                    zzjv zza3 = zza(zzou.zzc(), zza2.zzb());
                    return zzki.zza(zza3, zza(zza3.zze(), zza2.zzf().zzf()), zzou.zze());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzajk unused) {
                throw new GeneralSecurityException("Parsing HpkePublicKey failed");
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to HpkeProtoSerialization.parsePublicKey: ", zzou.zzf()));
        }
    }

    /* access modifiers changed from: private */
    public static zzjv zzb(zzot zzot) throws GeneralSecurityException {
        if (zzot.zza().zzf().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            try {
                return zza(zzot.zza().zzd(), zzun.zza(zzot.zza().zze(), zzaiq.zza()).zzc());
            } catch (zzajk e3) {
                throw new GeneralSecurityException("Parsing HpkeParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException(c.a("Wrong type URL in call to HpkeProtoSerialization.parseParameters: ", zzot.zza().zzf()));
        }
    }

    private static zzjv zza(zzvs zzvs, zzur zzur) throws GeneralSecurityException {
        return zzjv.zzc().zza(zzi.zza(zzvs)).zza(zzj.zza(zzur.zzc())).zza(zzk.zza(zzur.zzb())).zza(zzl.zza(zzur.zza())).zza();
    }

    private static zzur zzb(zzjv zzjv) throws GeneralSecurityException {
        return (zzur) ((zzaje) zzur.zzd().zza(zzj.zza(zzjv.zze())).zza(zzk.zza(zzjv.zzd())).zza(zzl.zza(zzjv.zzb())).zzf());
    }

    private static zzuv zza(zzki zzki) throws GeneralSecurityException {
        return (zzuv) ((zzaje) zzuv.zzc().zza(0).zza(zzb(zzki.zzb())).zza(zzaho.zza(zzki.zzd().zzb())).zzf());
    }

    private static zzxv zza(zzjv.zzd zzd2, byte[] bArr) throws GeneralSecurityException {
        return zzxv.zza(zzmd.zza(zzmd.zza(bArr), zzln.zzb(zzd2)));
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
