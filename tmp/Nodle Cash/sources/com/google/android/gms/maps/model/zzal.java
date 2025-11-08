package com.google.android.gms.maps.model;

import android.os.Parcelable;

public final class zzal implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
        /*
            r8 = this;
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r9)
            r0 = 0
            r2 = r0
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
        L_0x000a:
            int r0 = r9.dataPosition()
            if (r0 >= r8) goto L_0x005d
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r7 = 2
            if (r1 == r7) goto L_0x0053
            r7 = 3
            if (r1 == r7) goto L_0x0049
            r7 = 4
            if (r1 == r7) goto L_0x003f
            r7 = 5
            if (r1 == r7) goto L_0x0035
            r7 = 6
            if (r1 == r7) goto L_0x002b
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000a
        L_0x002b:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLngBounds> r1 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r6 = r0
            com.google.android.gms.maps.model.LatLngBounds r6 = (com.google.android.gms.maps.model.LatLngBounds) r6
            goto L_0x000a
        L_0x0035:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r1 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r5 = r0
            com.google.android.gms.maps.model.LatLng r5 = (com.google.android.gms.maps.model.LatLng) r5
            goto L_0x000a
        L_0x003f:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r1 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r4 = r0
            com.google.android.gms.maps.model.LatLng r4 = (com.google.android.gms.maps.model.LatLng) r4
            goto L_0x000a
        L_0x0049:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r1 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = (com.google.android.gms.maps.model.LatLng) r3
            goto L_0x000a
        L_0x0053:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r1 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r2 = r0
            com.google.android.gms.maps.model.LatLng r2 = (com.google.android.gms.maps.model.LatLng) r2
            goto L_0x000a
        L_0x005d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r8)
            com.google.android.gms.maps.model.VisibleRegion r8 = new com.google.android.gms.maps.model.VisibleRegion
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.zzal.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new VisibleRegion[i3];
    }
}
