package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkq  reason: invalid package */
public final class zzkq {
    public static final zzvc zza;
    private static final byte[] zzb;
    private static final zzvc zzc;
    private static final zzvc zzd;

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        zztw zztw = zztw.NIST_P256;
        zzub zzub = zzub.SHA256;
        zzti zzti = zzti.UNCOMPRESSED;
        zzvc zzvc = zzct.zza;
        zzvs zzvs = zzvs.TINK;
        zzub zzub2 = zzub;
        zzvc zzvc2 = zzvc;
        byte[] bArr2 = bArr;
        zza = zza(zztw, zzub2, zzti, zzvc2, zzvs, bArr2);
        zzc = zza(zztw, zzub2, zzti.COMPRESSED, zzvc2, zzvs.RAW, bArr2);
        zzd = zza(zztw, zzub2, zzti, zzct.zzb, zzvs, bArr2);
    }

    @Deprecated
    private static zzvc zza(zztw zztw, zzub zzub, zzti zzti, zzvc zzvc, zzvs zzvs, byte[] bArr) {
        return (zzvc) ((zzaje) zzvc.zza().zza(zzjk.zza()).zza(zzvs).zza(((zztn) ((zzaje) zztn.zza().zza((zzto) ((zzaje) zzto.zzc().zza((zztv) ((zzaje) zztv.zza().zza(zztw).zza(zzub).zza(zzaho.zza(bArr)).zzf())).zza((zztj) ((zzaje) zztj.zza().zza(zzvc).zzf())).zza(zzti).zzf())).zzf())).zzj()).zzf());
    }
}
