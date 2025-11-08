package com.google.android.gms.maps;

import android.os.Parcelable;

public final class zzac implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r27) {
        /*
            r26 = this;
            r0 = r27
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r27)
            r2 = 0
            r3 = 0
            r4 = -1
            r8 = r2
            r25 = r8
            r9 = r3
            r19 = r9
            r20 = r19
            r21 = r20
            r23 = r21
            r24 = r23
            r6 = r4
            r7 = r6
            r10 = r7
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r22 = r18
        L_0x0027:
            int r2 = r27.dataPosition()
            if (r2 >= r1) goto L_0x00ac
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r27)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x00a6;
                case 3: goto L_0x00a1;
                case 4: goto L_0x009c;
                case 5: goto L_0x0092;
                case 6: goto L_0x008d;
                case 7: goto L_0x0088;
                case 8: goto L_0x0083;
                case 9: goto L_0x007e;
                case 10: goto L_0x0079;
                case 11: goto L_0x0074;
                case 12: goto L_0x006f;
                case 13: goto L_0x0038;
                case 14: goto L_0x006a;
                case 15: goto L_0x0065;
                case 16: goto L_0x0060;
                case 17: goto L_0x005b;
                case 18: goto L_0x0050;
                case 19: goto L_0x004b;
                case 20: goto L_0x0046;
                case 21: goto L_0x0041;
                case 22: goto L_0x0038;
                case 23: goto L_0x003c;
                default: goto L_0x0038;
            }
        L_0x0038:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0027
        L_0x003c:
            int r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0027
        L_0x0041:
            java.lang.String r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0027
        L_0x0046:
            java.lang.Integer r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r0, r2)
            goto L_0x0027
        L_0x004b:
            byte r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x0050:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLngBounds> r3 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r21 = r2
            com.google.android.gms.maps.model.LatLngBounds r21 = (com.google.android.gms.maps.model.LatLngBounds) r21
            goto L_0x0027
        L_0x005b:
            java.lang.Float r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloatObject(r0, r2)
            goto L_0x0027
        L_0x0060:
            java.lang.Float r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloatObject(r0, r2)
            goto L_0x0027
        L_0x0065:
            byte r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x006a:
            byte r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x006f:
            byte r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x0074:
            byte r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x0079:
            byte r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x007e:
            byte r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x0083:
            byte r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x0088:
            byte r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x008d:
            byte r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x0092:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.CameraPosition> r3 = com.google.android.gms.maps.model.CameraPosition.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r9 = r2
            com.google.android.gms.maps.model.CameraPosition r9 = (com.google.android.gms.maps.model.CameraPosition) r9
            goto L_0x0027
        L_0x009c:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0027
        L_0x00a1:
            byte r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x00a6:
            byte r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r0, r2)
            goto L_0x0027
        L_0x00ac:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.maps.GoogleMapOptions r0 = new com.google.android.gms.maps.GoogleMapOptions
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.zzac.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new GoogleMapOptions[i3];
    }
}
