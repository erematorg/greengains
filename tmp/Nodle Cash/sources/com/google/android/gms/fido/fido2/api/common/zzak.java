package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzak implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r14)
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
            r11 = r10
            r12 = r11
        L_0x0010:
            int r0 = r14.dataPosition()
            if (r0 >= r13) goto L_0x0079
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r14)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 2: goto L_0x006f;
                case 3: goto L_0x0065;
                case 4: goto L_0x0060;
                case 5: goto L_0x0059;
                case 6: goto L_0x0054;
                case 7: goto L_0x004d;
                case 8: goto L_0x0043;
                case 9: goto L_0x003e;
                case 10: goto L_0x0034;
                case 11: goto L_0x002f;
                case 12: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r14, r0)
            goto L_0x0010
        L_0x0025:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r12 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r12 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions) r12
            goto L_0x0010
        L_0x002f:
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r0)
            goto L_0x0010
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.TokenBinding> r1 = com.google.android.gms.fido.fido2.api.common.TokenBinding.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r10 = r0
            com.google.android.gms.fido.fido2.api.common.TokenBinding r10 = (com.google.android.gms.fido.fido2.api.common.TokenBinding) r10
            goto L_0x0010
        L_0x003e:
            java.lang.Integer r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r14, r0)
            goto L_0x0010
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria> r1 = com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r8 = r0
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r8 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria) r8
            goto L_0x0010
        L_0x004d:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r1 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.CREATOR
            java.util.ArrayList r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r14, r0, r1)
            goto L_0x0010
        L_0x0054:
            java.lang.Double r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDoubleObject(r14, r0)
            goto L_0x0010
        L_0x0059:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r1 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters.CREATOR
            java.util.ArrayList r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r14, r0, r1)
            goto L_0x0010
        L_0x0060:
            byte[] r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r14, r0)
            goto L_0x0010
        L_0x0065:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity> r1 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r3 = r0
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r3 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity) r3
            goto L_0x0010
        L_0x006f:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity> r1 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r0, r1)
            r2 = r0
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity r2 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity) r2
            goto L_0x0010
        L_0x0079:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r14, r13)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions r13 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions
            r1 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzak.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new PublicKeyCredentialCreationOptions[i3];
    }
}
