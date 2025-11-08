package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagv  reason: invalid package */
public final class zzagv implements Parcelable.Creator<zzags> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 6:
                    str5 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 7:
                    str6 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    str7 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 9:
                    str8 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 10:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 11:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 12:
                    str9 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 13:
                    str10 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 14:
                    str11 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 15:
                    str12 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 16:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 17:
                    str13 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzags(str, str2, str3, str4, str5, str6, str7, str8, z2, z3, str9, str10, str11, str12, z4, str13);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzags[i3];
    }
}
