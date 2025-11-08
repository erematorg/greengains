package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbu;
import com.google.android.gms.internal.measurement.zzbw;
import java.util.ArrayList;
import java.util.List;

public final class zzgd extends zzbu implements zzgb {
    public zzgd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final zzak zza(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        Parcel zza = zza(21, a_);
        zzak zzak = (zzak) zzbw.zza(zza, zzak.CREATOR);
        zza.recycle();
        return zzak;
    }

    public final String zzb(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        Parcel zza = zza(11, a_);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final void zzc(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(27, a_);
    }

    public final void zzd(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(4, a_);
    }

    public final void zze(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(18, a_);
    }

    public final void zzf(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(20, a_);
    }

    public final void zzg(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(26, a_);
    }

    public final void zzh(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(6, a_);
    }

    public final void zzi(zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(25, a_);
    }

    public final List<zznk> zza(zzp zzp, Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzbw.zza(a_, (Parcelable) bundle);
        Parcel zza = zza(24, a_);
        ArrayList<zznk> createTypedArrayList = zza.createTypedArrayList(zznk.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final void zzb(Bundle bundle, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(28, a_);
    }

    public final List<zzok> zza(zzp zzp, boolean z2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzp);
        zzbw.zza(a_, z2);
        Parcel zza = zza(7, a_);
        ArrayList<zzok> createTypedArrayList = zza.createTypedArrayList(zzok.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzaf> zza(String str, String str2, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) zzp);
        Parcel zza = zza(16, a_);
        ArrayList<zzaf> createTypedArrayList = zza.createTypedArrayList(zzaf.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzaf> zza(String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        Parcel zza = zza(17, a_);
        ArrayList<zzaf> createTypedArrayList = zza.createTypedArrayList(zzaf.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzok> zza(String str, String str2, boolean z2, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, z2);
        zzbw.zza(a_, (Parcelable) zzp);
        Parcel zza = zza(14, a_);
        ArrayList<zzok> createTypedArrayList = zza.createTypedArrayList(zzok.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final List<zzok> zza(String str, String str2, String str3, boolean z2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzbw.zza(a_, z2);
        Parcel zza = zza(15, a_);
        ArrayList<zzok> createTypedArrayList = zza.createTypedArrayList(zzok.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final void zza(zzbh zzbh, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzbh);
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(1, a_);
    }

    public final void zza(zzbh zzbh, String str, String str2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzbh);
        a_.writeString(str);
        a_.writeString(str2);
        zzb(5, a_);
    }

    public final void zza(zzaf zzaf, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzaf);
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(12, a_);
    }

    public final void zza(zzaf zzaf) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzaf);
        zzb(13, a_);
    }

    public final void zza(long j2, String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j2);
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzb(10, a_);
    }

    public final void zza(Bundle bundle, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(19, a_);
    }

    public final void zza(zzok zzok, zzp zzp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzok);
        zzbw.zza(a_, (Parcelable) zzp);
        zzb(2, a_);
    }

    public final byte[] zza(zzbh zzbh, String str) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) zzbh);
        a_.writeString(str);
        Parcel zza = zza(9, a_);
        byte[] createByteArray = zza.createByteArray();
        zza.recycle();
        return createByteArray;
    }
}
