package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaiq  reason: invalid package */
public final class zzaiq {
    static final zzaiq zza = new zzaiq(true);
    private static volatile boolean zzb = false;
    private static boolean zzc = true;
    private final Map<zzait, zzaje.zzd<?, ?>> zzd;

    public zzaiq() {
        this.zzd = new HashMap();
    }

    public static zzaiq zza() {
        return zza;
    }

    public final <ContainingType extends zzakp> zzaje.zzd<ContainingType, ?> zza(ContainingType containingtype, int i3) {
        return this.zzd.get(new zzait(containingtype, i3));
    }

    private zzaiq(boolean z2) {
        this.zzd = Collections.emptyMap();
    }
}
