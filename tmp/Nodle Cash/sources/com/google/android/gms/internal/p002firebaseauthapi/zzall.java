package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzall  reason: invalid package */
final class zzall extends zzali {
    public zzall(int i3) {
        super(i3);
    }

    public final void zzd() {
        if (!zze()) {
            for (int i3 = 0; i3 < zza(); i3++) {
                Map.Entry zzb = zzb(i3);
                if (((zzaiz) zzb.getKey()).zze()) {
                    zzb.setValue(Collections.unmodifiableList((List) zzb.getValue()));
                }
            }
            for (Map.Entry entry : zzb()) {
                if (((zzaiz) entry.getKey()).zze()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzd();
    }
}
