package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        byte b3 = 0;
        long j2 = 0;
        float[] fArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1) {
                switch (fieldId) {
                    case 4:
                        f2 = SafeParcelReader.readFloat(parcel, readHeader);
                        break;
                    case 5:
                        f3 = SafeParcelReader.readFloat(parcel, readHeader);
                        break;
                    case 6:
                        j2 = SafeParcelReader.readLong(parcel, readHeader);
                        break;
                    case 7:
                        b3 = SafeParcelReader.readByte(parcel, readHeader);
                        break;
                    case 8:
                        f4 = SafeParcelReader.readFloat(parcel, readHeader);
                        break;
                    case 9:
                        f5 = SafeParcelReader.readFloat(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                fArr = SafeParcelReader.createFloatArray(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DeviceOrientation(fArr, f2, f3, j2, b3, f4, f5);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new DeviceOrientation[i3];
    }
}
