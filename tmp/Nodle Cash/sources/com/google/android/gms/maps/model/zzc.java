package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzc implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        LatLng latLng = null;
        ArrayList<PatternItem> arrayList = null;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        float f2 = 0.0f;
        float f3 = 0.0f;
        double d2 = 0.0d;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel2, readHeader, LatLng.CREATOR);
                    break;
                case 3:
                    d2 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 4:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 5:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 7:
                    f3 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 8:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 9:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel2, readHeader, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new CircleOptions(latLng, d2, f2, i3, i4, f3, z2, z3, arrayList);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new CircleOptions[i3];
    }
}
