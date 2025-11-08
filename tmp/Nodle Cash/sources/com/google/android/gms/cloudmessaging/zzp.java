package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.cloudmessaging.zzf;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

final class zzp implements ServiceConnection {
    int zza = 0;
    final Messenger zzb = new Messenger(new zzf(Looper.getMainLooper(), new zzm(this)));
    zzq zzc;
    final Queue zzd = new ArrayDeque();
    final SparseArray zze = new SparseArray();
    final /* synthetic */ zzv zzf;

    public /* synthetic */ zzp(zzv zzv, zzo zzo) {
        this.zzf = zzv;
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        this.zzf.zzc.execute(new zzi(this, iBinder));
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        this.zzf.zzc.execute(new zzl(this));
    }

    public final synchronized void zza(int i3, @Nullable String str) {
        zzb(i3, str, (Throwable) null);
    }

    public final synchronized void zzb(int i3, @Nullable String str, @Nullable Throwable th) {
        try {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                Log.d("MessengerIpcClient", "Disconnected: ".concat(String.valueOf(str)));
            }
            int i4 = this.zza;
            if (i4 == 0) {
                throw new IllegalStateException();
            } else if (i4 == 1 || i4 == 2) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.zza = 4;
                ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
                zzt zzt = new zzt(i3, str, th);
                for (zzs zzc2 : this.zzd) {
                    zzc2.zzc(zzt);
                }
                this.zzd.clear();
                for (int i5 = 0; i5 < this.zze.size(); i5++) {
                    ((zzs) this.zze.valueAt(i5)).zzc(zzt);
                }
                this.zze.clear();
            } else if (i4 == 3) {
                this.zza = 4;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final void zzc() {
        this.zzf.zzc.execute(new zzj(this));
    }

    public final synchronized void zzd() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    public final synchronized void zze(int i3) {
        zzs zzs = (zzs) this.zze.get(i3);
        if (zzs != null) {
            Log.w("MessengerIpcClient", "Timing out request: " + i3);
            this.zze.remove(i3);
            zzs.zzc(new zzt(3, "Timed out waiting for response", (Throwable) null));
            zzf();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzf() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.zza     // Catch:{ all -> 0x0026 }
            r1 = 2
            if (r0 != r1) goto L_0x003a
            java.util.Queue r0 = r2.zzd     // Catch:{ all -> 0x0026 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x003a
            android.util.SparseArray r0 = r2.zze     // Catch:{ all -> 0x0026 }
            int r0 = r0.size()     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = "MessengerIpcClient"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0028
            java.lang.String r0 = "MessengerIpcClient"
            java.lang.String r1 = "Finished handling requests, unbinding"
            android.util.Log.v(r0, r1)     // Catch:{ all -> 0x0026 }
            goto L_0x0028
        L_0x0026:
            r0 = move-exception
            goto L_0x003c
        L_0x0028:
            r0 = 3
            r2.zza = r0     // Catch:{ all -> 0x0026 }
            com.google.android.gms.cloudmessaging.zzv r0 = r2.zzf     // Catch:{ all -> 0x0026 }
            com.google.android.gms.common.stats.ConnectionTracker r1 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ all -> 0x0026 }
            android.content.Context r0 = r0.zzb     // Catch:{ all -> 0x0026 }
            r1.unbindService(r0, r2)     // Catch:{ all -> 0x0026 }
            monitor-exit(r2)
            return
        L_0x003a:
            monitor-exit(r2)
            return
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzp.zzf():void");
    }

    public final synchronized boolean zzg(zzs zzs) {
        int i3 = this.zza;
        if (i3 == 0) {
            this.zzd.add(zzs);
            Preconditions.checkState(this.zza == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.zza = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            try {
                if (!ConnectionTracker.getInstance().bindService(this.zzf.zzb, intent, this, 1)) {
                    zza(0, "Unable to bind to service");
                } else {
                    this.zzf.zzc.schedule(new zzk(this), 30, TimeUnit.SECONDS);
                }
            } catch (SecurityException e3) {
                zzb(0, "Unable to bind to service", e3);
            }
        } else if (i3 == 1) {
            this.zzd.add(zzs);
            return true;
        } else if (i3 != 2) {
            return false;
        } else {
            this.zzd.add(zzs);
            zzc();
            return true;
        }
        return true;
    }
}
