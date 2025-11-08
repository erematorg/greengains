package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import javax.annotation.Nullable;

public interface zzl extends IInterface {
    boolean zzA() throws RemoteException;

    double zzd() throws RemoteException;

    float zze() throws RemoteException;

    float zzf() throws RemoteException;

    int zzg() throws RemoteException;

    int zzh() throws RemoteException;

    int zzi() throws RemoteException;

    IObjectWrapper zzj() throws RemoteException;

    LatLng zzk() throws RemoteException;

    String zzl() throws RemoteException;

    List zzm() throws RemoteException;

    void zzn() throws RemoteException;

    void zzo(LatLng latLng) throws RemoteException;

    void zzp(boolean z2) throws RemoteException;

    void zzq(int i3) throws RemoteException;

    void zzr(double d2) throws RemoteException;

    void zzs(int i3) throws RemoteException;

    void zzt(@Nullable List list) throws RemoteException;

    void zzu(float f2) throws RemoteException;

    void zzv(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzw(boolean z2) throws RemoteException;

    void zzx(float f2) throws RemoteException;

    boolean zzy(@Nullable zzl zzl) throws RemoteException;

    boolean zzz() throws RemoteException;
}
