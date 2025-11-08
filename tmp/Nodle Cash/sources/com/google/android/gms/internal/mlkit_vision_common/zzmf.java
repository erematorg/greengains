package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public final class zzmf implements zzmc {
    @VisibleForTesting
    final List zza;

    public zzmf(Context context, zzme zzme) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzme.zzc()) {
            arrayList.add(new zzmp(context, zzme));
        }
    }

    public final void zza(zzmb zzmb) {
        for (zzmc zza2 : this.zza) {
            zza2.zza(zzmb);
        }
    }
}
