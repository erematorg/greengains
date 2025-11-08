package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbz extends zza implements IStreetViewPanoramaViewDelegate {
    public zzbz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate getStreetViewPanorama() throws android.os.RemoteException {
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
            java.lang.String r1 = "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
            if (r2 == 0) goto L_0x001f
            r0 = r1
            com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate r0 = (com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate) r0
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzbx r1 = new com.google.android.gms.maps.internal.zzbx
            r1.<init>(r0)
            r0 = r1
        L_0x0025:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzbz.getStreetViewPanorama():com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    public final void getStreetViewPanoramaAsync(zzbt zzbt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbt);
        zzc(9, zza);
    }

    public final IObjectWrapper getView() throws RemoteException {
        return a.e(zzJ(8, zza()));
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(2, zza);
    }

    public final void onDestroy() throws RemoteException {
        zzc(5, zza());
    }

    public final void onLowMemory() throws RemoteException {
        zzc(6, zza());
    }

    public final void onPause() throws RemoteException {
        zzc(4, zza());
    }

    public final void onResume() throws RemoteException {
        zzc(3, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        Parcel zzJ = zzJ(7, zza);
        if (zzJ.readInt() != 0) {
            bundle.readFromParcel(zzJ);
        }
        zzJ.recycle();
    }

    public final void onStart() throws RemoteException {
        zzc(10, zza());
    }

    public final void onStop() throws RemoteException {
        zzc(11, zza());
    }
}
