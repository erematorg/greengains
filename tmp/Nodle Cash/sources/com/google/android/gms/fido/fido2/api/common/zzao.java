package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzao implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r12)
            r0 = 0
            r2 = r0
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
        L_0x000e:
            int r0 = r12.dataPosition()
            if (r0 >= r11) goto L_0x005c
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r12)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 2: goto L_0x0057;
                case 3: goto L_0x0052;
                case 4: goto L_0x004d;
                case 5: goto L_0x0046;
                case 6: goto L_0x0041;
                case 7: goto L_0x0037;
                case 8: goto L_0x0032;
                case 9: goto L_0x0028;
                case 10: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r12, r0)
            goto L_0x000e
        L_0x0023:
            java.lang.Long r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLongObject(r12, r0)
            goto L_0x000e
        L_0x0028:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r0, r1)
            r9 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r9 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions) r9
            goto L_0x000e
        L_0x0032:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r0)
            goto L_0x000e
        L_0x0037:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.TokenBinding> r1 = com.google.android.gms.fido.fido2.api.common.TokenBinding.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r0, r1)
            r7 = r0
            com.google.android.gms.fido.fido2.api.common.TokenBinding r7 = (com.google.android.gms.fido.fido2.api.common.TokenBinding) r7
            goto L_0x000e
        L_0x0041:
            java.lang.Integer r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r12, r0)
            goto L_0x000e
        L_0x0046:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r1 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.CREATOR
            java.util.ArrayList r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r12, r0, r1)
            goto L_0x000e
        L_0x004d:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r0)
            goto L_0x000e
        L_0x0052:
            java.lang.Double r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDoubleObject(r12, r0)
            goto L_0x000e
        L_0x0057:
            byte[] r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r12, r0)
            goto L_0x000e
        L_0x005c:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r12, r11)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions r11 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzao.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new PublicKeyCredentialRequestOptions[i3];
    }
}
