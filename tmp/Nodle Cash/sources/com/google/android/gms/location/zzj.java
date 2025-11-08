package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zze;

public final class zzj implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        WorkSource workSource = new WorkSource();
        zze zze = null;
        int i3 = 0;
        boolean z2 = false;
        int i4 = 0;
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        int i5 = 102;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 2:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 3:
                    i5 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 4:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 6:
                    workSource = (WorkSource) SafeParcelReader.createParcelable(parcel2, readHeader, WorkSource.CREATOR);
                    break;
                case 7:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    zze = (zze) SafeParcelReader.createParcelable(parcel2, readHeader, zze.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new CurrentLocationRequest(j2, i3, i5, j3, z2, i4, workSource, zze);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new CurrentLocationRequest[i3];
    }
}
