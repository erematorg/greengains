package com.google.android.gms.common.internal;

import android.os.Parcelable;

public final class zaw implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
        /*
            r8 = this;
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r9)
            r0 = 0
            r1 = 0
            r3 = r0
            r6 = r3
            r7 = r6
            r4 = r1
            r5 = r4
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
            if (r1 == r2) goto L_0x0036
            r2 = 4
            if (r1 == r2) goto L_0x0031
            r2 = 5
            if (r1 == r2) goto L_0x002c
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000b
        L_0x002c:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r9, r0)
            goto L_0x000b
        L_0x0031:
            boolean r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r9, r0)
            goto L_0x000b
        L_0x0036:
            android.os.Parcelable$Creator<com.google.android.gms.common.ConnectionResult> r1 = com.google.android.gms.common.ConnectionResult.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r5 = r0
            com.google.android.gms.common.ConnectionResult r5 = (com.google.android.gms.common.ConnectionResult) r5
            goto L_0x000b
        L_0x0040:
            android.os.IBinder r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r9, r0)
            goto L_0x000b
        L_0x0045:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r0)
            goto L_0x000b
        L_0x004a:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r8)
            com.google.android.gms.common.internal.zav r8 = new com.google.android.gms.common.internal.zav
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zaw.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zav[i3];
    }
}
