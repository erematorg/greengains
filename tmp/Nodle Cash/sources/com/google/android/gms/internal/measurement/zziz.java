package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import com.google.common.base.Function;
import javax.annotation.Nullable;

public final class zziz {
    final String zza;
    final Uri zzb;
    final String zzc;
    final String zzd;
    final boolean zze;
    final boolean zzf;
    final boolean zzg;
    @Nullable
    final Function<Context, Boolean> zzh;
    private final boolean zzi;

    public zziz(Uri uri) {
        this((String) null, uri, "", "", false, false, false, false, (Function<Context, Boolean>) null);
    }

    public final zziz zza() {
        return new zziz(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, true, this.zzg, this.zzh);
    }

    public final zziz zzb() {
        if (this.zzc.isEmpty()) {
            Function<Context, Boolean> function = this.zzh;
            if (function == null) {
                return new zziz(this.zza, this.zzb, this.zzc, this.zzd, true, this.zzf, this.zzi, this.zzg, function);
            }
            throw new IllegalStateException("Cannot skip gservices both always and conditionally");
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    private zziz(String str, Uri uri, String str2, String str3, boolean z2, boolean z3, boolean z4, boolean z5, @Nullable Function<Context, Boolean> function) {
        this.zza = str;
        this.zzb = uri;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = z2;
        this.zzf = z3;
        this.zzi = z4;
        this.zzg = z5;
        this.zzh = function;
    }

    public final zzir<Double> zza(String str, double d2) {
        return zzir.zza(this, str, Double.valueOf(-3.0d), true);
    }

    public final zzir<Long> zza(String str, long j2) {
        return zzir.zza(this, str, Long.valueOf(j2), true);
    }

    public final zzir<String> zza(String str, String str2) {
        return zzir.zza(this, str, str2, true);
    }

    public final zzir<Boolean> zza(String str, boolean z2) {
        return zzir.zza(this, str, Boolean.valueOf(z2), true);
    }
}
