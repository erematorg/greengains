package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

public final class zzeh implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j2 = Long.MAX_VALUE;
        LocationRequest locationRequest = null;
        ArrayList<ClientIdentity> arrayList = null;
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                locationRequest = (LocationRequest) SafeParcelReader.createParcelable(parcel, readHeader, LocationRequest.CREATOR);
            } else if (fieldId == 5) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, ClientIdentity.CREATOR);
            } else if (fieldId == 8) {
                z2 = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId != 9) {
                switch (fieldId) {
                    case 11:
                        z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    case 12:
                        z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    case 13:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 14:
                        j2 = SafeParcelReader.readLong(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                z3 = SafeParcelReader.readBoolean(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzeg(locationRequest, arrayList, z2, z3, z4, z5, str, j2);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzeg[i3];
    }
}
