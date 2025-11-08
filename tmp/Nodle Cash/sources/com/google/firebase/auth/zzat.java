package com.google.firebase.auth;

import android.os.Parcelable;

public final class zzat implements Parcelable.Creator<TotpMultiFactorInfo> {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r0 = 0
            r1 = 0
            r4 = r0
            r5 = r4
            r8 = r5
            r6 = r1
        L_0x000b:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x0042
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            r2 = 1
            if (r1 == r2) goto L_0x003d
            r2 = 2
            if (r1 == r2) goto L_0x0038
            r2 = 3
            if (r1 == r2) goto L_0x0033
            r2 = 4
            if (r1 == r2) goto L_0x0029
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000b
        L_0x0029:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase-auth-api.zzagq> r1 = com.google.android.gms.internal.p002firebaseauthapi.zzagq.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r8 = r0
            com.google.android.gms.internal.firebase-auth-api.zzagq r8 = (com.google.android.gms.internal.p002firebaseauthapi.zzagq) r8
            goto L_0x000b
        L_0x0033:
            long r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r10, r0)
            goto L_0x000b
        L_0x0038:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000b
        L_0x003d:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000b
        L_0x0042:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.firebase.auth.TotpMultiFactorInfo r9 = new com.google.firebase.auth.TotpMultiFactorInfo
            r3 = r9
            r3.<init>(r4, r5, r6, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.zzat.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new TotpMultiFactorInfo[i3];
    }
}
