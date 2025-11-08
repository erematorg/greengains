package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zznh extends zzne {
    public zznh() {
        super();
    }

    public final void zzd() {
        if (!zze()) {
            for (int i3 = 0; i3 < zza(); i3++) {
                Map.Entry zza = zza(i3);
                if (((zzkx) zza.getKey()).zze()) {
                    zza.setValue(Collections.unmodifiableList((List) zza.getValue()));
                }
            }
            for (Map.Entry entry : zzb()) {
                if (((zzkx) entry.getKey()).zze()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzd();
    }
}
