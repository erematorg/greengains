package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzmj implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzbh zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzlp zzf;

    public zzmj(zzlp zzlp, boolean z2, zzp zzp, boolean z3, zzbh zzbh, String str) {
        this.zza = z2;
        this.zzb = zzp;
        this.zzc = z3;
        this.zzd = zzbh;
        this.zze = str;
        this.zzf = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzf.zzb;
        if (zza2 == null) {
            this.zzf.zzj().zzg().zza("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zza) {
            Preconditions.checkNotNull(this.zzb);
            this.zzf.zza(zza2, (AbstractSafeParcelable) this.zzc ? null : this.zzd, this.zzb);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze)) {
                    Preconditions.checkNotNull(this.zzb);
                    zza2.zza(this.zzd, this.zzb);
                } else {
                    zza2.zza(this.zzd, this.zze, this.zzf.zzj().zzx());
                }
            } catch (RemoteException e3) {
                this.zzf.zzj().zzg().zza("Failed to send event to the service", e3);
            }
        }
        this.zzf.zzar();
    }
}
