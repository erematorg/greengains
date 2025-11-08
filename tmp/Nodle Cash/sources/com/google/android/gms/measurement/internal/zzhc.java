package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.internal.measurement.zzby;
import com.google.android.gms.internal.measurement.zzbz;

public final class zzhc implements ServiceConnection {
    final /* synthetic */ zzhd zza;
    /* access modifiers changed from: private */
    public final String zzb;

    public zzhc(zzhd zzhd, String str) {
        this.zza = zzhd;
        this.zzb = str;
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zza.zza.zzj().zzu().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            zzbz zza2 = zzby.zza(iBinder);
            if (zza2 == null) {
                this.zza.zza.zzj().zzu().zza("Install Referrer Service implementation was not found");
                return;
            }
            this.zza.zza.zzj().zzp().zza("Install Referrer Service connected");
            this.zza.zza.zzl().zzb((Runnable) new zzhf(this, zza2, this));
        } catch (RuntimeException e3) {
            this.zza.zza.zzj().zzu().zza("Exception occurred while calling Install Referrer API", e3);
        }
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzj().zzp().zza("Install Referrer Service disconnected");
    }
}
