package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zza implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
        String str = null;
        ArrayList<String> arrayList = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    i5 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 8:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 11:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 12:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 13:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 14:
                    i6 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 15:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 16:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 17:
                    str5 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new WakeLockEvent(i3, j2, i4, str, i5, arrayList, str2, j3, i6, str3, str4, f2, j4, str5, z2);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new WakeLockEvent[i3];
    }
}
