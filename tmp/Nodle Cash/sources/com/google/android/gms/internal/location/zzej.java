package com.google.android.gms.internal.location;

import android.os.Parcelable;

public final class zzej implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r0 = 0
            r1 = 1
            r4 = r0
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r3 = r1
        L_0x000d:
            int r0 = r11.dataPosition()
            if (r0 >= r10) goto L_0x004f
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r0)
            switch(r1) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0040;
                case 3: goto L_0x003b;
                case 4: goto L_0x0031;
                case 5: goto L_0x002c;
                case 6: goto L_0x0027;
                case 7: goto L_0x001e;
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
            android.os.IBinder r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r11, r0)
            goto L_0x000d
        L_0x002c:
            android.os.IBinder r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r11, r0)
            goto L_0x000d
        L_0x0031:
            android.os.Parcelable$Creator r1 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r0, r1)
            r7 = r0
            android.app.PendingIntent r7 = (android.app.PendingIntent) r7
            goto L_0x000d
        L_0x003b:
            android.os.IBinder r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r11, r0)
            goto L_0x000d
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.internal.location.zzeg> r1 = com.google.android.gms.internal.location.zzeg.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r0, r1)
            r4 = r0
            com.google.android.gms.internal.location.zzeg r4 = (com.google.android.gms.internal.location.zzeg) r4
            goto L_0x000d
        L_0x004a:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r11, r0)
            goto L_0x000d
        L_0x004f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r10)
            com.google.android.gms.internal.location.zzei r10 = new com.google.android.gms.internal.location.zzei
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzej.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i3) {
        return new zzei[i3];
    }
}
