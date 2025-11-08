package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
public final class zze implements ServiceConnection {
    final /* synthetic */ BaseGmsClient zza;
    private final int zzb;

    public zze(BaseGmsClient baseGmsClient, int i3) {
        this.zza = baseGmsClient;
        this.zzb = i3;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            BaseGmsClient.zzk(this.zza, 16);
            return;
        }
        synchronized (this.zza.zzq) {
            try {
                BaseGmsClient baseGmsClient = this.zza;
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                baseGmsClient.zzr = (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) ? new zzad(iBinder) : (IGmsServiceBroker) queryLocalInterface;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.zza.zzl(0, (Bundle) null, this.zzb);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zza.zzq) {
            this.zza.zzr = null;
        }
        BaseGmsClient baseGmsClient = this.zza;
        int i3 = this.zzb;
        Handler handler = baseGmsClient.zzb;
        handler.sendMessage(handler.obtainMessage(6, i3, 1));
    }
}
