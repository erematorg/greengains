package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzal implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r0 = 0
            r2 = r0
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
        L_0x000d:
            int r0 = r11.dataPosition()
            if (r0 >= r10) goto L_0x005e
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x0059;
                case 2: goto L_0x0054;
                case 3: goto L_0x004f;
                case 4: goto L_0x0045;
                case 5: goto L_0x003b;
                case 6: goto L_0x0031;
                case 7: goto L_0x0027;
                case 8: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r0)
            goto L_0x000d
        L_0x0022:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r0)
            goto L_0x000d
        L_0x0027:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r0, r1)
            r8 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs r8 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs) r8
            goto L_0x000d
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r0, r1)
            r7 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse r7 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse) r7
            goto L_0x000d
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r0, r1)
            r6 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse r6 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse) r6
            goto L_0x000d
        L_0x0045:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r0, r1)
            r5 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse r5 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse) r5
            goto L_0x000d
        L_0x004f:
            byte[] r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r11, r0)
            goto L_0x000d
        L_0x0054:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r0)
            goto L_0x000d
        L_0x0059:
            java.lang.String r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r0)
            goto L_0x000d
        L_0x005e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r10)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredential r10 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredential
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzal.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new PublicKeyCredential[i3];
    }
}
