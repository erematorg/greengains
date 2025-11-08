package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.collection.SimpleArrayMap;
import androidx.work.impl.a;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzad;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

public final class zzdz extends GmsClient {
    public static final /* synthetic */ int zze = 0;
    private final SimpleArrayMap zzf = new SimpleArrayMap();
    private final SimpleArrayMap zzg = new SimpleArrayMap();
    private final SimpleArrayMap zzh = new SimpleArrayMap();
    private final SimpleArrayMap zzi = new SimpleArrayMap();

    public zzdz(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    private final boolean zzG(Feature feature) {
        Feature feature2;
        Feature[] availableFeatures = getAvailableFeatures();
        if (availableFeatures != null) {
            int i3 = 0;
            while (true) {
                if (i3 >= availableFeatures.length) {
                    feature2 = null;
                    break;
                }
                feature2 = availableFeatures[i3];
                if (feature.getName().equals(feature2.getName())) {
                    break;
                }
                i3++;
            }
            if (feature2 != null && feature2.getVersion() >= feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzv ? (zzv) queryLocalInterface : new zzu(iBinder);
    }

    public final Feature[] getApiFeatures() {
        return zzo.zzp;
    }

    public final int getMinApkVersion() {
        return 11717000;
    }

    public final String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    public final String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    public final void onConnectionSuspended(int i3) {
        super.onConnectionSuspended(i3);
        synchronized (this.zzf) {
            this.zzf.clear();
        }
        synchronized (this.zzg) {
            this.zzg.clear();
        }
        synchronized (this.zzh) {
            this.zzh.clear();
        }
    }

    public final boolean usesClientTelemetry() {
        return true;
    }

    public final void zzA(Location location, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzh)) {
            ((zzv) getService()).zzB(location, new zzdj((Object) null, taskCompletionSource));
            return;
        }
        ((zzv) getService()).zzA(location);
        taskCompletionSource.setResult(null);
    }

    public final void zzB(TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzv) getService()).zzC(new zzdn((Object) null, taskCompletionSource));
    }

    public final void zzC(ListenerHolder listenerHolder, DeviceOrientationRequest deviceOrientationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ListenerHolder.ListenerKey listenerKey = listenerHolder.getListenerKey();
        Objects.requireNonNull(listenerKey);
        synchronized (this.zzh) {
            try {
                zzdq zzdq = (zzdq) this.zzh.get(listenerKey);
                if (zzdq == null) {
                    zzdq = new zzdq(listenerHolder);
                    this.zzh.put(listenerKey, zzdq);
                } else {
                    zzdq.zzc(listenerHolder);
                }
                ((zzv) getService()).zzF(new zzj(1, new zzh(deviceOrientationRequest, zzh.zza, (String) null), zzdq, new zzdn((Object) null, taskCompletionSource)));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzD(ListenerHolder.ListenerKey listenerKey, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzh) {
            try {
                zzdq zzdq = (zzdq) this.zzh.remove(listenerKey);
                if (zzdq == null) {
                    taskCompletionSource.setResult(Boolean.FALSE);
                    return;
                }
                zzdq.zze();
                ((zzv) getService()).zzF(new zzj(2, (zzh) null, zzdq, new zzdn(Boolean.TRUE, taskCompletionSource)));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzE(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzn)) {
            ((zzv) getService()).zze(geofencingRequest, pendingIntent, new zzdj((Object) null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzd(geofencingRequest, pendingIntent, new zzdg(taskCompletionSource));
        }
    }

    public final void zzF(zzem zzem, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzn)) {
            ((zzv) getService()).zzg(zzem, new zzdj((Object) null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzf(zzem, new zzdg(taskCompletionSource));
        }
    }

    public final void zzp(zzad zzad, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzo(zzad, new zzee(5, (IBinder) null, new zzdl(taskCompletionSource), (PendingIntent) null, (String) null));
        } else {
            taskCompletionSource.setResult(((zzv) getService()).zzp(getContext().getPackageName()));
        }
    }

    public final void zzq(LastLocationRequest lastLocationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzq(lastLocationRequest, zzee.zzd(new zzdk(taskCompletionSource)));
        } else if (zzG(zzo.zzf)) {
            ((zzv) getService()).zzr(lastLocationRequest, new zzdk(taskCompletionSource));
        } else {
            taskCompletionSource.setResult(((zzv) getService()).zzs());
        }
    }

    public final void zzr(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ICancelToken zzt = ((zzv) getService()).zzt(currentLocationRequest, zzee.zzd(new zzdk(taskCompletionSource)));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new zzed(zzt));
            }
        } else if (zzG(zzo.zze)) {
            ICancelToken zzu = ((zzv) getService()).zzu(currentLocationRequest, new zzdk(taskCompletionSource));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new zzeb(zzu));
            }
        } else {
            ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(new zzdh(this, taskCompletionSource), zzfc.zza(), "GetCurrentLocation");
            ListenerHolder.ListenerKey listenerKey = createListenerHolder.getListenerKey();
            Objects.requireNonNull(listenerKey);
            zzdi zzdi = new zzdi(this, createListenerHolder, taskCompletionSource);
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            LocationRequest.Builder builder = new LocationRequest.Builder(currentLocationRequest.getPriority(), 0);
            builder.setMinUpdateIntervalMillis(0);
            builder.setDurationMillis(currentLocationRequest.getDurationMillis());
            builder.setGranularity(currentLocationRequest.getGranularity());
            builder.setMaxUpdateAgeMillis(currentLocationRequest.getMaxUpdateAgeMillis());
            builder.zzb(currentLocationRequest.zza());
            builder.zza(currentLocationRequest.zzb());
            builder.setWaitForAccurateLocation(true);
            builder.zzc(currentLocationRequest.zzc());
            zzt(zzdi, builder.build(), taskCompletionSource2);
            taskCompletionSource2.getTask().addOnCompleteListener(new zzea(taskCompletionSource));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new zzec(this, listenerKey));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f A[Catch:{ all -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0056 A[Catch:{ all -> 0x002e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzs(com.google.android.gms.internal.location.zzdr r18, com.google.android.gms.location.LocationRequest r19, com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.ListenerHolder r3 = r18.zza()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = r3.getListenerKey()
            java.util.Objects.requireNonNull(r4)
            com.google.android.gms.common.Feature r5 = com.google.android.gms.location.zzo.zzj
            boolean r5 = r0.zzG(r5)
            androidx.collection.SimpleArrayMap r6 = r0.zzf
            monitor-enter(r6)
            androidx.collection.SimpleArrayMap r7 = r0.zzf     // Catch:{ all -> 0x002e }
            java.lang.Object r7 = r7.get(r4)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzdy r7 = (com.google.android.gms.internal.location.zzdy) r7     // Catch:{ all -> 0x002e }
            r8 = 0
            if (r7 == 0) goto L_0x0030
            if (r5 == 0) goto L_0x0028
            goto L_0x0030
        L_0x0028:
            r7.zzc(r3)     // Catch:{ all -> 0x002e }
            r12 = r7
            r7 = r8
            goto L_0x003d
        L_0x002e:
            r0 = move-exception
            goto L_0x0077
        L_0x0030:
            com.google.android.gms.internal.location.zzdy r3 = new com.google.android.gms.internal.location.zzdy     // Catch:{ all -> 0x002e }
            r9 = r18
            r3.<init>(r9)     // Catch:{ all -> 0x002e }
            androidx.collection.SimpleArrayMap r9 = r0.zzf     // Catch:{ all -> 0x002e }
            r9.put(r4, r3)     // Catch:{ all -> 0x002e }
            r12 = r3
        L_0x003d:
            if (r5 == 0) goto L_0x0056
            android.os.IInterface r0 = r17.getService()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.internal.location.zzv) r0     // Catch:{ all -> 0x002e }
            java.lang.String r3 = r4.toIdString()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzee r3 = com.google.android.gms.internal.location.zzee.zza(r7, r12, r3)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzdj r4 = new com.google.android.gms.internal.location.zzdj     // Catch:{ all -> 0x002e }
            r4.<init>(r8, r2)     // Catch:{ all -> 0x002e }
            r0.zzw(r3, r1, r4)     // Catch:{ all -> 0x002e }
            goto L_0x0075
        L_0x0056:
            android.os.IInterface r0 = r17.getService()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.internal.location.zzv) r0     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzeg r11 = com.google.android.gms.internal.location.zzeg.zza(r8, r1)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzdo r15 = new com.google.android.gms.internal.location.zzdo     // Catch:{ all -> 0x002e }
            r15.<init>(r2, r12)     // Catch:{ all -> 0x002e }
            java.lang.String r16 = r4.toIdString()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzei r1 = new com.google.android.gms.internal.location.zzei     // Catch:{ all -> 0x002e }
            r13 = 0
            r14 = 0
            r10 = 1
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x002e }
            r0.zzv(r1)     // Catch:{ all -> 0x002e }
        L_0x0075:
            monitor-exit(r6)     // Catch:{ all -> 0x002e }
            return
        L_0x0077:
            monitor-exit(r6)     // Catch:{ all -> 0x002e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzs(com.google.android.gms.internal.location.zzdr, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f A[Catch:{ all -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0056 A[Catch:{ all -> 0x002e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzt(com.google.android.gms.internal.location.zzdr r18, com.google.android.gms.location.LocationRequest r19, com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.ListenerHolder r3 = r18.zza()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = r3.getListenerKey()
            java.util.Objects.requireNonNull(r4)
            com.google.android.gms.common.Feature r5 = com.google.android.gms.location.zzo.zzj
            boolean r5 = r0.zzG(r5)
            androidx.collection.SimpleArrayMap r6 = r0.zzg
            monitor-enter(r6)
            androidx.collection.SimpleArrayMap r7 = r0.zzg     // Catch:{ all -> 0x002e }
            java.lang.Object r7 = r7.get(r4)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzdv r7 = (com.google.android.gms.internal.location.zzdv) r7     // Catch:{ all -> 0x002e }
            r8 = 0
            if (r7 == 0) goto L_0x0030
            if (r5 == 0) goto L_0x0028
            goto L_0x0030
        L_0x0028:
            r7.zzc(r3)     // Catch:{ all -> 0x002e }
            r13 = r7
            r7 = r8
            goto L_0x003d
        L_0x002e:
            r0 = move-exception
            goto L_0x0077
        L_0x0030:
            com.google.android.gms.internal.location.zzdv r3 = new com.google.android.gms.internal.location.zzdv     // Catch:{ all -> 0x002e }
            r9 = r18
            r3.<init>(r9)     // Catch:{ all -> 0x002e }
            androidx.collection.SimpleArrayMap r9 = r0.zzg     // Catch:{ all -> 0x002e }
            r9.put(r4, r3)     // Catch:{ all -> 0x002e }
            r13 = r3
        L_0x003d:
            if (r5 == 0) goto L_0x0056
            android.os.IInterface r0 = r17.getService()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.internal.location.zzv) r0     // Catch:{ all -> 0x002e }
            java.lang.String r3 = r4.toIdString()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzee r3 = com.google.android.gms.internal.location.zzee.zzb(r7, r13, r3)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzdj r4 = new com.google.android.gms.internal.location.zzdj     // Catch:{ all -> 0x002e }
            r4.<init>(r8, r2)     // Catch:{ all -> 0x002e }
            r0.zzw(r3, r1, r4)     // Catch:{ all -> 0x002e }
            goto L_0x0075
        L_0x0056:
            android.os.IInterface r0 = r17.getService()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzv r0 = (com.google.android.gms.internal.location.zzv) r0     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzeg r11 = com.google.android.gms.internal.location.zzeg.zza(r8, r1)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzdd r15 = new com.google.android.gms.internal.location.zzdd     // Catch:{ all -> 0x002e }
            r15.<init>(r2, r13)     // Catch:{ all -> 0x002e }
            java.lang.String r16 = r4.toIdString()     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.location.zzei r1 = new com.google.android.gms.internal.location.zzei     // Catch:{ all -> 0x002e }
            r12 = 0
            r14 = 0
            r10 = 1
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x002e }
            r0.zzv(r1)     // Catch:{ all -> 0x002e }
        L_0x0075:
            monitor-exit(r6)     // Catch:{ all -> 0x002e }
            return
        L_0x0077:
            monitor-exit(r6)     // Catch:{ all -> 0x002e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzt(com.google.android.gms.internal.location.zzdr, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public final void zzu(PendingIntent pendingIntent, LocationRequest locationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzw(zzee.zzc(pendingIntent), locationRequest, new zzdj((Object) null, taskCompletionSource));
            return;
        }
        zzeg zza = zzeg.zza((String) null, locationRequest);
        zzdn zzdn = new zzdn((Object) null, taskCompletionSource);
        int hashCode = pendingIntent.hashCode();
        ((zzv) getService()).zzv(new zzei(1, zza, (IBinder) null, (IBinder) null, pendingIntent, zzdn, a.u(new StringBuilder(String.valueOf(hashCode).length() + 14), "PendingIntent@", hashCode)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzv(com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey r10, boolean r11, com.google.android.gms.tasks.TaskCompletionSource r12) throws android.os.RemoteException {
        /*
            r9 = this;
            androidx.collection.SimpleArrayMap r0 = r9.zzf
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap r1 = r9.zzf     // Catch:{ all -> 0x0015 }
            java.lang.Object r10 = r1.remove(r10)     // Catch:{ all -> 0x0015 }
            r4 = r10
            com.google.android.gms.internal.location.zzdy r4 = (com.google.android.gms.internal.location.zzdy) r4     // Catch:{ all -> 0x0015 }
            if (r4 != 0) goto L_0x0017
            java.lang.Boolean r9 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0015 }
            r12.setResult(r9)     // Catch:{ all -> 0x0015 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x0015:
            r9 = move-exception
            goto L_0x007c
        L_0x0017:
            r4.zzf()     // Catch:{ all -> 0x0015 }
            if (r11 == 0) goto L_0x0075
            com.google.android.gms.common.Feature r10 = com.google.android.gms.location.zzo.zzj     // Catch:{ all -> 0x0015 }
            boolean r10 = r9.zzG(r10)     // Catch:{ all -> 0x0015 }
            if (r10 == 0) goto L_0x0059
            android.os.IInterface r9 = r9.getService()     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzv r9 = (com.google.android.gms.internal.location.zzv) r9     // Catch:{ all -> 0x0015 }
            java.lang.String r10 = "ILocationListener@"
            int r11 = java.lang.System.identityHashCode(r4)     // Catch:{ all -> 0x0015 }
            java.lang.String r1 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0015 }
            int r1 = r1.length()     // Catch:{ all -> 0x0015 }
            int r1 = r1 + 18
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0015 }
            r2.<init>(r1)     // Catch:{ all -> 0x0015 }
            r2.append(r10)     // Catch:{ all -> 0x0015 }
            r2.append(r11)     // Catch:{ all -> 0x0015 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x0015 }
            r11 = 0
            com.google.android.gms.internal.location.zzee r10 = com.google.android.gms.internal.location.zzee.zza(r11, r4, r10)     // Catch:{ all -> 0x0015 }
            java.lang.Boolean r11 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzdj r1 = new com.google.android.gms.internal.location.zzdj     // Catch:{ all -> 0x0015 }
            r1.<init>(r11, r12)     // Catch:{ all -> 0x0015 }
            r9.zzx(r10, r1)     // Catch:{ all -> 0x0015 }
            goto L_0x007a
        L_0x0059:
            android.os.IInterface r9 = r9.getService()     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzv r9 = (com.google.android.gms.internal.location.zzv) r9     // Catch:{ all -> 0x0015 }
            java.lang.Boolean r10 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzdn r7 = new com.google.android.gms.internal.location.zzdn     // Catch:{ all -> 0x0015 }
            r7.<init>(r10, r12)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzei r10 = new com.google.android.gms.internal.location.zzei     // Catch:{ all -> 0x0015 }
            r6 = 0
            r8 = 0
            r2 = 2
            r3 = 0
            r5 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0015 }
            r9.zzv(r10)     // Catch:{ all -> 0x0015 }
            goto L_0x007a
        L_0x0075:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0015 }
            r12.setResult(r9)     // Catch:{ all -> 0x0015 }
        L_0x007a:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x007c:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzv(com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey, boolean, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzw(com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey r10, boolean r11, com.google.android.gms.tasks.TaskCompletionSource r12) throws android.os.RemoteException {
        /*
            r9 = this;
            androidx.collection.SimpleArrayMap r0 = r9.zzg
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap r1 = r9.zzg     // Catch:{ all -> 0x0015 }
            java.lang.Object r10 = r1.remove(r10)     // Catch:{ all -> 0x0015 }
            r5 = r10
            com.google.android.gms.internal.location.zzdv r5 = (com.google.android.gms.internal.location.zzdv) r5     // Catch:{ all -> 0x0015 }
            if (r5 != 0) goto L_0x0017
            java.lang.Boolean r9 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0015 }
            r12.setResult(r9)     // Catch:{ all -> 0x0015 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x0015:
            r9 = move-exception
            goto L_0x007c
        L_0x0017:
            r5.zzg()     // Catch:{ all -> 0x0015 }
            if (r11 == 0) goto L_0x0075
            com.google.android.gms.common.Feature r10 = com.google.android.gms.location.zzo.zzj     // Catch:{ all -> 0x0015 }
            boolean r10 = r9.zzG(r10)     // Catch:{ all -> 0x0015 }
            if (r10 == 0) goto L_0x0059
            android.os.IInterface r9 = r9.getService()     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzv r9 = (com.google.android.gms.internal.location.zzv) r9     // Catch:{ all -> 0x0015 }
            java.lang.String r10 = "ILocationCallback@"
            int r11 = java.lang.System.identityHashCode(r5)     // Catch:{ all -> 0x0015 }
            java.lang.String r1 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0015 }
            int r1 = r1.length()     // Catch:{ all -> 0x0015 }
            int r1 = r1 + 18
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0015 }
            r2.<init>(r1)     // Catch:{ all -> 0x0015 }
            r2.append(r10)     // Catch:{ all -> 0x0015 }
            r2.append(r11)     // Catch:{ all -> 0x0015 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x0015 }
            r11 = 0
            com.google.android.gms.internal.location.zzee r10 = com.google.android.gms.internal.location.zzee.zzb(r11, r5, r10)     // Catch:{ all -> 0x0015 }
            java.lang.Boolean r11 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzdj r1 = new com.google.android.gms.internal.location.zzdj     // Catch:{ all -> 0x0015 }
            r1.<init>(r11, r12)     // Catch:{ all -> 0x0015 }
            r9.zzx(r10, r1)     // Catch:{ all -> 0x0015 }
            goto L_0x007a
        L_0x0059:
            android.os.IInterface r9 = r9.getService()     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzv r9 = (com.google.android.gms.internal.location.zzv) r9     // Catch:{ all -> 0x0015 }
            java.lang.Boolean r10 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzdn r7 = new com.google.android.gms.internal.location.zzdn     // Catch:{ all -> 0x0015 }
            r7.<init>(r10, r12)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.internal.location.zzei r10 = new com.google.android.gms.internal.location.zzei     // Catch:{ all -> 0x0015 }
            r6 = 0
            r8 = 0
            r2 = 2
            r3 = 0
            r4 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0015 }
            r9.zzv(r10)     // Catch:{ all -> 0x0015 }
            goto L_0x007a
        L_0x0075:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0015 }
            r12.setResult(r9)     // Catch:{ all -> 0x0015 }
        L_0x007a:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x007c:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzdz.zzw(com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey, boolean, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public final void zzx(PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource, Object obj) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzx(zzee.zzc(pendingIntent), new zzdj((Object) null, taskCompletionSource));
            return;
        }
        ((zzv) getService()).zzv(new zzei(2, (zzeg) null, (IBinder) null, (IBinder) null, pendingIntent, new zzdn((Object) null, taskCompletionSource), (String) null));
    }

    public final void zzy(TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzg)) {
            ((zzv) getService()).zzz(true, new zzdj((Object) null, taskCompletionSource));
            return;
        }
        ((zzv) getService()).zzy(true);
        taskCompletionSource.setResult(null);
    }

    public final void zzz(TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzg)) {
            ((zzv) getService()).zzz(false, new zzdj(Boolean.TRUE, taskCompletionSource));
            return;
        }
        ((zzv) getService()).zzy(false);
        taskCompletionSource.setResult(Boolean.TRUE);
    }
}
