package com.google.android.recaptcha.internal;

import java.util.Map;

final class zzku extends zzle {
    public zzku(int i3) {
        super(i3, (zzld) null);
    }

    public final void zza() {
        if (!zzj()) {
            for (int i3 = 0; i3 < zzb(); i3++) {
                ((zzii) zzg(i3).getKey()).zzg();
            }
            for (Map.Entry key : zzc()) {
                ((zzii) key.getKey()).zzg();
            }
        }
        super.zza();
    }
}
