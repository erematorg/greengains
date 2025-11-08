package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import A.a;
import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzc {
    static {
        zzc.class.getClassLoader();
    }

    private zzc() {
    }

    public static Parcelable zza(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static void zzb(Parcel parcel) {
        int dataAvail = parcel.dataAvail();
        if (dataAvail > 0) {
            throw new BadParcelableException(a.k("Parcel data not fully consumed, unread size: ", dataAvail));
        }
    }
}
