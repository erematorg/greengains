package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;

public final class zzx implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v14, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            r0 = 0
            r2 = 0
            r3 = r0
            r5 = r3
            r6 = r5
            r7 = r6
            r4 = r2
        L_0x0010:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x007d
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r8) {
                case 1: goto L_0x0070;
                case 2: goto L_0x0063;
                case 3: goto L_0x0056;
                case 4: goto L_0x0049;
                case 5: goto L_0x0037;
                case 6: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x0010
        L_0x0025:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.DeviceMetaData> r7 = com.google.android.gms.auth.api.accounttransfer.DeviceMetaData.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r7)
            r7 = r0
            com.google.android.gms.auth.api.accounttransfer.DeviceMetaData r7 = (com.google.android.gms.auth.api.accounttransfer.DeviceMetaData) r7
            r0 = 6
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L_0x0010
        L_0x0037:
            android.os.Parcelable$Creator r6 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r6)
            r6 = r0
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            r0 = 5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L_0x0010
        L_0x0049:
            byte[] r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r10, r0)
            r0 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L_0x0010
        L_0x0056:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            r0 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L_0x0010
        L_0x0063:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L_0x0010
        L_0x0070:
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            r0 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L_0x0010
        L_0x007d:
            int r0 = r10.dataPosition()
            if (r0 != r9) goto L_0x008a
            com.google.android.gms.auth.api.accounttransfer.zzw r9 = new com.google.android.gms.auth.api.accounttransfer.zzw
            r0 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x008a:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r0 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            java.lang.String r1 = "Overread allowed size end="
            java.lang.String r9 = A.a.k(r1, r9)
            r0.<init>(r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzx.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzw[i3];
    }
}
