package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.List;

public interface zzgb extends IInterface {
    zzak zza(zzp zzp) throws RemoteException;

    List<zznk> zza(zzp zzp, Bundle bundle) throws RemoteException;

    @Nullable
    List<zzok> zza(zzp zzp, boolean z2) throws RemoteException;

    List<zzaf> zza(@Nullable String str, @Nullable String str2, zzp zzp) throws RemoteException;

    List<zzaf> zza(String str, @Nullable String str2, @Nullable String str3) throws RemoteException;

    List<zzok> zza(String str, @Nullable String str2, @Nullable String str3, boolean z2) throws RemoteException;

    List<zzok> zza(@Nullable String str, @Nullable String str2, boolean z2, zzp zzp) throws RemoteException;

    void zza(long j2, @Nullable String str, @Nullable String str2, String str3) throws RemoteException;

    void zza(Bundle bundle, zzp zzp) throws RemoteException;

    void zza(zzaf zzaf) throws RemoteException;

    void zza(zzaf zzaf, zzp zzp) throws RemoteException;

    void zza(zzbh zzbh, zzp zzp) throws RemoteException;

    void zza(zzbh zzbh, String str, @Nullable String str2) throws RemoteException;

    void zza(zzok zzok, zzp zzp) throws RemoteException;

    @Nullable
    byte[] zza(zzbh zzbh, String str) throws RemoteException;

    @Nullable
    String zzb(zzp zzp) throws RemoteException;

    void zzb(Bundle bundle, zzp zzp) throws RemoteException;

    void zzc(zzp zzp) throws RemoteException;

    void zzd(zzp zzp) throws RemoteException;

    void zze(zzp zzp) throws RemoteException;

    void zzf(zzp zzp) throws RemoteException;

    void zzg(zzp zzp) throws RemoteException;

    void zzh(zzp zzp) throws RemoteException;

    void zzi(zzp zzp) throws RemoteException;
}
