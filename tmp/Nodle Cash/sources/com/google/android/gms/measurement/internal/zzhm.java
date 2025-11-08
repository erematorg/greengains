package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzb;

final class zzhm extends LruCache<String, zzb> {
    private final /* synthetic */ zzhg zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzhm(zzhg zzhg, int i3) {
        super(20);
        this.zza = zzhg;
    }

    public final /* synthetic */ Object create(Object obj) {
        String str = (String) obj;
        Preconditions.checkNotEmpty(str);
        return zzhg.zza(this.zza, str);
    }
}
