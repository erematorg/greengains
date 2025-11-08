package com.google.android.gms.internal.location;

import android.os.Parcelable;

public final class zzf implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r0 = 0
            r1 = 0
            r4 = r0
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r3 = r1
        L_0x000c:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x0056
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r2 = 1
            if (r1 == r2) goto L_0x0051
            r2 = 3
            if (r1 == r2) goto L_0x004c
            r2 = 4
            if (r1 == r2) goto L_0x0047
            r2 = 6
            if (r1 == r2) goto L_0x0042
            r2 = 7
            if (r1 == r2) goto L_0x0038
            r2 = 8
            if (r1 == r2) goto L_0x0031
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000c
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.common.Feature> r1 = com.google.android.gms.common.Feature.CREATOR
            java.util.ArrayList r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r10, r0, r1)
            goto L_0x000c
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.internal.location.zze> r1 = com.google.android.gms.internal.location.zze.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r8 = r0
            com.google.android.gms.internal.location.zze r8 = (com.google.android.gms.internal.location.zze) r8
            goto L_0x000c
        L_0x0042:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0047:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x004c:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0051:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0056:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.android.gms.internal.location.zze r9 = new com.google.android.gms.internal.location.zze
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zze[i3];
    }
}
