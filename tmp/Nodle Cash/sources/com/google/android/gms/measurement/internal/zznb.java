package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzdh;
import org.checkerframework.dataflow.qual.Pure;

public final class zznb extends zzf {
    protected final zznj zza = new zznj(this);
    protected final zznh zzb = new zznh(this);
    /* access modifiers changed from: private */
    public Handler zzc;
    private boolean zzd = true;
    private final zznc zze = new zznc(this);

    public zznb(zzhw zzhw) {
        super(zzhw);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzab() {
        zzt();
        if (this.zzc == null) {
            this.zzc = new zzdh(Looper.getMainLooper());
        }
    }

    @WorkerThread
    public final boolean zzaa() {
        zzt();
        return this.zzd;
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgc zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjk zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlg zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzlp zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public static /* synthetic */ void zza(zznb zznb, long j2) {
        zznb.zzt();
        zznb.zzab();
        zznb.zzj().zzp().zza("Activity paused, time", Long.valueOf(j2));
        zznb.zze.zza(j2);
        if (zznb.zze().zzy()) {
            zznb.zzb.zzb(j2);
        }
    }

    public static /* synthetic */ void zzb(zznb zznb, long j2) {
        zznb.zzt();
        zznb.zzab();
        zznb.zzj().zzp().zza("Activity resumed, time", Long.valueOf(j2));
        if (zznb.zze().zza(zzbj.zzcn)) {
            if (zznb.zze().zzy() || zznb.zzd) {
                zznb.zzb.zzc(j2);
            }
        } else if (zznb.zze().zzy() || zznb.zzk().zzn.zza()) {
            zznb.zzb.zzc(j2);
        }
        zznb.zze.zza();
        zznj zznj = zznb.zza;
        zznj.zza.zzt();
        if (zznj.zza.zzu.zzac()) {
            zznj.zza(zznj.zza.zzb().currentTimeMillis(), false);
        }
    }

    @WorkerThread
    public final void zza(boolean z2) {
        zzt();
        this.zzd = z2;
    }

    public final boolean zza(boolean z2, boolean z3, long j2) {
        return this.zzb.zza(z2, z3, j2);
    }
}
