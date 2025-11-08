package com.google.android.gms.auth.api.identity;

import android.os.Parcelable;

public final class zbf implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r12)
            r0 = 0
            r1 = 0
            r6 = r0
            r7 = r6
            r10 = r7
            r3 = r1
            r4 = r3
            r5 = r4
            r8 = r5
            r9 = r8
        L_0x000e:
            int r0 = r12.dataPosition()
            if (r0 >= r11) goto L_0x005f
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r12)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x0055;
                case 2: goto L_0x004b;
                case 3: goto L_0x0046;
                case 4: goto L_0x0041;
                case 5: goto L_0x003c;
                case 6: goto L_0x0032;
                case 7: goto L_0x0028;
                case 8: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r12, r0)
            goto L_0x000e
        L_0x0023:
            boolean r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r12, r0)
            goto L_0x000e
        L_0x0028:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeyJsonRequestOptions> r1 = com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r0, r1)
            r9 = r0
            com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeyJsonRequestOptions r9 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions) r9
            goto L_0x000e
        L_0x0032:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeysRequestOptions> r1 = com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r0, r1)
            r8 = r0
            com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeysRequestOptions r8 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions) r8
            goto L_0x000e
        L_0x003c:
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r12, r0)
            goto L_0x000e
        L_0x0041:
            boolean r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r12, r0)
            goto L_0x000e
        L_0x0046:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r0)
            goto L_0x000e
        L_0x004b:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$GoogleIdTokenRequestOptions> r1 = com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r0, r1)
            r4 = r0
            com.google.android.gms.auth.api.identity.BeginSignInRequest$GoogleIdTokenRequestOptions r4 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions) r4
            goto L_0x000e
        L_0x0055:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$PasswordRequestOptions> r1 = com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r0, r1)
            r3 = r0
            com.google.android.gms.auth.api.identity.BeginSignInRequest$PasswordRequestOptions r3 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions) r3
            goto L_0x000e
        L_0x005f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r12, r11)
            com.google.android.gms.auth.api.identity.BeginSignInRequest r11 = new com.google.android.gms.auth.api.identity.BeginSignInRequest
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.identity.zbf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new BeginSignInRequest[i3];
    }
}
