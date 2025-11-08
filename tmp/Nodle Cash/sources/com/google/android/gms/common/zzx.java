package com.google.android.gms.common;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzx {
    private static final zzx zze = new zzx(true, 3, 1, (String) null, (Throwable) null);
    final boolean zza;
    @Nullable
    final String zzb;
    @Nullable
    final Throwable zzc;
    final int zzd;

    private zzx(boolean z2, int i3, int i4, @Nullable String str, @Nullable Throwable th) {
        this.zza = z2;
        this.zzd = i3;
        this.zzb = str;
        this.zzc = th;
    }

    @Deprecated
    public static zzx zzb() {
        return zze;
    }

    public static zzx zzc(@NonNull String str) {
        return new zzx(false, 1, 5, str, (Throwable) null);
    }

    public static zzx zzd(@NonNull String str, @NonNull Throwable th) {
        return new zzx(false, 1, 5, str, th);
    }

    public static zzx zzf(int i3) {
        return new zzx(true, i3, 1, (String) null, (Throwable) null);
    }

    public static zzx zzg(int i3, int i4, @NonNull String str, @Nullable Throwable th) {
        return new zzx(false, i3, i4, str, th);
    }

    @Nullable
    public String zza() {
        return this.zzb;
    }

    public final void zze() {
        if (!this.zza && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.zzc != null) {
                Log.d("GoogleCertificatesRslt", zza(), this.zzc);
            } else {
                Log.d("GoogleCertificatesRslt", zza());
            }
        }
    }
}
