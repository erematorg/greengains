package com.google.firebase.auth;

import android.os.Parcelable;

public final class zze implements Parcelable.Creator<zzf> {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r0 = 0
            r2 = r0
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L_0x000c:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x0049
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x0044;
                case 2: goto L_0x003f;
                case 3: goto L_0x003a;
                case 4: goto L_0x0030;
                case 5: goto L_0x002b;
                case 6: goto L_0x0026;
                case 7: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000c
        L_0x0021:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0026:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x002b:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0030:
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase-auth-api.zzags> r1 = com.google.android.gms.internal.p002firebaseauthapi.zzags.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r5 = r0
            com.google.android.gms.internal.firebase-auth-api.zzags r5 = (com.google.android.gms.internal.p002firebaseauthapi.zzags) r5
            goto L_0x000c
        L_0x003a:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x003f:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0044:
            java.lang.String r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0049:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.firebase.auth.zzf r9 = new com.google.firebase.auth.zzf
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.zze.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzf[i3];
    }
}
