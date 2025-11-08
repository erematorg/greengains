package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzk extends zza implements IMapFragmentDelegate {
    public zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IGoogleMapDelegate getMap() throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 1
            android.os.Parcel r1 = r3.zza()
            android.os.Parcel r3 = r3.zzJ(r0, r1)
            android.os.IBinder r0 = r3.readStrongBinder()
            if (r0 != 0) goto L_0x0011
            r0 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r1 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.IGoogleMapDelegate
            if (r2 == 0) goto L_0x001f
            r0 = r1
            com.google.android.gms.maps.internal.IGoogleMapDelegate r0 = (com.google.android.gms.maps.internal.IGoogleMapDelegate) r0
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzg r1 = new com.google.android.gms.maps.internal.zzg
            r1.<init>(r0)
            r0 = r1
        L_0x0025:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzk.getMap():com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final void getMapAsync(zzat zzat) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzat);
        zzc(12, zza);
    }

    public final boolean isReady() throws RemoteException {
        Parcel zzJ = zzJ(11, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(3, zza);
    }

    public final IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc.zzg(zza, iObjectWrapper2);
        zzc.zze(zza, bundle);
        return a.e(zzJ(4, zza));
    }

    public final void onDestroy() throws RemoteException {
        zzc(8, zza());
    }

    public final void onDestroyView() throws RemoteException {
        zzc(7, zza());
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(13, zza);
    }

    public final void onExitAmbient() throws RemoteException {
        zzc(14, zza());
    }

    public final void onInflate(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc.zze(zza, googleMapOptions);
        zzc.zze(zza, bundle);
        zzc(2, zza);
    }

    public final void onLowMemory() throws RemoteException {
        zzc(9, zza());
    }

    public final void onPause() throws RemoteException {
        zzc(6, zza());
    }

    public final void onResume() throws RemoteException {
        zzc(5, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        Parcel zzJ = zzJ(10, zza);
        if (zzJ.readInt() != 0) {
            bundle.readFromParcel(zzJ);
        }
        zzJ.recycle();
    }

    public final void onStart() throws RemoteException {
        zzc(15, zza());
    }

    public final void onStop() throws RemoteException {
        zzc(16, zza());
    }
}
