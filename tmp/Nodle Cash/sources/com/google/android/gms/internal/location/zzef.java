package com.google.android.gms.internal.location;

import android.os.Parcelable;

public final class zzef implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
        /*
            r8 = this;
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r9)
            r0 = 0
            r1 = 0
            r4 = r0
            r5 = r4
            r6 = r5
            r7 = r6
            r3 = r1
        L_0x000b:
            int r0 = r9.dataPosition()
            if (r0 >= r8) goto L_0x004a
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r2 = 1
            if (r1 == r2) goto L_0x0045
            r2 = 2
            if (r1 == r2) goto L_0x0040
            r2 = 3
            if (r1 == r2) goto L_0x003b
            r2 = 4
            if (r1 == r2) goto L_0x0031
            r2 = 6
            if (r1 == r2) goto L_0x002c
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000b
        L_0x002c:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r0)
            goto L_0x000b
        L_0x0031:
            android.os.Parcelable$Creator r1 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r6 = r0
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            goto L_0x000b
        L_0x003b:
            android.os.IBinder r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r9, r0)
            goto L_0x000b
        L_0x0040:
            android.os.IBinder r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r9, r0)
            goto L_0x000b
        L_0x0045:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r0)
            goto L_0x000b
        L_0x004a:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r8)
            com.google.android.gms.internal.location.zzee r8 = new com.google.android.gms.internal.location.zzee
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzef.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzee[i3];
    }
}
