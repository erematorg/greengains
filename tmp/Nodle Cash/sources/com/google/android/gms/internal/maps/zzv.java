package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface zzv extends IInterface {
    boolean zzA() throws RemoteException;

    boolean zzB() throws RemoteException;

    float zzd() throws RemoteException;

    float zze() throws RemoteException;

    float zzf() throws RemoteException;

    float zzg() throws RemoteException;

    float zzh() throws RemoteException;

    int zzi() throws RemoteException;

    IObjectWrapper zzj() throws RemoteException;

    LatLng zzk() throws RemoteException;

    LatLngBounds zzl() throws RemoteException;

    String zzm() throws RemoteException;

    void zzn() throws RemoteException;

    void zzo(float f2) throws RemoteException;

    void zzp(boolean z2) throws RemoteException;

    void zzq(float f2) throws RemoteException;

    void zzr(float f2, float f3) throws RemoteException;

    void zzs(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzt(LatLng latLng) throws RemoteException;

    void zzu(LatLngBounds latLngBounds) throws RemoteException;

    void zzv(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzw(float f2) throws RemoteException;

    void zzx(boolean z2) throws RemoteException;

    void zzy(float f2) throws RemoteException;

    boolean zzz(zzv zzv) throws RemoteException;
}
