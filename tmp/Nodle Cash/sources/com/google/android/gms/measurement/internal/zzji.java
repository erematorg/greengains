package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdt;

@VisibleForTesting
public final class zzji {
    final Context zza;
    @Nullable
    String zzb;
    @Nullable
    String zzc;
    @Nullable
    String zzd;
    @Nullable
    Boolean zze;
    long zzf;
    @Nullable
    zzdt zzg;
    boolean zzh = true;
    @Nullable
    Long zzi;
    @Nullable
    String zzj;

    @VisibleForTesting
    public zzji(Context context, @Nullable zzdt zzdt, @Nullable Long l2) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l2;
        if (zzdt != null) {
            this.zzg = zzdt;
            this.zzb = zzdt.zzf;
            this.zzc = zzdt.zze;
            this.zzd = zzdt.zzd;
            this.zzh = zzdt.zzc;
            this.zzf = zzdt.zzb;
            this.zzj = zzdt.zzh;
            Bundle bundle = zzdt.zzg;
            if (bundle != null) {
                this.zze = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
