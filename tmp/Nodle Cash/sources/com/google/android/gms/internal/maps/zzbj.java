package com.google.android.gms.internal.maps;

import androidx.camera.camera2.internal.C0118y;
import androidx.emoji2.emojipicker.StickyVariantProvider;

final class zzbj {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    public zzbj(Object obj, Object obj2, Object obj3) {
        this.zza = obj;
        this.zzb = obj2;
        this.zzc = obj3;
    }

    public final IllegalArgumentException zza() {
        Object obj = this.zzc;
        Object obj2 = this.zzb;
        Object obj3 = this.zza;
        String valueOf = String.valueOf(obj3);
        String valueOf2 = String.valueOf(obj2);
        return new IllegalArgumentException(C0118y.j(C0118y.l("Multiple entries with same key: ", valueOf, StickyVariantProvider.KEY_VALUE_DELIMITER, valueOf2, " and "), String.valueOf(obj3), StickyVariantProvider.KEY_VALUE_DELIMITER, String.valueOf(obj)));
    }
}
