package com.google.android.gms.maps.model;

import android.os.Parcelable;

public final class zzad implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r0 = 0
            r1 = 0
            r2 = 0
            r8 = r0
            r5 = r1
            r6 = r5
            r7 = r6
            r4 = r2
        L_0x000c:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x004b
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r2 = 2
            if (r1 == r2) goto L_0x0046
            r2 = 3
            if (r1 == r2) goto L_0x0041
            r2 = 4
            if (r1 == r2) goto L_0x003c
            r2 = 5
            if (r1 == r2) goto L_0x0037
            r2 = 6
            if (r1 == r2) goto L_0x002d
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000c
        L_0x002d:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.StampStyle> r1 = com.google.android.gms.maps.model.StampStyle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r8 = r0
            com.google.android.gms.maps.model.StampStyle r8 = (com.google.android.gms.maps.model.StampStyle) r8
            goto L_0x000c
        L_0x0037:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r10, r0)
            goto L_0x000c
        L_0x003c:
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0041:
            int r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0046:
            float r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r10, r0)
            goto L_0x000c
        L_0x004b:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.android.gms.maps.model.StrokeStyle r9 = new com.google.android.gms.maps.model.StrokeStyle
            r3 = r9
            r3.<init>(r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.zzad.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new StrokeStyle[i3];
    }
}
