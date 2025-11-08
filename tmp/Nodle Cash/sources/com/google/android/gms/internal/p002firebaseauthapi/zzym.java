package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.internal.zzbh;
import com.google.firebase.auth.zzf;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzym  reason: invalid package */
public final class zzym {
    @SafeParcelable.Field(getter = "getMfaPendingCredential", id = 1)
    private String zza;
    @SafeParcelable.Field(getter = "getMfaInfoList", id = 2)
    private List<zzafq> zzb;
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 3)
    private zzf zzc;

    @SafeParcelable.Constructor
    public zzym(String str, List<zzafq> list, @Nullable zzf zzf) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zzf;
    }

    public final zzf zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    public final List<MultiFactorInfo> zzc() {
        return zzbh.zza(this.zzb);
    }
}
