package com.google.zxing.oned;

import com.adjust.sdk.Constants;
import com.google.common.base.Ascii;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;
import jnr.ffi.provider.jffi.JNINativeInterface;
import jnr.posix.FileStat;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.objectweb.asm.signature.SignatureVisitor;

public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    static final int ASTERISK_ENCODING = 148;
    static final int[] CHARACTER_ENCODINGS = {52, 289, 97, 352, 49, 304, 112, 37, FileStat.ALL_READ, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, Constants.MINIMAL_ERROR_STATUS_CODE, JNINativeInterface.SetByteArrayRegion, 133, 388, 196, 168, 162, 138, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        char c3;
        int i3;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i4 = 0;
        while (i4 < length) {
            char charAt = charSequence.charAt(i4);
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                i4++;
                char charAt2 = charSequence.charAt(i4);
                if (charAt != '$') {
                    if (charAt != '%') {
                        if (charAt != '+') {
                            if (charAt == '/') {
                                if (charAt2 >= 'A' && charAt2 <= 'O') {
                                    i3 = charAt2 - ' ';
                                } else if (charAt2 == 'Z') {
                                    c3 = AbstractJsonLexerKt.COLON;
                                    sb.append(c3);
                                } else {
                                    throw FormatException.getFormatInstance();
                                }
                            }
                        } else if (charAt2 < 'A' || charAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        } else {
                            i3 = charAt2 + ' ';
                        }
                    } else if (charAt2 >= 'A' && charAt2 <= 'E') {
                        i3 = charAt2 - '&';
                    } else if (charAt2 >= 'F' && charAt2 <= 'J') {
                        i3 = charAt2 - 11;
                    } else if (charAt2 >= 'K' && charAt2 <= 'O') {
                        i3 = charAt2 + 16;
                    } else if (charAt2 >= 'P' && charAt2 <= 'T') {
                        i3 = charAt2 + SignatureVisitor.EXTENDS;
                    } else if (charAt2 != 'U') {
                        if (charAt2 == 'V') {
                            c3 = '@';
                        } else if (charAt2 == 'W') {
                            c3 = '`';
                        } else if (charAt2 == 'X' || charAt2 == 'Y' || charAt2 == 'Z') {
                            c3 = Ascii.MAX;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        sb.append(c3);
                    }
                    c3 = 0;
                    sb.append(c3);
                } else if (charAt2 < 'A' || charAt2 > 'Z') {
                    throw FormatException.getFormatInstance();
                } else {
                    i3 = charAt2 - '@';
                }
                c3 = (char) i3;
                sb.append(c3);
            } else {
                sb.append(charAt);
            }
            i4++;
        }
        return sb.toString();
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] iArr) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int length = iArr.length;
        boolean z2 = false;
        int i3 = 0;
        int i4 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z2) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (toNarrowWidePattern(iArr) == 148 && bitArray.isRange(Math.max(0, i4 - ((nextSet - i4) / 2)), i4, false)) {
                    return new int[]{i4, nextSet};
                } else {
                    i4 += iArr[0] + iArr[1];
                    int i5 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i3] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                z2 = !z2;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i3) throws NotFoundException {
        int i4 = 0;
        while (true) {
            int[] iArr = CHARACTER_ENCODINGS;
            if (i4 < iArr.length) {
                if (iArr[i4] == i3) {
                    return ALPHABET_STRING.charAt(i4);
                }
                i4++;
            } else if (i3 == 148) {
                return '*';
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int length = iArr.length;
        int i3 = 0;
        while (true) {
            int i4 = Integer.MAX_VALUE;
            for (int i5 : iArr) {
                if (i5 < i4 && i5 > i3) {
                    i4 = i5;
                }
            }
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            for (int i9 = 0; i9 < length; i9++) {
                int i10 = iArr[i9];
                if (i10 > i4) {
                    i7 |= 1 << ((length - 1) - i9);
                    i6++;
                    i8 += i10;
                }
            }
            if (i6 == 3) {
                for (int i11 = 0; i11 < length && i6 > 0; i11++) {
                    int i12 = iArr[i11];
                    if (i12 > i4) {
                        i6--;
                        if (i12 * 2 >= i8) {
                            return -1;
                        }
                    }
                }
                return i7;
            } else if (i6 <= 3) {
                return -1;
            } else {
                i3 = i4;
            }
        }
    }

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        int[] findAsteriskPattern = findAsteriskPattern(bitArray, iArr);
        int nextSet = bitArray.getNextSet(findAsteriskPattern[1]);
        int size = bitArray.getSize();
        while (true) {
            OneDReader.recordPattern(bitArray, nextSet, iArr);
            int narrowWidePattern = toNarrowWidePattern(iArr);
            if (narrowWidePattern >= 0) {
                char patternToChar = patternToChar(narrowWidePattern);
                sb.append(patternToChar);
                int i4 = nextSet;
                for (int i5 : iArr) {
                    i4 += i5;
                }
                int nextSet2 = bitArray.getNextSet(i4);
                if (patternToChar == '*') {
                    sb.setLength(sb.length() - 1);
                    int i6 = 0;
                    for (int i7 : iArr) {
                        i6 += i7;
                    }
                    int i8 = (nextSet2 - nextSet) - i6;
                    if (nextSet2 == size || i8 * 2 >= i6) {
                        if (this.usingCheckDigit) {
                            int length = sb.length() - 1;
                            int i9 = 0;
                            for (int i10 = 0; i10 < length; i10++) {
                                i9 += ALPHABET_STRING.indexOf(this.decodeRowResult.charAt(i10));
                            }
                            if (sb.charAt(length) == ALPHABET_STRING.charAt(i9 % 43)) {
                                sb.setLength(length);
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        if (sb.length() != 0) {
                            float f2 = (float) i3;
                            Result result = new Result(this.extendedMode ? decodeExtended(sb) : sb.toString(), (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (findAsteriskPattern[1] + findAsteriskPattern[0])) / 2.0f, f2), new ResultPoint((((float) i6) / 2.0f) + ((float) nextSet), f2)}, BarcodeFormat.CODE_39);
                            result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]A0");
                            return result;
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                nextSet = nextSet2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public Code39Reader(boolean z2) {
        this(z2, false);
    }

    public Code39Reader(boolean z2, boolean z3) {
        this.usingCheckDigit = z2;
        this.extendedMode = z3;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }
}
