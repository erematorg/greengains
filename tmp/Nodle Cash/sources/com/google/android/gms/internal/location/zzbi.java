package com.google.android.gms.internal.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.DeviceOrientationListener;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

public final class zzbi extends GoogleApi implements FusedLocationProviderClient {
    static final Api.ClientKey zza;
    public static final Api zzb;
    private static final Object zzc = new Object();
    @GuardedBy("mockModeMarkerLock")
    private static Object zzd;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzb = new Api("LocationServices.API", new zzbf(), clientKey);
    }

    public zzbi(Activity activity) {
        super(activity, zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private final Task zza(LocationRequest locationRequest, ListenerHolder listenerHolder) {
        zzbh zzbh = new zzbh(this, listenerHolder, zzcd.zza);
        return doRegisterEventListener(RegistrationMethods.builder().register(new zzbt(zzbh, locationRequest)).unregister(zzbh).withHolder(listenerHolder).setMethodKey(2435).build());
    }

    private final Task zzb(LocationRequest locationRequest, ListenerHolder listenerHolder) {
        zzbh zzbh = new zzbh(this, listenerHolder, zzbz.zza);
        return doRegisterEventListener(RegistrationMethods.builder().register(new zzbu(zzbh, locationRequest)).unregister(zzbh).withHolder(listenerHolder).setMethodKey(2436).build());
    }

    private final Task zzc(DeviceOrientationRequest deviceOrientationRequest, ListenerHolder listenerHolder) {
        zzbm zzbm = new zzbm(listenerHolder, deviceOrientationRequest);
        return doRegisterEventListener(RegistrationMethods.builder().register(zzbm).unregister(new zzbn(listenerHolder)).withHolder(listenerHolder).setMethodKey(2434).build());
    }

    public final Task<Void> flushLocations() {
        return doWrite(TaskApiCall.builder().run(zzca.zza).setMethodKey(2422).build());
    }

    public final String getApiFallbackAttributionTag(Context context) {
        return null;
    }

    public final Task<Location> getCurrentLocation(int i3, CancellationToken cancellationToken) {
        CurrentLocationRequest.Builder builder = new CurrentLocationRequest.Builder();
        builder.setPriority(i3);
        CurrentLocationRequest build = builder.build();
        if (cancellationToken != null) {
            Preconditions.checkArgument(!cancellationToken.isCancellationRequested(), "cancellationToken may not be already canceled");
        }
        Task<Location> doRead = doRead(TaskApiCall.builder().run(new zzbp(build, cancellationToken)).setMethodKey(2415).build());
        if (cancellationToken == null) {
            return doRead;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWith(new zzbq(taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public final Task<Location> getLastLocation() {
        return doRead(TaskApiCall.builder().run(zzby.zza).setMethodKey(2414).build());
    }

    public final Task<LocationAvailability> getLocationAvailability() {
        return doRead(TaskApiCall.builder().run(zzbr.zza).setMethodKey(2416).build());
    }

    public final Task<Void> removeDeviceOrientationUpdates(DeviceOrientationListener deviceOrientationListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(deviceOrientationListener, "DeviceOrientationListener"), 2440).continueWith(zzcg.zza, zzbo.zza);
    }

    public final Task<Void> removeLocationUpdates(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzbx(pendingIntent)).setMethodKey(2418).build());
    }

    public final Task<Void> requestDeviceOrientationUpdates(DeviceOrientationRequest deviceOrientationRequest, DeviceOrientationListener deviceOrientationListener, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            Preconditions.checkNotNull(looper, "invalid null looper");
        }
        return zzc(deviceOrientationRequest, ListenerHolders.createListenerHolder(deviceOrientationListener, looper, "DeviceOrientationListener"));
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzbs(pendingIntent, locationRequest)).setMethodKey(2417).build());
    }

    public final Task<Void> setMockLocation(Location location) {
        Preconditions.checkArgument(location != null);
        return doWrite(TaskApiCall.builder().run(new zzbl(location)).setMethodKey(2421).build());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        return com.google.android.gms.tasks.Tasks.forResult(null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> setMockMode(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = zzc
            monitor-enter(r0)
            r1 = 0
            r2 = 2420(0x974, float:3.391E-42)
            if (r6 == 0) goto L_0x0041
            java.lang.Object r6 = zzd     // Catch:{ all -> 0x003f }
            if (r6 != 0) goto L_0x005b
            java.lang.Object r6 = new java.lang.Object     // Catch:{ all -> 0x003f }
            r6.<init>()     // Catch:{ all -> 0x003f }
            zzd = r6     // Catch:{ all -> 0x003f }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r1 = com.google.android.gms.common.api.internal.RegistrationMethods.builder()     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.location.zzcb r3 = com.google.android.gms.internal.location.zzcb.zza     // Catch:{ all -> 0x003f }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r1 = r1.register(r3)     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.location.zzcc r3 = com.google.android.gms.internal.location.zzcc.zza     // Catch:{ all -> 0x003f }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r1 = r1.unregister(r3)     // Catch:{ all -> 0x003f }
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x003f }
            java.lang.String r4 = "Object"
            com.google.android.gms.common.api.internal.ListenerHolder r6 = com.google.android.gms.common.api.internal.ListenerHolders.createListenerHolder(r6, (android.os.Looper) r3, (java.lang.String) r4)     // Catch:{ all -> 0x003f }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r6 = r1.withHolder(r6)     // Catch:{ all -> 0x003f }
            com.google.android.gms.common.api.internal.RegistrationMethods$Builder r6 = r6.setMethodKey(r2)     // Catch:{ all -> 0x003f }
            com.google.android.gms.common.api.internal.RegistrationMethods r6 = r6.build()     // Catch:{ all -> 0x003f }
            com.google.android.gms.tasks.Task r5 = r5.doRegisterEventListener(r6)     // Catch:{ all -> 0x003f }
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return r5
        L_0x003f:
            r5 = move-exception
            goto L_0x0061
        L_0x0041:
            java.lang.Object r6 = zzd     // Catch:{ all -> 0x003f }
            if (r6 == 0) goto L_0x005b
            zzd = r1     // Catch:{ all -> 0x003f }
            java.lang.String r1 = "Object"
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r6 = com.google.android.gms.common.api.internal.ListenerHolders.createListenerKey(r6, r1)     // Catch:{ all -> 0x003f }
            com.google.android.gms.tasks.Task r5 = r5.doUnregisterEventListener(r6, r2)     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.location.zzcf r6 = com.google.android.gms.internal.location.zzcf.zza     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.location.zzbk r1 = com.google.android.gms.internal.location.zzbk.zza     // Catch:{ all -> 0x003f }
            com.google.android.gms.tasks.Task r5 = r5.continueWith(r6, r1)     // Catch:{ all -> 0x003f }
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return r5
        L_0x005b:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            com.google.android.gms.tasks.Task r5 = com.google.android.gms.tasks.Tasks.forResult(r1)
            return r5
        L_0x0061:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzbi.setMockMode(boolean):com.google.android.gms.tasks.Task");
    }

    public zzbi(Context context) {
        super(context, zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Location> getLastLocation(LastLocationRequest lastLocationRequest) {
        return doRead(TaskApiCall.builder().run(new zzbj(lastLocationRequest)).setMethodKey(2414).setFeatures(zzo.zzf).build());
    }

    public final Task<Void> removeLocationUpdates(LocationCallback locationCallback) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(locationCallback, "LocationCallback"), 2418).continueWith(zzce.zza, zzbw.zza);
    }

    public final Task<Void> requestDeviceOrientationUpdates(DeviceOrientationRequest deviceOrientationRequest, Executor executor, DeviceOrientationListener deviceOrientationListener) {
        return zzc(deviceOrientationRequest, ListenerHolders.createListenerHolder(deviceOrientationListener, executor, "DeviceOrientationListener"));
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            Preconditions.checkNotNull(looper, "invalid null looper");
        }
        return zzb(locationRequest, ListenerHolders.createListenerHolder(locationCallback, looper, "LocationCallback"));
    }

    public final Task<Void> removeLocationUpdates(LocationListener locationListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(locationListener, "LocationListener"), 2418).continueWith(zzch.zza, zzbv.zza);
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            Preconditions.checkNotNull(looper, "invalid null looper");
        }
        return zza(locationRequest, ListenerHolders.createListenerHolder(locationListener, looper, "LocationListener"));
    }

    public final Task<Location> getCurrentLocation(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken) {
        if (cancellationToken != null) {
            Preconditions.checkArgument(!cancellationToken.isCancellationRequested(), "cancellationToken may not be already canceled");
        }
        Task<Location> doRead = doRead(TaskApiCall.builder().run(new zzbp(currentLocationRequest, cancellationToken)).setMethodKey(2415).build());
        if (cancellationToken == null) {
            return doRead;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWith(new zzbq(taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, Executor executor, LocationCallback locationCallback) {
        return zzb(locationRequest, ListenerHolders.createListenerHolder(locationCallback, executor, "LocationCallback"));
    }

    public final Task<Void> requestLocationUpdates(LocationRequest locationRequest, Executor executor, LocationListener locationListener) {
        return zza(locationRequest, ListenerHolders.createListenerHolder(locationListener, executor, "LocationListener"));
    }
}
