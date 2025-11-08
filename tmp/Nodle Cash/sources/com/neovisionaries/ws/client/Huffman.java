package com.neovisionaries.ws.client;

class Huffman {
    private final int mMaxCodeLen;
    private final int[] mMaxCodeValsFromCodeLen;
    private final int mMinCodeLen;
    private final int[] mSymsFromCodeVal;

    public Huffman(int[] iArr) {
        this.mMinCodeLen = Math.max(Misc.min(iArr), 1);
        int max = Misc.max(iArr);
        this.mMaxCodeLen = max;
        Object[] objArr = new Object[2];
        this.mMaxCodeValsFromCodeLen = createMaxCodeValsFromCodeLen(createCountsFromCodeLen(iArr, max), max, objArr);
        this.mSymsFromCodeVal = createSymsFromCodeVal(iArr, (int[]) objArr[0], ((Integer) objArr[1]).intValue());
    }

    private static int[] createCountsFromCodeLen(int[] iArr, int i3) {
        int[] iArr2 = new int[(i3 + 1)];
        for (int i4 : iArr) {
            iArr2[i4] = iArr2[i4] + 1;
        }
        return iArr2;
    }

    private static int[] createIntArray(int i3, int i4) {
        int[] iArr = new int[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            iArr[i5] = i4;
        }
        return iArr;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int[] createMaxCodeValsFromCodeLen(int[] r7, int r8, java.lang.Object[] r9) {
        /*
            r0 = 1
            int r8 = r8 + r0
            r1 = -1
            int[] r1 = createIntArray(r8, r1)
            r2 = 0
            r7[r2] = r2
            int[] r8 = new int[r8]
            r3 = r0
            r4 = r2
            r5 = r4
        L_0x000f:
            int r6 = r7.length
            if (r3 >= r6) goto L_0x0023
            int r4 = r3 + -1
            r4 = r7[r4]
            int r5 = r5 + r4
            int r5 = r5 << r0
            r8[r3] = r5
            r4 = r7[r3]
            int r4 = r4 + r5
            int r4 = r4 - r0
            r1[r3] = r4
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0023:
            r9[r2] = r8
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r9[r0] = r7
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.Huffman.createMaxCodeValsFromCodeLen(int[], int, java.lang.Object[]):int[]");
    }

    private static int[] createSymsFromCodeVal(int[] iArr, int[] iArr2, int i3) {
        int[] iArr3 = new int[(i3 + 1)];
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (i5 != 0) {
                int i6 = iArr2[i5];
                iArr2[i5] = i6 + 1;
                iArr3[i6] = i4;
            }
        }
        return iArr3;
    }

    public int readSym(ByteArray byteArray, int[] iArr) throws FormatException {
        int huffmanBits;
        for (int i3 = this.mMinCodeLen; i3 <= this.mMaxCodeLen; i3++) {
            int i4 = this.mMaxCodeValsFromCodeLen[i3];
            if (i4 >= 0 && i4 >= (huffmanBits = byteArray.getHuffmanBits(iArr[0], i3))) {
                int i5 = this.mSymsFromCodeVal[huffmanBits];
                iArr[0] = iArr[0] + i3;
                return i5;
            }
        }
        throw new FormatException(String.format("[%s] Bad code at the bit index '%d'.", new Object[]{getClass().getSimpleName(), Integer.valueOf(iArr[0])}));
    }
}
