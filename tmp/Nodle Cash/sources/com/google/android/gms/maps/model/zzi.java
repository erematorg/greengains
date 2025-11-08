package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzi implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Integer num = null;
        Integer num2 = null;
        Float f2 = null;
        Float f3 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                num = SafeParcelReader.readIntegerObject(parcel, readHeader);
            } else if (fieldId == 2) {
                num2 = SafeParcelReader.readIntegerObject(parcel, readHeader);
            } else if (fieldId == 3) {
                f2 = SafeParcelReader.readFloatObject(parcel, readHeader);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f3 = SafeParcelReader.readFloatObject(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new FeatureStyle(num, num2, f2, f3);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new FeatureStyle[i3];
    }
}
