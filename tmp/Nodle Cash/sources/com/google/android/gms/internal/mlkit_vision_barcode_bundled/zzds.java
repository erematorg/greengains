package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzds {
    static final zzds zza = new zzds(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private final Map zzd;

    public zzds() {
        this.zzd = new HashMap();
    }

    public static zzds zza() {
        int i3 = zzfu.zza;
        return zza;
    }

    public final zzef zzb(zzfm zzfm, int i3) {
        return (zzef) this.zzd.get(new zzdr(zzfm, i3));
    }

    public zzds(boolean z2) {
        this.zzd = Collections.emptyMap();
    }
}
