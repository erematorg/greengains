package com.google.firebase.auth.internal;

import android.os.Parcelable;

public final class zzam implements Parcelable.Creator<zzaj> {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
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
            if (r0 >= r8) goto L_0x004d
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r7 = 1
            if (r1 == r7) goto L_0x0048
            r7 = 2
            if (r1 == r7) goto L_0x0043
            r7 = 3
            if (r1 == r7) goto L_0x003c
            r7 = 4
            if (r1 == r7) goto L_0x0035
            r7 = 5
            if (r1 == r7) goto L_0x002b
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000a
        L_0x002b:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzac> r1 = com.google.firebase.auth.internal.zzac.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r6 = r0
            com.google.firebase.auth.internal.zzac r6 = (com.google.firebase.auth.internal.zzac) r6
            goto L_0x000a
        L_0x0035:
            android.os.Parcelable$Creator<com.google.firebase.auth.TotpMultiFactorInfo> r1 = com.google.firebase.auth.TotpMultiFactorInfo.CREATOR
            java.util.ArrayList r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r9, r0, r1)
            goto L_0x000a
        L_0x003c:
            android.os.Parcelable$Creator<com.google.firebase.auth.PhoneMultiFactorInfo> r1 = com.google.firebase.auth.PhoneMultiFactorInfo.CREATOR
            java.util.ArrayList r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r9, r0, r1)
            goto L_0x000a
        L_0x0043:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r0)
            goto L_0x000a
        L_0x0048:
            java.lang.String r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r0)
            goto L_0x000a
        L_0x004d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r8)
            com.google.firebase.auth.internal.zzaj r8 = new com.google.firebase.auth.internal.zzaj
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzam.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzaj[i3];
    }
}
