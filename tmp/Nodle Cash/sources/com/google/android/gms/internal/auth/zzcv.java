package com.google.android.gms.internal.auth;

import android.support.v4.media.session.a;
import android.util.Log;
import javax.annotation.Nullable;

final class zzcv extends zzdc {
    public zzcv(zzcz zzcz, String str, Long l2, boolean z2) {
        super(zzcz, str, l2, true, (zzdb) null);
    }

    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (NumberFormatException unused) {
            StringBuilder w2 = a.w("Invalid long value for ", this.zzc, ": ");
            w2.append((String) obj);
            Log.e("PhenotypeFlag", w2.toString());
            return null;
        }
    }
}
