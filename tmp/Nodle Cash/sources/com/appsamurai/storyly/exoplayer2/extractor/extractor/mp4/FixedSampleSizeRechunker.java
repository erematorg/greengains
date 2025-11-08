package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import com.appsamurai.storyly.exoplayer2.common.util.Util;

final class FixedSampleSizeRechunker {
    private static final int MAX_SAMPLE_SIZE = 8192;

    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;

        private Results(long[] jArr, int[] iArr, int i3, long[] jArr2, int[] iArr2, long j2) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i3;
            this.timestamps = jArr2;
            this.flags = iArr2;
            this.duration = j2;
        }
    }

    private FixedSampleSizeRechunker() {
    }

    public static Results rechunk(int i3, long[] jArr, int[] iArr, long j2) {
        int[] iArr2 = iArr;
        int i4 = 8192 / i3;
        int i5 = 0;
        for (int ceilDivide : iArr2) {
            i5 += Util.ceilDivide(ceilDivide, i4);
        }
        long[] jArr2 = new long[i5];
        int[] iArr3 = new int[i5];
        long[] jArr3 = new long[i5];
        int[] iArr4 = new int[i5];
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < iArr2.length; i9++) {
            int i10 = iArr2[i9];
            long j3 = jArr[i9];
            while (i10 > 0) {
                int min = Math.min(i4, i10);
                jArr2[i7] = j3;
                int i11 = i3 * min;
                iArr3[i7] = i11;
                i8 = Math.max(i8, i11);
                jArr3[i7] = ((long) i6) * j2;
                iArr4[i7] = 1;
                j3 += (long) iArr3[i7];
                i6 += min;
                i10 -= min;
                i7++;
            }
        }
        return new Results(jArr2, iArr3, i8, jArr3, iArr4, j2 * ((long) i6));
    }
}
