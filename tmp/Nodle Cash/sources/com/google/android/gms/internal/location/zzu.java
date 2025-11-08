package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
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

public final class zzu extends zza implements zzv {
    public zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final void zzA(Location location) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, location);
        zzc(13, zza);
    }

    public final void zzB(Location location, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, location);
        zzc.zzc(zza, iStatusCallback);
        zzc(85, zza);
    }

    public final void zzC(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzr);
        zzc(67, zza);
    }

    public final void zzD(LocationSettingsRequest locationSettingsRequest, zzab zzab, String str) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, locationSettingsRequest);
        zzc.zzc(zza, zzab);
        zza.writeString((String) null);
        zzc(63, zza);
    }

    public final void zzE(zzo zzo) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzo);
        zzc(95, zza);
    }

    public final void zzF(zzj zzj) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzj);
        zzc(75, zza);
    }

    public final void zzd(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzt zzt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, geofencingRequest);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, zzt);
        zzc(57, zza);
    }

    public final void zze(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, geofencingRequest);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        zzc(97, zza);
    }

    public final void zzf(zzem zzem, zzt zzt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzem);
        zzc.zzc(zza, zzt);
        zzc(74, zza);
    }

    public final void zzg(zzem zzem, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzem);
        zzc.zzc(zza, iStatusCallback);
        zzc(98, zza);
    }

    public final void zzh(long j2, boolean z2, PendingIntent pendingIntent) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j2);
        int i3 = zzc.zza;
        zza.writeInt(1);
        zzc.zzb(zza, pendingIntent);
        zzc(5, zza);
    }

    public final void zzi(zzb zzb, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzb);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        zzc(70, zza);
    }

    public final void zzj(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, activityTransitionRequest);
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        zzc(72, zza);
    }

    public final void zzk(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        zzc(73, zza);
    }

    public final void zzl(PendingIntent pendingIntent) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, pendingIntent);
        zzc(6, zza);
    }

    public final void zzm(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, pendingIntent);
        zzc.zzb(zza, sleepSegmentRequest);
        zzc.zzc(zza, iStatusCallback);
        zzc(79, zza);
    }

    public final void zzn(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, pendingIntent);
        zzc.zzc(zza, iStatusCallback);
        zzc(69, zza);
    }

    public final void zzo(zzad zzad, zzee zzee) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzad);
        zzc.zzb(zza, zzee);
        zzc(91, zza);
    }

    public final LocationAvailability zzp(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        Parcel zzb = zzb(34, zza);
        LocationAvailability locationAvailability = (LocationAvailability) zzc.zza(zzb, LocationAvailability.CREATOR);
        zzb.recycle();
        return locationAvailability;
    }

    public final void zzq(LastLocationRequest lastLocationRequest, zzee zzee) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, lastLocationRequest);
        zzc.zzb(zza, zzee);
        zzc(90, zza);
    }

    public final void zzr(LastLocationRequest lastLocationRequest, zzz zzz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, lastLocationRequest);
        zzc.zzc(zza, zzz);
        zzc(82, zza);
    }

    public final Location zzs() throws RemoteException {
        Parcel zzb = zzb(7, zza());
        Location location = (Location) zzc.zza(zzb, Location.CREATOR);
        zzb.recycle();
        return location;
    }

    public final ICancelToken zzt(CurrentLocationRequest currentLocationRequest, zzee zzee) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, currentLocationRequest);
        zzc.zzb(zza, zzee);
        Parcel zzb = zzb(92, zza);
        ICancelToken asInterface = ICancelToken.Stub.asInterface(zzb.readStrongBinder());
        zzb.recycle();
        return asInterface;
    }

    public final ICancelToken zzu(CurrentLocationRequest currentLocationRequest, zzz zzz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, currentLocationRequest);
        zzc.zzc(zza, zzz);
        Parcel zzb = zzb(87, zza);
        ICancelToken asInterface = ICancelToken.Stub.asInterface(zzb.readStrongBinder());
        zzb.recycle();
        return asInterface;
    }

    public final void zzv(zzei zzei) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzei);
        zzc(59, zza);
    }

    public final void zzw(zzee zzee, LocationRequest locationRequest, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzee);
        zzc.zzb(zza, locationRequest);
        zzc.zzc(zza, iStatusCallback);
        zzc(88, zza);
    }

    public final void zzx(zzee zzee, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzee);
        zzc.zzc(zza, iStatusCallback);
        zzc(89, zza);
    }

    public final void zzy(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(12, zza);
    }

    public final void zzz(boolean z2, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc.zzc(zza, iStatusCallback);
        zzc(84, zza);
    }
}
