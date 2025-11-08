package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzhc extends zzhb {
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r12[r13] <= -65) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0080, code lost:
        if (r12[r13] <= -65) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r12[r13] <= -65) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(int r11, byte[] r12, int r13, int r14) {
        /*
            r10 = this;
            r10 = -19
            r0 = -16
            r1 = -62
            r2 = 0
            r3 = -96
            r4 = -32
            r5 = -65
            r6 = -1
            if (r11 == 0) goto L_0x0084
            if (r13 < r14) goto L_0x0013
            return r11
        L_0x0013:
            byte r7 = (byte) r11
            if (r7 >= r4) goto L_0x0022
            if (r7 < r1) goto L_0x0021
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 > r5) goto L_0x0021
        L_0x001e:
            r13 = r11
            goto L_0x0084
        L_0x0021:
            return r6
        L_0x0022:
            int r8 = r11 >> 8
            int r8 = ~r8
            if (r7 >= r0) goto L_0x004b
            byte r11 = (byte) r8
            if (r11 != 0) goto L_0x0039
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r11 >= r14) goto L_0x0034
            r9 = r13
            r13 = r11
            r11 = r9
            goto L_0x0039
        L_0x0034:
            int r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhe.zzh(r7, r13)
            return r10
        L_0x0039:
            if (r11 > r5) goto L_0x004a
            if (r7 != r4) goto L_0x003f
            if (r11 < r3) goto L_0x004a
        L_0x003f:
            if (r7 != r10) goto L_0x0043
            if (r11 >= r3) goto L_0x004a
        L_0x0043:
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 > r5) goto L_0x004a
            goto L_0x001e
        L_0x004a:
            return r6
        L_0x004b:
            byte r8 = (byte) r8
            if (r8 != 0) goto L_0x005c
            int r11 = r13 + 1
            byte r8 = r12[r13]
            if (r11 >= r14) goto L_0x0057
            r13 = r11
            r11 = r2
            goto L_0x005e
        L_0x0057:
            int r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhe.zzh(r7, r8)
            return r10
        L_0x005c:
            int r11 = r11 >> 16
        L_0x005e:
            if (r11 != 0) goto L_0x006f
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r11 >= r14) goto L_0x006a
            r9 = r13
            r13 = r11
            r11 = r9
            goto L_0x006f
        L_0x006a:
            int r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhe.zzi(r7, r8, r13)
            return r10
        L_0x006f:
            if (r8 > r5) goto L_0x0083
            int r7 = r7 << 28
            int r8 = r8 + 112
            int r8 = r8 + r7
            int r7 = r8 >> 30
            if (r7 != 0) goto L_0x0083
            if (r11 > r5) goto L_0x0083
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 > r5) goto L_0x0083
            goto L_0x001e
        L_0x0083:
            return r6
        L_0x0084:
            if (r13 >= r14) goto L_0x008d
            byte r11 = r12[r13]
            if (r11 < 0) goto L_0x008d
            int r13 = r13 + 1
            goto L_0x0084
        L_0x008d:
            if (r13 < r14) goto L_0x0091
            goto L_0x00f0
        L_0x0091:
            if (r13 < r14) goto L_0x0095
            goto L_0x00f0
        L_0x0095:
            int r11 = r13 + 1
            byte r7 = r12[r13]
            if (r7 >= 0) goto L_0x00f1
            if (r7 >= r4) goto L_0x00ab
            if (r11 < r14) goto L_0x00a1
            r2 = r7
            goto L_0x00f0
        L_0x00a1:
            if (r7 < r1) goto L_0x00a9
            int r13 = r13 + 2
            byte r11 = r12[r11]
            if (r11 <= r5) goto L_0x0091
        L_0x00a9:
            r2 = r6
            goto L_0x00f0
        L_0x00ab:
            if (r7 >= r0) goto L_0x00cb
            int r8 = r14 + -1
            if (r11 < r8) goto L_0x00b6
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhe.zzc(r12, r11, r14)
            goto L_0x00f0
        L_0x00b6:
            int r8 = r13 + 2
            byte r11 = r12[r11]
            if (r11 > r5) goto L_0x00a9
            if (r7 != r4) goto L_0x00c0
            if (r11 < r3) goto L_0x00a9
        L_0x00c0:
            if (r7 != r10) goto L_0x00c4
            if (r11 >= r3) goto L_0x00a9
        L_0x00c4:
            int r13 = r13 + 3
            byte r11 = r12[r8]
            if (r11 <= r5) goto L_0x0091
            goto L_0x00a9
        L_0x00cb:
            int r8 = r14 + -2
            if (r11 < r8) goto L_0x00d4
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhe.zzc(r12, r11, r14)
            goto L_0x00f0
        L_0x00d4:
            int r8 = r13 + 2
            byte r11 = r12[r11]
            if (r11 > r5) goto L_0x00a9
            int r7 = r7 << 28
            int r11 = r11 + 112
            int r11 = r11 + r7
            int r11 = r11 >> 30
            if (r11 != 0) goto L_0x00a9
            int r11 = r13 + 3
            byte r7 = r12[r8]
            if (r7 > r5) goto L_0x00a9
            int r13 = r13 + 4
            byte r11 = r12[r11]
            if (r11 <= r5) goto L_0x0091
            goto L_0x00a9
        L_0x00f0:
            return r2
        L_0x00f1:
            r13 = r11
            goto L_0x0091
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhc.zza(int, byte[], int, int):int");
    }
}
