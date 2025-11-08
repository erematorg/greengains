package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;

public final class zzp implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v9, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
        /*
            r8 = this;
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r9)
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            r0 = 0
            r2 = 0
            r3 = r0
            r5 = r3
            r4 = r2
        L_0x000e:
            int r0 = r9.dataPosition()
            if (r0 >= r8) goto L_0x0063
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r7 = 1
            if (r6 == r7) goto L_0x0057
            r7 = 2
            if (r6 == r7) goto L_0x0049
            r7 = 3
            if (r6 == r7) goto L_0x003d
            r7 = 4
            if (r6 == r7) goto L_0x002c
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000e
        L_0x002c:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzs> r5 = com.google.android.gms.auth.api.accounttransfer.zzs.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r5)
            r5 = r0
            com.google.android.gms.auth.api.accounttransfer.zzs r5 = (com.google.android.gms.auth.api.accounttransfer.zzs) r5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)
            r1.add(r0)
            goto L_0x000e
        L_0x003d:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)
            r1.add(r0)
            goto L_0x000e
        L_0x0049:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzu> r3 = com.google.android.gms.auth.api.accounttransfer.zzu.CREATOR
            java.util.ArrayList r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r9, r0, r3)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)
            r1.add(r0)
            goto L_0x000e
        L_0x0057:
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)
            r1.add(r0)
            goto L_0x000e
        L_0x0063:
            int r0 = r9.dataPosition()
            if (r0 != r8) goto L_0x0070
            com.google.android.gms.auth.api.accounttransfer.zzo r8 = new com.google.android.gms.auth.api.accounttransfer.zzo
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            return r8
        L_0x0070:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r0 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            java.lang.String r1 = "Overread allowed size end="
            java.lang.String r8 = A.a.k(r1, r8)
            r0.<init>(r8, r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzp.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzo[i3];
    }
}
