package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

public class QRCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (topLeftOnBit == null || bottomRightOnBit == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float moduleSize = moduleSize(topLeftOnBit, bitMatrix);
        int i3 = topLeftOnBit[1];
        int i4 = bottomRightOnBit[1];
        int i5 = topLeftOnBit[0];
        int i6 = bottomRightOnBit[0];
        if (i5 >= i6 || i3 >= i4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i7 = i4 - i3;
        if (i7 == i6 - i5 || (i6 = i5 + i7) < bitMatrix.getWidth()) {
            int round = Math.round(((float) ((i6 - i5) + 1)) / moduleSize);
            int round2 = Math.round(((float) (i7 + 1)) / moduleSize);
            if (round <= 0 || round2 <= 0) {
                throw NotFoundException.getNotFoundInstance();
            } else if (round2 == round) {
                int i8 = (int) (moduleSize / 2.0f);
                int i9 = i3 + i8;
                int i10 = i5 + i8;
                int i11 = (((int) (((float) (round - 1)) * moduleSize)) + i10) - i6;
                if (i11 > 0) {
                    if (i11 <= i8) {
                        i10 -= i11;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                int i12 = (((int) (((float) (round2 - 1)) * moduleSize)) + i9) - i4;
                if (i12 > 0) {
                    if (i12 <= i8) {
                        i9 -= i12;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                BitMatrix bitMatrix2 = new BitMatrix(round, round2);
                for (int i13 = 0; i13 < round2; i13++) {
                    int i14 = ((int) (((float) i13) * moduleSize)) + i9;
                    for (int i15 = 0; i15 < round; i15++) {
                        if (bitMatrix.get(((int) (((float) i15) * moduleSize)) + i10, i14)) {
                            bitMatrix2.set(i15, i13);
                        }
                    }
                }
                return bitMatrix2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static float moduleSize(int[] iArr, BitMatrix bitMatrix) throws NotFoundException {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        int i3 = iArr[0];
        boolean z2 = true;
        int i4 = iArr[1];
        int i5 = 0;
        while (i3 < width && i4 < height) {
            if (z2 != bitMatrix.get(i3, i4)) {
                i5++;
                if (i5 == 5) {
                    break;
                }
                z2 = !z2;
            }
            i3++;
            i4++;
        }
        if (i3 != width && i4 != height) {
            return ((float) (i3 - iArr[0])) / 7.0f;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public final Decoder getDecoder() {
        return this.decoder;
    }

    public void reset() {
    }

    public final Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] resultPointArr;
        DecoderResult decoderResult;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect(map);
            decoderResult = this.decoder.decode(detect.getBits(), map);
            resultPointArr = detect.getPoints();
        } else {
            decoderResult = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), map);
            resultPointArr = NO_POINTS;
        }
        if (decoderResult.getOther() instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) decoderResult.getOther()).applyMirroredCorrection(resultPointArr);
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.QR_CODE);
        List<byte[]> byteSegments = decoderResult.getByteSegments();
        if (byteSegments != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        String eCLevel = decoderResult.getECLevel();
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        if (decoderResult.hasStructuredAppend()) {
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decoderResult.getStructuredAppendSequenceNumber()));
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decoderResult.getStructuredAppendParity()));
        }
        result.putMetadata(ResultMetadataType.ERRORS_CORRECTED, decoderResult.getErrorsCorrected());
        ResultMetadataType resultMetadataType = ResultMetadataType.SYMBOLOGY_IDENTIFIER;
        result.putMetadata(resultMetadataType, "]Q" + decoderResult.getSymbologyModifier());
        return result;
    }
}
