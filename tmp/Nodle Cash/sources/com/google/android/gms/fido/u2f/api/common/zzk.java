package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcelable;

public final class zzk implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
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
            if (r0 >= r9) goto L_0x0050
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 2: goto L_0x004b;
                case 3: goto L_0x0046;
                case 4: goto L_0x003c;
                case 5: goto L_0x0037;
                case 6: goto L_0x0030;
                case 7: goto L_0x0026;
                case 8: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r0)
            goto L_0x000c
        L_0x0021:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r0)
            goto L_0x000c
        L_0x0026:
            android.os.Parcelable$Creator<com.google.android.gms.fido.u2f.api.common.ChannelIdValue> r1 = com.google.android.gms.fido.u2f.api.common.ChannelIdValue.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r7 = r0
            com.google.android.gms.fido.u2f.api.common.ChannelIdValue r7 = (com.google.android.gms.fido.u2f.api.common.ChannelIdValue) r7
            goto L_0x000c
        L_0x0030:
            android.os.Parcelable$Creator<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r1 = com.google.android.gms.fido.u2f.api.common.RegisteredKey.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r10, r0, r1)
            goto L_0x000c
        L_0x0037:
            byte[] r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r10, r0)
            goto L_0x000c
        L_0x003c:
            android.os.Parcelable$Creator r1 = android.net.Uri.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r0, r1)
            r4 = r0
            android.net.Uri r4 = (android.net.Uri) r4
            goto L_0x000c
        L_0x0046:
            java.lang.Double r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDoubleObject(r10, r0)
            goto L_0x000c
        L_0x004b:
            java.lang.Integer r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r10, r0)
            goto L_0x000c
        L_0x0050:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r9)
            com.google.android.gms.fido.u2f.api.common.SignRequestParams r9 = new com.google.android.gms.fido.u2f.api.common.SignRequestParams
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.common.zzk.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new SignRequestParams[i3];
    }
}
