package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import androidx.emoji2.emojipicker.StickyVariantProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaw  reason: invalid package */
final class zzaw {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    public zzaw(Object obj, Object obj2, Object obj3) {
        this.zza = obj;
        this.zzb = obj2;
        this.zzc = obj3;
    }

    public final IllegalArgumentException zza() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        return new IllegalArgumentException(C0118y.j(C0118y.l("Multiple entries with same key: ", valueOf, StickyVariantProvider.KEY_VALUE_DELIMITER, valueOf2, " and "), String.valueOf(this.zza), StickyVariantProvider.KEY_VALUE_DELIMITER, String.valueOf(this.zzc)));
    }
}
