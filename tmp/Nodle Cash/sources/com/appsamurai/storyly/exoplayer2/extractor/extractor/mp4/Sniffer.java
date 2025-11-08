package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;

final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    private Sniffer() {
    }

    private static boolean isCompatibleBrand(int i3, boolean z2) {
        if ((i3 >>> 8) == 3368816) {
            return true;
        }
        if (i3 == 1751476579 && z2) {
            return true;
        }
        for (int i4 : COMPATIBLE_BRANDS) {
            if (i4 == i3) {
                return true;
            }
        }
        return false;
    }

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f0, code lost:
        r0 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean sniffInternal(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r21, boolean r22, boolean r23) throws java.io.IOException {
        /*
            r0 = r21
            long r1 = r21.getLength()
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r6 = 4096(0x1000, double:2.0237E-320)
            if (r5 == 0) goto L_0x0014
            int r8 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0013
            goto L_0x0014
        L_0x0013:
            r6 = r1
        L_0x0014:
            int r6 = (int) r6
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r7 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
            r8 = 64
            r7.<init>((int) r8)
            r8 = 0
            r9 = r8
            r10 = r9
        L_0x001f:
            r11 = 1
            if (r9 >= r6) goto L_0x0031
            r12 = 8
            r7.reset((int) r12)
            byte[] r13 = r7.getData()
            boolean r13 = r0.peekFully(r13, r8, r12, r11)
            if (r13 != 0) goto L_0x0035
        L_0x0031:
            r5 = r8
            r8 = r11
            goto L_0x00f2
        L_0x0035:
            long r13 = r7.readUnsignedInt()
            int r15 = r7.readInt()
            r16 = 1
            int r16 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r16 != 0) goto L_0x0057
            byte[] r13 = r7.getData()
            r0.peekFully(r13, r12, r12)
            r13 = 16
            r7.setLimit(r13)
            long r16 = r7.readLong()
            r3 = r13
            r13 = r16
            goto L_0x006f
        L_0x0057:
            r16 = 0
            int r16 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r16 != 0) goto L_0x006e
            long r16 = r21.getLength()
            int r18 = (r16 > r3 ? 1 : (r16 == r3 ? 0 : -1))
            if (r18 == 0) goto L_0x006e
            long r13 = r21.getPeekPosition()
            long r16 = r16 - r13
            long r13 = (long) r12
            long r13 = r16 + r13
        L_0x006e:
            r3 = r12
        L_0x006f:
            long r11 = (long) r3
            int r19 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r19 >= 0) goto L_0x0075
            return r8
        L_0x0075:
            int r9 = r9 + r3
            r3 = 1836019574(0x6d6f6f76, float:4.631354E27)
            if (r15 != r3) goto L_0x0088
            int r3 = (int) r13
            int r6 = r6 + r3
            if (r5 == 0) goto L_0x0085
            long r3 = (long) r6
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x0085
            int r6 = (int) r1
        L_0x0085:
            r3 = -1
            goto L_0x001f
        L_0x0088:
            r3 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            if (r15 == r3) goto L_0x0092
            r3 = 1836475768(0x6d766578, float:4.7659988E27)
            if (r15 != r3) goto L_0x0095
        L_0x0092:
            r5 = r8
            r8 = 1
            goto L_0x00f0
        L_0x0095:
            r3 = r5
            long r4 = (long) r9
            long r4 = r4 + r13
            long r4 = r4 - r11
            r20 = r9
            long r8 = (long) r6
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x00a3
            r5 = 0
            r8 = 1
            goto L_0x00f2
        L_0x00a3:
            long r13 = r13 - r11
            int r4 = (int) r13
            int r9 = r20 + r4
            r5 = 1718909296(0x66747970, float:2.8862439E23)
            if (r15 != r5) goto L_0x00e5
            r5 = 8
            if (r4 >= r5) goto L_0x00b2
            r5 = 0
            return r5
        L_0x00b2:
            r5 = 0
            r7.reset((int) r4)
            byte[] r8 = r7.getData()
            r0.peekFully(r8, r5, r4)
            int r4 = r4 / 4
            r5 = 0
        L_0x00c0:
            if (r5 >= r4) goto L_0x00dd
            r8 = 1
            if (r5 != r8) goto L_0x00cc
            r11 = 4
            r7.skipBytes(r11)
            r12 = r23
            goto L_0x00da
        L_0x00cc:
            int r11 = r7.readInt()
            r12 = r23
            boolean r11 = isCompatibleBrand(r11, r12)
            if (r11 == 0) goto L_0x00da
            r10 = r8
            goto L_0x00df
        L_0x00da:
            int r5 = r5 + 1
            goto L_0x00c0
        L_0x00dd:
            r12 = r23
        L_0x00df:
            if (r10 != 0) goto L_0x00e3
            r5 = 0
            return r5
        L_0x00e3:
            r5 = 0
            goto L_0x00ed
        L_0x00e5:
            r12 = r23
            r5 = 0
            if (r4 == 0) goto L_0x00ed
            r0.advancePeekPosition(r4)
        L_0x00ed:
            r8 = r5
            r5 = r3
            goto L_0x0085
        L_0x00f0:
            r0 = r8
            goto L_0x00f3
        L_0x00f2:
            r0 = r5
        L_0x00f3:
            if (r10 == 0) goto L_0x00fa
            r1 = r22
            if (r1 != r0) goto L_0x00fa
            goto L_0x00fb
        L_0x00fa:
            r8 = r5
        L_0x00fb:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Sniffer.sniffInternal(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput, boolean, boolean):boolean");
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, false, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput, boolean z2) throws IOException {
        return sniffInternal(extractorInput, false, z2);
    }
}
