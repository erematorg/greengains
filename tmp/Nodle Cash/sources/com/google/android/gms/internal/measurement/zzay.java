package com.google.android.gms.internal.measurement;

import androidx.browser.trusted.c;
import java.util.ArrayList;
import java.util.List;

public abstract class zzay {
    final List<zzbv> zza = new ArrayList();

    public final zzaq zza(String str) {
        if (this.zza.contains(zzg.zza(str))) {
            throw new UnsupportedOperationException(c.a("Command not implemented: ", str));
        }
        throw new IllegalArgumentException("Command not supported");
    }

    public abstract zzaq zza(String str, zzh zzh, List<zzaq> list);
}
