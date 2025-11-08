package com.google.android.gms.auth.api.proxy;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r0 = 0
            r1 = 0
            r5 = r0
            r7 = r5
            r8 = r7
            r3 = r1
            r4 = r3
            r6 = r4
        L_0x000c:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x0054
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r2 = 1
            if (r1 == r2) goto L_0x004f
            r2 = 2
            if (r1 == r2) goto L_0x0045
            r2 = 3
            if (r1 == r2) goto L_0x0040
            r2 = 4
            if (r1 == r2) goto L_0x003b
            r2 = 5
            if (r1 == r2) goto L_0x0036
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 == r2) goto L_0x0031
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000c
        L_0x0031:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0036:
            byte[] r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r10, r0)
            goto L_0x000c
        L_0x003b:
            android.os.Bundle r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r10, r0)
            goto L_0x000c
        L_0x0040:
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0045:
            android.os.Parcelable$Creator r1 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r5 = r0
            android.app.PendingIntent r5 = (android.app.PendingIntent) r5
            goto L_0x000c
        L_0x004f:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0054:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.android.gms.auth.api.proxy.ProxyResponse r9 = new com.google.android.gms.auth.api.proxy.ProxyResponse
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.proxy.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new ProxyResponse[i3];
    }
}
