package com.amplitude.security;

import androidx.work.WorkInfo;
import java.security.DigestException;
import java.security.MessageDigest;

public final class MD5 extends MessageDigest implements Cloneable {
    private long bytes;
    private int hA;
    private int hB;
    private int hC;
    private int hD;
    private byte[] pad = new byte[64];
    private int padded;

    public MD5() {
        super("MD5");
        init();
    }

    public Object clone() throws CloneNotSupportedException {
        MD5 md5 = (MD5) super.clone();
        md5.pad = (byte[]) this.pad.clone();
        return md5;
    }

    public byte[] engineDigest() {
        try {
            byte[] bArr = new byte[16];
            engineDigest(bArr, 0, 16);
            return bArr;
        } catch (DigestException unused) {
            return null;
        }
    }

    public int engineGetDigestLength() {
        return 16;
    }

    public void engineReset() {
        this.padded = 0;
        this.bytes = 0;
        byte[] bArr = this.pad;
        int i3 = 60;
        do {
            bArr[i3 - 4] = 0;
            bArr[i3 - 3] = 0;
            bArr[i3 - 2] = 0;
            bArr[i3 - 1] = 0;
            bArr[i3] = 0;
            bArr[i3 + 1] = 0;
            bArr[i3 + 2] = 0;
            bArr[i3 + 3] = 0;
            i3 -= 8;
        } while (i3 >= 0);
        init();
    }

    public void engineUpdate(byte b3) {
        this.bytes++;
        int i3 = this.padded;
        if (i3 < 63) {
            byte[] bArr = this.pad;
            this.padded = i3 + 1;
            bArr[i3] = b3;
            return;
        }
        byte[] bArr2 = this.pad;
        bArr2[63] = b3;
        engineUpdate(bArr2, i3);
        this.padded = 0;
    }

    public void init() {
        this.hA = 1732584193;
        this.hB = -271733879;
        this.hC = -1732584194;
        this.hD = 271733878;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r3[61] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        r3[62] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        r3[63] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        engineUpdate(r3, 0);
        r4 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        switch((r4 & 7)) {
            case 0: goto L_0x00a7;
            case 1: goto L_0x008e;
            case 2: goto L_0x0079;
            case 3: goto L_0x0068;
            case 4: goto L_0x005b;
            case 5: goto L_0x0050;
            case 6: goto L_0x0047;
            case 7: goto L_0x0043;
            default: goto L_0x0041;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r4 = r4 - 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r13 = r4 - 2;
        r3[r4 + 1] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        r4 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        r13 = r4 - 1;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
        r13 = r4 + 1;
        r3[r13] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
        r3[r4 + 4] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
        r13 = r4 + 2;
        r3[r4 + 1] = 0;
        r3[r13] = 0;
        r3[r4 + 3] = 0;
        r3[r4 + 4] = 0;
        r3[r4 + 5] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008e, code lost:
        r13 = r4 + 3;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r13] = 0;
        r3[r4 + 4] = 0;
        r3[r4 + 5] = 0;
        r3[r4 + 6] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r13 = r4 + 4;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
        r3[r13] = 0;
        r3[r4 + 5] = 0;
        r3[r4 + 6] = 0;
        r3[r4 + 7] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c4, code lost:
        r13 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c8, code lost:
        if (r13 > 52) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ca, code lost:
        r3[r4 + 4] = 0;
        r3[r4 + 5] = 0;
        r3[r4 + 6] = 0;
        r3[r4 + 7] = 0;
        r3[r13] = 0;
        r3[r4 + 9] = 0;
        r3[r4 + 10] = 0;
        r3[r4 + 11] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ea, code lost:
        r13 = r0.bytes;
        r4 = ((int) r13) << 3;
        r3[56] = (byte) r4;
        r3[57] = (byte) (r4 >>> 8);
        r3[58] = (byte) (r4 >>> 16);
        r3[59] = (byte) (r4 >>> 24);
        r4 = (int) (r13 >>> 29);
        r3[60] = (byte) r4;
        r3[61] = (byte) (r4 >>> 8);
        r3[62] = (byte) (r4 >>> 16);
        r3[63] = (byte) (r4 >>> 24);
        engineUpdate(r3, 0);
        r3 = r0.hA;
        r1[r19] = (byte) r3;
        r1[r19 + 1] = (byte) (r3 >>> 8);
        r1[r19 + 2] = (byte) (r3 >>> 16);
        r1[r19 + 3] = (byte) (r3 >>> 24);
        r4 = r0.hB;
        r1[r19 + 4] = (byte) r4;
        r1[r19 + 5] = (byte) (r4 >>> 8);
        r1[r19 + 6] = (byte) (r4 >>> 16);
        r1[r19 + 7] = (byte) (r4 >>> 24);
        r5 = r0.hC;
        r1[r19 + 8] = (byte) r5;
        r1[r19 + 9] = (byte) (r5 >>> 8);
        r1[r19 + 10] = (byte) (r5 >>> 16);
        r1[r19 + 11] = (byte) (r5 >>> 24);
        r4 = r0.hD;
        r1[r19 + 12] = (byte) r4;
        r1[r19 + 13] = (byte) (r4 >>> 8);
        r1[r19 + 14] = (byte) (r4 >>> 16);
        r1[r19 + 15] = (byte) (r4 >>> 24);
        engineReset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x018e, code lost:
        return 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002c, code lost:
        r3[58] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002e, code lost:
        r3[59] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r3[60] = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int engineDigest(byte[] r18, int r19, int r20) throws java.security.DigestException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 16
            r3 = r20
            if (r3 < r2) goto L_0x0197
            int r3 = r1.length
            int r3 = r3 - r19
            if (r3 < r2) goto L_0x018f
            byte[] r3 = r0.pad
            int r4 = r0.padded
            r5 = -128(0xffffffffffffff80, float:NaN)
            r3[r4] = r5
            r5 = 63
            r6 = 62
            r7 = 61
            r8 = 60
            r9 = 59
            r10 = 58
            r11 = 57
            r12 = 0
            switch(r4) {
                case 56: goto L_0x002a;
                case 57: goto L_0x002c;
                case 58: goto L_0x002e;
                case 59: goto L_0x0030;
                case 60: goto L_0x0032;
                case 61: goto L_0x0034;
                case 62: goto L_0x0036;
                case 63: goto L_0x0038;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x003c
        L_0x002a:
            r3[r11] = r12
        L_0x002c:
            r3[r10] = r12
        L_0x002e:
            r3[r9] = r12
        L_0x0030:
            r3[r8] = r12
        L_0x0032:
            r3[r7] = r12
        L_0x0034:
            r3[r6] = r12
        L_0x0036:
            r3[r5] = r12
        L_0x0038:
            r0.engineUpdate(r3, r12)
            r4 = -1
        L_0x003c:
            r13 = r4 & 7
            switch(r13) {
                case 0: goto L_0x00a7;
                case 1: goto L_0x008e;
                case 2: goto L_0x0079;
                case 3: goto L_0x0068;
                case 4: goto L_0x005b;
                case 5: goto L_0x0050;
                case 6: goto L_0x0047;
                case 7: goto L_0x0043;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x00c4
        L_0x0043:
            int r4 = r4 + -3
            goto L_0x00c4
        L_0x0047:
            int r13 = r4 + -2
            int r4 = r4 + 1
            r3[r4] = r12
        L_0x004d:
            r4 = r13
            goto L_0x00c4
        L_0x0050:
            int r13 = r4 + -1
            int r14 = r4 + 1
            r3[r14] = r12
            int r4 = r4 + 2
            r3[r4] = r12
            goto L_0x004d
        L_0x005b:
            int r13 = r4 + 1
            r3[r13] = r12
            int r13 = r4 + 2
            r3[r13] = r12
            int r13 = r4 + 3
            r3[r13] = r12
            goto L_0x00c4
        L_0x0068:
            int r13 = r4 + 1
            r3[r13] = r12
            int r14 = r4 + 2
            r3[r14] = r12
            int r14 = r4 + 3
            r3[r14] = r12
            int r4 = r4 + 4
            r3[r4] = r12
            goto L_0x004d
        L_0x0079:
            int r13 = r4 + 2
            int r14 = r4 + 1
            r3[r14] = r12
            r3[r13] = r12
            int r14 = r4 + 3
            r3[r14] = r12
            int r14 = r4 + 4
            r3[r14] = r12
            int r4 = r4 + 5
            r3[r4] = r12
            goto L_0x004d
        L_0x008e:
            int r13 = r4 + 3
            int r14 = r4 + 1
            r3[r14] = r12
            int r14 = r4 + 2
            r3[r14] = r12
            r3[r13] = r12
            int r14 = r4 + 4
            r3[r14] = r12
            int r14 = r4 + 5
            r3[r14] = r12
            int r4 = r4 + 6
            r3[r4] = r12
            goto L_0x004d
        L_0x00a7:
            int r13 = r4 + 4
            int r14 = r4 + 1
            r3[r14] = r12
            int r14 = r4 + 2
            r3[r14] = r12
            int r14 = r4 + 3
            r3[r14] = r12
            r3[r13] = r12
            int r14 = r4 + 5
            r3[r14] = r12
            int r14 = r4 + 6
            r3[r14] = r12
            int r4 = r4 + 7
            r3[r4] = r12
            goto L_0x004d
        L_0x00c4:
            int r13 = r4 + 8
            r14 = 52
            if (r13 > r14) goto L_0x00ea
            int r14 = r4 + 4
            r3[r14] = r12
            int r14 = r4 + 5
            r3[r14] = r12
            int r14 = r4 + 6
            r3[r14] = r12
            int r14 = r4 + 7
            r3[r14] = r12
            r3[r13] = r12
            int r14 = r4 + 9
            r3[r14] = r12
            int r14 = r4 + 10
            r3[r14] = r12
            int r4 = r4 + 11
            r3[r4] = r12
            goto L_0x004d
        L_0x00ea:
            long r13 = r0.bytes
            int r4 = (int) r13
            int r4 = r4 << 3
            byte r15 = (byte) r4
            r16 = 56
            r3[r16] = r15
            int r15 = r4 >>> 8
            byte r15 = (byte) r15
            r3[r11] = r15
            int r11 = r4 >>> 16
            byte r11 = (byte) r11
            r3[r10] = r11
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r3[r9] = r4
            r4 = 29
            long r9 = r13 >>> r4
            int r4 = (int) r9
            byte r9 = (byte) r4
            r3[r8] = r9
            int r8 = r4 >>> 8
            byte r8 = (byte) r8
            r3[r7] = r8
            int r7 = r4 >>> 16
            byte r7 = (byte) r7
            r3[r6] = r7
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r3[r5] = r4
            r0.engineUpdate(r3, r12)
            int r3 = r0.hA
            byte r4 = (byte) r3
            r1[r19] = r4
            int r4 = r19 + 1
            int r5 = r3 >>> 8
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r19 + 2
            int r5 = r3 >>> 16
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r19 + 3
            int r3 = r3 >>> 24
            byte r3 = (byte) r3
            r1[r4] = r3
            int r3 = r19 + 4
            int r4 = r0.hB
            byte r5 = (byte) r4
            r1[r3] = r5
            int r3 = r19 + 5
            int r5 = r4 >>> 8
            byte r5 = (byte) r5
            r1[r3] = r5
            int r3 = r19 + 10
            int r5 = r19 + 6
            int r6 = r4 >>> 16
            byte r6 = (byte) r6
            r1[r5] = r6
            int r5 = r19 + 7
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r1[r5] = r4
            int r4 = r19 + 8
            int r5 = r0.hC
            byte r6 = (byte) r5
            r1[r4] = r6
            int r4 = r19 + 9
            int r6 = r5 >>> 8
            byte r6 = (byte) r6
            r1[r4] = r6
            int r4 = r5 >>> 16
            byte r4 = (byte) r4
            r1[r3] = r4
            int r3 = r19 + 11
            int r4 = r5 >>> 24
            byte r4 = (byte) r4
            r1[r3] = r4
            int r3 = r19 + 12
            int r4 = r0.hD
            byte r5 = (byte) r4
            r1[r3] = r5
            int r3 = r19 + 13
            int r5 = r4 >>> 8
            byte r5 = (byte) r5
            r1[r3] = r5
            int r3 = r19 + 14
            int r5 = r4 >>> 16
            byte r5 = (byte) r5
            r1[r3] = r5
            int r3 = r19 + 15
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r1[r3] = r4
            r17.engineReset()
            return r2
        L_0x018f:
            java.security.DigestException r0 = new java.security.DigestException
            java.lang.String r1 = "insufficient space in output buffer to store the digest"
            r0.<init>(r1)
            throw r0
        L_0x0197:
            java.security.DigestException r0 = new java.security.DigestException
            java.lang.String r1 = "partial digests not returned"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.security.MD5.engineDigest(byte[], int, int):int");
    }

    public void engineUpdate(byte[] bArr, int i3, int i4) {
        if (i3 < 0 || i4 < 0 || i3 + i4 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
        this.bytes += (long) i4;
        int i5 = this.padded;
        if (i5 > 0 && i5 + i4 >= 64) {
            int i6 = 64 - i5;
            System.arraycopy(bArr, i3, this.pad, i5, i6);
            byte[] bArr2 = this.pad;
            this.padded = 0;
            engineUpdate(bArr2, 0);
            i3 += i6;
            i4 -= i6;
        }
        while (i4 >= 512) {
            engineUpdate(bArr, i3);
            engineUpdate(bArr, i3 + 64);
            engineUpdate(bArr, i3 + 128);
            engineUpdate(bArr, i3 + 192);
            engineUpdate(bArr, i3 + 256);
            engineUpdate(bArr, i3 + DilithiumEngine.DilithiumPolyT1PackedBytes);
            engineUpdate(bArr, i3 + KyberEngine.KyberPolyBytes);
            engineUpdate(bArr, i3 + 448);
            i3 += 512;
            i4 += WorkInfo.STOP_REASON_UNKNOWN;
        }
        while (i4 >= 64) {
            engineUpdate(bArr, i3);
            i3 += 64;
            i4 -= 64;
        }
        if (i4 > 0) {
            System.arraycopy(bArr, i3, this.pad, this.padded, i4);
            this.padded += i4;
        }
    }

    /* JADX WARNING: type inference failed for: r28v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v9, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r8v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v5, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r11v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r11v5, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r12v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v9, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r15v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v13, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v8, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r14v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v12, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v16, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v20, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r15v24, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v6, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r13v25, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v27, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v31, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r13v35, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v28, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v32, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v36, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r1v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r1v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r1v8, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r11v24, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v41, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v45, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v49, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r15v53, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v40, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v44, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r13v48, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v50, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v54, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r13v58, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v57, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v61, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v65, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r12v16, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v63, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v67, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v71, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r13v74, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v70, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v74, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v78, types: [int, byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void engineUpdate(byte[] r28, int r29) {
        /*
            r27 = this;
            r0 = r27
            int r1 = r0.hB
            int r2 = r0.hC
            int r3 = r0.hD
            r4 = r2 ^ r3
            r4 = r4 & r1
            r4 = r4 ^ r3
            byte r5 = r28[r29]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r29 + 1
            byte r6 = r28[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r5 = r5 | r6
            int r6 = r29 + 2
            byte r6 = r28[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 16
            r5 = r5 | r6
            int r6 = r29 + 3
            byte r6 = r28[r6]
            int r6 = r6 << 24
            r5 = r5 | r6
            int r4 = r4 + r5
            r6 = -680876936(0xffffffffd76aa478, float:-2.57992109E14)
            int r4 = r4 + r6
            int r6 = r0.hA
            int r4 = r4 + r6
            int r7 = r4 << 7
            int r4 = r4 >>> 25
            r4 = r4 | r7
            int r4 = r4 + r1
            r7 = r1 ^ r2
            r7 = r7 & r4
            r7 = r7 ^ r2
            int r8 = r29 + 4
            byte r8 = r28[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r9 = r29 + 5
            byte r9 = r28[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << 8
            r8 = r8 | r9
            int r9 = r29 + 10
            int r10 = r29 + 6
            byte r10 = r28[r10]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r10 = r10 << 16
            r8 = r8 | r10
            int r10 = r29 + 7
            byte r10 = r28[r10]
            int r10 = r10 << 24
            r8 = r8 | r10
            r10 = -389564586(0xffffffffe8c7b756, float:-7.545063E24)
            int r7 = android.support.v4.media.session.a.D(r7, r8, r10, r3)
            int r10 = r7 << 12
            int r7 = r7 >>> 20
            r7 = r7 | r10
            int r7 = r7 + r4
            r10 = r4 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r1
            int r11 = r29 + 8
            byte r11 = r28[r11]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r12 = r29 + 9
            byte r12 = r28[r12]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << 8
            r11 = r11 | r12
            byte r9 = r28[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << 16
            r9 = r9 | r11
            int r11 = r29 + 11
            byte r11 = r28[r11]
            int r11 = r11 << 24
            r9 = r9 | r11
            r11 = 606105819(0x242070db, float:3.4790062E-17)
            int r10 = android.support.v4.media.session.a.D(r10, r9, r11, r2)
            int r11 = r10 << 17
            int r10 = r10 >>> 15
            r10 = r10 | r11
            int r10 = r10 + r7
            r11 = r7 ^ r4
            r11 = r11 & r10
            r11 = r11 ^ r4
            int r12 = r29 + 12
            byte r12 = r28[r12]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r13 = r29 + 13
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 8
            r12 = r12 | r13
            int r13 = r29 + 14
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 16
            r12 = r12 | r13
            int r13 = r29 + 15
            byte r13 = r28[r13]
            int r13 = r13 << 24
            r12 = r12 | r13
            r13 = -1044525330(0xffffffffc1bdceee, float:-23.72604)
            int r11 = android.support.v4.media.session.a.D(r11, r12, r13, r1)
            int r13 = r11 << 22
            int r11 = r11 >>> 10
            r11 = r11 | r13
            int r11 = r11 + r10
            r13 = r10 ^ r7
            r13 = r13 & r11
            r13 = r13 ^ r7
            int r14 = r29 + 20
            int r15 = r29 + 16
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 17
            r17 = r6
            byte r6 = r28[r16]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r6 = r6 | r15
            int r15 = r29 + 18
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r6 = r6 | r15
            int r15 = r29 + 19
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r6 = r6 | r15
            r15 = -176418897(0xfffffffff57c0faf, float:-3.1952561E32)
            int r4 = android.support.v4.media.session.a.D(r13, r6, r15, r4)
            int r13 = r4 << 7
            int r4 = r4 >>> 25
            r4 = r4 | r13
            int r4 = r4 + r11
            r13 = r11 ^ r10
            r13 = r13 & r4
            r13 = r13 ^ r10
            byte r14 = r28[r14]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r15 = r29 + 21
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 8
            r14 = r14 | r15
            int r15 = r29 + 22
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r14 = r14 | r15
            int r15 = r29 + 23
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r14 = r14 | r15
            r15 = 1200080426(0x4787c62a, float:69516.33)
            int r7 = android.support.v4.media.session.a.D(r13, r14, r15, r7)
            int r13 = r7 << 12
            int r7 = r7 >>> 20
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r11
            r13 = r13 & r7
            r13 = r13 ^ r11
            int r15 = r29 + 24
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 25
            r18 = r3
            byte r3 = r28[r16]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            r3 = r3 | r15
            int r15 = r29 + 30
            int r16 = r29 + 26
            r19 = r2
            byte r2 = r28[r16]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 16
            r2 = r2 | r3
            int r3 = r29 + 27
            byte r3 = r28[r3]
            int r3 = r3 << 24
            r2 = r2 | r3
            r3 = -1473231341(0xffffffffa8304613, float:-9.7851575E-15)
            int r3 = android.support.v4.media.session.a.D(r13, r2, r3, r10)
            int r10 = r3 << 17
            int r3 = r3 >>> 15
            r3 = r3 | r10
            int r3 = r3 + r7
            r10 = r7 ^ r4
            r10 = r10 & r3
            r10 = r10 ^ r4
            int r13 = r29 + 28
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 29
            byte r0 = r28[r16]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << 8
            r0 = r0 | r13
            byte r13 = r28[r15]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 16
            r0 = r0 | r13
            int r13 = r29 + 31
            byte r13 = r28[r13]
            int r13 = r13 << 24
            r0 = r0 | r13
            r13 = -45705983(0xfffffffffd469501, float:-1.6497551E37)
            int r10 = android.support.v4.media.session.a.D(r10, r0, r13, r11)
            int r11 = r10 << 22
            int r10 = r10 >>> 10
            r10 = r10 | r11
            int r10 = r10 + r3
            r11 = r3 ^ r7
            r11 = r11 & r10
            r11 = r11 ^ r7
            int r13 = r29 + 32
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r15 = r29 + 33
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 8
            r13 = r13 | r15
            int r15 = r29 + 34
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r15 = r29 + 35
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r13 = r13 | r15
            r15 = 1770035416(0x698098d8, float:1.9433036E25)
            int r4 = android.support.v4.media.session.a.D(r11, r13, r15, r4)
            int r11 = r4 << 7
            int r4 = r4 >>> 25
            r4 = r4 | r11
            int r4 = r4 + r10
            r11 = r10 ^ r3
            r11 = r11 & r4
            r11 = r11 ^ r3
            int r15 = r29 + 40
            int r16 = r29 + 36
            r20 = r1
            byte r1 = r28[r16]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 37
            r21 = r0
            byte r0 = r28[r16]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << 8
            r0 = r0 | r1
            int r1 = r29 + 38
            byte r1 = r28[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 16
            r0 = r0 | r1
            int r1 = r29 + 39
            byte r1 = r28[r1]
            int r1 = r1 << 24
            r0 = r0 | r1
            r1 = -1958414417(0xffffffff8b44f7af, float:-3.7934563E-32)
            int r1 = android.support.v4.media.session.a.D(r11, r0, r1, r7)
            int r7 = r1 << 12
            int r1 = r1 >>> 20
            r1 = r1 | r7
            int r1 = r1 + r4
            r7 = r4 ^ r10
            r7 = r7 & r1
            r7 = r7 ^ r10
            byte r11 = r28[r15]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r15 = r29 + 41
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 8
            r11 = r11 | r15
            int r15 = r29 + 42
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r11 = r11 | r15
            int r15 = r29 + 43
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r11 = r11 | r15
            r15 = -42063(0xffffffffffff5bb1, float:NaN)
            int r3 = android.support.v4.media.session.a.D(r7, r11, r15, r3)
            int r7 = r3 << 17
            int r3 = r3 >>> 15
            r3 = r3 | r7
            int r3 = r3 + r1
            r7 = r1 ^ r4
            r7 = r7 & r3
            r7 = r7 ^ r4
            int r15 = r29 + 44
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 45
            r22 = r9
            byte r9 = r28[r16]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << 8
            r9 = r9 | r15
            int r15 = r29 + 50
            int r16 = r29 + 46
            r23 = r13
            byte r13 = r28[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 16
            r9 = r9 | r13
            int r13 = r29 + 47
            byte r13 = r28[r13]
            int r13 = r13 << 24
            r9 = r9 | r13
            r13 = -1990404162(0xffffffff895cd7be, float:-2.6582978E-33)
            int r7 = android.support.v4.media.session.a.D(r7, r9, r13, r10)
            int r10 = r7 << 22
            int r7 = r7 >>> 10
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r1
            int r13 = r29 + 48
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 49
            r24 = r12
            byte r12 = r28[r16]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << 8
            r12 = r12 | r13
            byte r13 = r28[r15]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 16
            r12 = r12 | r13
            int r13 = r29 + 51
            byte r13 = r28[r13]
            int r13 = r13 << 24
            r12 = r12 | r13
            r13 = 1804603682(0x6b901122, float:3.4833245E26)
            int r4 = android.support.v4.media.session.a.D(r10, r12, r13, r4)
            int r10 = r4 << 7
            int r4 = r4 >>> 25
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r3
            r10 = r10 & r4
            r10 = r10 ^ r3
            int r13 = r29 + 52
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r15 = r29 + 53
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 8
            r13 = r13 | r15
            int r15 = r29 + 54
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r15 = r29 + 55
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r13 = r13 | r15
            r15 = -40341101(0xfffffffffd987193, float:-2.5329046E37)
            int r1 = android.support.v4.media.session.a.D(r10, r13, r15, r1)
            int r10 = r1 << 12
            int r1 = r1 >>> 20
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r7
            r10 = r10 & r1
            r10 = r10 ^ r7
            int r15 = r29 + 60
            int r16 = r29 + 56
            r25 = r12
            byte r12 = r28[r16]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r16 = r29 + 57
            r26 = r13
            byte r13 = r28[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 8
            r12 = r12 | r13
            int r13 = r29 + 58
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 16
            r12 = r12 | r13
            int r13 = r29 + 59
            byte r13 = r28[r13]
            int r13 = r13 << 24
            r12 = r12 | r13
            r13 = -1502002290(0xffffffffa679438e, float:-8.6480783E-16)
            int r3 = android.support.v4.media.session.a.D(r10, r12, r13, r3)
            int r10 = r3 << 17
            int r3 = r3 >>> 15
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r4
            r10 = r10 & r3
            r10 = r10 ^ r4
            byte r13 = r28[r15]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r15 = r29 + 61
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 8
            r13 = r13 | r15
            int r15 = r29 + 62
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r15 = r29 + 63
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r13 = r13 | r15
            r15 = 1236535329(0x49b40821, float:1474820.1)
            int r7 = android.support.v4.media.session.a.D(r10, r13, r15, r7)
            int r10 = r7 << 22
            int r7 = r7 >>> 10
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 & r1
            r10 = r10 ^ r3
            r15 = -165796510(0xfffffffff61e2562, float:-8.018956E32)
            int r4 = android.support.v4.media.session.a.D(r10, r8, r15, r4)
            int r10 = r4 << 5
            int r4 = r4 >>> 27
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 & r3
            r10 = r10 ^ r7
            r15 = -1069501632(0xffffffffc040b340, float:-3.0109406)
            int r1 = android.support.v4.media.session.a.D(r10, r2, r15, r1)
            int r10 = r1 << 9
            int r1 = r1 >>> 23
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r4
            r15 = 643717713(0x265e5a51, float:7.7144124E-16)
            int r3 = android.support.v4.media.session.a.D(r10, r9, r15, r3)
            int r10 = r3 << 14
            int r3 = r3 >>> 18
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 & r4
            r10 = r10 ^ r1
            r15 = -373897302(0xffffffffe9b6c7aa, float:-2.7620923E25)
            int r7 = android.support.v4.media.session.a.D(r10, r5, r15, r7)
            int r10 = r7 << 20
            int r7 = r7 >>> 12
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 & r1
            r10 = r10 ^ r3
            r15 = -701558691(0xffffffffd62f105d, float:-4.8121204E13)
            int r4 = android.support.v4.media.session.a.D(r10, r14, r15, r4)
            int r10 = r4 << 5
            int r4 = r4 >>> 27
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 & r3
            r10 = r10 ^ r7
            r15 = 38016083(0x2441453, float:1.4405639E-37)
            int r1 = android.support.v4.media.session.a.D(r10, r11, r15, r1)
            int r10 = r1 << 9
            int r1 = r1 >>> 23
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r4
            r15 = -660478335(0xffffffffd8a1e681, float:-1.42409103E15)
            int r3 = android.support.v4.media.session.a.D(r10, r13, r15, r3)
            int r10 = r3 << 14
            int r3 = r3 >>> 18
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 & r4
            r10 = r10 ^ r1
            r15 = -405537848(0xffffffffe7d3fbc8, float:-2.0021277E24)
            int r7 = android.support.v4.media.session.a.D(r10, r6, r15, r7)
            int r10 = r7 << 20
            int r7 = r7 >>> 12
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 & r1
            r10 = r10 ^ r3
            r15 = 568446438(0x21e1cde6, float:1.5301094E-18)
            int r4 = android.support.v4.media.session.a.D(r10, r0, r15, r4)
            int r10 = r4 << 5
            int r4 = r4 >>> 27
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 & r3
            r10 = r10 ^ r7
            r15 = -1019803690(0xffffffffc33707d6, float:-183.03061)
            int r1 = android.support.v4.media.session.a.D(r10, r12, r15, r1)
            int r10 = r1 << 9
            int r1 = r1 >>> 23
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r4
            r15 = -187363961(0xfffffffff4d50d87, float:-1.3503828E32)
            r28 = r13
            r13 = r24
            int r3 = android.support.v4.media.session.a.D(r10, r13, r15, r3)
            int r10 = r3 << 14
            int r3 = r3 >>> 18
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 & r4
            r10 = r10 ^ r1
            r15 = 1163531501(0x455a14ed, float:3489.3079)
            r16 = r0
            r0 = r23
            int r7 = android.support.v4.media.session.a.D(r10, r0, r15, r7)
            int r10 = r7 << 20
            int r7 = r7 >>> 12
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 & r1
            r10 = r10 ^ r3
            r15 = -1444681467(0xffffffffa9e3e905, float:-1.01212475E-13)
            r23 = r2
            r2 = r26
            int r4 = android.support.v4.media.session.a.D(r10, r2, r15, r4)
            int r10 = r4 << 5
            int r4 = r4 >>> 27
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 & r3
            r10 = r10 ^ r7
            r15 = -51403784(0xfffffffffcefa3f8, float:-9.954277E36)
            r13 = r22
            int r1 = android.support.v4.media.session.a.D(r10, r13, r15, r1)
            int r10 = r1 << 9
            int r1 = r1 >>> 23
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r4
            r15 = 1735328473(0x676f02d9, float:1.1286981E24)
            r13 = r21
            int r3 = android.support.v4.media.session.a.D(r10, r13, r15, r3)
            int r10 = r3 << 14
            int r3 = r3 >>> 18
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 & r4
            r10 = r10 ^ r1
            r15 = -1926607734(0xffffffff8d2a4c8a, float:-5.2477425E-31)
            r21 = r5
            r5 = r25
            int r7 = android.support.v4.media.session.a.D(r10, r5, r15, r7)
            int r10 = r7 << 20
            int r7 = r7 >>> 12
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 ^ r1
            r15 = -378558(0xfffffffffffa3942, float:NaN)
            int r4 = android.support.v4.media.session.a.D(r10, r14, r15, r4)
            int r10 = r4 << 4
            int r4 = r4 >>> 28
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 ^ r3
            r15 = -2022574463(0xffffffff8771f681, float:-1.8203266E-34)
            int r1 = android.support.v4.media.session.a.D(r10, r0, r15, r1)
            int r10 = r1 << 11
            int r1 = r1 >>> 21
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 ^ r7
            r15 = 1839030562(0x6d9d6122, float:6.0883216E27)
            int r3 = android.support.v4.media.session.a.D(r10, r9, r15, r3)
            int r10 = r3 << 16
            int r3 = r3 >>> 16
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 ^ r4
            r15 = -35309556(0xfffffffffde5380c, float:-3.8085528E37)
            int r7 = android.support.v4.media.session.a.D(r10, r12, r15, r7)
            int r10 = r7 << 23
            int r7 = r7 >>> 9
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 ^ r1
            r15 = -1530992060(0xffffffffa4beea44, float:-8.2796227E-17)
            int r4 = android.support.v4.media.session.a.D(r10, r8, r15, r4)
            int r10 = r4 << 4
            int r4 = r4 >>> 28
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 ^ r3
            r15 = 1272893353(0x4bdecfa9, float:2.9204306E7)
            int r1 = android.support.v4.media.session.a.D(r10, r6, r15, r1)
            int r10 = r1 << 11
            int r1 = r1 >>> 21
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 ^ r7
            r15 = -155497632(0xfffffffff6bb4b60, float:-1.8993912E33)
            int r3 = android.support.v4.media.session.a.D(r10, r13, r15, r3)
            int r10 = r3 << 16
            int r3 = r3 >>> 16
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 ^ r4
            r15 = -1094730640(0xffffffffbebfbc70, float:-0.37448454)
            int r7 = android.support.v4.media.session.a.D(r10, r11, r15, r7)
            int r10 = r7 << 23
            int r7 = r7 >>> 9
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 ^ r1
            r15 = 681279174(0x289b7ec6, float:1.7263436E-14)
            int r4 = android.support.v4.media.session.a.D(r10, r2, r15, r4)
            int r10 = r4 << 4
            int r4 = r4 >>> 28
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 ^ r3
            r15 = -358537222(0xffffffffeaa127fa, float:-9.741292E25)
            r25 = r9
            r9 = r21
            int r1 = android.support.v4.media.session.a.D(r10, r9, r15, r1)
            int r10 = r1 << 11
            int r1 = r1 >>> 21
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 ^ r7
            r15 = -722521979(0xffffffffd4ef3085, float:-8.2184897E12)
            r21 = r6
            r6 = r24
            int r3 = android.support.v4.media.session.a.D(r10, r6, r15, r3)
            int r10 = r3 << 16
            int r3 = r3 >>> 16
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 ^ r4
            r15 = 76029189(0x4881d05, float:3.2000097E-36)
            r2 = r23
            int r7 = android.support.v4.media.session.a.D(r10, r2, r15, r7)
            int r10 = r7 << 23
            int r7 = r7 >>> 9
            r7 = r7 | r10
            int r7 = r7 + r3
            r10 = r3 ^ r7
            r10 = r10 ^ r1
            r15 = -640364487(0xffffffffd9d4d039, float:-7.4877048E15)
            r2 = r16
            int r4 = android.support.v4.media.session.a.D(r10, r2, r15, r4)
            int r10 = r4 << 4
            int r4 = r4 >>> 28
            r4 = r4 | r10
            int r4 = r4 + r7
            r10 = r7 ^ r4
            r10 = r10 ^ r3
            r15 = -421815835(0xffffffffe6db99e5, float:-5.1851856E23)
            int r1 = android.support.v4.media.session.a.D(r10, r5, r15, r1)
            int r10 = r1 << 11
            int r1 = r1 >>> 21
            r1 = r1 | r10
            int r1 = r1 + r4
            r10 = r4 ^ r1
            r10 = r10 ^ r7
            r15 = 530742520(0x1fa27cf8, float:6.881641E-20)
            r2 = r28
            int r3 = android.support.v4.media.session.a.D(r10, r2, r15, r3)
            int r10 = r3 << 16
            int r3 = r3 >>> 16
            r3 = r3 | r10
            int r3 = r3 + r1
            r10 = r1 ^ r3
            r10 = r10 ^ r4
            r15 = -995338651(0xffffffffc4ac5665, float:-1378.6998)
            r2 = r22
            int r7 = android.support.v4.media.session.a.D(r10, r2, r15, r7)
            int r10 = r7 << 23
            int r7 = r7 >>> 9
            r7 = r7 | r10
            int r7 = r7 + r3
            int r10 = ~r1
            r10 = r10 | r7
            r10 = r10 ^ r3
            r15 = -198630844(0xfffffffff4292244, float:-5.3600657E31)
            int r4 = android.support.v4.media.session.a.D(r10, r9, r15, r4)
            int r9 = r4 << 6
            int r4 = r4 >>> 26
            r4 = r4 | r9
            int r4 = r4 + r7
            int r9 = ~r3
            r9 = r9 | r4
            r9 = r9 ^ r7
            r10 = 1126891415(0x432aff97, float:170.9984)
            int r1 = android.support.v4.media.session.a.D(r9, r13, r10, r1)
            int r9 = r1 << 10
            int r1 = r1 >>> 22
            r1 = r1 | r9
            int r1 = r1 + r4
            int r9 = ~r7
            r9 = r9 | r1
            r9 = r9 ^ r4
            r10 = -1416354905(0xffffffffab9423a7, float:-1.0525928E-12)
            int r3 = android.support.v4.media.session.a.D(r9, r12, r10, r3)
            int r9 = r3 << 15
            int r3 = r3 >>> 17
            r3 = r3 | r9
            int r3 = r3 + r1
            int r9 = ~r4
            r9 = r9 | r3
            r9 = r9 ^ r1
            r10 = -57434055(0xfffffffffc93a039, float:-6.132139E36)
            int r7 = android.support.v4.media.session.a.D(r9, r14, r10, r7)
            int r9 = r7 << 21
            int r7 = r7 >>> 11
            r7 = r7 | r9
            int r7 = r7 + r3
            int r9 = ~r1
            r9 = r9 | r7
            r9 = r9 ^ r3
            r10 = 1700485571(0x655b59c3, float:6.474088E22)
            int r4 = android.support.v4.media.session.a.D(r9, r5, r10, r4)
            int r5 = r4 << 6
            int r4 = r4 >>> 26
            r4 = r4 | r5
            int r4 = r4 + r7
            int r5 = ~r3
            r5 = r5 | r4
            r5 = r5 ^ r7
            r9 = -1894986606(0xffffffff8f0ccc92, float:-6.941932E-30)
            int r1 = android.support.v4.media.session.a.D(r5, r6, r9, r1)
            int r5 = r1 << 10
            int r1 = r1 >>> 22
            r1 = r1 | r5
            int r1 = r1 + r4
            int r5 = ~r7
            r5 = r5 | r1
            r5 = r5 ^ r4
            r6 = -1051523(0xffffffffffeff47d, float:NaN)
            int r3 = android.support.v4.media.session.a.D(r5, r11, r6, r3)
            int r5 = r3 << 15
            int r3 = r3 >>> 17
            r3 = r3 | r5
            int r3 = r3 + r1
            int r5 = ~r4
            r5 = r5 | r3
            r5 = r5 ^ r1
            r6 = -2054922799(0xffffffff85845dd1, float:-1.2447683E-35)
            int r5 = android.support.v4.media.session.a.D(r5, r8, r6, r7)
            int r6 = r5 << 21
            int r5 = r5 >>> 11
            r5 = r5 | r6
            int r5 = r5 + r3
            int r6 = ~r1
            r6 = r6 | r5
            r6 = r6 ^ r3
            r7 = 1873313359(0x6fa87e4f, float:1.0429236E29)
            int r0 = android.support.v4.media.session.a.D(r6, r0, r7, r4)
            int r4 = r0 << 6
            int r0 = r0 >>> 26
            r0 = r0 | r4
            int r0 = r0 + r5
            int r4 = ~r3
            r4 = r4 | r0
            r4 = r4 ^ r5
            r6 = -30611744(0xfffffffffe2ce6e0, float:-5.7456497E37)
            r7 = r28
            int r1 = android.support.v4.media.session.a.D(r4, r7, r6, r1)
            int r4 = r1 << 10
            int r1 = r1 >>> 22
            r1 = r1 | r4
            int r1 = r1 + r0
            int r4 = ~r5
            r4 = r4 | r1
            r4 = r4 ^ r0
            r6 = -1560198380(0xffffffffa3014314, float:-7.007308E-18)
            r7 = r23
            int r3 = android.support.v4.media.session.a.D(r4, r7, r6, r3)
            int r4 = r3 << 15
            int r3 = r3 >>> 17
            r3 = r3 | r4
            int r3 = r3 + r1
            int r4 = ~r0
            r4 = r4 | r3
            r4 = r4 ^ r1
            r6 = 1309151649(0x4e0811a1, float:5.7071418E8)
            r7 = r26
            int r4 = android.support.v4.media.session.a.D(r4, r7, r6, r5)
            int r5 = r4 << 21
            int r4 = r4 >>> 11
            r4 = r4 | r5
            int r4 = r4 + r3
            int r5 = ~r1
            r5 = r5 | r4
            r5 = r5 ^ r3
            r6 = -145523070(0xfffffffff7537e82, float:-4.2896114E33)
            r7 = r21
            int r0 = android.support.v4.media.session.a.D(r5, r7, r6, r0)
            int r5 = r0 << 6
            int r0 = r0 >>> 26
            r0 = r0 | r5
            int r0 = r0 + r4
            int r5 = ~r3
            r5 = r5 | r0
            r5 = r5 ^ r4
            r6 = -1120210379(0xffffffffbd3af235, float:-0.045641143)
            r7 = r25
            int r1 = android.support.v4.media.session.a.D(r5, r7, r6, r1)
            int r5 = r1 << 10
            int r1 = r1 >>> 22
            r1 = r1 | r5
            int r1 = r1 + r0
            int r5 = ~r4
            r5 = r5 | r1
            r5 = r5 ^ r0
            r6 = 718787259(0x2ad7d2bb, float:3.8337896E-13)
            int r2 = android.support.v4.media.session.a.D(r5, r2, r6, r3)
            int r3 = r2 << 15
            int r2 = r2 >>> 17
            r2 = r2 | r3
            int r2 = r2 + r1
            int r3 = r20 + r2
            int r5 = ~r0
            r5 = r5 | r2
            r5 = r5 ^ r1
            r6 = -343485551(0xffffffffeb86d391, float:-3.259903E26)
            r7 = r16
            int r4 = android.support.v4.media.session.a.D(r5, r7, r6, r4)
            int r5 = r4 << 21
            int r4 = r4 >>> 11
            r4 = r4 | r5
            int r3 = r3 + r4
            r4 = r27
            r4.hB = r3
            int r2 = r19 + r2
            r4.hC = r2
            int r3 = r18 + r1
            r4.hD = r3
            int r6 = r17 + r0
            r4.hA = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.security.MD5.engineUpdate(byte[], int):void");
    }
}
