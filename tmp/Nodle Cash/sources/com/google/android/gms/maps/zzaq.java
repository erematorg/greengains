package com.google.android.gms.maps;

import android.os.Parcelable;

public final class zzaq implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r14)
            r0 = 0
            r1 = 0
            r3 = r0
            r4 = r3
            r5 = r4
            r6 = r5
            r12 = r6
            r7 = r1
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
        L_0x0010:
            int r0 = r14.dataPosition()
            if (r0 >= r13) goto L_0x0066
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r14)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 2: goto L_0x005c;
                case 3: goto L_0x0057;
                case 4: goto L_0x004d;
                case 5: goto L_0x0048;
                case 6: goto L_0x0043;
                case 7: goto L_0x003e;
                case 8: goto L_0x0039;
                case 9: goto L_0x0034;
                case 10: goto L_0x002f;
                case 11: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r14, r0)
            goto L_0x0010
        L_0x0025:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.StreetViewSource> r1 = com.google.android.gms.maps.model.StreetViewSource.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r12 = r0
            com.google.android.gms.maps.model.StreetViewSource r12 = (com.google.android.gms.maps.model.StreetViewSource) r12
            goto L_0x0010
        L_0x002f:
            byte r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r14, r0)
            goto L_0x0010
        L_0x0034:
            byte r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r14, r0)
            goto L_0x0010
        L_0x0039:
            byte r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r14, r0)
            goto L_0x0010
        L_0x003e:
            byte r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r14, r0)
            goto L_0x0010
        L_0x0043:
            byte r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r14, r0)
            goto L_0x0010
        L_0x0048:
            java.lang.Integer r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r14, r0)
            goto L_0x0010
        L_0x004d:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r1 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r5 = r0
            com.google.android.gms.maps.model.LatLng r5 = (com.google.android.gms.maps.model.LatLng) r5
            goto L_0x0010
        L_0x0057:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r0)
            goto L_0x0010
        L_0x005c:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.StreetViewPanoramaCamera> r1 = com.google.android.gms.maps.model.StreetViewPanoramaCamera.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r3 = r0
            com.google.android.gms.maps.model.StreetViewPanoramaCamera r3 = (com.google.android.gms.maps.model.StreetViewPanoramaCamera) r3
            goto L_0x0010
        L_0x0066:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r14, r13)
            com.google.android.gms.maps.StreetViewPanoramaOptions r13 = new com.google.android.gms.maps.StreetViewPanoramaOptions
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.zzaq.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new StreetViewPanoramaOptions[i3];
    }
}
