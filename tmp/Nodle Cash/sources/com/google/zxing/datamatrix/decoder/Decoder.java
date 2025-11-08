package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);

    private int correctErrors(byte[] bArr, int i3) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            iArr[i4] = bArr[i4] & 255;
        }
        try {
            int decodeWithECCount = this.rsDecoder.decodeWithECCount(iArr, bArr.length - i3);
            for (int i5 = 0; i5 < i3; i5++) {
                bArr[i5] = (byte) iArr[i5];
            }
            return decodeWithECCount;
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public DecoderResult decode(boolean[][] zArr) throws FormatException, ChecksumException {
        return decode(BitMatrix.parse(zArr));
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws FormatException, ChecksumException {
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        DataBlock[] dataBlocks = DataBlock.getDataBlocks(bitMatrixParser.readCodewords(), bitMatrixParser.getVersion());
        int i3 = 0;
        for (DataBlock numDataCodewords : dataBlocks) {
            i3 += numDataCodewords.getNumDataCodewords();
        }
        byte[] bArr = new byte[i3];
        int length = dataBlocks.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            DataBlock dataBlock = dataBlocks[i5];
            byte[] codewords = dataBlock.getCodewords();
            int numDataCodewords2 = dataBlock.getNumDataCodewords();
            i4 += correctErrors(codewords, numDataCodewords2);
            for (int i6 = 0; i6 < numDataCodewords2; i6++) {
                bArr[(i6 * length) + i5] = codewords[i6];
            }
        }
        DecoderResult decode = DecodedBitStreamParser.decode(bArr);
        decode.setErrorsCorrected(Integer.valueOf(i4));
        return decode;
    }
}
