package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public final class zzmm implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzlp zza;
    /* access modifiers changed from: private */
    public volatile boolean zzb;
    private volatile zzgj zzc;

    public zzmm(zzlp zzlp) {
        this.zza = zzlp;
    }

    @MainThread
    public final void onConnected(Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                Preconditions.checkNotNull(this.zzc);
                this.zza.zzl().zzb((Runnable) new zzmr(this, (zzgb) this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzgi zzm = this.zza.zzu.zzm();
        if (zzm != null) {
            zzm.zzu().zza("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzl().zzb((Runnable) new zzmt(this));
    }

    @MainThread
    public final void onConnectionSuspended(int i3) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zza.zzj().zzc().zza("Service connection suspended");
        this.zza.zzl().zzb((Runnable) new zzmq(this));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r3.zza.zzj().zzg().zza("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0060 */
    @androidx.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            if (r5 != 0) goto L_0x001f
            r3.zzb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzlp r4 = r3.zza     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzgi r4 = r4.zzj()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "Service connected with null binder"
            r4.zza(r5)     // Catch:{ all -> 0x001c }
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x001c:
            r4 = move-exception
            goto L_0x0097
        L_0x001f:
            r0 = 0
            java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0060 }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0060 }
            if (r2 == 0) goto L_0x0050
            java.lang.String r1 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x0060 }
            boolean r2 = r1 instanceof com.google.android.gms.measurement.internal.zzgb     // Catch:{ RemoteException -> 0x0060 }
            if (r2 == 0) goto L_0x003a
            com.google.android.gms.measurement.internal.zzgb r1 = (com.google.android.gms.measurement.internal.zzgb) r1     // Catch:{ RemoteException -> 0x0060 }
        L_0x0038:
            r0 = r1
            goto L_0x0040
        L_0x003a:
            com.google.android.gms.measurement.internal.zzgd r1 = new com.google.android.gms.measurement.internal.zzgd     // Catch:{ RemoteException -> 0x0060 }
            r1.<init>(r5)     // Catch:{ RemoteException -> 0x0060 }
            goto L_0x0038
        L_0x0040:
            com.google.android.gms.measurement.internal.zzlp r5 = r3.zza     // Catch:{ RemoteException -> 0x0060 }
            com.google.android.gms.measurement.internal.zzgi r5 = r5.zzj()     // Catch:{ RemoteException -> 0x0060 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzp()     // Catch:{ RemoteException -> 0x0060 }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.zza(r1)     // Catch:{ RemoteException -> 0x0060 }
            goto L_0x006f
        L_0x0050:
            com.google.android.gms.measurement.internal.zzlp r5 = r3.zza     // Catch:{ RemoteException -> 0x0060 }
            com.google.android.gms.measurement.internal.zzgi r5 = r5.zzj()     // Catch:{ RemoteException -> 0x0060 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ RemoteException -> 0x0060 }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.zza(r2, r1)     // Catch:{ RemoteException -> 0x0060 }
            goto L_0x006f
        L_0x0060:
            com.google.android.gms.measurement.internal.zzlp r5 = r3.zza     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzgi r5 = r5.zzj()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.zza(r1)     // Catch:{ all -> 0x001c }
        L_0x006f:
            if (r0 != 0) goto L_0x0087
            r3.zzb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.stats.ConnectionTracker r4 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0095 }
            com.google.android.gms.measurement.internal.zzlp r5 = r3.zza     // Catch:{ IllegalArgumentException -> 0x0095 }
            android.content.Context r5 = r5.zza()     // Catch:{ IllegalArgumentException -> 0x0095 }
            com.google.android.gms.measurement.internal.zzlp r0 = r3.zza     // Catch:{ IllegalArgumentException -> 0x0095 }
            com.google.android.gms.measurement.internal.zzmm r0 = r0.zza     // Catch:{ IllegalArgumentException -> 0x0095 }
            r4.unbindService(r5, r0)     // Catch:{ IllegalArgumentException -> 0x0095 }
            goto L_0x0095
        L_0x0087:
            com.google.android.gms.measurement.internal.zzlp r4 = r3.zza     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzhp r4 = r4.zzl()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzmp r5 = new com.google.android.gms.measurement.internal.zzmp     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x001c }
            r4.zzb((java.lang.Runnable) r5)     // Catch:{ all -> 0x001c }
        L_0x0095:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x0097:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzmm.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zza.zzj().zzc().zza("Service disconnected");
        this.zza.zzl().zzb((Runnable) new zzmo(this, componentName));
    }

    @WorkerThread
    public final void zzb() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }

    @WorkerThread
    public final void zza(Intent intent) {
        this.zza.zzt();
        Context zza2 = this.zza.zza();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            try {
                if (this.zzb) {
                    this.zza.zzj().zzp().zza("Connection attempt already in progress");
                    return;
                }
                this.zza.zzj().zzp().zza("Using local app measurement service");
                this.zzb = true;
                instance.bindService(zza2, intent, this.zza.zza, 129);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @WorkerThread
    public final void zza() {
        this.zza.zzt();
        Context zza2 = this.zza.zza();
        synchronized (this) {
            try {
                if (this.zzb) {
                    this.zza.zzj().zzp().zza("Connection attempt already in progress");
                } else if (this.zzc == null || (!this.zzc.isConnecting() && !this.zzc.isConnected())) {
                    this.zzc = new zzgj(zza2, Looper.getMainLooper(), this, this);
                    this.zza.zzj().zzp().zza("Connecting to remote service");
                    this.zzb = true;
                    Preconditions.checkNotNull(this.zzc);
                    this.zzc.checkAvailabilityAndConnect();
                } else {
                    this.zza.zzj().zzp().zza("Already awaiting connection attempt");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
