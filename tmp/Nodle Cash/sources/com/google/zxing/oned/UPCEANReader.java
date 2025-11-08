package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {
    static final int[] END_PATTERN = {1, 1, 1, 1, 1, 1};
    static final int[][] L_AND_G_PATTERNS;
    static final int[][] L_PATTERNS;
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] MIDDLE_PATTERN = {1, 1, 1, 1, 1};
    static final int[] START_END_PATTERN = {1, 1, 1};
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    static {
        int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        L_PATTERNS = iArr;
        int[][] iArr2 = new int[20][];
        L_AND_G_PATTERNS = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i3 = 10; i3 < 20; i3++) {
            int[] iArr3 = L_PATTERNS[i3 - 10];
            int[] iArr4 = new int[iArr3.length];
            for (int i4 = 0; i4 < iArr3.length; i4++) {
                iArr4[i4] = iArr3[(iArr3.length - i4) - 1];
            }
            L_AND_G_PATTERNS[i3] = iArr4;
        }
    }

    public static boolean checkStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i3 = length - 1;
        return getStandardUPCEANChecksum(charSequence.subSequence(0, i3)) == Character.digit(charSequence.charAt(i3), 10);
    }

    public static int decodeDigit(BitArray bitArray, int[] iArr, int i3, int[][] iArr2) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i3, iArr);
        int length = iArr2.length;
        float f2 = MAX_AVG_VARIANCE;
        int i4 = -1;
        for (int i5 = 0; i5 < length; i5++) {
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, iArr2[i5], 0.7f);
            if (patternMatchVariance < f2) {
                i4 = i5;
                f2 = patternMatchVariance;
            }
        }
        if (i4 >= 0) {
            return i4;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static int[] findGuardPattern(BitArray bitArray, int i3, boolean z2, int[] iArr) throws NotFoundException {
        return findGuardPattern(bitArray, i3, z2, iArr, new int[iArr.length]);
    }

    public static int[] findStartGuardPattern(BitArray bitArray) throws NotFoundException {
        int[] iArr = new int[START_END_PATTERN.length];
        int[] iArr2 = null;
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            int[] iArr3 = START_END_PATTERN;
            Arrays.fill(iArr, 0, iArr3.length, 0);
            iArr2 = findGuardPattern(bitArray, i3, false, iArr3, iArr);
            int i4 = iArr2[0];
            int i5 = iArr2[1];
            int i6 = i4 - (i5 - i4);
            if (i6 >= 0) {
                z2 = bitArray.isRange(i6, i4, false);
            }
            i3 = i5;
        }
        return iArr2;
    }

    public static int getStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        int i3 = 0;
        for (int i4 = length - 1; i4 >= 0; i4 -= 2) {
            int charAt = charSequence.charAt(i4) - '0';
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i3 += charAt;
        }
        int i5 = i3 * 3;
        for (int i6 = length - 2; i6 >= 0; i6 -= 2) {
            int charAt2 = charSequence.charAt(i6) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw FormatException.getFormatInstance();
            }
            i5 += charAt2;
        }
        return (1000 - i5) % 10;
    }

    public boolean checkChecksum(String str) throws FormatException {
        return checkStandardUPCEANChecksum(str);
    }

    public int[] decodeEnd(BitArray bitArray, int i3) throws NotFoundException {
        return findGuardPattern(bitArray, i3, false, START_END_PATTERN);
    }

    public abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException;

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return decodeRow(i3, bitArray, findStartGuardPattern(bitArray), map);
    }

    public abstract BarcodeFormat getBarcodeFormat();

    private static int[] findGuardPattern(BitArray bitArray, int i3, boolean z2, int[] iArr, int[] iArr2) throws NotFoundException {
        int size = bitArray.getSize();
        int nextUnset = z2 ? bitArray.getNextUnset(i3) : bitArray.getNextSet(i3);
        int length = iArr.length;
        boolean z3 = z2;
        int i4 = 0;
        int i5 = nextUnset;
        while (nextUnset < size) {
            if (bitArray.get(nextUnset) != z3) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (OneDReader.patternMatchVariance(iArr2, iArr, 0.7f) < MAX_AVG_VARIANCE) {
                    return new int[]{i5, nextUnset};
                } else {
                    i5 += iArr2[0] + iArr2[1];
                    int i6 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i4] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z3 = !z3;
            }
            nextUnset++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decodeRow(int i3, BitArray bitArray, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPointCallback resultPointCallback;
        int i4;
        String lookupCountryIdentifier;
        int[] iArr2 = null;
        if (map == null) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        int i5 = 0;
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i3));
        }
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, iArr, sb);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((float) decodeMiddle, (float) i3));
        }
        int[] decodeEnd = decodeEnd(bitArray, decodeMiddle);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(((float) (decodeEnd[0] + decodeEnd[1])) / 2.0f, (float) i3));
        }
        int i6 = decodeEnd[1];
        int i7 = (i6 - decodeEnd[0]) + i6;
        if (i7 >= bitArray.getSize() || !bitArray.isRange(i6, i7, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw FormatException.getFormatInstance();
        } else if (checkChecksum(sb2)) {
            BarcodeFormat barcodeFormat = getBarcodeFormat();
            float f2 = (float) i3;
            Result result = new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (iArr[1] + iArr[0])) / 2.0f, f2), new ResultPoint(((float) (decodeEnd[1] + decodeEnd[0])) / 2.0f, f2)}, barcodeFormat);
            try {
                Result decodeRow = this.extensionReader.decodeRow(i3, bitArray, decodeEnd[1]);
                result.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, decodeRow.getText());
                result.putAllMetadata(decodeRow.getResultMetadata());
                result.addResultPoints(decodeRow.getResultPoints());
                i4 = decodeRow.getText().length();
            } catch (ReaderException unused) {
                i4 = 0;
            }
            if (map != null) {
                iArr2 = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            if (iArr2 != null) {
                int length = iArr2.length;
                int i8 = 0;
                while (i8 < length) {
                    if (i4 != iArr2[i8]) {
                        i8++;
                    }
                }
                throw NotFoundException.getNotFoundInstance();
            }
            if ((barcodeFormat == BarcodeFormat.EAN_13 || barcodeFormat == BarcodeFormat.UPC_A) && (lookupCountryIdentifier = this.eanManSupport.lookupCountryIdentifier(sb2)) != null) {
                result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, lookupCountryIdentifier);
            }
            if (barcodeFormat == BarcodeFormat.EAN_8) {
                i5 = 4;
            }
            result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]E" + i5);
            return result;
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
