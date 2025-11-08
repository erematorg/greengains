package com.google.android.gms.internal.measurement;

import A.a;
import androidx.browser.trusted.c;
import java.util.List;

public final class zzbq extends zzay {
    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        if (str == null || str.isEmpty() || !zzh.zzb(str)) {
            throw new IllegalArgumentException(c.a("Command not found: ", str));
        }
        zzaq zza = zzh.zza(str);
        if (zza instanceof zzal) {
            return ((zzal) zza).zza(zzh, list);
        }
        throw new IllegalArgumentException(a.l("Function ", str, " is not defined"));
    }
}
