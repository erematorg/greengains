package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import java.util.Map;

public final class AztecReader implements Reader {
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public void reset() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0070 A[LOOP:0: B:35:0x006e->B:36:0x0070, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r10, java.util.Map<com.google.zxing.DecodeHintType, ?> r11) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r9 = this;
            com.google.zxing.aztec.detector.Detector r9 = new com.google.zxing.aztec.detector.Detector
            com.google.zxing.common.BitMatrix r10 = r10.getBlackMatrix()
            r9.<init>(r10)
            r10 = 0
            r0 = 0
            com.google.zxing.aztec.AztecDetectorResult r1 = r9.detect(r0)     // Catch:{ NotFoundException -> 0x0033, FormatException -> 0x0030 }
            com.google.zxing.ResultPoint[] r2 = r1.getPoints()     // Catch:{ NotFoundException -> 0x0033, FormatException -> 0x0030 }
            int r3 = r1.getErrorsCorrected()     // Catch:{ NotFoundException -> 0x002d, FormatException -> 0x002a }
            com.google.zxing.aztec.decoder.Decoder r4 = new com.google.zxing.aztec.decoder.Decoder     // Catch:{ NotFoundException -> 0x0028, FormatException -> 0x0026 }
            r4.<init>()     // Catch:{ NotFoundException -> 0x0028, FormatException -> 0x0026 }
            com.google.zxing.common.DecoderResult r1 = r4.decode(r1)     // Catch:{ NotFoundException -> 0x0028, FormatException -> 0x0026 }
            r4 = r3
            r3 = r2
            r2 = r10
            r10 = r1
            r1 = r2
            goto L_0x003e
        L_0x0026:
            r1 = move-exception
            goto L_0x0036
        L_0x0028:
            r1 = move-exception
            goto L_0x003b
        L_0x002a:
            r1 = move-exception
        L_0x002b:
            r3 = r0
            goto L_0x0036
        L_0x002d:
            r1 = move-exception
        L_0x002e:
            r3 = r0
            goto L_0x003b
        L_0x0030:
            r1 = move-exception
            r2 = r10
            goto L_0x002b
        L_0x0033:
            r1 = move-exception
            r2 = r10
            goto L_0x002e
        L_0x0036:
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r10
            goto L_0x003e
        L_0x003b:
            r4 = r3
            r3 = r2
            r2 = r10
        L_0x003e:
            if (r10 != 0) goto L_0x0056
            r10 = 1
            com.google.zxing.aztec.AztecDetectorResult r9 = r9.detect(r10)     // Catch:{ FormatException | NotFoundException -> 0x0059 }
            com.google.zxing.ResultPoint[] r3 = r9.getPoints()     // Catch:{ FormatException | NotFoundException -> 0x0059 }
            int r4 = r9.getErrorsCorrected()     // Catch:{ FormatException | NotFoundException -> 0x0059 }
            com.google.zxing.aztec.decoder.Decoder r10 = new com.google.zxing.aztec.decoder.Decoder     // Catch:{ FormatException | NotFoundException -> 0x0059 }
            r10.<init>()     // Catch:{ FormatException | NotFoundException -> 0x0059 }
            com.google.zxing.common.DecoderResult r10 = r10.decode(r9)     // Catch:{ FormatException | NotFoundException -> 0x0059 }
        L_0x0056:
            r5 = r3
            r9 = r4
            goto L_0x0061
        L_0x0059:
            r9 = move-exception
            if (r1 != 0) goto L_0x0060
            if (r2 == 0) goto L_0x005f
            throw r2
        L_0x005f:
            throw r9
        L_0x0060:
            throw r1
        L_0x0061:
            if (r11 == 0) goto L_0x0078
            com.google.zxing.DecodeHintType r1 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            java.lang.Object r11 = r11.get(r1)
            com.google.zxing.ResultPointCallback r11 = (com.google.zxing.ResultPointCallback) r11
            if (r11 == 0) goto L_0x0078
            int r1 = r5.length
        L_0x006e:
            if (r0 >= r1) goto L_0x0078
            r2 = r5[r0]
            r11.foundPossibleResultPoint(r2)
            int r0 = r0 + 1
            goto L_0x006e
        L_0x0078:
            com.google.zxing.Result r11 = new com.google.zxing.Result
            java.lang.String r2 = r10.getText()
            byte[] r3 = r10.getRawBytes()
            int r4 = r10.getNumBits()
            com.google.zxing.BarcodeFormat r6 = com.google.zxing.BarcodeFormat.AZTEC
            long r7 = java.lang.System.currentTimeMillis()
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            java.util.List r0 = r10.getByteSegments()
            if (r0 == 0) goto L_0x009b
            com.google.zxing.ResultMetadataType r1 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS
            r11.putMetadata(r1, r0)
        L_0x009b:
            java.lang.String r0 = r10.getECLevel()
            if (r0 == 0) goto L_0x00a6
            com.google.zxing.ResultMetadataType r1 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r11.putMetadata(r1, r0)
        L_0x00a6:
            java.lang.Integer r0 = r10.getErrorsCorrected()
            int r0 = r0.intValue()
            int r0 = r0 + r9
            com.google.zxing.ResultMetadataType r9 = com.google.zxing.ResultMetadataType.ERRORS_CORRECTED
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r11.putMetadata(r9, r0)
            com.google.zxing.ResultMetadataType r9 = com.google.zxing.ResultMetadataType.SYMBOLOGY_IDENTIFIER
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "]z"
            r0.<init>(r1)
            int r10 = r10.getSymbologyModifier()
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r11.putMetadata(r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }
}
