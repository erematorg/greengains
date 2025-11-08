package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbw;
import com.google.android.gms.internal.measurement.zzbx;
import java.util.List;

public abstract class zzga extends zzbx implements zzgb {
    public zzga() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        switch (i3) {
            case 1:
                zzbw.zzb(parcel);
                zza((zzbh) zzbw.zza(parcel, zzbh.CREATOR), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                zzbw.zzb(parcel);
                zza((zzok) zzbw.zza(parcel, zzok.CREATOR), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 4:
                zzbw.zzb(parcel);
                zzd((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 5:
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                zzbw.zzb(parcel);
                zza((zzbh) zzbw.zza(parcel, zzbh.CREATOR), readString, readString2);
                parcel2.writeNoException();
                return true;
            case 6:
                zzbw.zzb(parcel);
                zzh((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                boolean zzc = zzbw.zzc(parcel);
                zzbw.zzb(parcel);
                List<zzok> zza = zza((zzp) zzbw.zza(parcel, zzp.CREATOR), zzc);
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                return true;
            case 9:
                String readString3 = parcel.readString();
                zzbw.zzb(parcel);
                byte[] zza2 = zza((zzbh) zzbw.zza(parcel, zzbh.CREATOR), readString3);
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                return true;
            case 10:
                long readLong = parcel.readLong();
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                String readString6 = parcel.readString();
                zzbw.zzb(parcel);
                zza(readLong, readString4, readString5, readString6);
                parcel2.writeNoException();
                return true;
            case 11:
                zzbw.zzb(parcel);
                String zzb = zzb((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzb);
                return true;
            case 12:
                zzbw.zzb(parcel);
                zza((zzaf) zzbw.zza(parcel, zzaf.CREATOR), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 13:
                zzbw.zzb(parcel);
                zza((zzaf) zzbw.zza(parcel, zzaf.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                zzbw.zzb(parcel);
                List<zzok> zza3 = zza(parcel.readString(), parcel.readString(), zzbw.zzc(parcel), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                return true;
            case 15:
                String readString7 = parcel.readString();
                String readString8 = parcel.readString();
                String readString9 = parcel.readString();
                boolean zzc2 = zzbw.zzc(parcel);
                zzbw.zzb(parcel);
                List<zzok> zza4 = zza(readString7, readString8, readString9, zzc2);
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                return true;
            case 16:
                zzbw.zzb(parcel);
                List<zzaf> zza5 = zza(parcel.readString(), parcel.readString(), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                return true;
            case 17:
                String readString10 = parcel.readString();
                String readString11 = parcel.readString();
                String readString12 = parcel.readString();
                zzbw.zzb(parcel);
                List<zzaf> zza6 = zza(readString10, readString11, readString12);
                parcel2.writeNoException();
                parcel2.writeTypedList(zza6);
                return true;
            case 18:
                zzbw.zzb(parcel);
                zze((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 19:
                zzbw.zzb(parcel);
                zza((Bundle) zzbw.zza(parcel, Bundle.CREATOR), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 20:
                zzbw.zzb(parcel);
                zzf((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 21:
                zzbw.zzb(parcel);
                zzak zza7 = zza((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                zzbw.zzb(parcel2, zza7);
                return true;
            case 24:
                zzbw.zzb(parcel);
                List<zznk> zza8 = zza((zzp) zzbw.zza(parcel, zzp.CREATOR), (Bundle) zzbw.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza8);
                return true;
            case 25:
                zzbw.zzb(parcel);
                zzi((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 26:
                zzbw.zzb(parcel);
                zzg((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 27:
                zzbw.zzb(parcel);
                zzc((zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            case 28:
                zzbw.zzb(parcel);
                zzb((Bundle) zzbw.zza(parcel, Bundle.CREATOR), (zzp) zzbw.zza(parcel, zzp.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
