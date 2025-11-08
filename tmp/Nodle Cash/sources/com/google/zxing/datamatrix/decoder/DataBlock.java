package com.google.zxing.datamatrix.decoder;

import com.google.zxing.datamatrix.decoder.Version;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i3, byte[] bArr) {
        this.numDataCodewords = i3;
        this.codewords = bArr;
    }

    public static DataBlock[] getDataBlocks(byte[] bArr, Version version) {
        Version.ECBlocks eCBlocks = version.getECBlocks();
        Version.ECB[] eCBlocks2 = eCBlocks.getECBlocks();
        int i3 = 0;
        for (Version.ECB count : eCBlocks2) {
            i3 += count.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[i3];
        int i4 = 0;
        for (Version.ECB ecb : eCBlocks2) {
            int i5 = 0;
            while (i5 < ecb.getCount()) {
                int dataCodewords = ecb.getDataCodewords();
                dataBlockArr[i4] = new DataBlock(dataCodewords, new byte[(eCBlocks.getECCodewords() + dataCodewords)]);
                i5++;
                i4++;
            }
        }
        int length = dataBlockArr[0].codewords.length - eCBlocks.getECCodewords();
        int i6 = length - 1;
        int i7 = 0;
        for (int i8 = 0; i8 < i6; i8++) {
            int i9 = 0;
            while (i9 < i4) {
                dataBlockArr[i9].codewords[i8] = bArr[i7];
                i9++;
                i7++;
            }
        }
        boolean z2 = version.getVersionNumber() == 24;
        int i10 = z2 ? 8 : i4;
        int i11 = 0;
        while (i11 < i10) {
            dataBlockArr[i11].codewords[i6] = bArr[i7];
            i11++;
            i7++;
        }
        int length2 = dataBlockArr[0].codewords.length;
        while (length < length2) {
            int i12 = 0;
            while (i12 < i4) {
                int i13 = z2 ? (i12 + 8) % i4 : i12;
                dataBlockArr[i13].codewords[(!z2 || i13 <= 7) ? length : length - 1] = bArr[i7];
                i12++;
                i7++;
            }
            length++;
        }
        if (i7 == bArr.length) {
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    public byte[] getCodewords() {
        return this.codewords;
    }

    public int getNumDataCodewords() {
        return this.numDataCodewords;
    }
}
