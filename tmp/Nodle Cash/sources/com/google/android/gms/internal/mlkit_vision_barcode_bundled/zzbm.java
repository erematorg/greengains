package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzbm extends zzb implements zzbn {
    public zzbm() {
        super("com.google.mlkit.vision.barcode.aidls.IBarcodeScanner");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 == 1) {
            zzd();
            parcel2.writeNoException();
        } else if (i3 == 2) {
            zzf();
            parcel2.writeNoException();
        } else if (i3 == 3) {
            zzc.zzb(parcel);
            List zzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzcc) zzc.zza(parcel, zzcc.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedList(zzb);
        } else if (i3 == 4) {
            zzc.zzb(parcel);
            List zzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzcc) zzc.zza(parcel, zzcc.CREATOR), (zzbc) zzc.zza(parcel, zzbc.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedList(zzc);
        } else if (i3 != 5) {
            return false;
        } else {
            zzc.zzb(parcel);
            zze((zzbe) zzc.zza(parcel, zzbe.CREATOR));
            parcel2.writeNoException();
        }
        return true;
    }
}
