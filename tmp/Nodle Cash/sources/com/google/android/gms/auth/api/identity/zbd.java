package com.google.android.gms.auth.api.identity;

import android.os.Parcelable;

public final class zbd implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r13)
            r0 = 0
            r1 = 0
            r3 = r0
            r4 = r3
            r7 = r4
            r8 = r7
            r9 = r8
            r11 = r9
            r5 = r1
            r6 = r5
            r10 = r6
        L_0x000f:
            int r0 = r13.dataPosition()
            if (r0 >= r12) goto L_0x0058
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x0051;
                case 2: goto L_0x004c;
                case 3: goto L_0x0047;
                case 4: goto L_0x0042;
                case 5: goto L_0x0038;
                case 6: goto L_0x0033;
                case 7: goto L_0x002e;
                case 8: goto L_0x0029;
                case 9: goto L_0x0024;
                default: goto L_0x0020;
            }
        L_0x0020:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r0)
            goto L_0x000f
        L_0x0024:
            android.os.Bundle r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r13, r0)
            goto L_0x000f
        L_0x0029:
            boolean r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r0)
            goto L_0x000f
        L_0x002e:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r0)
            goto L_0x000f
        L_0x0033:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r0)
            goto L_0x000f
        L_0x0038:
            android.os.Parcelable$Creator r1 = android.accounts.Account.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r7 = r0
            android.accounts.Account r7 = (android.accounts.Account) r7
            goto L_0x000f
        L_0x0042:
            boolean r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r0)
            goto L_0x000f
        L_0x0047:
            boolean r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r0)
            goto L_0x000f
        L_0x004c:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r0)
            goto L_0x000f
        L_0x0051:
            android.os.Parcelable$Creator<com.google.android.gms.common.api.Scope> r1 = com.google.android.gms.common.api.Scope.CREATOR
            java.util.ArrayList r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r13, r0, r1)
            goto L_0x000f
        L_0x0058:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r12)
            com.google.android.gms.auth.api.identity.AuthorizationRequest r12 = new com.google.android.gms.auth.api.identity.AuthorizationRequest
            r2 = r12
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.identity.zbd.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new AuthorizationRequest[i3];
    }
}
