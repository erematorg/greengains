package com.google.android.gms.internal.mlkit_vision_barcode;

import A.a;

final class zzcj {
    public static int zza(int i3) {
        return (i3 + 1) * (i3 < 32 ? 4 : 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r5 != -1) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        zze(r11, r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        r12[r5] = (r12[r5] & r4) | (r7 & r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzb(@javax.annotation.CheckForNull java.lang.Object r8, @javax.annotation.CheckForNull java.lang.Object r9, int r10, java.lang.Object r11, int[] r12, java.lang.Object[] r13, @javax.annotation.CheckForNull java.lang.Object[] r14) {
        /*
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode.zzck.zza(r8)
            r1 = r0 & r10
            int r2 = zzc(r11, r1)
            r3 = -1
            if (r2 == 0) goto L_0x003e
            int r4 = ~r10
            r0 = r0 & r4
            r5 = r3
        L_0x0010:
            int r2 = r2 + r3
            r6 = r12[r2]
            r7 = r6 & r10
            r6 = r6 & r4
            if (r6 != r0) goto L_0x0039
            r6 = r13[r2]
            boolean r6 = com.google.android.gms.internal.mlkit_vision_barcode.zzax.zza(r8, r6)
            if (r6 == 0) goto L_0x0039
            if (r14 == 0) goto L_0x002a
            r6 = r14[r2]
            boolean r6 = com.google.android.gms.internal.mlkit_vision_barcode.zzax.zza(r9, r6)
            if (r6 == 0) goto L_0x0039
        L_0x002a:
            if (r5 != r3) goto L_0x0030
            zze(r11, r1, r7)
            goto L_0x0038
        L_0x0030:
            r8 = r12[r5]
            r8 = r8 & r4
            r9 = r7 & r10
            r8 = r8 | r9
            r12[r5] = r8
        L_0x0038:
            return r2
        L_0x0039:
            if (r7 == 0) goto L_0x003e
            r5 = r2
            r2 = r7
            goto L_0x0010
        L_0x003e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzcj.zzb(java.lang.Object, java.lang.Object, int, java.lang.Object, int[], java.lang.Object[], java.lang.Object[]):int");
    }

    public static int zzc(Object obj, int i3) {
        return obj instanceof byte[] ? ((byte[]) obj)[i3] & 255 : obj instanceof short[] ? (char) ((short[]) obj)[i3] : ((int[]) obj)[i3];
    }

    public static Object zzd(int i3) {
        if (i3 >= 2 && i3 <= 1073741824 && Integer.highestOneBit(i3) == i3) {
            return i3 <= 256 ? new byte[i3] : i3 <= 65536 ? new short[i3] : new int[i3];
        }
        throw new IllegalArgumentException(a.k("must be power of 2 between 2^1 and 2^30: ", i3));
    }

    public static void zze(Object obj, int i3, int i4) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i3] = (byte) i4;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i3] = (short) i4;
        } else {
            ((int[]) obj)[i3] = i4;
        }
    }
}
