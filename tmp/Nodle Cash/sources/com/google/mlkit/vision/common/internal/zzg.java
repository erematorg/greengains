package com.google.mlkit.vision.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzg implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                i4 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                i5 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                i6 = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new VisionImageMetadataParcel(i3, i4, i5, j2, i6);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new VisionImageMetadataParcel[i3];
    }
}
