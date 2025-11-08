package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public final class zzsc implements zzrz {
    @VisibleForTesting
    final List zza;

    public zzsc(Context context, zzsb zzsb) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzsb.zzc()) {
            arrayList.add(new zzsp(context, zzsb));
        }
    }

    public final void zza(zzry zzry) {
        for (zzrz zza2 : this.zza) {
            zza2.zza(zzry);
        }
    }
}
