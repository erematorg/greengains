package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzd implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v8, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v10, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v11, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r13)
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
        L_0x000f:
            int r0 = r13.dataPosition()
            if (r0 >= r12) goto L_0x0088
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 2: goto L_0x007e;
                case 3: goto L_0x0074;
                case 4: goto L_0x006a;
                case 5: goto L_0x0060;
                case 6: goto L_0x0056;
                case 7: goto L_0x004c;
                case 8: goto L_0x0042;
                case 9: goto L_0x0038;
                case 10: goto L_0x002e;
                case 11: goto L_0x0024;
                default: goto L_0x0020;
            }
        L_0x0020:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r0)
            goto L_0x000f
        L_0x0024:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzai> r1 = com.google.android.gms.fido.fido2.api.common.zzai.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r11 = r0
            com.google.android.gms.fido.fido2.api.common.zzai r11 = (com.google.android.gms.fido.fido2.api.common.zzai) r11
            goto L_0x000f
        L_0x002e:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.GoogleThirdPartyPaymentExtension> r1 = com.google.android.gms.fido.fido2.api.common.GoogleThirdPartyPaymentExtension.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r10 = r0
            com.google.android.gms.fido.fido2.api.common.GoogleThirdPartyPaymentExtension r10 = (com.google.android.gms.fido.fido2.api.common.GoogleThirdPartyPaymentExtension) r10
            goto L_0x000f
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzag> r1 = com.google.android.gms.fido.fido2.api.common.zzag.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r9 = r0
            com.google.android.gms.fido.fido2.api.common.zzag r9 = (com.google.android.gms.fido.fido2.api.common.zzag) r9
            goto L_0x000f
        L_0x0042:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzu> r1 = com.google.android.gms.fido.fido2.api.common.zzu.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r8 = r0
            com.google.android.gms.fido.fido2.api.common.zzu r8 = (com.google.android.gms.fido.fido2.api.common.zzu) r8
            goto L_0x000f
        L_0x004c:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzad> r1 = com.google.android.gms.fido.fido2.api.common.zzad.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r7 = r0
            com.google.android.gms.fido.fido2.api.common.zzad r7 = (com.google.android.gms.fido.fido2.api.common.zzad) r7
            goto L_0x000f
        L_0x0056:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzab> r1 = com.google.android.gms.fido.fido2.api.common.zzab.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r6 = r0
            com.google.android.gms.fido.fido2.api.common.zzab r6 = (com.google.android.gms.fido.fido2.api.common.zzab) r6
            goto L_0x000f
        L_0x0060:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzz> r1 = com.google.android.gms.fido.fido2.api.common.zzz.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r5 = r0
            com.google.android.gms.fido.fido2.api.common.zzz r5 = (com.google.android.gms.fido.fido2.api.common.zzz) r5
            goto L_0x000f
        L_0x006a:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension> r1 = com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r4 = r0
            com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension r4 = (com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension) r4
            goto L_0x000f
        L_0x0074:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.zzs> r1 = com.google.android.gms.fido.fido2.api.common.zzs.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r3 = r0
            com.google.android.gms.fido.fido2.api.common.zzs r3 = (com.google.android.gms.fido.fido2.api.common.zzs) r3
            goto L_0x000f
        L_0x007e:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension> r1 = com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r0, r1)
            r2 = r0
            com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension r2 = (com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension) r2
            goto L_0x000f
        L_0x0088:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r12)
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r12 = new com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzd.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new AuthenticationExtensions[i3];
    }
}
