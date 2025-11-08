package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class zzmn implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzp zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ zzlp zzg;

    public zzmn(zzlp zzlp, AtomicReference atomicReference, String str, String str2, String str3, zzp zzp, boolean z2) {
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = zzp;
        this.zzf = z2;
        this.zzg = zzlp;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                zzgb zza2 = this.zzg.zzb;
                if (zza2 == null) {
                    this.zzg.zzj().zzg().zza("(legacy) Failed to get user properties; not connected to service", zzgi.zza(this.zzb), this.zzc, this.zzd);
                    this.zza.set(Collections.emptyList());
                    this.zza.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zzb)) {
                    Preconditions.checkNotNull(this.zze);
                    this.zza.set(zza2.zza(this.zzc, this.zzd, this.zzf, this.zze));
                } else {
                    this.zza.set(zza2.zza(this.zzb, this.zzc, this.zzd, this.zzf));
                }
                this.zzg.zzar();
                this.zza.notify();
            } catch (RemoteException e3) {
                try {
                    this.zzg.zzj().zzg().zza("(legacy) Failed to get user properties; remote exception", zzgi.zza(this.zzb), this.zzc, e3);
                    this.zza.set(Collections.emptyList());
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
