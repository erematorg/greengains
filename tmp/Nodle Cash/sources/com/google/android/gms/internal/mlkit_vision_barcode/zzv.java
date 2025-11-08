package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzv implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        double d2 = 0.0d;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        String str = null;
        String str2 = null;
        Point[] pointArr = null;
        zzn zzn = null;
        zzq zzq = null;
        zzr zzr = null;
        zzt zzt = null;
        zzs zzs = null;
        zzo zzo = null;
        zzk zzk = null;
        zzl zzl = null;
        zzm zzm = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    pointArr = (Point[]) SafeParcelReader.createTypedArray(parcel2, readHeader, Point.CREATOR);
                    break;
                case 7:
                    zzn = (zzn) SafeParcelReader.createParcelable(parcel2, readHeader, zzn.CREATOR);
                    break;
                case 8:
                    zzq = (zzq) SafeParcelReader.createParcelable(parcel2, readHeader, zzq.CREATOR);
                    break;
                case 9:
                    zzr = (zzr) SafeParcelReader.createParcelable(parcel2, readHeader, zzr.CREATOR);
                    break;
                case 10:
                    zzt = (zzt) SafeParcelReader.createParcelable(parcel2, readHeader, zzt.CREATOR);
                    break;
                case 11:
                    zzs = (zzs) SafeParcelReader.createParcelable(parcel2, readHeader, zzs.CREATOR);
                    break;
                case 12:
                    zzo = (zzo) SafeParcelReader.createParcelable(parcel2, readHeader, zzo.CREATOR);
                    break;
                case 13:
                    zzk = (zzk) SafeParcelReader.createParcelable(parcel2, readHeader, zzk.CREATOR);
                    break;
                case 14:
                    zzl = (zzl) SafeParcelReader.createParcelable(parcel2, readHeader, zzl.CREATOR);
                    break;
                case 15:
                    zzm = (zzm) SafeParcelReader.createParcelable(parcel2, readHeader, zzm.CREATOR);
                    break;
                case 16:
                    bArr = SafeParcelReader.createByteArray(parcel2, readHeader);
                    break;
                case 17:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 18:
                    d2 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzu(i3, str, str2, i4, pointArr, zzn, zzq, zzr, zzt, zzs, zzo, zzk, zzl, zzm, bArr, z2, d2);
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzu[i3];
    }
}
