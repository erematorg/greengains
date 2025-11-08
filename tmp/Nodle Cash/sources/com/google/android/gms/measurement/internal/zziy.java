package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

class zziy implements zzja {
    protected final zzhw zzu;

    public zziy(zzhw zzhw) {
        Preconditions.checkNotNull(zzhw);
        this.zzu = zzhw;
    }

    @Pure
    public Context zza() {
        return this.zzu.zza();
    }

    @Pure
    public Clock zzb() {
        return this.zzu.zzb();
    }

    @Pure
    public zzac zzd() {
        return this.zzu.zzd();
    }

    @Pure
    public zzah zze() {
        return this.zzu.zzf();
    }

    @Pure
    public zzbb zzf() {
        return this.zzu.zzg();
    }

    @Pure
    public zzgh zzi() {
        return this.zzu.zzk();
    }

    @Pure
    public zzgi zzj() {
        return this.zzu.zzj();
    }

    @Pure
    public zzgu zzk() {
        return this.zzu.zzn();
    }

    @Pure
    public zzhp zzl() {
        return this.zzu.zzl();
    }

    @Pure
    public zzop zzq() {
        return this.zzu.zzt();
    }

    public void zzr() {
        this.zzu.zzl().zzr();
    }

    public void zzs() {
        this.zzu.zzy();
    }

    public void zzt() {
        this.zzu.zzl().zzt();
    }
}
