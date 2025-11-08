package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i3, byte[] bArr) {
        this.numDataCodewords = i3;
        this.codewords = bArr;
    }

    public static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length == version.getTotalCodewords()) {
            Version.ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
            Version.ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
            int i3 = 0;
            for (Version.ECB count : eCBlocks) {
                i3 += count.getCount();
            }
            DataBlock[] dataBlockArr = new DataBlock[i3];
            int i4 = 0;
            for (Version.ECB ecb : eCBlocks) {
                int i5 = 0;
                while (i5 < ecb.getCount()) {
                    int dataCodewords = ecb.getDataCodewords();
                    dataBlockArr[i4] = new DataBlock(dataCodewords, new byte[(eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords)]);
                    i5++;
                    i4++;
                }
            }
            int length = dataBlockArr[0].codewords.length;
            int i6 = i3 - 1;
            while (i6 >= 0 && dataBlockArr[i6].codewords.length != length) {
                i6--;
            }
            int i7 = i6 + 1;
            int eCCodewordsPerBlock = length - eCBlocksForLevel.getECCodewordsPerBlock();
            int i8 = 0;
            for (int i9 = 0; i9 < eCCodewordsPerBlock; i9++) {
                int i10 = 0;
                while (i10 < i4) {
                    dataBlockArr[i10].codewords[i9] = bArr[i8];
                    i10++;
                    i8++;
                }
            }
            int i11 = i7;
            while (i11 < i4) {
                dataBlockArr[i11].codewords[eCCodewordsPerBlock] = bArr[i8];
                i11++;
                i8++;
            }
            int length2 = dataBlockArr[0].codewords.length;
            while (eCCodewordsPerBlock < length2) {
                int i12 = 0;
                while (i12 < i4) {
                    dataBlockArr[i12].codewords[i12 < i7 ? eCCodewordsPerBlock : eCCodewordsPerBlock + 1] = bArr[i8];
                    i12++;
                    i8++;
                }
                eCCodewordsPerBlock++;
            }
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
