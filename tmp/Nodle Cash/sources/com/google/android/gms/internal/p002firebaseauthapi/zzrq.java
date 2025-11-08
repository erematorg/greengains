package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrq  reason: invalid package */
public final class zzrq {
    @Nullable
    private ArrayList<zzrt> zza = new ArrayList<>();
    private zzrk zzb = zzrk.zza;
    @Nullable
    private Integer zzc = null;

    public final zzrq zza(zzbw zzbw, int i3, String str, String str2) {
        ArrayList<zzrt> arrayList = this.zza;
        if (arrayList != null) {
            arrayList.add(new zzrt(zzbw, i3, str, str2));
            return this;
        }
        throw new IllegalStateException("addEntry cannot be called after build()");
    }

    public final zzrq zza(zzrk zzrk) {
        if (this.zza != null) {
            this.zzb = zzrk;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build()");
    }

    public final zzrq zza(int i3) {
        if (this.zza != null) {
            this.zzc = Integer.valueOf(i3);
            return this;
        }
        throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
    }

    public final zzrr zza() throws GeneralSecurityException {
        if (this.zza != null) {
            Integer num = this.zzc;
            if (num != null) {
                int intValue = num.intValue();
                ArrayList<zzrt> arrayList = this.zza;
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    zzrt zzrt = arrayList.get(i3);
                    i3++;
                    if (zzrt.zza() == intValue) {
                    }
                }
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            zzrr zzrr = new zzrr(this.zzb, Collections.unmodifiableList(this.zza), this.zzc);
            this.zza = null;
            return zzrr;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
