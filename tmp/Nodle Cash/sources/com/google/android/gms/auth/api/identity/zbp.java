package com.google.android.gms.auth.api.identity;

import android.os.Parcelable;

public final class zbp implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r0 = 0
            r1 = 0
            r8 = r0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x000c:
            int r0 = r10.dataPosition()
            if (r0 >= r9) goto L_0x0044
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x003a;
                case 2: goto L_0x0035;
                case 3: goto L_0x0030;
                case 4: goto L_0x002b;
                case 5: goto L_0x0026;
                case 6: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000c
        L_0x0021:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r0)
            goto L_0x000c
        L_0x0026:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x002b:
            java.util.ArrayList r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r10, r0)
            goto L_0x000c
        L_0x0030:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0035:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x003a:
            android.os.Parcelable$Creator r1 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r3 = r0
            android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            goto L_0x000c
        L_0x0044:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest r9 = new com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.identity.zbp.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new SaveAccountLinkingTokenRequest[i3];
    }
}
