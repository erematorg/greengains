package com.google.android.gms.common.data;

import android.os.Parcelable;

public final class zaf implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Object[]] */
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
            r7 = r5
            r3 = r1
            r6 = r3
        L_0x000b:
            int r0 = r9.dataPosition()
            if (r0 >= r8) goto L_0x004b
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r2 = 1
            if (r1 == r2) goto L_0x0046
            r2 = 2
            if (r1 == r2) goto L_0x003c
            r2 = 3
            if (r1 == r2) goto L_0x0037
            r2 = 4
            if (r1 == r2) goto L_0x0032
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 == r2) goto L_0x002d
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000b
        L_0x002d:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r0)
            goto L_0x000b
        L_0x0032:
            android.os.Bundle r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r9, r0)
            goto L_0x000b
        L_0x0037:
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r0)
            goto L_0x000b
        L_0x003c:
            android.os.Parcelable$Creator r1 = android.database.CursorWindow.CREATOR
            java.lang.Object[] r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r9, r0, r1)
            r5 = r0
            android.database.CursorWindow[] r5 = (android.database.CursorWindow[]) r5
            goto L_0x000b
        L_0x0046:
            java.lang.String[] r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r9, r0)
            goto L_0x000b
        L_0x004b:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r8)
            com.google.android.gms.common.data.DataHolder r8 = new com.google.android.gms.common.data.DataHolder
            r2 = r8
            r2.<init>((int) r3, (java.lang.String[]) r4, (android.database.CursorWindow[]) r5, (int) r6, (android.os.Bundle) r7)
            r8.zad()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.zaf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new DataHolder[i3];
    }
}
