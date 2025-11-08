package com.google.android.gms.maps.model;

import android.os.Parcelable;

public final class zzp implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r28) {
        /*
            r27 = this;
            r0 = r28
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r28)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 1056964608(0x3f000000, float:0.5)
            r12 = r2
            r13 = r12
            r17 = r13
            r19 = r17
            r21 = r19
            r26 = r21
            r8 = r3
            r9 = r8
            r10 = r9
            r11 = r10
            r23 = r11
            r25 = r23
            r14 = r4
            r15 = r14
            r16 = r15
            r22 = r16
            r24 = r22
            r20 = r5
            r18 = r6
        L_0x002b:
            int r2 = r28.dataPosition()
            if (r2 >= r1) goto L_0x00a4
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r28)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x009a;
                case 3: goto L_0x0095;
                case 4: goto L_0x0090;
                case 5: goto L_0x008b;
                case 6: goto L_0x0086;
                case 7: goto L_0x0081;
                case 8: goto L_0x007c;
                case 9: goto L_0x0077;
                case 10: goto L_0x0072;
                case 11: goto L_0x006d;
                case 12: goto L_0x0068;
                case 13: goto L_0x0063;
                case 14: goto L_0x005e;
                case 15: goto L_0x0059;
                case 16: goto L_0x003c;
                case 17: goto L_0x0054;
                case 18: goto L_0x004f;
                case 19: goto L_0x004a;
                case 20: goto L_0x0045;
                case 21: goto L_0x0040;
                default: goto L_0x003c;
            }
        L_0x003c:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x002b
        L_0x0040:
            float r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x0045:
            java.lang.String r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x002b
        L_0x004a:
            int r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002b
        L_0x004f:
            android.os.IBinder r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x002b
        L_0x0054:
            int r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002b
        L_0x0059:
            float r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x005e:
            float r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x0063:
            float r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x0068:
            float r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x006d:
            float r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x0072:
            boolean r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x002b
        L_0x0077:
            boolean r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x002b
        L_0x007c:
            boolean r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x002b
        L_0x0081:
            float r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x0086:
            float r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x002b
        L_0x008b:
            android.os.IBinder r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x002b
        L_0x0090:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x002b
        L_0x0095:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x002b
        L_0x009a:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r8 = r2
            com.google.android.gms.maps.model.LatLng r8 = (com.google.android.gms.maps.model.LatLng) r8
            goto L_0x002b
        L_0x00a4:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.maps.model.MarkerOptions r0 = new com.google.android.gms.maps.model.MarkerOptions
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.zzp.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new MarkerOptions[i3];
    }
}
