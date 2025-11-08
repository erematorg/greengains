package com.google.android.gms.maps.model;

import android.os.Parcelable;

public final class zzu implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r19) {
        /*
            r18 = this;
            r0 = r19
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r19)
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = r2
            r13 = r6
            r14 = r13
            r16 = r14
            r17 = r16
            r8 = r3
            r10 = r8
            r11 = r10
            r12 = r11
            r15 = r12
            r7 = r4
            r9 = r7
        L_0x0017:
            int r2 = r19.dataPosition()
            if (r2 >= r1) goto L_0x0078
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r19)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x0071;
                case 3: goto L_0x006c;
                case 4: goto L_0x0067;
                case 5: goto L_0x0062;
                case 6: goto L_0x005d;
                case 7: goto L_0x0058;
                case 8: goto L_0x0053;
                case 9: goto L_0x0049;
                case 10: goto L_0x003f;
                case 11: goto L_0x003a;
                case 12: goto L_0x0033;
                case 13: goto L_0x002c;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0017
        L_0x002c:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.StyleSpan> r3 = com.google.android.gms.maps.model.StyleSpan.CREATOR
            java.util.ArrayList r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0017
        L_0x0033:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.PatternItem> r3 = com.google.android.gms.maps.model.PatternItem.CREATOR
            java.util.ArrayList r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0017
        L_0x003a:
            int r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0017
        L_0x003f:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.Cap> r3 = com.google.android.gms.maps.model.Cap.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r14 = r2
            com.google.android.gms.maps.model.Cap r14 = (com.google.android.gms.maps.model.Cap) r14
            goto L_0x0017
        L_0x0049:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.Cap> r3 = com.google.android.gms.maps.model.Cap.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            com.google.android.gms.maps.model.Cap r13 = (com.google.android.gms.maps.model.Cap) r13
            goto L_0x0017
        L_0x0053:
            boolean r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0017
        L_0x0058:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0017
        L_0x005d:
            boolean r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0017
        L_0x0062:
            float r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0017
        L_0x0067:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0017
        L_0x006c:
            float r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0017
        L_0x0071:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0017
        L_0x0078:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.maps.model.PolylineOptions r0 = new com.google.android.gms.maps.model.PolylineOptions
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.zzu.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new PolylineOptions[i3];
    }
}
