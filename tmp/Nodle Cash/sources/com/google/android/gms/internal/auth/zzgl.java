package com.google.android.gms.internal.auth;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzgl extends zzgv {
    public zzgl(int i3) {
        super(i3, (zzgu) null);
    }

    public final void zza() {
        if (!zzj()) {
            for (int i3 = 0; i3 < zzb(); i3++) {
                Map.Entry zzg = zzg(i3);
                if (((zzep) zzg.getKey()).zzc()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzep) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
