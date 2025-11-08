package com.google.firebase.auth.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzafq;
import com.google.android.gms.internal.p002firebaseauthapi.zzagq;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

public final class zzbh {
    @Nullable
    public static MultiFactorInfo zza(zzafq zzafq) {
        if (zzafq == null) {
            return null;
        }
        if (!TextUtils.isEmpty(zzafq.zze())) {
            return new PhoneMultiFactorInfo(zzafq.zzd(), zzafq.zzc(), zzafq.zza(), Preconditions.checkNotEmpty(zzafq.zze()));
        }
        if (zzafq.zzb() != null) {
            return new TotpMultiFactorInfo(zzafq.zzd(), zzafq.zzc(), zzafq.zza(), (zzagq) Preconditions.checkNotNull(zzafq.zzb(), "totpInfo cannot be null."));
        }
        return null;
    }

    public static List<MultiFactorInfo> zza(List<zzafq> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (zzafq zza : list) {
            MultiFactorInfo zza2 = zza(zza);
            if (zza2 != null) {
                arrayList.add(zza2);
            }
        }
        return arrayList;
    }
}
