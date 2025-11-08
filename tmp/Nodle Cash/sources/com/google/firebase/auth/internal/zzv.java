package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.zzf;

public final class zzv implements Parcelable.Creator<zzw> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzac zzac = null;
        zzu zzu = null;
        zzf zzf = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                zzac = (zzac) SafeParcelReader.createParcelable(parcel, readHeader, zzac.CREATOR);
            } else if (fieldId == 2) {
                zzu = (zzu) SafeParcelReader.createParcelable(parcel, readHeader, zzu.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzf = (zzf) SafeParcelReader.createParcelable(parcel, readHeader, zzf.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzw(zzac, zzu, zzf);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzw[i3];
    }
}
