package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SleepSegmentRequest;
import com.google.android.gms.location.zzad;
import com.google.android.gms.location.zzb;

public interface zzv extends IInterface {
    @Deprecated
    void zzA(Location location) throws RemoteException;

    void zzB(Location location, IStatusCallback iStatusCallback) throws RemoteException;

    void zzC(zzr zzr) throws RemoteException;

    void zzD(LocationSettingsRequest locationSettingsRequest, zzab zzab, String str) throws RemoteException;

    void zzE(zzo zzo) throws RemoteException;

    void zzF(zzj zzj) throws RemoteException;

    @Deprecated
    void zzd(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzt zzt) throws RemoteException;

    void zze(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    @Deprecated
    void zzf(zzem zzem, zzt zzt) throws RemoteException;

    void zzg(zzem zzem, IStatusCallback iStatusCallback) throws RemoteException;

    void zzh(long j2, boolean z2, PendingIntent pendingIntent) throws RemoteException;

    void zzi(zzb zzb, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzj(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzk(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzl(PendingIntent pendingIntent) throws RemoteException;

    void zzm(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest, IStatusCallback iStatusCallback) throws RemoteException;

    void zzn(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzo(zzad zzad, zzee zzee) throws RemoteException;

    @Deprecated
    LocationAvailability zzp(String str) throws RemoteException;

    void zzq(LastLocationRequest lastLocationRequest, zzee zzee) throws RemoteException;

    @Deprecated
    void zzr(LastLocationRequest lastLocationRequest, zzz zzz) throws RemoteException;

    @Deprecated
    Location zzs() throws RemoteException;

    ICancelToken zzt(CurrentLocationRequest currentLocationRequest, zzee zzee) throws RemoteException;

    @Deprecated
    ICancelToken zzu(CurrentLocationRequest currentLocationRequest, zzz zzz) throws RemoteException;

    @Deprecated
    void zzv(zzei zzei) throws RemoteException;

    void zzw(zzee zzee, LocationRequest locationRequest, IStatusCallback iStatusCallback) throws RemoteException;

    void zzx(zzee zzee, IStatusCallback iStatusCallback) throws RemoteException;

    @Deprecated
    void zzy(boolean z2) throws RemoteException;

    void zzz(boolean z2, IStatusCallback iStatusCallback) throws RemoteException;
}
