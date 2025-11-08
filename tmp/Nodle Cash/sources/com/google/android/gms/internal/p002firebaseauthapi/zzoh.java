package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoh  reason: invalid package */
public final class zzoh {
    /* access modifiers changed from: private */
    public final Map<zzoj, zzoe<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<Class<?>, zzch<?, ?>> zzb;

    public final <KeyT extends zzbu, PrimitiveT> zzoh zza(zzoe<KeyT, PrimitiveT> zzoe) throws GeneralSecurityException {
        if (zzoe != null) {
            zzoj zzoj = new zzoj(zzoe.zza(), zzoe.zzb());
            if (this.zza.containsKey(zzoj)) {
                zzoe zzoe2 = this.zza.get(zzoj);
                if (!zzoe2.equals(zzoe) || !zzoe.equals(zzoe2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: ".concat(String.valueOf(zzoj)));
                }
            } else {
                this.zza.put(zzoj, zzoe);
            }
            return this;
        }
        throw new NullPointerException("primitive constructor must be non-null");
    }

    private zzoh() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    private zzoh(zzoi zzoi) {
        this.zza = new HashMap(zzoi.zza);
        this.zzb = new HashMap(zzoi.zzb);
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> zzoh zza(zzch<InputPrimitiveT, WrapperPrimitiveT> zzch) throws GeneralSecurityException {
        if (zzch != null) {
            Class<WrapperPrimitiveT> zzb2 = zzch.zzb();
            if (this.zzb.containsKey(zzb2)) {
                zzch zzch2 = this.zzb.get(zzb2);
                if (!zzch2.equals(zzch) || !zzch.equals(zzch2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type".concat(String.valueOf(zzb2)));
                }
            } else {
                this.zzb.put(zzb2, zzch);
            }
            return this;
        }
        throw new NullPointerException("wrapper must be non-null");
    }

    public final zzoi zza() {
        return new zzoi(this);
    }
}
