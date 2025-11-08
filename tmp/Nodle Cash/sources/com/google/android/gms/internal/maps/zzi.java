package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.PinConfig;

public interface zzi extends IInterface {
    IObjectWrapper zzd() throws RemoteException;

    IObjectWrapper zze(float f2) throws RemoteException;

    IObjectWrapper zzf(String str) throws RemoteException;

    IObjectWrapper zzg(Bitmap bitmap) throws RemoteException;

    IObjectWrapper zzh(String str) throws RemoteException;

    IObjectWrapper zzi(String str) throws RemoteException;

    IObjectWrapper zzj(PinConfig pinConfig) throws RemoteException;

    IObjectWrapper zzk(int i3) throws RemoteException;
}
