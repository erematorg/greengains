package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzme  reason: invalid package */
public final class zzme implements zzce {
    private final SharedPreferences.Editor zza;
    private final String zzb;

    public zzme(Context context, String str, String str2) {
        if (str != null) {
            this.zzb = str;
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
            } else {
                this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
            }
        } else {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
    }

    public final void zza(zztx zztx) throws IOException {
        if (!this.zza.putString(this.zzb, zzxl.zza(zztx.a_())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    public final void zza(zzvg zzvg) throws IOException {
        if (!this.zza.putString(this.zzb, zzxl.zza(zzvg.a_())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
