package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzel implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i3 = -1;
        int i4 = 0;
        short s3 = 0;
        int i5 = 0;
        long j2 = 0;
        float f2 = 0.0f;
        double d2 = 0.0d;
        double d3 = 0.0d;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    s3 = SafeParcelReader.readShort(parcel2, readHeader);
                    break;
                case 4:
                    d2 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 5:
                    d3 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 6:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 7:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 8:
                    i5 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzek(str, i4, s3, d2, d3, f2, j2, i5, i3);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzek[i3];
    }
}
