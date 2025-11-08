package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

final class zziv extends zzir<Long> {
    public zziv(zziz zziz, String str, Long l2, boolean z2) {
        super(zziz, str, l2);
    }

    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zzb */
    public final Long zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zzb = zzb();
        String valueOf = String.valueOf(obj);
        Log.e("PhenotypeFlag", "Invalid long value for " + zzb + ": " + valueOf);
        return null;
    }
}
