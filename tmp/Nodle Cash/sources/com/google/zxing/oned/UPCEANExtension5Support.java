package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    private int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i3 = iArr[1];
        int i4 = 0;
        for (int i5 = 0; i5 < 5 && i3 < size; i5++) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i3, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (int i6 : iArr2) {
                i3 += i6;
            }
            if (decodeDigit >= 10) {
                i4 |= 1 << (4 - i5);
            }
            if (i5 != 4) {
                i3 = bitArray.getNextUnset(bitArray.getNextSet(i3));
            }
        }
        if (sb.length() == 5) {
            if (extensionChecksum(sb.toString()) == determineCheckDigit(i4)) {
                return i3;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int determineCheckDigit(int i3) throws NotFoundException {
        for (int i4 = 0; i4 < 10; i4++) {
            if (i3 == CHECK_DIGIT_ENCODINGS[i4]) {
                return i4;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(CharSequence charSequence) {
        int length = charSequence.length();
        int i3 = 0;
        for (int i4 = length - 2; i4 >= 0; i4 -= 2) {
            i3 += charSequence.charAt(i4) - '0';
        }
        int i5 = i3 * 3;
        for (int i6 = length - 1; i6 >= 0; i6 -= 2) {
            i5 += charSequence.charAt(i6) - '0';
        }
        return (i5 * 3) % 10;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r5.equals("90000") == false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String parseExtension5String(java.lang.String r5) {
        /*
            r0 = 1
            r1 = 0
            char r2 = r5.charAt(r1)
            r3 = 48
            if (r2 == r3) goto L_0x004d
            r3 = 53
            if (r2 == r3) goto L_0x004a
            r3 = 57
            java.lang.String r4 = ""
            if (r2 == r3) goto L_0x0015
            goto L_0x004f
        L_0x0015:
            r2 = -1
            int r3 = r5.hashCode()
            switch(r3) {
                case 54118329: goto L_0x0035;
                case 54395376: goto L_0x002a;
                case 54395377: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            r1 = r2
            goto L_0x003e
        L_0x001f:
            java.lang.String r1 = "99991"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0028
            goto L_0x001d
        L_0x0028:
            r1 = 2
            goto L_0x003e
        L_0x002a:
            java.lang.String r1 = "99990"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0033
            goto L_0x001d
        L_0x0033:
            r1 = r0
            goto L_0x003e
        L_0x0035:
            java.lang.String r3 = "90000"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x003e
            goto L_0x001d
        L_0x003e:
            switch(r1) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0045;
                case 2: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x004f
        L_0x0042:
            java.lang.String r5 = "0.00"
            return r5
        L_0x0045:
            java.lang.String r5 = "Used"
            return r5
        L_0x0048:
            r5 = 0
            return r5
        L_0x004a:
            java.lang.String r4 = "$"
            goto L_0x004f
        L_0x004d:
            java.lang.String r4 = "£"
        L_0x004f:
            java.lang.String r5 = r5.substring(r0)
            int r5 = java.lang.Integer.parseInt(r5)
            int r0 = r5 / 100
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r5 = r5 % 100
            r1 = 10
            if (r5 >= r1) goto L_0x006a
            java.lang.String r1 = "0"
            java.lang.String r5 = A.a.k(r1, r5)
            goto L_0x006e
        L_0x006a:
            java.lang.String r5 = java.lang.String.valueOf(r5)
        L_0x006e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            r1.append(r0)
            r0 = 46
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtension5Support.parseExtension5String(java.lang.String):java.lang.String");
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        String parseExtension5String;
        if (str.length() != 5 || (parseExtension5String = parseExtension5String(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, parseExtension5String);
        return enumMap;
    }

    public Result decodeRow(int i3, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, iArr, sb);
        String sb2 = sb.toString();
        Map<ResultMetadataType, Object> parseExtensionString = parseExtensionString(sb2);
        float f2 = (float) i3;
        Result result = new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, f2), new ResultPoint((float) decodeMiddle, f2)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }
}
