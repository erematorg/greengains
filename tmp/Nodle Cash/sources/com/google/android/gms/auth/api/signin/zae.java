package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;

public final class zae implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r14)
            r0 = 0
            r1 = 0
            r4 = r0
            r5 = r4
            r9 = r5
            r10 = r9
            r11 = r10
            r12 = r11
            r3 = r1
            r6 = r3
            r7 = r6
            r8 = r7
        L_0x0010:
            int r0 = r14.dataPosition()
            if (r0 >= r13) goto L_0x0060
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r14)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x005b;
                case 2: goto L_0x0054;
                case 3: goto L_0x004a;
                case 4: goto L_0x0045;
                case 5: goto L_0x0040;
                case 6: goto L_0x003b;
                case 7: goto L_0x0036;
                case 8: goto L_0x0031;
                case 9: goto L_0x002a;
                case 10: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r14, r0)
            goto L_0x0010
        L_0x0025:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r0)
            goto L_0x0010
        L_0x002a:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> r1 = com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable.CREATOR
            java.util.ArrayList r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r14, r0, r1)
            goto L_0x0010
        L_0x0031:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r0)
            goto L_0x0010
        L_0x0036:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r0)
            goto L_0x0010
        L_0x003b:
            boolean r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r14, r0)
            goto L_0x0010
        L_0x0040:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r14, r0)
            goto L_0x0010
        L_0x0045:
            boolean r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r14, r0)
            goto L_0x0010
        L_0x004a:
            android.os.Parcelable$Creator r1 = android.accounts.Account.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r5 = r0
            android.accounts.Account r5 = (android.accounts.Account) r5
            goto L_0x0010
        L_0x0054:
            android.os.Parcelable$Creator<com.google.android.gms.common.api.Scope> r1 = com.google.android.gms.common.api.Scope.CREATOR
            java.util.ArrayList r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r14, r0, r1)
            goto L_0x0010
        L_0x005b:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r14, r0)
            goto L_0x0010
        L_0x0060:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r14, r13)
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r13 = new com.google.android.gms.auth.api.signin.GoogleSignInOptions
            r2 = r13
            r2.<init>((int) r3, (java.util.ArrayList) r4, (android.accounts.Account) r5, (boolean) r6, (boolean) r7, (boolean) r8, (java.lang.String) r9, (java.lang.String) r10, (java.util.ArrayList) r11, (java.lang.String) r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.zae.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new GoogleSignInOptions[i3];
    }
}
