package com.google.android.gms.internal.auth;

import android.support.v4.media.session.a;
import android.util.Log;
import javax.annotation.Nullable;

final class zzcx extends zzdc {
    public zzcx(zzcz zzcz, String str, Double d2, boolean z2) {
        super(zzcz, str, d2, true, (zzdb) null);
    }

    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        try {
            return Double.valueOf(Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            StringBuilder w2 = a.w("Invalid double value for ", this.zzc, ": ");
            w2.append((String) obj);
            Log.e("PhenotypeFlag", w2.toString());
            return null;
        }
    }
}
