package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;

public final class zze extends zza implements zzf {
    public zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }

    public final int zzd() throws RemoteException {
        Parcel zzJ = zzJ(9, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate zze() throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 4
            android.os.Parcel r1 = r3.zza()
            android.os.Parcel r3 = r3.zzJ(r0, r1)
            android.os.IBinder r0 = r3.readStrongBinder()
            if (r0 != 0) goto L_0x0011
            r0 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r1 = "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
            if (r2 == 0) goto L_0x001f
            r0 = r1
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r0 = (com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate) r0
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzb r1 = new com.google.android.gms.maps.internal.zzb
            r1.<init>(r0)
            r0 = r1
        L_0x0025:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zze():com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IMapFragmentDelegate zzf(com.google.android.gms.dynamic.IObjectWrapper r3) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zza()
            com.google.android.gms.internal.maps.zzc.zzg(r0, r3)
            r3 = 2
            android.os.Parcel r2 = r2.zzJ(r3, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0014
            r3 = 0
            goto L_0x0028
        L_0x0014:
            java.lang.String r0 = "com.google.android.gms.maps.internal.IMapFragmentDelegate"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.maps.internal.IMapFragmentDelegate
            if (r1 == 0) goto L_0x0022
            r3 = r0
            com.google.android.gms.maps.internal.IMapFragmentDelegate r3 = (com.google.android.gms.maps.internal.IMapFragmentDelegate) r3
            goto L_0x0028
        L_0x0022:
            com.google.android.gms.maps.internal.zzk r0 = new com.google.android.gms.maps.internal.zzk
            r0.<init>(r3)
            r3 = r0
        L_0x0028:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzf(com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IMapViewDelegate zzg(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.maps.GoogleMapOptions r3) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zza()
            com.google.android.gms.internal.maps.zzc.zzg(r0, r2)
            com.google.android.gms.internal.maps.zzc.zze(r0, r3)
            r2 = 3
            android.os.Parcel r1 = r1.zzJ(r2, r0)
            android.os.IBinder r2 = r1.readStrongBinder()
            if (r2 != 0) goto L_0x0017
            r2 = 0
            goto L_0x002b
        L_0x0017:
            java.lang.String r3 = "com.google.android.gms.maps.internal.IMapViewDelegate"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r0 = r3 instanceof com.google.android.gms.maps.internal.IMapViewDelegate
            if (r0 == 0) goto L_0x0025
            r2 = r3
            com.google.android.gms.maps.internal.IMapViewDelegate r2 = (com.google.android.gms.maps.internal.IMapViewDelegate) r2
            goto L_0x002b
        L_0x0025:
            com.google.android.gms.maps.internal.zzl r3 = new com.google.android.gms.maps.internal.zzl
            r3.<init>(r2)
            r2 = r3
        L_0x002b:
            r1.recycle()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzg(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.maps.GoogleMapOptions):com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate zzh(com.google.android.gms.dynamic.IObjectWrapper r3) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zza()
            com.google.android.gms.internal.maps.zzc.zzg(r0, r3)
            r3 = 8
            android.os.Parcel r2 = r2.zzJ(r3, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0015
            r3 = 0
            goto L_0x0029
        L_0x0015:
            java.lang.String r0 = "com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
            if (r1 == 0) goto L_0x0023
            r3 = r0
            com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate r3 = (com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate) r3
            goto L_0x0029
        L_0x0023:
            com.google.android.gms.maps.internal.zzby r0 = new com.google.android.gms.maps.internal.zzby
            r0.<init>(r3)
            r3 = r0
        L_0x0029:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzh(com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate zzi(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.maps.StreetViewPanoramaOptions r3) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zza()
            com.google.android.gms.internal.maps.zzc.zzg(r0, r2)
            com.google.android.gms.internal.maps.zzc.zze(r0, r3)
            r2 = 7
            android.os.Parcel r1 = r1.zzJ(r2, r0)
            android.os.IBinder r2 = r1.readStrongBinder()
            if (r2 != 0) goto L_0x0017
            r2 = 0
            goto L_0x002b
        L_0x0017:
            java.lang.String r3 = "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r0 = r3 instanceof com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
            if (r0 == 0) goto L_0x0025
            r2 = r3
            com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate r2 = (com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate) r2
            goto L_0x002b
        L_0x0025:
            com.google.android.gms.maps.internal.zzbz r3 = new com.google.android.gms.maps.internal.zzbz
            r3.<init>(r2)
            r2 = r3
        L_0x002b:
            r1.recycle()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzi(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.maps.StreetViewPanoramaOptions):com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }

    public final zzi zzj() throws RemoteException {
        Parcel zzJ = zzJ(5, zza());
        zzi zzb = zzh.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final void zzk(IObjectWrapper iObjectWrapper, int i3) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zza.writeInt(19000000);
        zzc(6, zza);
    }

    public final void zzl(IObjectWrapper iObjectWrapper, int i3) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zza.writeInt(i3);
        zzc(10, zza);
    }

    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(11, zza);
    }
}
