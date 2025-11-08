package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzxd;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwv  reason: invalid package */
public final class zzwv<T_WRAPPER extends zzxd<JcePrimitiveT>, JcePrimitiveT> {
    public static final zzwv<zzxc, Cipher> zza = new zzwv<>(new zzxc());
    public static final zzwv<zzxg, Mac> zzb = new zzwv<>(new zzxg());
    public static final zzwv<zzxf, KeyAgreement> zzc = new zzwv<>(new zzxf());
    public static final zzwv<zzxh, KeyPairGenerator> zzd = new zzwv<>(new zzxh());
    public static final zzwv<zzxe, KeyFactory> zze = new zzwv<>(new zzxe());
    private static final zzwv<zzxi, Signature> zzf = new zzwv<>(new zzxi());
    private static final zzwv<zzxj, MessageDigest> zzg = new zzwv<>(new zzxj());
    private final zzxa<JcePrimitiveT> zzh;

    private zzwv(T_WRAPPER t_wrapper) {
        if (zzig.zzb()) {
            this.zzh = new zzwy(t_wrapper);
        } else if (zzxr.zza()) {
            this.zzh = new zzwu(t_wrapper);
        } else {
            this.zzh = new zzww(t_wrapper);
        }
    }

    public final JcePrimitiveT zza(String str) throws GeneralSecurityException {
        return this.zzh.zza(str);
    }

    public static List<Provider> zza(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String provider : strArr) {
            Provider provider2 = Security.getProvider(provider);
            if (provider2 != null) {
                arrayList.add(provider2);
            }
        }
        return arrayList;
    }
}
