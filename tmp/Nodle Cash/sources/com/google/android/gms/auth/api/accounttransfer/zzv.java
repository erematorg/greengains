package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;

public final class zzv implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v7, types: [android.os.Parcelable] */
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
            r4 = r3
            r5 = r4
            r6 = r5
        L_0x000f:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x0071
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r8 = 1
            if (r7 == r8) goto L_0x0065
            r8 = 2
            if (r7 == r8) goto L_0x0054
            r8 = 3
            if (r7 == r8) goto L_0x0048
            r8 = 4
            if (r7 == r8) goto L_0x003c
            r8 = 5
            if (r7 == r8) goto L_0x0030
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000f
        L_0x0030:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r1.add(r0)
            goto L_0x000f
        L_0x003c:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r1.add(r0)
            goto L_0x000f
        L_0x0048:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r1.add(r0)
            goto L_0x000f
        L_0x0054:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzw> r3 = com.google.android.gms.auth.api.accounttransfer.zzw.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r3)
            r3 = r0
            com.google.android.gms.auth.api.accounttransfer.zzw r3 = (com.google.android.gms.auth.api.accounttransfer.zzw) r3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r1.add(r0)
            goto L_0x000f
        L_0x0065:
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r1.add(r0)
            goto L_0x000f
        L_0x0071:
            int r0 = r10.dataPosition()
            if (r0 != r9) goto L_0x007e
            com.google.android.gms.auth.api.accounttransfer.zzu r9 = new com.google.android.gms.auth.api.accounttransfer.zzu
            r0 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r9
        L_0x007e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r0 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            java.lang.String r1 = "Overread allowed size end="
            java.lang.String r9 = A.a.k(r1, r9)
            r0.<init>(r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzv.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzu[i3];
    }
}
