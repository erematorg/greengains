package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public final class zzwi implements zzwf {
    @VisibleForTesting
    final List zza;

    public zzwi(Context context, zzwh zzwh) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzwh.zzc()) {
            arrayList.add(new zzwx(context, zzwh));
        }
    }

    public final void zza(zzwe zzwe) {
        for (zzwf zza2 : this.zza) {
            zza2.zza(zzwe);
        }
    }
}
