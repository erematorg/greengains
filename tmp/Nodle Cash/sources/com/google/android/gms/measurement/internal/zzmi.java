package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzmi implements Runnable {
    private final /* synthetic */ boolean zza = true;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzaf zzd;
    private final /* synthetic */ zzaf zze;
    private final /* synthetic */ zzlp zzf;

    public zzmi(zzlp zzlp, boolean z2, zzp zzp, boolean z3, zzaf zzaf, zzaf zzaf2) {
        this.zzb = zzp;
        this.zzc = z3;
        this.zzd = zzaf;
        this.zze = zzaf2;
        this.zzf = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzf.zzb;
        if (zza2 == null) {
            this.zzf.zzj().zzg().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zza) {
            Preconditions.checkNotNull(this.zzb);
            this.zzf.zza(zza2, (AbstractSafeParcelable) this.zzc ? null : this.zzd, this.zzb);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze.zza)) {
                    Preconditions.checkNotNull(this.zzb);
                    zza2.zza(this.zzd, this.zzb);
                } else {
                    zza2.zza(this.zzd);
                }
            } catch (RemoteException e3) {
                this.zzf.zzj().zzg().zza("Failed to send conditional user property to the service", e3);
            }
        }
        this.zzf.zzar();
    }
}
