package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoi  reason: invalid package */
public final class zzoi {
    /* access modifiers changed from: private */
    public final Map<zzoj, zzoe<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<Class<?>, zzch<?, ?>> zzb;

    public static zzoh zza(zzoi zzoi) {
        return new zzoh(zzoi);
    }

    private zzoi(zzoh zzoh) {
        this.zza = new HashMap(zzoh.zza);
        this.zzb = new HashMap(zzoh.zzb);
    }

    public final Class<?> zza(Class<?> cls) throws GeneralSecurityException {
        if (this.zzb.containsKey(cls)) {
            return this.zzb.get(cls).zza();
        }
        throw new GeneralSecurityException(a.l("No input primitive class for ", String.valueOf(cls), " available"));
    }

    public final <KeyT extends zzbu, PrimitiveT> PrimitiveT zza(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        zzoj zzoj = new zzoj(keyt.getClass(), cls);
        if (this.zza.containsKey(zzoj)) {
            return this.zza.get(zzoj).zza(keyt);
        }
        throw new GeneralSecurityException(a.l("No PrimitiveConstructor for ", String.valueOf(zzoj), " available"));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.firebase-auth-api.zzoo<InputPrimitiveT>, com.google.android.gms.internal.firebase-auth-api.zzoo] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT zza(com.google.android.gms.internal.p002firebaseauthapi.zzoo<InputPrimitiveT> r2, java.lang.Class<WrapperPrimitiveT> r3) throws java.security.GeneralSecurityException {
        /*
            r1 = this;
            java.util.Map<java.lang.Class<?>, com.google.android.gms.internal.firebase-auth-api.zzch<?, ?>> r0 = r1.zzb
            boolean r0 = r0.containsKey(r3)
            if (r0 == 0) goto L_0x0039
            java.util.Map<java.lang.Class<?>, com.google.android.gms.internal.firebase-auth-api.zzch<?, ?>> r1 = r1.zzb
            java.lang.Object r1 = r1.get(r3)
            com.google.android.gms.internal.firebase-auth-api.zzch r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzch) r1
            java.lang.Class r3 = r2.zzc()
            java.lang.Class r0 = r1.zza()
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0031
            java.lang.Class r3 = r1.zza()
            java.lang.Class r0 = r2.zzc()
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0031
            java.lang.Object r1 = r1.zza(r2)
            return r1
        L_0x0031:
            java.security.GeneralSecurityException r1 = new java.security.GeneralSecurityException
            java.lang.String r2 = "Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet"
            r1.<init>(r2)
            throw r1
        L_0x0039:
            java.security.GeneralSecurityException r1 = new java.security.GeneralSecurityException
            java.lang.String r2 = java.lang.String.valueOf(r3)
            java.lang.String r3 = "No wrapper found for "
            java.lang.String r2 = r3.concat(r2)
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzoi.zza(com.google.android.gms.internal.firebase-auth-api.zzoo, java.lang.Class):java.lang.Object");
    }
}
