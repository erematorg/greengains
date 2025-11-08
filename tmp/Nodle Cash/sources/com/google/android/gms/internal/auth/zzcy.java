package com.google.android.gms.internal.auth;

import android.support.v4.media.session.a;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;

final class zzcy extends zzdc {
    final /* synthetic */ zzhy zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzcy(zzcz zzcz, String str, Object obj, boolean z2, zzhy zzhy) {
        super(zzcz, "getTokenRefactor__blocked_packages", obj, true, (zzdb) null);
        this.zza = zzhy;
    }

    public final Object zza(Object obj) {
        try {
            return zzhs.zzp(Base64.decode((String) obj, 3));
        } catch (IOException | IllegalArgumentException unused) {
            StringBuilder w2 = a.w("Invalid byte[] value for ", this.zzc, ": ");
            w2.append((String) obj);
            Log.e("PhenotypeFlag", w2.toString());
            return null;
        }
    }
}
