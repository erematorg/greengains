package com.google.firebase.auth.internal;

import android.os.Parcelable;

public final class zzak implements Parcelable.Creator<zzai> {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
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
            r7 = r6
        L_0x000b:
            int r0 = r9.dataPosition()
            if (r0 >= r8) goto L_0x0051
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0040;
                case 3: goto L_0x003b;
                case 4: goto L_0x0031;
                case 5: goto L_0x0027;
                case 6: goto L_0x0020;
                default: goto L_0x001c;
            }
        L_0x001c:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r0)
            goto L_0x000b
        L_0x0020:
            android.os.Parcelable$Creator<com.google.firebase.auth.TotpMultiFactorInfo> r1 = com.google.firebase.auth.TotpMultiFactorInfo.CREATOR
            java.util.ArrayList r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r9, r0, r1)
            goto L_0x000b
        L_0x0027:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzac> r1 = com.google.firebase.auth.internal.zzac.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r6 = r0
            com.google.firebase.auth.internal.zzac r6 = (com.google.firebase.auth.internal.zzac) r6
            goto L_0x000b
        L_0x0031:
            android.os.Parcelable$Creator<com.google.firebase.auth.zzf> r1 = com.google.firebase.auth.zzf.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r5 = r0
            com.google.firebase.auth.zzf r5 = (com.google.firebase.auth.zzf) r5
            goto L_0x000b
        L_0x003b:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r9, r0)
            goto L_0x000b
        L_0x0040:
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzaj> r1 = com.google.firebase.auth.internal.zzaj.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r0, r1)
            r3 = r0
            com.google.firebase.auth.internal.zzaj r3 = (com.google.firebase.auth.internal.zzaj) r3
            goto L_0x000b
        L_0x004a:
            android.os.Parcelable$Creator<com.google.firebase.auth.PhoneMultiFactorInfo> r1 = com.google.firebase.auth.PhoneMultiFactorInfo.CREATOR
            java.util.ArrayList r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r9, r0, r1)
            goto L_0x000b
        L_0x0051:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r9, r8)
            com.google.firebase.auth.internal.zzai r8 = new com.google.firebase.auth.internal.zzai
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzak.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzai[i3];
    }
}
