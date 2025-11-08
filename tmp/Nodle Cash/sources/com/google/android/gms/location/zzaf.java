package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zze;

public final class zzaf implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        WorkSource workSource = new WorkSource();
        zze zze = null;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        long j2 = -1;
        float f2 = 0.0f;
        int i5 = Integer.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        long j4 = Long.MAX_VALUE;
        long j5 = 0;
        long j6 = 600000;
        long j7 = 3600000;
        int i6 = 102;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i6 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    j7 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    j6 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 5:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 6:
                    i5 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 7:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 8:
                    j5 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 11:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 13:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 15:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 16:
                    workSource = (WorkSource) SafeParcelReader.createParcelable(parcel2, readHeader, WorkSource.CREATOR);
                    break;
                case 17:
                    zze = (zze) SafeParcelReader.createParcelable(parcel2, readHeader, zze.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new LocationRequest(i6, j7, j6, j5, j3, j4, i5, f2, z2, j2, i3, i4, z3, workSource, zze);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new LocationRequest[i3];
    }
}
